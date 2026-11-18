# ColombiaStyle — Store & Sales Management System (JavaFX)

Desktop application developed in Java and JavaFX (Maven) to manage product catalogs, inventory control, shopping cart operations, and sales billing for a Colombian clothing and accessories store.

## Key Features
- **Role-Based Access:** Separate workflows for **Customers** (*Ingresar como cliente*) to browse items and manage their shopping cart, and **Store Owners** (*Ingresar como dueño*) to manage inventory and product pricing.
- **Object-Oriented Architecture:** Modular design separating domain models (`Producto.java`, `ItemCarrito.java`) from application and UI logic (`ColombiaStyle.java`).
- **Input Validation & Stability:** Form validation and `try-catch` exception handling to prevent runtime errors during transactions.

## Application Preview

### Role Selection Screen
![Main Screen](./inicio_colombiastyle.png)

### Customer View (Catalog & Shopping Cart)
![Customer View](./vista_cliente.png)

### Owner View (Inventory Management)
![Owner View](./vista_dueno.png)

## Technologies
- Java (JDK) & JavaFX
- Apache Maven (`pom.xml`)
