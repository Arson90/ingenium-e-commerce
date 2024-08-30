# Ingenium-e-commerce

## Overview: 
The E-Commerce Platform is a private project designed to offer an online shopping experience. It allows users to register, log in using JWT authentication, manage products in a shopping cart, and complete purchases. Additionally, users receive an email containing a PDF document with the details of their order. The project features an Angular-based frontend and a MySQL database for data management.

## Key Features:

1. **User Authentication:**
   
   1.1. **Account Registration:** Users can create new accounts by providing necessary details such as email, password, and personal information.
   
   1.2. **Login:** Secure login functionality using JSON Web Tokens (JWT) for session management and authentication.
   
   1.3. **JWT Authentication:** Provides a secure method for verifying user identity and maintaining user sessions.

3. **Product Management:**
   
   2.1. **Product Catalog:** Users can browse products, each with details such as name, description, price, and availability.
   
   2.2. **Search and Filters:** Functionality to search for products and apply filters based on name.

5. **Shopping Cart:**
   
   3.1. **Add to Cart:** Users can add products to their shopping cart, which keeps track of selected items and their quantities.
   
   3.2. **Cart Management:** Users can view, update, or remove items from their cart as needed.

7. **Checkout Process:**
   
   4.1. **Order Review:** Users can review their cart before proceeding to checkout.
   
   4.2. **Payment:** Users can enter payment details and complete their purchase.
   
   4.3. **Order Confirmation:** After successful payment, users receive an order confirmation.

9. **Order Confirmation:**
    
   5.1. **PDF Confirmation:** A confirmation of the order is generated in PDF format.
   
   5.2. **Email Delivery:** The PDF confirmation is sent to the user's registered email address.

11. **User Interface:**
    
   6.1. **Angular Frontend:** A responsive and user-friendly interface built using Angular, allowing for smooth navigation and interaction.
    
   6.2. **Interactive Design:** Modern design elements and intuitive navigation to enhance user experience.

13. **Backend and Database:**
    
   7.1. **MySQL Database:** A relational database management system used to store user information, product details, order history, and other relevant data.
    
   7.2. **Server-Side Logic:** Business logic and API endpoints implemented to handle user requests, process orders, and interact with the database.

## Technology stack: 
* Java 11
* JUnit5
* Spring Boot/Data/Security
* Angular
* NgRx
* JSON Web Token
* CQRS
* MySQL
* Maven
* Liquibase
