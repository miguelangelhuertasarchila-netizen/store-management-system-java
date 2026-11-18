# ColombiaStyle — Store & Sales Management System (Java)

Modular console-based application developed in Java (Maven) to manage product catalogs, shopping cart operations, and sales billing for a Colombian retail store.

## Project Architecture
- **`ColombiaStyle.java`:** Main application entry point, interactive console menus, and user flow control.
- **`Producto.java`:** Data model representing store items, pricing, and inventory attributes.
- **`ItemCarrito.java`:** Shopping cart line-item logic handling product quantities and subtotal calculations.

## Key Features & Concepts Applied
- **Object-Oriented Programming (OOP):** Encapsulation with private attributes, constructors, getters/setters, and modular separation between domain models and main execution logic.
- **Cart & Billing Logic:** Dynamic item addition, quantity tracking, and automated purchase total calculation.
- **Input Validation & Stability:** Console input checks and `try-catch` exception handling to prevent runtime errors.

## Technologies
- Java (JDK) & Apache Maven (`pom.xml`)
- Apache NetBeans / Visual Studio Code
