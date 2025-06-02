# 🌾 Kisaan Bazaar 🚜

Welcome to **Kisaan Bazaar** — a comprehensive **Java-based agricultural marketplace** that connects **farmers** and **buyers** directly to promote fair trade, transparency, and convenience.  
This platform bridges the gap between producers and consumers, helping farmers get better prices and buyers access fresh produce easily. 🥦🍅

---

## 🌟 Key Features

### 👥 User Management  
- **Multiple Roles:** Farmers, Buyers, and Admins with role-based access control.  
- **Registration & Authentication:** Secure login and sign-up process.  
- **Admin Account:** Auto-generated admin for easy system oversight.

### 🥕 Product Management  
- **Add Products:** Farmers can upload new products with detailed attributes like name, category, retail price, wholesale price, minimum wholesale quantity, target price, and quantity available.  
- **Product Browsing:** Buyers can browse/filter products by category, price range, and sort options.

### 🛒 Order Management  
- **Order Placement:** Buyers place orders based on product availability.  
- **Pricing Calculations:** Orders factor in retail/wholesale prices, commission fees, platform fees, and shipping fees for accurate billing.  
- **Stock Validation:** Prevents orders exceeding current stock.  
- **Order History:** Users can view their past orders with status updates.

### 💬 Price Negotiation System  
- **Flexible Offers:** Buyers can propose alternate prices and quantities different from listed values.  
- **Farmer Response:** Farmers can accept, reject, or counter-negotiations.  
- **Status Tracking:** Negotiation statuses (Pending, Accepted, Rejected) are tracked and updated.

### 🔔 Notifications & Alerts  
- **Real-Time Updates:** Users receive notifications for new orders, price proposals, negotiation responses, stock alerts, and market price fluctuations.  
- **Read Status:** Notifications can be marked as read/unread to help users track new activity.  
- **Notification Management:** Both buyers and farmers have dedicated notification panels.

### ⭐ Ratings & Reviews  
- **Trust Building:** Buyers and farmers rate each other after transactions.  
- **Feedback System:** Includes star ratings and textual comments to foster transparency.

### 🖥️ User-Friendly GUI  
- Developed with **Java Swing**, featuring:  
  - Login and registration screens  
  - Dashboards customized per role  
  - Product catalog with filtering and sorting  
  - Order placement and tracking  
  - Negotiation interfaces  
  - Notifications and messaging windows

---

## 🛠️ Technologies & Tools

- **Language:** Java SE (Swing for UI)  
- **Database:** MySQL (relational database)  
- **Data Access:** JDBC for connecting Java app with MySQL  
- **Logging:** `java.util.logging` for systematic event logging  
- **Unique IDs:** UUIDs generated for users, products, orders, negotiations, and notifications  
- **Validation:** Input validation utilities for emails, numbers, and dates  

---


