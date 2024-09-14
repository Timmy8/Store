# Online Store

This project is an online store platform designed for buying and selling goods. It includes essential features for user-friendly interactions, such as integrated messaging for notifications, user authentication, and seamless browsing of products.

# Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
  - [Fill the application.properties file:](#fill-the-applicationproperties-file)
  - [Clone the repository:](#clone-the-repository)
  - [Running with Maven:](#running-with-maven)
  - [Running with Docker:](#running-with-docker)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Contributions](#contributions)
- [Contact](#contact)

## Features

- **Product Listing**: Users can browse a list of available products and get detailed information on each item.
- **User Authentication**: Secure login and registration system using Spring Security.
- **Messaging System**: Integrated Telegram API to send notifications to users about their orders or updates.
- **Order Management**: Users can add products to their cart, place orders, and receive real-time updates on their purchase.
- **Admin Panel**: Admin users can manage products, view orders, and track the status of the store in real-time.
- **Search and Filtering**: Users can search for products by category, name, or price range.

## Technologies

- **Java 17**
- **Spring Framework**: Spring Boot, Spring MVC, Spring Data, Spring Security
- **Database**: MySQL with Hibernate ORM
- **Front-end**: HTML, CSS, JavaScript
- **Testing**: JUnit, Mockito
- **APIs**: Telegram API for user notifications
- **Others**: Maven, Docker, Lombok, Swagger

## Prerequisites

To run this project, you'll need:

- Java 17+
- Maven
- Docker (optional, for containerization)
- MySQL database
- Telegram Bot Token (for messaging feature)

## Installation

### Fill the application.properties file
Set up the database:
Create a MySQL database and configure the connection in the application.properties file located in src/main/resources/.

>spring.datasource.url=jdbc:mysql://localhost:3306/your_database
>
>spring.datasource.username=your_username
>
>spring.datasource.password=your_password

   
To add your own admin user, change Admin Data in the application.properties file located in src/main/resources/.

> security.admin.login='your login'
> 
> security.admin.password='your password'
> 
> security.admin.email='your email'

If you want to add test data to the site, change starter value in the application.properties file located in src/main/resources/.

>security.starter.data.setup=true

To add your own telegram bot, change Telegram Properties in the application.properties file located in src/main/resources/.
> telegram.bot.token='your telegram bot token'
>
> telegram.bot.name='your telegram bot name'

If you want to see all debug processes, remove the # sign in debug section in the application.properties file located in src/main/resources/.

> logging.level.web=debug

### Clone the repository:

```bash
git clone https://github.com/Timmy8/Store.git
```

### Running with Maven
1. **Build** the project:
```bash
mvn clean install
```

2. **Run** the application:
```bash
mvn spring-boot:run
```

3. **Access** the application by navigating to http://localhost:8080 in your browser.

### Running with Docker
Alternatively, you can run the project using Docker.

1. **Uncomment** docker compose dependency in pom.xml file.
2.  **Run** the container.

```bash
docker-compose up --build
```
3. **Access** the application by navigating to http://localhost:8080 in your browser.

## API Documentation
The project includes API documentation generated with Swagger. After starting the application, you can view the API docs at:

```bash
http://localhost:8080/swagger-ui.html
```

## Testing
The project uses JUnit and Mockito for unit testing. To run the tests, execute:

```bash
mvn test
```

## Contributions
Contributions are welcome! If you have suggestions or find bugs, please feel free to open an issue or create a pull request.

## Contact
If you have any questions or feedback, feel free to contact me via:

- Email: tima.sniezhkov1@gmail.com
- LinkedIn: [Tsimafei Sniazhkou](https://www.linkedin.com/in/tsimafei-sniazhkou/)
