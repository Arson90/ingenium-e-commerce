# Ingenium-e-commerce

## Overview: 
The E-Commerce Platform is a private project designed to offer an online shopping experience. It allows users to register, log in using JWT authentication, manage products in a shopping cart, and complete purchases. Additionally, users receive an email containing a PDF document with the details of their order. The project features an Angular-based frontend and a MySQL database for data management.

## Key Features:

1. **User Authentication:**
   
   * **Account Registration:** Users can create new accounts by providing necessary details such as email, password, and personal information.
   
   * **Login:** Secure login functionality using JSON Web Tokens (JWT) for session management and authentication.
   
   * **JWT Authentication:** Provides a secure method for verifying user identity and maintaining user sessions.

2. **Product Management:**
   
   * **Product Catalog:** Users can browse products, each with details such as name, description, price, and availability.
   
   * **Search and Filters:** Functionality to search for products and apply filters based on name.

3. **Shopping Cart:**
   
   * **Add to Cart:** Users can add products to their shopping cart, which keeps track of selected items and their quantities.
   
   * **Cart Management:** Users can view, update, or remove items from their cart as needed.

4. **Checkout Process:**
   
   * **Order Review:** Users can review their cart before proceeding to checkout.
   
   * **Payment:** Users can enter payment details and complete their purchase.
   
   * **Order Confirmation:** After successful payment, users receive an order confirmation.

5. **Order Confirmation:**
    
   * **PDF Confirmation:** A confirmation of the order is generated in PDF format.
   
   * **Email Delivery:** The PDF confirmation is sent to the user's registered email address.

6. **User Interface:**
    
   * **Angular Frontend:** A responsive and user-friendly interface built using Angular, allowing for smooth navigation and interaction.
    
   * **Interactive Design:** Modern design elements and intuitive navigation to enhance user experience.

7. **Backend and Database:**
    
   * **MySQL Database:** A relational database management system used to store user information, product details, order history, and other relevant data.
    
   * **Server-Side Logic:** Business logic and API endpoints implemented to handle user requests, process orders, and interact with the database.

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
