package com.example.myresale.controllers;

import com.example.myresale.DTOs.DeliveryAddressCreateDTO;
import com.example.myresale.entities.UserInfo;
import com.example.myresale.services.DeliveryAddressService;
import com.example.myresale.services.ItemService;
import com.example.myresale.services.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/purchase")
public class ItemPurchaseController {
    private final ItemService itemService;
    private final PurchaseOrderService purchaseOrderService;

    public ItemPurchaseController(ItemService itemService, PurchaseOrderService purchaseOrderService) {
        this.itemService = itemService;
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/{id}")
    public String itemPurchaseForm(@PathVariable("id") Long itemId,
                                   Model model)
    {
        model.addAttribute("itemId", itemId);

        return "form_item_purchase.html";
    }

    @PostMapping("/{id:\\d+}")
    public String purchaseItem(@PathVariable("id") Long itemId,
                               @ModelAttribute("deliveryAddress") @Valid DeliveryAddressCreateDTO dto,
                               Errors errors, Authentication authentication, Model model) {

        if (errors.hasErrors()) {
            List<String> errorsList = new ArrayList<>();

            errors.getAllErrors().forEach(error -> errorsList.add(error.getDefaultMessage()));
            model.addAttribute("errors", errorsList);

            return itemPurchaseForm(itemId, model);
        } else {
            UserInfo user = null;
            if (authentication != null) {
                user = (UserInfo) authentication.getPrincipal();
            }

            var item = itemService.findItemById(itemId);
            var orderId = purchaseOrderService.saveOrder(Set.of(item), dto, user);

            if (orderId.isPresent())
                model.addAttribute("purchaseNumber", orderId.get());
            else throw new RuntimeException("Can't create order, try again!");

            return "successful_purchase.html";
        }
    }

    @GetMapping("/allCartPurchase")
    public String purchaseAllFromUserCart(Model model){

        model.addAttribute("itemId", "allCartPurchase");

        return "form_item_purchase.html";
    }

    @PostMapping("/allCartPurchase")
    public ResponseEntity<String> purchaseAllCart(@ModelAttribute("deliveryAddress") DeliveryAddressCreateDTO dto,
                                                  Authentication authentication)
    {
        var user = (UserInfo) authentication.getPrincipal();

        var orderId = purchaseOrderService.saveOrder(user.getUserCart().getAllItemsFromCart(), dto, user);

        return orderId.map(id -> ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("Thank you for your purchase\nPurchase number - " + id)).orElseGet(() -> ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Sorry, item is unavailable"));
    }
}
