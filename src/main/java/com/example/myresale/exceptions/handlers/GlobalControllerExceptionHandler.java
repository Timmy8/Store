package com.example.myresale.exceptions.handlers;

import com.example.myresale.exceptions.ItemNotFoundException;
import com.example.myresale.exceptions.UserExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    Logger logger = Logger.getLogger(GlobalControllerExceptionHandler.class.getName());

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> itemNotFoundExceptionHandler(ItemNotFoundException exception) {
        logger.info(exception.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Can't find item with id: " + exception.getId());
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<String> HttpBodyConversionExceptionHandler(HttpMessageConversionException ex) {
        logger.info(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(ex.getMessage() + "with cause: " + ex.getCause());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> NumberFormatException(NumberFormatException ex) {
        logger.info(ex.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Page not found!");
    }

    @ExceptionHandler(UserExistsException.class)
    public ModelAndView UserExistsException(UserExistsException ex, HttpServletRequest request){
        logger.info(ex.getMessage());

        ModelAndView modelAndView = new ModelAndView(request.getRequestURI());

        modelAndView.addObject("errors", ex.getMessage());

        return modelAndView;
    }
}
