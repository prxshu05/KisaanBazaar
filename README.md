# 🌾 Kisaan Bazaar 🚜

Welcome to **Kisaan Bazaar** — your one-stop **Java-based agricultural marketplace** that brings **farmers** and **buyers** together in a seamless, trusted ecosystem! 🌱🤝

---

## ✨ Features

- 👥 **User Management**  
  Register and login as **Farmer**, **Buyer**, or **Admin**.  
  Admin account auto-created for easy management.

- 🥕 **Product Management**  
  Farmers list fresh produce with detailed info — category, prices (retail & wholesale), quantity, and target price.

- 🛒 **Order Management**  
  Buyers place orders with dynamic pricing including commissions & fees, with real-time stock checks.

- 💬 **Price Negotiation**  
  Flexible negotiation system where buyers propose prices & quantities, farmers can accept or reject.

- 🔔 **Notifications**  
  Real-time alerts for new orders, negotiation updates, low stock warnings, and market price changes.

- ⭐ **Ratings & Reviews**  
  Build trust with ratings and comments between buyers and farmers.

- 🖥️ **User-Friendly GUI**  
  Interactive Swing-based dashboards for Buyers and Farmers to manage products, orders, and negotiations.

---

## 🛠️ Technologies Used

- Java SE with **Swing** for GUI  
- MySQL as the relational database backend  
- **JDBC** for database interaction  
- Logging with `java.util.logging`  
- Unique IDs with **UUID**

---

## 🗄️ Database Setup

- Database: `kisaan_bazaar`  
- Core tables: Users, Products, Orders, Negotiations, Notifications, Ratings, Revenue, PlatformFees.

> **Note:** Update the database credentials in the `DBConnection` class before running:

```java
private static final String URL = "jdbc:mysql://localhost:3306/kisaan_bazaar?useSSL=false&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "comp@209";
