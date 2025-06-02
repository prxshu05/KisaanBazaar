import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;

// Model Classes
class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String address;

    public User(String name, String email, String password, String role, String address) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public String getAddress() { return address; }
}

class Product {
    private String id;
    private String farmerId;
    private String name;
    private String category;
    private double retailPrice;
    private double wholesalePrice;
    private int minWholesaleQty;
    private double targetPrice;
    private int quantity;

    public Product(String farmerId, String name, String category, double retailPrice, double wholesalePrice, int minWholesaleQty, double targetPrice, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.farmerId = farmerId;
        this.name = name;
        this.category = category;
        this.retailPrice = retailPrice;
        this.wholesalePrice = wholesalePrice;
        this.minWholesaleQty = minWholesaleQty;
        this.targetPrice = targetPrice;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getFarmerId() { return farmerId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getRetailPrice() { return retailPrice; }
    public double getWholesalePrice() { return wholesalePrice; }
    public int getMinWholesaleQty() { return minWholesaleQty; }
    public double getTargetPrice() { return targetPrice; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}

class Order {
    private String id;
    private String buyerId;
    private String productId;
    private int quantity;
    private double totalPrice;
    private String status;
    private String createdAt;

    public Order(String buyerId, String productId, int quantity, double totalPrice) {
        this.id = UUID.randomUUID().toString();
        this.buyerId = buyerId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = "Placed";
        this.createdAt = new Timestamp(System.currentTimeMillis()).toString();
    }

    public String getId() { return id; }
    public String getBuyerId() { return buyerId; }
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public void setStatus(String status) { this.status = status; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}

class Notification {
    private String id;
    private String userId;
    private String message;
    private String timestamp;
    private boolean isRead;

    public Notification(String userId, String message) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.isRead = false;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public String getMessage() { return message; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { this.isRead = read; }
}

class Negotiation {
    private String id;
    private String orderId;
    private String buyerId;
    private String farmerId;
    private double proposedPrice;
    private int proposedQuantity;
    private String status;

    public Negotiation(String orderId, String buyerId, String farmerId, double proposedPrice, int proposedQuantity) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.farmerId = farmerId;
        this.proposedPrice = proposedPrice;
        this.proposedQuantity = proposedQuantity;
        this.status = "Pending";
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public String getBuyerId() { return buyerId; }
    public String getFarmerId() { return farmerId; }
    public double getProposedPrice() { return proposedPrice; }
    public int getProposedQuantity() { return proposedQuantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

class Rating {
    private String id;
    private String raterId;
    private String ratedId;
    private int rating;
    private String comment;

    public Rating(String raterId, String ratedId, int rating, String comment) {
        this.id = UUID.randomUUID().toString();
        this.raterId = raterId;
        this.ratedId = ratedId;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() { return id; }
    public String getRaterId() { return raterId; }
    public String getRatedId() { return ratedId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}

// DTO for Product Data
class ProductDTO {
    private String id;
    private String farmerId;
    private String name;
    private String category;
    private double retailPrice;
    private double wholesalePrice;
    private int minWholesaleQty;
    private double targetPrice;
    private int quantity;

    public ProductDTO(String id, String farmerId, String name, String category, double retailPrice, double wholesalePrice, int minWholesaleQty, double targetPrice, int quantity) {
        this.id = id;
        this.farmerId = farmerId;
        this.name = name;
        this.category = category;
        this.retailPrice = retailPrice;
        this.wholesalePrice = wholesalePrice;
        this.minWholesaleQty = minWholesaleQty;
        this.targetPrice = targetPrice;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getFarmerId() { return farmerId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getRetailPrice() { return retailPrice; }
    public double getWholesalePrice() { return wholesalePrice; }
    public int getMinWholesaleQty() { return minWholesaleQty; }
    public double getTargetPrice() { return targetPrice; }
    public int getQuantity() { return quantity; }

    public Object[] toRow() {
        return new Object[]{id, farmerId, name, category, retailPrice, wholesalePrice, minWholesaleQty, targetPrice, quantity};
    }
}

// DTO for Order Data
class OrderDTO {
    private String id;
    private String buyerId;
    private String productId;
    private String productName;
    private int quantity;
    private double totalPrice;
    private String status;
    private String createdAt;
    private String buyerAddress;

    public OrderDTO(String id, String buyerId, String productId, String productName, int quantity, double totalPrice, String status, String createdAt, String buyerAddress) {
        this.id = id;
        this.buyerId = buyerId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.buyerAddress = buyerAddress;
    }

    public String getId() { return id; }
    public String getBuyerId() { return buyerId; }
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public String getCreatedAt() { return createdAt; }
    public String getBuyerAddress() { return buyerAddress; }

    public Object[] toRow() {
        return new Object[]{id, buyerId, productId, productName, quantity, totalPrice, status, createdAt, buyerAddress};
    }
}

// DTO for Negotiation Data
class NegotiationDTO {
    private String id;
    private String orderId;
    private String buyerId;
    private String farmerId;
    private double proposedPrice;
    private int proposedQuantity;
    private String status;

    public NegotiationDTO(String id, String orderId, String buyerId, String farmerId, double proposedPrice, int proposedQuantity, String status) {
        this.id = id;
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.farmerId = farmerId;
        this.proposedPrice = proposedPrice;
        this.proposedQuantity = proposedQuantity;
        this.status = status;
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public String getBuyerId() { return buyerId; }
    public String getFarmerId() { return farmerId; }
    public double getProposedPrice() { return proposedPrice; }
    public int getProposedQuantity() { return proposedQuantity; }
    public String getStatus() { return status; }

    public Object[] toRow() {
        return new Object[]{id, orderId, buyerId, farmerId, proposedPrice, proposedQuantity, status};
    }
}

// DTO for Platform Fees
class PlatformFeeDTO {
    private String id;
    private String buyerId;
    private double fee;
    private String timestamp;

    public PlatformFeeDTO(String id, String buyerId, double fee, String timestamp) {
        this.id = id;
        this.buyerId = buyerId;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public String getBuyerId() { return buyerId; }
    public double getFee() { return fee; }
    public String getTimestamp() { return timestamp; }

    public Object[] toRow() {
        return new Object[]{id, buyerId, fee, timestamp};
    }
}

// DTO for User Data
class UserDTO {
    private String id;
    private String name;
    private String email;
    private String role;
    private String address;

    public UserDTO(String id, String name, String email, String role, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.address = address;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getAddress() { return address; }

    public Object[] toRow() {
        return new Object[]{id, name, email, role, address};
    }
}

// Utility Classes
class ValidationUtils {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isPositiveNumber(String input) {
        try {
            double value = Double.parseDouble(input);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidDate(String input) {
        return input != null && input.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}

// Simulated Payment Gateway with Platform Fees and Shipping
class PaymentGateway {
    private static final double PLATFORM_FEE = 20.0; // 20 rupees platform fee
    private static final double SHIPPING_FEE = 100.0; // 100 rupees shipping fee
    private static final Logger LOGGER = Logger.getLogger(PaymentGateway.class.getName());

    public static boolean processPayment(String buyerId, double amount) throws SQLException {
        double totalAmount = amount + SHIPPING_FEE;
        int result = JOptionPane.showConfirmDialog(null, 
            "Process payment of ₹" + String.format("%.2f", amount) + " + ₹20 platform fee + ₹100 shipping fee = ₹" + String.format("%.2f", totalAmount) + " for Buyer ID: " + buyerId + "?\n" +
            "Payment Method: Credit Card (Simulated)", 
            "Payment Confirmation", 
            JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            // Save platform fee to the database
            savePlatformFee(buyerId, PLATFORM_FEE);
            return true;
        }
        return false;
    }

    private static void savePlatformFee(String buyerId, double fee) throws SQLException {
        String sql = "INSERT INTO PlatformFees (id, buyer_id, fee, timestamp) VALUES (?, ?, ?, ?)";
        LOGGER.info("Executing query to save platform fee: " + sql + " for buyerId: " + buyerId);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, buyerId);
            stmt.setDouble(3, fee);
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Platform fee saved for buyer: " + buyerId + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error saving platform fee: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for savePlatformFee"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for savePlatformFee"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public List<PlatformFeeDTO> getPlatformFees() throws SQLException {
        List<PlatformFeeDTO> fees = new ArrayList<>();
        String sql = "SELECT id, buyer_id, fee, timestamp FROM PlatformFees ORDER BY timestamp DESC";
        LOGGER.info("Executing query to get platform fees: " + sql);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                PlatformFeeDTO fee = new PlatformFeeDTO(
                    rs.getString("id"),
                    rs.getString("buyer_id"),
                    rs.getDouble("fee"),
                    rs.getTimestamp("timestamp").toString()
                );
                fees.add(fee);
            }
            LOGGER.info("Retrieved " + fees.size() + " platform fee records.");
            return fees;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving platform fees: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getPlatformFees"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getPlatformFees"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getPlatformFees"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

// Database Connection
class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/kisaan_bazaar?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "comp@209";
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());

    public static Connection getConnection() throws SQLException {
        LOGGER.info("Establishing database connection to URL: " + URL);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            LOGGER.info("Database connection established successfully.");
            return conn;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Failed to connect to the database: " + ex.getMessage(), ex);
            throw ex;
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "MySQL JDBC Driver not found: " + ex.getMessage(), ex);
            throw new SQLException("MySQL JDBC Driver not found", ex);
        }
    }
}

// DAO Classes
class UserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private static final String ADMIN_EMAIL = "admin@kisaanbazaar.com";
    private static final String ADMIN_PASSWORD = "admin@123";

    public void ensureAdminAccount() throws SQLException {
        LOGGER.info("Ensuring admin account exists for email: " + ADMIN_EMAIL);
        if (!doesEmailExist(ADMIN_EMAIL)) {
            User admin = new User("Admin", ADMIN_EMAIL, ADMIN_PASSWORD, "Admin", "Admin Address");
            saveUser(admin);
            LOGGER.info("Static admin account created: " + ADMIN_EMAIL);
        } else {
            LOGGER.info("Admin account already exists: " + ADMIN_EMAIL);
        }
    }

    public boolean doesEmailExist(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Users WHERE email = ?";
        LOGGER.info("Executing query to check if email exists: " + sql + " with email: " + email);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                LOGGER.info("Email check result for " + email + ": count = " + count);
                return count > 0;
            }
            return false;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error checking email existence: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for doesEmailExist"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for doesEmailExist"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for doesEmailExist"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (id, name, email, password, role, address) VALUES (?, ?, ?, ?, ?, ?)";
        LOGGER.info("Executing query to save user: " + sql + " for email: " + user.getEmail());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole());
            stmt.setString(6, user.getAddress());
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("User saved: " + user.getEmail() + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error saving user: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for saveUser"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for saveUser"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public User getUserByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ?";
        LOGGER.info("Executing query to get user: " + sql + " with email: " + email);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (password.equals(storedPassword)) {
                    LOGGER.info("User logged in: " + email);
                    User user = new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        storedPassword,
                        rs.getString("role"),
                        rs.getString("address")
                    );
                    user.setId(rs.getString("id"));
                    return user;
                }
            }
            LOGGER.warning("Failed login attempt for: " + email);
            return null;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving user: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getUserByEmailAndPassword"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getUserByEmailAndPassword"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getUserByEmailAndPassword"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public void resetPassword(String email) throws SQLException {
        String sql = "UPDATE Users SET password = ? WHERE email = ?";
        LOGGER.info("Executing query to reset password: " + sql + " for email: " + email);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "reset123");
            stmt.setString(2, email);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                LOGGER.info("Password reset for user: " + email + ", rows updated: " + rowsUpdated);
            } else {
                LOGGER.warning("No user found with email: " + email);
                throw new SQLException("No user found with email: " + email);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error resetting password: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for resetPassword"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for resetPassword"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public List<UserDTO> getAllUsers() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        String sql = "SELECT id, name, email, role, address FROM Users ORDER BY role, name";
        LOGGER.info("Executing query to get all users: " + sql);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getString("address")
                );
                users.add(user);
            }
            LOGGER.info("Retrieved " + users.size() + " users.");
            return users;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving users: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getAllUsers"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getAllUsers"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getAllUsers"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    // New method to check if a userId exists in the Users table
    public boolean doesUserIdExist(String userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Users WHERE id = ?";
        LOGGER.info("Executing query to check if userId exists: " + sql + " with userId: " + userId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                LOGGER.info("UserId check result for " + userId + ": count = " + count);
                return count > 0;
            }
            return false;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error checking userId existence: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for doesUserIdExist"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for doesUserIdExist"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for doesUserIdExist"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

class ProductDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    public static final String[] PRODUCT_COLUMNS = {"ID", "Farmer ID", "Name", "Category", "Retail Price (₹)", "Wholesale Price (₹)", "Min Wholesale Qty", "Target Price (₹)", "Quantity"};

    public void saveProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Products (id, farmer_id, name, category, retail_price, wholesale_price, min_wholesale_qty, target_price, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        LOGGER.info("Executing query to save product: " + sql + " for product name: " + product.getName());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getId());
            stmt.setString(2, product.getFarmerId());
            stmt.setString(3, product.getName());
            stmt.setString(4, product.getCategory());
            stmt.setDouble(5, product.getRetailPrice());
            stmt.setDouble(6, product.getWholesalePrice());
            stmt.setInt(7, product.getMinWholesaleQty());
            stmt.setDouble(8, product.getTargetPrice());
            stmt.setInt(9, product.getQuantity());
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Product added: " + product.getName() + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error saving product: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for saveProduct"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for saveProduct"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public List<ProductDTO> getProducts(String category, double minPrice, double maxPrice, String sortBy) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE category LIKE ? AND retail_price BETWEEN ? AND ? ORDER BY " + sortBy;
        LOGGER.info("Executing query to get products: " + sql + " with category: " + category + ", minPrice: " + minPrice + ", maxPrice: " + maxPrice);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, category.isEmpty() ? "%" : category);
            stmt.setDouble(2, minPrice);
            stmt.setDouble(3, maxPrice);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO(
                    rs.getString("id"),
                    rs.getString("farmer_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("retail_price"),
                    rs.getDouble("wholesale_price"),
                    rs.getInt("min_wholesale_qty"),
                    rs.getDouble("target_price"),
                    rs.getInt("quantity")
                );
                products.add(product);
            }
            LOGGER.info("Retrieved " + products.size() + " products.");
            return products;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving products: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getProducts"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getProducts"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getProducts"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public List<ProductDTO> getProductsByFarmer(String farmerId) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE farmer_id = ?";
        LOGGER.info("Executing query to get products by farmer: " + sql + " with farmerId: " + farmerId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, farmerId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO(
                    rs.getString("id"),
                    rs.getString("farmer_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("retail_price"),
                    rs.getDouble("wholesale_price"),
                    rs.getInt("min_wholesale_qty"),
                    rs.getDouble("target_price"),
                    rs.getInt("quantity")
                );
                products.add(product);
            }
            LOGGER.info("Retrieved " + products.size() + " products for farmer: " + farmerId);
            return products;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving products by farmer: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getProductsByFarmer"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getProductsByFarmer"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getProductsByFarmer"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public void updateQuantity(String productId, int quantity) throws SQLException {
        String sql = "UPDATE Products SET quantity = ? WHERE id = ?";
        LOGGER.info("Executing query to update quantity: " + sql + " for productId: " + productId + ", new quantity: " + quantity);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantity);
            stmt.setString(2, productId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Updated quantity for product: " + productId + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating product quantity: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for updateQuantity"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for updateQuantity"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public int getProductCount(String farmerId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM Products WHERE farmer_id = ?";
        LOGGER.info("Executing query to get product count: " + sql + " for farmerId: " + farmerId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, farmerId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                LOGGER.info("Product count for farmer " + farmerId + ": " + count);
                return count;
            }
            return 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving product count: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getProductCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getProductCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getProductCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public ProductDTO getProductById(String productId) throws SQLException {
        String sql = "SELECT * FROM Products WHERE id = ?";
        LOGGER.info("Executing query to get product by ID: " + sql + " with productId: " + productId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                ProductDTO product = new ProductDTO(
                    rs.getString("id"),
                    rs.getString("farmer_id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("retail_price"),
                    rs.getDouble("wholesale_price"),
                    rs.getInt("min_wholesale_qty"),
                    rs.getDouble("target_price"),
                    rs.getInt("quantity")
                );
                LOGGER.info("Product retrieved by ID: " + productId);
                return product;
            } else {
                throw new SQLException("Product not found!");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving product by ID: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getProductById"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getProductById"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getProductById"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

class MarketPriceDAO {
    private static final Logger LOGGER = Logger.getLogger(MarketPriceDAO.class.getName());

    public double getMarketPrice(String productName) throws SQLException {
        String sql = "SELECT price_per_kg FROM MarketPrices WHERE LOWER(product_name) = LOWER(?) LIMIT 1";
        LOGGER.info("Executing query to get market price: " + sql + " for product: " + productName);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, productName);
            rs = stmt.executeQuery();
            if (rs.next()) {
                double price = rs.getDouble("price_per_kg");
                LOGGER.info("Market price for " + productName + ": ₹" + price);
                return price;
            }
            LOGGER.warning("No market price found for product: " + productName);
            return 0.0; // Default if not found
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving market price: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getMarketPrice"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getMarketPrice"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getMarketPrice"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

class OrderDAO {
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    public static final String[] ORDER_COLUMNS = {"ID", "Buyer ID", "Product ID", "Product Name", "Quantity", "Total Price (₹)", "Status", "Created At", "Buyer Address"};

    public void saveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO Orders (id, buyer_id, product_id, quantity, total_price, status, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        LOGGER.info("Executing query to save order: " + sql + " for order ID: " + order.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, order.getId());
            stmt.setString(2, order.getBuyerId());
            stmt.setString(3, order.getProductId());
            stmt.setInt(4, order.getQuantity());
            stmt.setDouble(5, order.getTotalPrice());
            stmt.setString(6, order.getStatus());
            stmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Order placed: " + order.getId() + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error saving order: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for saveOrder"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for saveOrder"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public void deleteOrder(String orderId) throws SQLException {
        String sql = "DELETE FROM Orders WHERE id = ?";
        LOGGER.info("Executing query to delete order: " + sql + " for orderId: " + orderId);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Order deleted: " + orderId + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting order: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for deleteOrder"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for deleteOrder"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public void updateStatus(String orderId, String status) throws SQLException {
        String sql = "UPDATE Orders SET status = ? WHERE id = ?";
        LOGGER.info("Executing query to update order status: " + sql + " for orderId: " + orderId + ", new status: " + status);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setString(2, orderId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Order status updated: " + orderId + " to " + status + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating order status: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for updateStatus"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for updateStatus"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public List<OrderDTO> getOrdersByUser(String userId, String role) throws SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        String sql;
        if (role.equals("Farmer")) {
            sql = "SELECT o.id, o.buyer_id, o.product_id, p.name AS product_name, o.quantity, o.total_price, o.status, o.created_at, u.address AS buyer_address " +
                  "FROM Orders o JOIN Products p ON o.product_id = p.id JOIN Users u ON o.buyer_id = u.id WHERE p.farmer_id = ?";
        } else if (role.equals("Buyer")) {
            sql = "SELECT o.id, o.buyer_id, o.product_id, p.name AS product_name, o.quantity, o.total_price, o.status, o.created_at, u.address AS buyer_address " +
                  "FROM Orders o JOIN Products p ON o.product_id = p.id JOIN Users u ON o.buyer_id = u.id WHERE o.buyer_id = ?";
        } else {
            sql = "SELECT o.id, o.buyer_id, o.product_id, p.name AS product_name, o.quantity, o.total_price, o.status, o.created_at, u.address AS buyer_address " +
                  "FROM Orders o JOIN Products p ON o.product_id = p.id JOIN Users u ON o.buyer_id = u.id";
        }
        LOGGER.info("Executing query to get orders by user: " + sql + " for userId: " + userId + ", role: " + role);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            if (!role.equals("Admin")) stmt.setString(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO(
                    rs.getString("id"),
                    rs.getString("buyer_id"),
                    rs.getString("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("total_price"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at").toString(),
                    rs.getString("buyer_address")
                );
                orders.add(order);
            }
            LOGGER.info("Retrieved " + orders.size() + " orders for user: " + userId + ", role: " + role);
            return orders;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving orders by user: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getOrdersByUser"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getOrdersByUser"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getOrdersByUser"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public List<OrderDTO> getOrdersWithFilters(String status, String startDate, String endDate) throws SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT o.id, o.buyer_id, o.product_id, p.name AS product_name, o.quantity, o.total_price, o.status, o.created_at, u.address AS buyer_address " +
                     "FROM Orders o JOIN Products p ON o.product_id = p.id JOIN Users u ON o.buyer_id = u.id WHERE 1=1";
        List<String> params = new ArrayList<>();
        if (!status.isEmpty()) {
            sql += " AND o.status = ?";
            params.add(status);
        }
        if (ValidationUtils.isValidDate(startDate) && ValidationUtils.isValidDate(endDate)) {
            sql += " AND o.created_at BETWEEN ? AND ?";
            params.add(startDate);
            params.add(endDate);
        }
        LOGGER.info("Executing query to get orders with filters: " + sql + " with status: " + status);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }
            rs = stmt.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO(
                    rs.getString("id"),
                    rs.getString("buyer_id"),
                    rs.getString("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("total_price"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at").toString(),
                    rs.getString("buyer_address")
                );
                orders.add(order);
            }
            LOGGER.info("Retrieved " + orders.size() + " orders with filters.");
            return orders;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving orders with filters: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getOrdersWithFilters"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getOrdersWithFilters"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getOrdersWithFilters"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public int getPendingOrderCount(String farmerId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM Orders o JOIN Products p ON o.product_id = p.id WHERE p.farmer_id = ? AND o.status != 'Delivered'";
        LOGGER.info("Executing query to get pending order count: " + sql + " for farmerId: " + farmerId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, farmerId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                LOGGER.info("Pending order count for farmer " + farmerId + ": " + count);
                return count;
            }
            return 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving pending order count: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getPendingOrderCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getPendingOrderCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getPendingOrderCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public int getOrderCount(String buyerId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM Orders WHERE buyer_id = ?";
        LOGGER.info("Executing query to get order count: " + sql + " for buyerId: " + buyerId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, buyerId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                LOGGER.info("Order count for buyer " + buyerId + ": " + count);
                return count;
            }
            return 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving order count: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getOrderCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getOrderCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getOrderCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

class NotificationDAO {
    private static final Logger LOGGER = Logger.getLogger(NotificationDAO.class.getName());

    public void saveNotification(Notification notification) throws SQLException {
        String sql = "INSERT INTO Notifications (id, user_id, message, timestamp, is_read) VALUES (?, ?, ?, ?, ?)";
        LOGGER.info("Executing query to save notification: " + sql + " for userId: " + notification.getUserId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, notification.getId());
            stmt.setString(2, notification.getUserId());
            stmt.setString(3, notification.getMessage());
            stmt.setTimestamp(4, Timestamp.valueOf(notification.getTimestamp()));
            stmt.setBoolean(5, notification.isRead());
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Notification saved for user: " + notification.getUserId() + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error saving notification: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for saveNotification"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for saveNotification"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public List<Notification> getNotificationsByUser(String userId) throws SQLException {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications WHERE user_id = ? ORDER BY timestamp DESC";
        LOGGER.info("Executing query to get notifications: " + sql + " for userId: " + userId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification(rs.getString("user_id"), rs.getString("message"));
                notification.setId(rs.getString("id"));
                notification.setTimestamp(rs.getTimestamp("timestamp").toString());
                notification.setRead(rs.getBoolean("is_read"));
                notifications.add(notification);
            }
            LOGGER.info("Retrieved " + notifications.size() + " notifications for user: " + userId);
            return notifications;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving notifications: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getNotificationsByUser"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getNotificationsByUser"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getNotificationsByUser"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public void markAsRead(String notificationId) throws SQLException {
        String sql = "UPDATE Notifications SET is_read = true WHERE id = ?";
        LOGGER.info("Executing query to mark notification as read: " + sql + " for notificationId: " + notificationId);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, notificationId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Notification marked as read: " + notificationId + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error marking notification as read: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for markAsRead"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for markAsRead"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public int getUnreadNotificationCount(String userId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM Notifications WHERE user_id = ? AND is_read = false";
        LOGGER.info("Executing query to get unread notification count: " + sql + " for userId: " + userId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                LOGGER.info("Unread notification count for user " + userId + ": " + count);
                return count;
            }
            return 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving unread notification count: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getUnreadNotificationCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getUnreadNotificationCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getUnreadNotificationCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

class NegotiationDAO {
    private static final Logger LOGGER = Logger.getLogger(NegotiationDAO.class.getName());
    public static final String[] NEGOTIATION_COLUMNS = {"ID", "Order ID", "Buyer ID", "Farmer ID", "Proposed Price (₹)", "Proposed Quantity", "Status"};

    public void saveNegotiation(Negotiation negotiation) throws SQLException {
        String sql = "INSERT INTO Negotiations (id, order_id, buyer_id, farmer_id, proposed_price, proposed_quantity, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        LOGGER.info("Executing query to save negotiation: " + sql + " for negotiation ID: " + negotiation.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, negotiation.getId());
            stmt.setString(2, negotiation.getOrderId());
            stmt.setString(3, negotiation.getBuyerId());
            stmt.setString(4, negotiation.getFarmerId());
            stmt.setDouble(5, negotiation.getProposedPrice());
            stmt.setInt(6, negotiation.getProposedQuantity());
            stmt.setString(7, negotiation.getStatus());
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Negotiation saved: " + negotiation.getId() + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error saving negotiation: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for saveNegotiation"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for saveNegotiation"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public List<NegotiationDTO> getNegotiationsByFarmer(String farmerId) throws SQLException {
        List<NegotiationDTO> negotiations = new ArrayList<>();
        String sql = "SELECT * FROM Negotiations WHERE farmer_id = ?";
        LOGGER.info("Executing query to get negotiations by farmer: " + sql + " with farmerId: " + farmerId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, farmerId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                NegotiationDTO negotiation = new NegotiationDTO(
                    rs.getString("id"),
                    rs.getString("order_id"),
                    rs.getString("buyer_id"),
                    rs.getString("farmer_id"),
                    rs.getDouble("proposed_price"),
                    rs.getInt("proposed_quantity"),
                    rs.getString("status")
                );
                negotiations.add(negotiation);
            }
            LOGGER.info("Retrieved " + negotiations.size() + " negotiations for farmer: " + farmerId);
            return negotiations;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving negotiations by farmer: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getNegotiationsByFarmer"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getNegotiationsByFarmer"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getNegotiationsByFarmer"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public void updateNegotiationStatus(String negotiationId, String status) throws SQLException {
        String sql = "UPDATE Negotiations SET status = ? WHERE id = ?";
        LOGGER.info("Executing query to update negotiation status: " + sql + " for negotiationId: " + negotiationId + ", new status: " + status);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setString(2, negotiationId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Negotiation status updated: " + negotiationId + " to " + status + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error updating negotiation status: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for updateNegotiationStatus"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for updateNegotiationStatus"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public int getPendingNegotiationCount(String farmerId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM Negotiations WHERE farmer_id = ? AND status = 'Pending'";
        LOGGER.info("Executing query to get pending negotiation count: " + sql + " for farmerId: " + farmerId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, farmerId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                LOGGER.info("Pending negotiation count for farmer " + farmerId + ": " + count);
                return count;
            }
            return 0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving pending negotiation count: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getPendingNegotiationCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getPendingNegotiationCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getPendingNegotiationCount"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public Negotiation getNegotiationById(String negotiationId) throws SQLException {
        String sql = "SELECT * FROM Negotiations WHERE id = ?";
        LOGGER.info("Executing query to get negotiation by ID: " + sql + " with negotiationId: " + negotiationId);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, negotiationId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Negotiation negotiation = new Negotiation(
                    rs.getString("order_id"),
                    rs.getString("buyer_id"),
                    rs.getString("farmer_id"),
                    rs.getDouble("proposed_price"),
                    rs.getInt("proposed_quantity")
                );
                negotiation.setStatus(rs.getString("status"));
                LOGGER.info("Negotiation retrieved by ID: " + negotiationId);
                return negotiation;
            } else {
                throw new SQLException("Negotiation not found!");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving negotiation by ID: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getNegotiationById"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getNegotiationById"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getNegotiationById"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public void deleteNegotiation(String negotiationId) throws SQLException {
        String sql = "DELETE FROM Negotiations WHERE id = ?";
        LOGGER.info("Executing query to delete negotiation: " + sql + " for negotiationId: " + negotiationId);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, negotiationId);
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Negotiation deleted: " + negotiationId + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error deleting negotiation: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for deleteNegotiation"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for deleteNegotiation"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

class RatingDAO {
    private static final Logger LOGGER = Logger.getLogger(RatingDAO.class.getName());

    public void saveRating(Rating rating) throws SQLException {
        String sql = "INSERT INTO Ratings (id, rater_id, rated_id, rating, comment) VALUES (?, ?, ?, ?, ?)";
        LOGGER.info("Executing query to save rating: " + sql + " for rating ID: " + rating.getId());
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, rating.getId());
            stmt.setString(2, rating.getRaterId());
            stmt.setString(3, rating.getRatedId());
            stmt.setInt(4, rating.getRating());
            stmt.setString(5, rating.getComment());
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Rating saved: " + rating.getId() + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error saving rating: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for saveRating"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for saveRating"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

class RevenueDAO {
    private static final Logger LOGGER = Logger.getLogger(RevenueDAO.class.getName());

    public void saveRevenue(String orderId, double fee) throws SQLException {
        String sql = "INSERT INTO Revenue (id, order_id, fee, timestamp) VALUES (?, ?, ?, ?)";
        LOGGER.info("Executing query to save revenue: " + sql + " for orderId: " + orderId);
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, orderId);
            stmt.setDouble(3, fee);
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            int rowsAffected = stmt.executeUpdate();
            LOGGER.info("Revenue saved for order: " + orderId + ", rows affected: " + rowsAffected);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error saving revenue: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for saveRevenue"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for saveRevenue"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }

    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(fee) as total FROM Revenue";
        LOGGER.info("Executing query to get total revenue: " + sql);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                double total = rs.getDouble("total");
                LOGGER.info("Total revenue retrieved: " + total);
                return total;
            }
            return 0.0;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error retrieving total revenue: " + ex.getMessage(), ex);
            throw ex;
        } finally {
            if (rs != null) try { rs.close(); LOGGER.info("ResultSet closed for getTotalRevenue"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing ResultSet: " + e.getMessage(), e); }
            if (stmt != null) try { stmt.close(); LOGGER.info("Statement closed for getTotalRevenue"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Statement: " + e.getMessage(), e); }
            if (conn != null) try { conn.close(); LOGGER.info("Connection closed for getTotalRevenue"); } catch (SQLException e) { LOGGER.log(Level.WARNING, "Error closing Connection: " + e.getMessage(), e); }
        }
    }
}

// Service Classes
class NotificationService {
    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());

    public void notifyUser(String userId, String message) throws SQLException {
        LOGGER.info("Notifying user: " + userId + " with message: " + message);
        UserDAO userDAO = new UserDAO();
        if (!userDAO.doesUserIdExist(userId)) {
            LOGGER.warning("Cannot notify user: userId " + userId + " does not exist in Users table.");
            return; // Skip notification if user doesn't exist
        }
        Notification notification = new Notification(userId, message);
        NotificationDAO dao = new NotificationDAO();
        dao.saveNotification(notification);
        LOGGER.info("Notification sent to user " + userId + ": " + message);
        NotificationFrame.updateNotifications(userId);
    }

    public void notifyMarketPriceDrop(String farmerId, String productName, double marketPrice, double targetPrice) throws SQLException {
        String message = "Market price for " + productName + " is ₹" + marketPrice + ", lower than your target (₹" + targetPrice + "). A buyer is interested. Do you want to sell?";
        notifyUser(farmerId, message);
    }

    public void notifyBuyerMarketPrice(String buyerId, String productName, double marketPrice) throws SQLException {
        String message = "Farmer has listed " + productName + " above market price (₹" + marketPrice + "). Propose purchase at current rate?";
        notifyUser(buyerId, message);
    }

    public void notifyProductSoldOut(String farmerId, String buyerId, String productName) throws SQLException {
        notifyUser(farmerId, "Your product " + productName + " is sold out. Consider restocking.");
        notifyUser(buyerId, "Product " + productName + " is currently unavailable.");
    }

    public void notifyLowInventory(String farmerId, String buyerId, String productName, int quantity) throws SQLException {
        notifyUser(farmerId, "Only " + quantity + "kg left for " + productName + ". Update soon.");
        notifyUser(buyerId, "Limited stock left for " + productName + ". Hurry!");
    }

    public void notifyOrderPlaced(String farmerId, String buyerId, String productName) throws SQLException {
        notifyUser(farmerId, "New order received for " + productName + ". Please pack and ship.");
        notifyUser(buyerId, "Your order has been placed successfully.");
    }

    public void notifyOrderStatusUpdate(String farmerId, String buyerId, String orderId, String status) throws SQLException {
        notifyUser(farmerId, "You updated order #" + orderId + " to " + status + ".");
        notifyUser(buyerId, "Your order #" + orderId + " has been " + status + ".");
    }

    public void notifyPriceProposal(String farmerId, String orderId, double proposedPrice, int proposedQuantity) throws SQLException {
        notifyUser(farmerId, "Buyer proposed ₹" + proposedPrice + " for " + proposedQuantity + " kg on order #" + orderId + ". Accept or reject?");
    }

    public void notifyNegotiationResponse(String buyerId, String orderId, String response) throws SQLException {
        notifyUser(buyerId, "Farmer " + response + " your price proposal for order #" + orderId + ".");
    }
}

class OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private static final double PLATFORM_FEE = 20.0; // Same as in PaymentGateway

    public void placeOrder(Order order, boolean isWholesale, String buyerId) throws SQLException {
        LOGGER.info("Placing order: " + order.getId() + ", isWholesale: " + isWholesale);
        ProductDAO productDAO = new ProductDAO();
        ProductDTO product = productDAO.getProductById(order.getProductId());
        
        int availableQty = product.getQuantity();
        double price = isWholesale ? product.getWholesalePrice() * 0.9 : product.getRetailPrice();
        if (order.getQuantity() <= availableQty) {
            // Calculate base total (without platform fee and shipping fee)
            double baseTotal = order.getQuantity() * price;
            // Total price includes 5% commission on base total + platform fee + shipping fee
            double commission = baseTotal * 0.05;
            order.setTotalPrice(baseTotal + commission + PLATFORM_FEE + 100.0); // Shipping fee added in PaymentGateway
            
            boolean paymentSuccessful = PaymentGateway.processPayment(buyerId, order.getTotalPrice());
            if (!paymentSuccessful) {
                throw new SQLException("Payment failed! Order not placed.");
            }

            OrderDAO orderDAO = new OrderDAO();
            orderDAO.saveOrder(order);
            productDAO.updateQuantity(order.getProductId(), availableQty - order.getQuantity());
            RevenueDAO revenueDAO = new RevenueDAO();
            // Save only the commission as revenue (excluding platform fee and shipping fee)
            revenueDAO.saveRevenue(order.getId(), commission);
            NotificationService notificationService = new NotificationService();
            String farmerId = product.getFarmerId();
            String buyerIdForOrder = order.getBuyerId();
            LOGGER.info("Placing order - notifying farmerId: " + farmerId + ", buyerId: " + buyerIdForOrder);
            notificationService.notifyOrderPlaced(farmerId, buyerIdForOrder, product.getName());
            if (availableQty - order.getQuantity() <= 10) {
                notificationService.notifyLowInventory(product.getFarmerId(), order.getBuyerId(), product.getName(), availableQty - order.getQuantity());
            }
            if (availableQty - order.getQuantity() == 0) {
                notificationService.notifyProductSoldOut(product.getFarmerId(), order.getBuyerId(), product.getName());
            }
            LOGGER.info("Order placed: " + order.getId());
            PricePredictionService priceService = new PricePredictionService();
            double marketPrice = priceService.predictPrice(product.getName());
            if (marketPrice < product.getTargetPrice()) {
                notificationService.notifyMarketPriceDrop(product.getFarmerId(), product.getName(), marketPrice, product.getTargetPrice());
                notificationService.notifyBuyerMarketPrice(order.getBuyerId(), product.getName(), marketPrice);
            }
        } else {
            throw new SQLException("Insufficient quantity!");
        }
    }
}

class NegotiationService {
    private static final Logger LOGGER = Logger.getLogger(NegotiationService.class.getName());

    public void proposePrice(String orderId, String buyerId, String farmerId, double proposedPrice, int proposedQuantity) throws SQLException {
        LOGGER.info("Proposing price for order: " + orderId + ", buyerId: " + buyerId + ", farmerId: " + farmerId + ", proposedPrice: " + proposedPrice + ", quantity: " + proposedQuantity);
        Negotiation negotiation = new Negotiation(orderId, buyerId, farmerId, proposedPrice, proposedQuantity);
        NegotiationDAO dao = new NegotiationDAO();
        dao.saveNegotiation(negotiation);
        NotificationService notificationService = new NotificationService();
        notificationService.notifyPriceProposal(farmerId, orderId, proposedPrice, proposedQuantity);
        LOGGER.info("Price proposed for order: " + orderId);
    }

    public void attendToProposal(String negotiationId, String response, String buyerId, String orderId, JFrame parentFrame) throws SQLException {
        LOGGER.info("Attending to proposal: " + negotiationId + ", response: " + response + ", buyerId: " + buyerId + ", orderId: " + orderId);
        NegotiationDAO dao = new NegotiationDAO();
        dao.updateNegotiationStatus(negotiationId, response);
        NotificationService notificationService = new NotificationService();
        notificationService.notifyNegotiationResponse(buyerId, orderId, response);

        if (response.equals("Accepted")) {
            Negotiation negotiation = dao.getNegotiationById(negotiationId);
            ProductDAO productDAO = new ProductDAO();
            OrderDAO orderDAO = new OrderDAO();
            Order order = new Order(buyerId, orderId, negotiation.getProposedQuantity(), negotiation.getProposedPrice());
            try {
                OrderService orderService = new OrderService();
                orderService.placeOrder(order, false, buyerId);
                JOptionPane.showMessageDialog(parentFrame, "Negotiation accepted and order executed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Failed to execute order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                throw ex;
            }
        } else if (response.equals("Rejected")) {
            // Delete the negotiation record first to avoid foreign key constraint violation
            dao.deleteNegotiation(negotiationId);
            // Now delete the order
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.deleteOrder(orderId);
            JOptionPane.showMessageDialog(parentFrame, "Negotiation rejected. The order has been cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        LOGGER.info("Negotiation response: " + response + " for " + negotiationId);
    }
}

class RatingService {
    private static final Logger LOGGER = Logger.getLogger(RatingService.class.getName());

    public void submitRating(Rating rating) throws SQLException {
        LOGGER.info("Submitting rating: " + rating.getId());
        RatingDAO dao = new RatingDAO();
        dao.saveRating(rating);
        NotificationService notificationService = new NotificationService();
        notificationService.notifyUser(rating.getRatedId(), "You received a " + rating.getRating() + "-star rating!");
        LOGGER.info("Rating submitted: " + rating.getId());
    }
}

class PricePredictionService {
    public double predictPrice(String productName) {
        return productName.contains("Apple") ? 2.5 : productName.contains("Potato") ? 1.2 : 1.8;
    }
}

// GUI Classes
class NotificationFrame extends JFrame {
    private static final List<NotificationFrame> openFrames = new ArrayList<>();
    private JList<String> notificationList;
    private DefaultListModel<String> listModel;
    private List<Notification> notifications;
    private String userId;

    public NotificationFrame(String userId) {
        this.userId = userId;
        setTitle("Notifications for User " + userId);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        notificationList = new JList<>(listModel);
        updateNotifications();
        JScrollPane scrollPane = new JScrollPane(notificationList);
        add(scrollPane, BorderLayout.CENTER);

        JButton markReadButton = new JButton("Mark Selected as Read");
        markReadButton.addActionListener(e -> markAsRead());
        add(markReadButton, BorderLayout.SOUTH);

        openFrames.add(this);
        setVisible(true);
    }

    public static void updateNotifications(String userId) {
        for (NotificationFrame frame : openFrames) {
            if (frame.userId.equals(userId)) {
                frame.updateNotifications();
            }
        }
    }

    private void updateNotifications() {
        try {
            NotificationDAO dao = new NotificationDAO();
            notifications = dao.getNotificationsByUser(userId);
            listModel.clear();
            for (Notification n : notifications) {
                listModel.addElement((n.isRead() ? "[Read] " : "[Unread] ") + n.getMessage());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load notifications: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markAsRead() {
        int selectedIndex = notificationList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < notifications.size()) {
            try {
                Notification notification = notifications.get(selectedIndex);
                if (!notification.isRead()) {
                    NotificationDAO dao = new NotificationDAO();
                    dao.markAsRead(notification.getId());
                    updateNotifications();
                } else {
                    JOptionPane.showMessageDialog(this, "Notification is already marked as read!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to mark as read: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a notification to mark as read!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    private JCheckBox rememberMeCheckBox;
    private static String rememberedEmail = "";
    private static final String ADMIN_EMAIL = "admin@kisaanbazaar.com";
    private static final String ADMIN_PASSWORD = "admin@123";

    public LoginFrame() {
        setTitle("Kisaan Bazaar - Login");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            UserDAO userDAO = new UserDAO();
            userDAO.ensureAdminAccount();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to initialize admin account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(173, 216, 230), 0, getHeight(), new Color(144, 238, 144));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Login to Kisaan Bazaar", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        JButton adminLoginButton = new JButton("Login as Admin");
        adminLoginButton.setFont(new Font("Arial", Font.BOLD, 14));
        adminLoginButton.setBackground(new Color(255, 165, 0));
        adminLoginButton.setForeground(Color.WHITE);
        adminLoginButton.setBorder(BorderFactory.createLineBorder(new Color(255, 165, 0), 2));
        adminLoginButton.setPreferredSize(new Dimension(150, 40));
        adminLoginButton.addActionListener(e -> roleCombo.setSelectedItem("Admin"));
        adminLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminLoginButton.setBackground(new Color(255, 140, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminLoginButton.setBackground(new Color(255, 165, 0));
            }
        });
        headerPanel.add(adminLoginButton, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(15);
        emailField.setFont(fieldFont);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 1));
        emailField.setText(rememberedEmail);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(fieldFont);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(labelFont);
        roleLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(roleLabel, gbc);

        roleCombo = new JComboBox<>(new String[]{"Farmer", "Buyer", "Admin"});
        roleCombo.setFont(fieldFont);
        roleCombo.setBackground(Color.WHITE);
        roleCombo.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(roleCombo, gbc);

        rememberMeCheckBox = new JCheckBox("Remember Me");
        rememberMeCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        rememberMeCheckBox.setOpaque(false);
        rememberMeCheckBox.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(rememberMeCheckBox, gbc);

        JButton forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setFont(new Font("Arial", Font.BOLD, 14));
        forgotPasswordButton.setBackground(new Color(255, 165, 0));
        forgotPasswordButton.setForeground(Color.WHITE);
        forgotPasswordButton.setBorder(BorderFactory.createLineBorder(new Color(255, 165, 0), 2));
        forgotPasswordButton.setPreferredSize(new Dimension(150, 40));
        forgotPasswordButton.addActionListener(e -> forgotPassword());
        forgotPasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotPasswordButton.setBackground(new Color(255, 140, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgotPasswordButton.setBackground(new Color(255, 165, 0));
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(forgotPasswordButton, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 2));
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.addActionListener(e -> login());
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(50, 205, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(34, 139, 34));
            }
        });
        buttonPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(new Color(139, 69, 19));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2));
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addActionListener(e -> {
            dispose();
            new RegisterFrame();
        });
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(165, 42, 42));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(139, 69, 19));
            }
        });
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void login() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        if (!ValidationUtils.isValidEmail(email) || !ValidationUtils.isNonEmpty(password)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByEmailAndPassword(email, password);
            if (user != null) {
                if (rememberMeCheckBox.isSelected()) {
                    rememberedEmail = email;
                } else {
                    rememberedEmail = "";
                }

                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                if (user.getRole().equals("Farmer")) {
                    new FarmerFrame(user.getId());
                } else if (user.getRole().equals("Buyer")) {
                    new BuyerFrame(user.getId());
                } else {
                    new AdminFrame();
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to connect to the database! " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void forgotPassword() {
        String email = JOptionPane.showInputDialog(this, "Enter your email to reset password:", "Forgot Password", JOptionPane.PLAIN_MESSAGE);
        if (email != null && ValidationUtils.isValidEmail(email)) {
            try {
                UserDAO userDAO = new UserDAO();
                if (userDAO.doesEmailExist(email)) {
                    userDAO.resetPassword(email);
                    JOptionPane.showMessageDialog(this, 
                        "Your password has been reset to 'reset123'. Please log in and change your password.",
                        "Password Reset", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "No account found with email: " + email, 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, 
                    "Failed to reset password: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else if (email != null) {
            JOptionPane.showMessageDialog(this, 
                "Please enter a valid email address!", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}

class RegisterFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<String> roleCombo;
    private JTextField addressField;

    public RegisterFrame() {
        setTitle("Kisaan Bazaar - Register");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(173, 216, 230), 0, getHeight(), new Color(144, 238, 144));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Register for Kisaan Bazaar", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(34, 139, 34), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(labelFont);
        emailLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField(15);
        emailField.setFont(fieldFont);
        emailField.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 1));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(fieldFont);
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(labelFont);
        confirmPasswordLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField(15);
        confirmPasswordField.setFont(fieldFont);
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(confirmPasswordField, gbc);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(labelFont);
        roleLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(roleLabel, gbc);

        roleCombo = new JComboBox<>(new String[]{"Farmer", "Buyer"});
        roleCombo.setFont(fieldFont);
        roleCombo.setBackground(Color.WHITE);
        roleCombo.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 1));
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(roleCombo, gbc);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(new Color(34, 139, 34));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(addressLabel, gbc);

        addressField = new JTextField(15);
        addressField.setFont(fieldFont);
        addressField.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 1));
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(addressField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(new Color(34, 139, 34));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 2));
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.addActionListener(e -> register());
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(50, 205, 50));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(34, 139, 34));
            }
        });
        buttonPanel.add(registerButton);

        JButton backButton = new JButton("Back to Login");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(139, 69, 19));
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2));
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(165, 42, 42));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(139, 69, 19));
            }
        });
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void register() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();
        String address = addressField.getText();

        if (!ValidationUtils.isValidEmail(email) || !ValidationUtils.isNonEmpty(password)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!ValidationUtils.isNonEmpty(address)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid address!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            UserDAO userDAO = new UserDAO();
            if (userDAO.doesEmailExist(email)) {
                JOptionPane.showMessageDialog(this, "Email already in use!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = new User("User", email, password, role, address);
            userDAO.saveUser(user);
            JOptionPane.showMessageDialog(this, "Registration successful! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginFrame();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Unable to connect to the database! " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class FarmerFrame extends JFrame {
    private String farmerId;
    private JPanel mainContentPanel;
    private CardLayout cardLayout;
    private String currentPanel = "Dashboard";
    private static final Color SIDEBAR_COLOR = new Color(34, 139, 34);
    private static final Color CONTENT_COLOR = new Color(245, 245, 220);
    private static final Color BUTTON_COLOR = new Color(50, 205, 50);
    private static final Color BUTTON_HOVER_COLOR = new Color(46, 139, 87);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font FIELD_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Font SIDEBAR_FONT = new Font("Arial", Font.BOLD, 16);
    private static final int MIN_WHOLESALE_QTY = 50;

    public FarmerFrame(String farmerId) {
        this.farmerId = farmerId;
        setTitle("Farmer Dashboard - Kisaan Bazaar");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel sidebar = createSidebar();
        mainPanel.add(sidebar, BorderLayout.WEST);

        JPanel headerPanel = createHeaderPanel("Farmer");
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(CONTENT_COLOR);

        mainContentPanel.add(createDashboardPanel(), "Dashboard");
        mainContentPanel.add(createAddProductPanel(), "AddProduct");
        mainContentPanel.add(createViewProductsPanel(), "ViewProducts");
        mainContentPanel.add(createOrderHistoryPanel(), "OrderHistory");
        mainContentPanel.add(createNegotiationsPanel(), "Negotiations");

        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        add(mainPanel);
        cardLayout.show(mainContentPanel, "Dashboard");
        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        String[] menuItems = {"Dashboard", "Add Product", "View Products", "Order History", "Negotiations", "Notifications"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(SIDEBAR_FONT);
            button.setForeground(Color.WHITE);
            button.setBackground(SIDEBAR_COLOR);
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            button.addActionListener(e -> {
                if (item.equals("Notifications")) {
                    new NotificationFrame(farmerId);
                } else {
                    String panelName = item.replace(" ", "");
                    currentPanel = panelName;
                    cardLayout.show(mainContentPanel, panelName);
                }
            });
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(50, 205, 50));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(SIDEBAR_COLOR);
                }
            });
            sidebar.add(button);
            sidebar.add(Box.createVerticalStrut(5));
        }

        return sidebar;
    }

    private JPanel createHeaderPanel(String role) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel welcomeLabel = new JLabel("Welcome, " + role + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(SIDEBAR_COLOR);
        headerPanel.add(welcomeLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setBackground(BUTTON_COLOR);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        refreshButton.addActionListener(e -> refreshAllPanels());
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshButton.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshButton.setBackground(BUTTON_COLOR);
            }
        });
        buttonPanel.add(refreshButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(BUTTON_COLOR);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(BUTTON_COLOR);
            }
        });
        buttonPanel.add(logoutButton);

        headerPanel.add(buttonPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private void refreshAllPanels() {
        mainContentPanel.remove(mainContentPanel.getComponent(0));
        mainContentPanel.add(createDashboardPanel(), "Dashboard", 0);

        mainContentPanel.remove(mainContentPanel.getComponent(1));
        mainContentPanel.add(createAddProductPanel(), "AddProduct", 1);

        mainContentPanel.remove(mainContentPanel.getComponent(2));
        mainContentPanel.add(createViewProductsPanel(), "ViewProducts", 2);

        mainContentPanel.remove(mainContentPanel.getComponent(3));
        mainContentPanel.add(createOrderHistoryPanel(), "OrderHistory", 3);

        mainContentPanel.remove(mainContentPanel.getComponent(4));
        mainContentPanel.add(createNegotiationsPanel(), "Negotiations", 4);

        cardLayout.show(mainContentPanel, currentPanel);
        JOptionPane.showMessageDialog(this, "Data refreshed!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(CONTENT_COLOR);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel summaryPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        summaryPanel.setBackground(CONTENT_COLOR);

        try {
            ProductDAO productDAO = new ProductDAO();
            JPanel productCard = createSummaryCard("Total Products Listed", productDAO.getProductCount(farmerId));
            summaryPanel.add(productCard);

            OrderDAO orderDAO = new OrderDAO();
            JPanel orderCard = createSummaryCard("Pending Orders", orderDAO.getPendingOrderCount(farmerId));
            summaryPanel.add(orderCard);

            NegotiationDAO negotiationDAO = new NegotiationDAO();
            JPanel negotiationCard = createSummaryCard("Pending Negotiations", negotiationDAO.getPendingNegotiationCount(farmerId));
            summaryPanel.add(negotiationCard);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load dashboard statistics: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        dashboardPanel.add(summaryPanel, BorderLayout.NORTH);

        JPanel quickActionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        quickActionsPanel.setBackground(CONTENT_COLOR);

        JButton addProductButton = createStyledButton("Add New Product");
        addProductButton.addActionListener(e -> {
            currentPanel = "AddProduct";
            cardLayout.show(mainContentPanel, "AddProduct");
        });
        quickActionsPanel.add(addProductButton);

        JButton viewOrdersButton = createStyledButton("View Order History");
        viewOrdersButton.addActionListener(e -> {
            currentPanel = "OrderHistory";
            cardLayout.show(mainContentPanel, "OrderHistory");
        });
        quickActionsPanel.add(viewOrdersButton);

        JButton viewNegotiationsButton = createStyledButton("View Negotiations");
        viewNegotiationsButton.addActionListener(e -> {
            currentPanel = "Negotiations";
            cardLayout.show(mainContentPanel, "Negotiations");
        });
        quickActionsPanel.add(viewNegotiationsButton);

        dashboardPanel.add(quickActionsPanel, BorderLayout.CENTER);

        return dashboardPanel;
    }

    private JPanel createSummaryCard(String title, int value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SIDEBAR_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(SIDEBAR_COLOR);
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(SIDEBAR_COLOR);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(BUTTON_COLOR, 2));
        button.setPreferredSize(new Dimension(150, 40));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });
        return button;
    }

    private JPanel createAddProductPanel() {
        JPanel productPanel = new JPanel(new GridBagLayout());
        productPanel.setBackground(CONTENT_COLOR);
        productPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField nameField = new JTextField(20);
        nameField.setFont(FIELD_FONT);
        nameField.setToolTipText("Enter the product name (e.g., Carrot)");
        JComboBox<String> categoryCombo = new JComboBox<>(new String[]{"Fruits", "Vegetables"});
        categoryCombo.setFont(FIELD_FONT);
        JTextField retailPriceField = new JTextField(20);
        retailPriceField.setFont(FIELD_FONT);
        retailPriceField.setToolTipText("Enter the retail price per kg (e.g., 10.0)");
        JTextField wholesalePriceField = new JTextField(20);
        wholesalePriceField.setFont(FIELD_FONT);
        wholesalePriceField.setToolTipText("Enter the wholesale price per kg (e.g., 7.0)");
        JLabel minWholesaleQtyLabel = new JLabel(String.valueOf(MIN_WHOLESALE_QTY) + " kg (Fixed)");
        minWholesaleQtyLabel.setFont(FIELD_FONT);
        minWholesaleQtyLabel.setForeground(Color.GRAY);
        JTextField targetPriceField = new JTextField(20);
        targetPriceField.setFont(FIELD_FONT);
        targetPriceField.setToolTipText("Enter the target price per kg (e.g., 12.0)");
        JTextField quantityField = new JTextField(20);
        quantityField.setFont(FIELD_FONT);
        quantityField.setToolTipText("Enter the quantity in kg (e.g., 100)");

        JLabel titleLabel = new JLabel("Add New Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        productPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(LABEL_FONT);
        nameLabel.setForeground(SIDEBAR_COLOR);
        productPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        productPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton checkMarketPriceButton = createStyledButton("Check Market Price");
        checkMarketPriceButton.addActionListener(e -> {
            String productName = nameField.getText().trim();
            if (!ValidationUtils.isNonEmpty(productName)) {
                JOptionPane.showMessageDialog(this, "Please enter a product name first!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            productName = productName.substring(0, 1).toUpperCase() + productName.substring(1).toLowerCase();
            try {
                MarketPriceDAO marketPriceDAO = new MarketPriceDAO();
                double marketPrice = marketPriceDAO.getMarketPrice(productName);
                if (marketPrice > 0) {
                    JOptionPane.showMessageDialog(this, "Current market price for " + productName + ": ₹" + marketPrice + " per kg", "Market Price", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No market price found for " + productName, "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to fetch market price: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        productPanel.add(checkMarketPriceButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(LABEL_FONT);
        categoryLabel.setForeground(SIDEBAR_COLOR);
        productPanel.add(categoryLabel, gbc);
        gbc.gridx = 1;
        productPanel.add(categoryCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel retailPriceLabel = new JLabel("Retail Price (per kg):");
        retailPriceLabel.setFont(LABEL_FONT);
        retailPriceLabel.setForeground(SIDEBAR_COLOR);
        productPanel.add(retailPriceLabel, gbc);
        gbc.gridx = 1;
        productPanel.add(retailPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel wholesalePriceLabel = new JLabel("Wholesale Price (per kg):");
        wholesalePriceLabel.setFont(LABEL_FONT);
        wholesalePriceLabel.setForeground(SIDEBAR_COLOR);
        productPanel.add(wholesalePriceLabel, gbc);
        gbc.gridx = 1;
        productPanel.add(wholesalePriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel minWholesaleQtyLabelLabel = new JLabel("Min Wholesale Qty:");
        minWholesaleQtyLabelLabel.setFont(LABEL_FONT);
        minWholesaleQtyLabelLabel.setForeground(SIDEBAR_COLOR);
        productPanel.add(minWholesaleQtyLabelLabel, gbc);
        gbc.gridx = 1;
        productPanel.add(minWholesaleQtyLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel targetPriceLabel = new JLabel("Target Price (per kg):");
        targetPriceLabel.setFont(LABEL_FONT);
        targetPriceLabel.setForeground(SIDEBAR_COLOR);
        productPanel.add(targetPriceLabel, gbc);
        gbc.gridx = 1;
        productPanel.add(targetPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JLabel quantityLabel = new JLabel("Quantity (kg):");
        quantityLabel.setFont(LABEL_FONT);
        quantityLabel.setForeground(SIDEBAR_COLOR);
        productPanel.add(quantityLabel, gbc);
        gbc.gridx = 1;
        productPanel.add(quantityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        JButton addButton = createStyledButton("Add Product");
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String retailPrice = retailPriceField.getText();
            String wholesalePrice = wholesalePriceField.getText();
            String targetPrice = targetPriceField.getText();
            String quantity = quantityField.getText();

            if (!ValidationUtils.isNonEmpty(name)) {
                JOptionPane.showMessageDialog(this, "Product name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ValidationUtils.isPositiveNumber(retailPrice)) {
                JOptionPane.showMessageDialog(this, "Retail price must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ValidationUtils.isPositiveNumber(wholesalePrice)) {
                JOptionPane.showMessageDialog(this, "Wholesale price must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ValidationUtils.isPositiveNumber(targetPrice)) {
                JOptionPane.showMessageDialog(this, "Target price must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ValidationUtils.isPositiveNumber(quantity)) {
                JOptionPane.showMessageDialog(this, "Quantity must be a positive number!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Product product = new Product(
                        farmerId,
                        name,
                        (String) categoryCombo.getSelectedItem(),
                        Double.parseDouble(retailPrice),
                        Double.parseDouble(wholesalePrice),
                        MIN_WHOLESALE_QTY,
                        Double.parseDouble(targetPrice),
                        Integer.parseInt(quantity)
                );
                ProductDAO productDAO = new ProductDAO();
                productDAO.saveProduct(product);
                NotificationService notificationService = new NotificationService();
                notificationService.notifyUser(farmerId, "Product " + name + " listed successfully!");
                JOptionPane.showMessageDialog(this, "Product added! You can view it in the 'View Products' section.", "Success", JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                retailPriceField.setText("");
                wholesalePriceField.setText("");
                targetPriceField.setText("");
                quantityField.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to add product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format in input fields!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        productPanel.add(addButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 9;
        JButton viewProductsButton = createStyledButton("View Products");
        viewProductsButton.addActionListener(e -> {
            currentPanel = "ViewProducts";
            cardLayout.show(mainContentPanel, "ViewProducts");
        });
        productPanel.add(viewProductsButton, gbc);

        return productPanel;
    }

    private JPanel createViewProductsPanel() {
        JPanel viewProductsPanel = new JPanel(new BorderLayout());
        viewProductsPanel.setBackground(CONTENT_COLOR);
        viewProductsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("My Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        viewProductsPanel.add(titleLabel, BorderLayout.NORTH);

        JTable productTable = new JTable();
        try {
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> products = productDAO.getProductsByFarmer(farmerId);
            productTable.setModel(ListTableModel.fromProducts(products, ProductDAO.PRODUCT_COLUMNS));
            productTable.setRowHeight(25);
            productTable.setGridColor(SIDEBAR_COLOR);
            productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(productTable);
        viewProductsPanel.add(scrollPane, BorderLayout.CENTER);

        return viewProductsPanel;
    }

    private JPanel createOrderHistoryPanel() {
        JPanel orderHistoryPanel = new JPanel(new BorderLayout());
        orderHistoryPanel.setBackground(CONTENT_COLOR);
        orderHistoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Order History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        orderHistoryPanel.add(titleLabel, BorderLayout.NORTH);

        JTable historyTable = new JTable();
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<OrderDTO> orders = orderDAO.getOrdersByUser(farmerId, "Farmer");
            historyTable.setModel(ListTableModel.fromOrders(orders, OrderDAO.ORDER_COLUMNS));
            historyTable.setRowHeight(25);
            historyTable.setGridColor(SIDEBAR_COLOR);
            historyTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load order history: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(historyTable);
        orderHistoryPanel.add(scrollPane, BorderLayout.CENTER);

        return orderHistoryPanel;
    }

    private JPanel createNegotiationsPanel() {
        JPanel negotiationPanel = new JPanel(new BorderLayout());
        negotiationPanel.setBackground(CONTENT_COLOR);
        negotiationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Negotiations");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        negotiationPanel.add(titleLabel, BorderLayout.NORTH);

        JTable negotiationTable = new JTable();
        try {
            NegotiationDAO negotiationDAO = new NegotiationDAO();
            List<NegotiationDTO> negotiations = negotiationDAO.getNegotiationsByFarmer(farmerId);
            negotiationTable.setModel(ListTableModel.fromNegotiations(negotiations, NegotiationDAO.NEGOTIATION_COLUMNS));
            negotiationTable.setRowHeight(25);
            negotiationTable.setGridColor(SIDEBAR_COLOR);
            negotiationTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load negotiations: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(negotiationTable);
        negotiationPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel negotiationButtons = new JPanel(new FlowLayout());
        negotiationButtons.setBackground(CONTENT_COLOR);
        JButton acceptButton = createStyledButton("Accept");
        JButton rejectButton = createStyledButton("Reject");
        acceptButton.addActionListener(e -> handleNegotiationResponse(negotiationTable, "Accepted"));
        rejectButton.addActionListener(e -> handleNegotiationResponse(negotiationTable, "Rejected"));
        negotiationButtons.add(acceptButton);
        negotiationButtons.add(rejectButton);
        negotiationPanel.add(negotiationButtons, BorderLayout.SOUTH);

        return negotiationPanel;
    }

    private void handleNegotiationResponse(JTable table, String response) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String negotiationId = table.getValueAt(row, 0).toString();
            String orderId = table.getValueAt(row, 1).toString();
            String buyerId = table.getValueAt(row, 2).toString();
            try {
                NegotiationService negotiationService = new NegotiationService();
                negotiationService.attendToProposal(negotiationId, response, buyerId, orderId, this);
                NegotiationDAO negotiationDAO = new NegotiationDAO();
                List<NegotiationDTO> negotiations = negotiationDAO.getNegotiationsByFarmer(farmerId);
                table.setModel(ListTableModel.fromNegotiations(negotiations, NegotiationDAO.NEGOTIATION_COLUMNS));
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to respond to negotiation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a negotiation to respond to!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class BuyerFrame extends JFrame {
    private String buyerId;
    private JPanel mainContentPanel;
    private CardLayout cardLayout;
    private String currentPanel = "Dashboard";
    private static final Color SIDEBAR_COLOR = new Color(34, 139, 34);
    private static final Color CONTENT_COLOR = new Color(245, 245, 220);
    private static final Color BUTTON_COLOR = new Color(50, 205, 50);
    private static final Color BUTTON_HOVER_COLOR = new Color(46, 139, 87);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font FIELD_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Font SIDEBAR_FONT = new Font("Arial", Font.BOLD, 16);

    public BuyerFrame(String buyerId) {
        this.buyerId = buyerId;
        setTitle("Buyer Dashboard - Kisaan Bazaar");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel sidebar = createSidebar();
        mainPanel.add(sidebar, BorderLayout.WEST);

        JPanel headerPanel = createHeaderPanel("Buyer");
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(CONTENT_COLOR);

        mainContentPanel.add(createDashboardPanel(), "Dashboard");
        mainContentPanel.add(createBrowseProductsPanel(), "BrowseProducts");
        mainContentPanel.add(createOrderStatusPanel(), "OrderStatus");
        mainContentPanel.add(createOrderHistoryPanel(), "OrderHistory");

        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        add(mainPanel);
        cardLayout.show(mainContentPanel, "Dashboard");
        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        String[] menuItems = {"Dashboard", "Browse Products", "Order Status", "Order History", "Notifications"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(SIDEBAR_FONT);
            button.setForeground(Color.WHITE);
            button.setBackground(SIDEBAR_COLOR);
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            button.addActionListener(e -> {
                if (item.equals("Notifications")) {
                    new NotificationFrame(buyerId);
                } else {
                    String panelName = item.replace(" ", "");
                    currentPanel = panelName;
                    cardLayout.show(mainContentPanel, panelName);
                }
            });
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(50, 205, 50));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(SIDEBAR_COLOR);
                }
            });
            sidebar.add(button);
            sidebar.add(Box.createVerticalStrut(5));
        }

        return sidebar;
    }

    private JPanel createHeaderPanel(String role) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel welcomeLabel = new JLabel("Welcome, " + role + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(SIDEBAR_COLOR);
        headerPanel.add(welcomeLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setBackground(BUTTON_COLOR);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        refreshButton.addActionListener(e -> refreshAllPanels());
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshButton.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshButton.setBackground(BUTTON_COLOR);
            }
        });
        buttonPanel.add(refreshButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(BUTTON_COLOR);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(BUTTON_COLOR);
            }
        });
        buttonPanel.add(logoutButton);

        headerPanel.add(buttonPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private void refreshAllPanels() {
        mainContentPanel.remove(mainContentPanel.getComponent(0));
        mainContentPanel.add(createDashboardPanel(), "Dashboard", 0);

        mainContentPanel.remove(mainContentPanel.getComponent(1));
        mainContentPanel.add(createBrowseProductsPanel(), "BrowseProducts", 1);

        mainContentPanel.remove(mainContentPanel.getComponent(2));
        mainContentPanel.add(createOrderStatusPanel(), "OrderStatus", 2);

        mainContentPanel.remove(mainContentPanel.getComponent(3));
        mainContentPanel.add(createOrderHistoryPanel(), "OrderHistory", 3);

        cardLayout.show(mainContentPanel, currentPanel);
        JOptionPane.showMessageDialog(this, "Data refreshed!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(CONTENT_COLOR);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel summaryPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        summaryPanel.setBackground(CONTENT_COLOR);

        try {
            OrderDAO orderDAO = new OrderDAO();
            JPanel orderCard = createSummaryCard("Total Orders", orderDAO.getOrderCount(buyerId));
            summaryPanel.add(orderCard);

            NotificationDAO notificationDAO = new NotificationDAO();
            JPanel notificationCard = createSummaryCard("Unread Notifications", notificationDAO.getUnreadNotificationCount(buyerId));
            summaryPanel.add(notificationCard);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load dashboard statistics: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        dashboardPanel.add(summaryPanel, BorderLayout.NORTH);

        JPanel quickActionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        quickActionsPanel.setBackground(CONTENT_COLOR);

        JButton browseProductsButton = createStyledButton("Browse Products");
        browseProductsButton.addActionListener(e -> {
            currentPanel = "BrowseProducts";
            cardLayout.show(mainContentPanel, "BrowseProducts");
        });
        quickActionsPanel.add(browseProductsButton);

        JButton viewOrdersButton = createStyledButton("View Orders");
        viewOrdersButton.addActionListener(e -> {
            currentPanel = "OrderStatus";
            cardLayout.show(mainContentPanel, "OrderStatus");
        });
        quickActionsPanel.add(viewOrdersButton);

        dashboardPanel.add(quickActionsPanel, BorderLayout.CENTER);

        return dashboardPanel;
    }

    private JPanel createSummaryCard(String title, int value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SIDEBAR_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(SIDEBAR_COLOR);
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(String.valueOf(value), SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(SIDEBAR_COLOR);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(BUTTON_COLOR, 2));
        button.setPreferredSize(new Dimension(150, 40));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });
        return button;
    }

    private JPanel createBrowseProductsPanel() {
        JPanel browsePanel = new JPanel(new BorderLayout());
        browsePanel.setBackground(CONTENT_COLOR);
        browsePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Browse Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        browsePanel.add(titleLabel, BorderLayout.NORTH);

        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.setBackground(CONTENT_COLOR);

        JComboBox<String> categoryCombo = new JComboBox<>(new String[]{"All", "Fruits", "Vegetables"});
        categoryCombo.setFont(FIELD_FONT);
        JTextField minPriceField = new JTextField("0", 5);
        minPriceField.setFont(FIELD_FONT);
        JTextField maxPriceField = new JTextField("1000", 5);
        maxPriceField.setFont(FIELD_FONT);
        JComboBox<String> sortCombo = new JComboBox<>(new String[]{"retail_price ASC", "retail_price DESC"});
        sortCombo.setFont(FIELD_FONT);
        JButton filterButton = createStyledButton("Filter");

        filterPanel.add(new JLabel("Category:"));
        filterPanel.add(categoryCombo);
        filterPanel.add(new JLabel("Min Price:"));
        filterPanel.add(minPriceField);
        filterPanel.add(new JLabel("Max Price:"));
        filterPanel.add(maxPriceField);
        filterPanel.add(new JLabel("Sort By:"));
        filterPanel.add(sortCombo);
        filterPanel.add(filterButton);

        JTable productTable = new JTable();
        try {
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> products = productDAO.getProducts("", 0, 1000, "retail_price ASC");
            productTable.setModel(ListTableModel.fromProducts(products, ProductDAO.PRODUCT_COLUMNS));
            productTable.setRowHeight(25);
            productTable.setGridColor(SIDEBAR_COLOR);
            productTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        filterButton.addActionListener(e -> {
            String category = categoryCombo.getSelectedItem().toString().equals("All") ? "" : categoryCombo.getSelectedItem().toString();
            String minPriceStr = minPriceField.getText();
            String maxPriceStr = maxPriceField.getText();
            String sortBy = sortCombo.getSelectedItem().toString();

            double minPrice = ValidationUtils.isPositiveNumber(minPriceStr) ? Double.parseDouble(minPriceStr) : 0;
            double maxPrice = ValidationUtils.isPositiveNumber(maxPriceStr) ? Double.parseDouble(maxPriceStr) : Double.MAX_VALUE;

            try {
                ProductDAO productDAO = new ProductDAO();
                List<ProductDTO> products = productDAO.getProducts(category, minPrice, maxPrice, sortBy);
                productTable.setModel(ListTableModel.fromProducts(products, ProductDAO.PRODUCT_COLUMNS));
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to filter products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JScrollPane scrollPane = new JScrollPane(productTable);
        browsePanel.add(scrollPane, BorderLayout.CENTER);
        browsePanel.add(filterPanel, BorderLayout.NORTH);

        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBackground(CONTENT_COLOR);

        JButton buyButton = createStyledButton("Buy Retail");
        JButton buyWholesaleButton = createStyledButton("Buy Wholesale");
        JButton proposePriceButton = createStyledButton("Propose Price");

        buyButton.addActionListener(e -> handleBuy(productTable, false));
        buyWholesaleButton.addActionListener(e -> handleBuy(productTable, true));
        proposePriceButton.addActionListener(e -> handleProposePrice(productTable));

        actionPanel.add(buyButton);
        actionPanel.add(buyWholesaleButton);
        actionPanel.add(proposePriceButton);
        browsePanel.add(actionPanel, BorderLayout.SOUTH);

        return browsePanel;
    }

   private void handleBuy(JTable table, boolean isWholesale) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String productId = table.getValueAt(row, 0).toString();
            String farmerId = table.getValueAt(row, 1).toString();
            int availableQty = Integer.parseInt(table.getValueAt(row, 8).toString());
            String quantityStr = JOptionPane.showInputDialog(this, "Enter quantity to buy (Available: " + availableQty + " kg):", "Buy Product", JOptionPane.PLAIN_MESSAGE);
            if (quantityStr != null && ValidationUtils.isPositiveNumber(quantityStr)) {
                int quantity = Integer.parseInt(quantityStr);
                if (quantity > 0 && quantity <= availableQty) {
                    try {
                        Order order = new Order(buyerId, productId, quantity, 0);
                        OrderService orderService = new OrderService();
                        orderService.placeOrder(order, isWholesale, buyerId);
                        JOptionPane.showMessageDialog(this, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        ProductDAO productDAO = new ProductDAO();
                        List<ProductDTO> products = productDAO.getProducts("", 0, 1000, "retail_price ASC");
                        table.setModel(ListTableModel.fromProducts(products, ProductDAO.PRODUCT_COLUMNS));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Failed to place order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to buy!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleProposePrice(JTable table) {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String productId = table.getValueAt(row, 0).toString();
            String farmerId = table.getValueAt(row, 1).toString();
            int availableQty = Integer.parseInt(table.getValueAt(row, 8).toString());
            double currentPrice = Double.parseDouble(table.getValueAt(row, 4).toString());

            JPanel panel = new JPanel(new GridLayout(2, 2));
            JTextField quantityField = new JTextField(5);
            JTextField priceField = new JTextField(5);
            panel.add(new JLabel("Quantity (kg, Available: " + availableQty + "):"));
            panel.add(quantityField);
            panel.add(new JLabel("Proposed Price (Current: ₹" + currentPrice + "):"));
            panel.add(priceField);

            int result = JOptionPane.showConfirmDialog(this, panel, "Propose Price", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String quantityStr = quantityField.getText();
                String proposedPriceStr = priceField.getText();

                if (!ValidationUtils.isPositiveNumber(quantityStr) || !ValidationUtils.isPositiveNumber(proposedPriceStr)) {
                    JOptionPane.showMessageDialog(this, "Please enter valid quantity and price!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int proposedQuantity = Integer.parseInt(quantityStr);
                double proposedPrice = Double.parseDouble(proposedPriceStr);

                if (proposedQuantity <= 0 || proposedQuantity > availableQty) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity! Must be between 1 and " + availableQty, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (proposedPrice <= 0) {
                    JOptionPane.showMessageDialog(this, "Proposed price must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Order order = new Order(buyerId, productId, proposedQuantity, proposedPrice);
                    OrderDAO orderDAO = new OrderDAO();
                    orderDAO.saveOrder(order);
                    NegotiationService negotiationService = new NegotiationService();
                    negotiationService.proposePrice(order.getId(), buyerId, farmerId, proposedPrice, proposedQuantity);
                    JOptionPane.showMessageDialog(this, "Price proposal sent!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to propose price: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to propose a price for!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel createOrderStatusPanel() {
        JPanel orderStatusPanel = new JPanel(new BorderLayout());
        orderStatusPanel.setBackground(CONTENT_COLOR);
        orderStatusPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Order Status");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        orderStatusPanel.add(titleLabel, BorderLayout.NORTH);

        JTable orderTable = new JTable();
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<OrderDTO> orders = orderDAO.getOrdersByUser(buyerId, "Buyer");
            orderTable.setModel(ListTableModel.fromOrders(orders, OrderDAO.ORDER_COLUMNS));
            orderTable.setRowHeight(25);
            orderTable.setGridColor(SIDEBAR_COLOR);
            orderTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load orders: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderStatusPanel.add(scrollPane, BorderLayout.CENTER);

        return orderStatusPanel;
    }

    private JPanel createOrderHistoryPanel() {
        JPanel orderHistoryPanel = new JPanel(new BorderLayout());
        orderHistoryPanel.setBackground(CONTENT_COLOR);
        orderHistoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Order History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        orderHistoryPanel.add(titleLabel, BorderLayout.NORTH);

        JTable historyTable = new JTable();
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<OrderDTO> orders = orderDAO.getOrdersByUser(buyerId, "Buyer");
            historyTable.setModel(ListTableModel.fromOrders(orders, OrderDAO.ORDER_COLUMNS));
            historyTable.setRowHeight(25);
            historyTable.setGridColor(SIDEBAR_COLOR);
            historyTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load order history: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(historyTable);
        orderHistoryPanel.add(scrollPane, BorderLayout.CENTER);

        return orderHistoryPanel;
    }
}

class AdminFrame extends JFrame {
    private JPanel mainContentPanel;
    private CardLayout cardLayout;
    private String currentPanel = "Dashboard";
    private static final Color SIDEBAR_COLOR = new Color(34, 139, 34);
    private static final Color CONTENT_COLOR = new Color(245, 245, 220);
    private static final Color BUTTON_COLOR = new Color(50, 205, 50);
    private static final Color BUTTON_HOVER_COLOR = new Color(46, 139, 87);
    private static final Font LABEL_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font FIELD_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Font SIDEBAR_FONT = new Font("Arial", Font.BOLD, 16);
    public static final String[] PLATFORM_FEE_COLUMNS = {"Transaction ID", "Buyer ID", "Fee (₹)", "Timestamp"};
    public static final String[] USER_COLUMNS = {"ID", "Name", "Email", "Role", "Address"};

    public AdminFrame() {
        setTitle("Admin Dashboard - Kisaan Bazaar");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel sidebar = createSidebar();
        mainPanel.add(sidebar, BorderLayout.WEST);

        JPanel headerPanel = createHeaderPanel("Admin");
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        mainContentPanel.setBackground(CONTENT_COLOR);

        mainContentPanel.add(createDashboardPanel(), "Dashboard");
        mainContentPanel.add(createOrderManagementPanel(), "OrderManagement");
        mainContentPanel.add(createOrderStatusPanel(), "OrderStatus");
        mainContentPanel.add(createPaymentGatewayPanel(), "PaymentGateway");
        mainContentPanel.add(createAllProductsPanel(), "AllProducts");
        mainContentPanel.add(createAllUsersPanel(), "AllUsers");

        mainPanel.add(mainContentPanel, BorderLayout.CENTER);

        add(mainPanel);
        cardLayout.show(mainContentPanel, "Dashboard");
        setVisible(true);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        String[] menuItems = {"Dashboard", "Order Management", "Order Status", "Payment Gateway", "All Products", "All Users"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setFont(SIDEBAR_FONT);
            button.setForeground(Color.WHITE);
            button.setBackground(SIDEBAR_COLOR);
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            button.setAlignmentX(Component.LEFT_ALIGNMENT);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            button.addActionListener(e -> {
                String panelName = item.replace(" ", "");
                currentPanel = panelName;
                cardLayout.show(mainContentPanel, panelName);
            });
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(50, 205, 50));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(SIDEBAR_COLOR);
                }
            });
            sidebar.add(button);
            sidebar.add(Box.createVerticalStrut(5));
        }

        return sidebar;
    }

    private JPanel createHeaderPanel(String role) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel welcomeLabel = new JLabel("Welcome, " + role + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setForeground(SIDEBAR_COLOR);
        headerPanel.add(welcomeLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 14));
        refreshButton.setBackground(BUTTON_COLOR);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        refreshButton.addActionListener(e -> refreshAllPanels());
        refreshButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                refreshButton.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshButton.setBackground(BUTTON_COLOR);
            }
        });
        buttonPanel.add(refreshButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(BUTTON_COLOR);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButton.setBackground(BUTTON_COLOR);
            }
        });
        buttonPanel.add(logoutButton);

        headerPanel.add(buttonPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private void refreshAllPanels() {
        mainContentPanel.remove(mainContentPanel.getComponent(0));
        mainContentPanel.add(createDashboardPanel(), "Dashboard", 0);

        mainContentPanel.remove(mainContentPanel.getComponent(1));
        mainContentPanel.add(createOrderManagementPanel(), "OrderManagement", 1);

        mainContentPanel.remove(mainContentPanel.getComponent(2));
        mainContentPanel.add(createOrderStatusPanel(), "OrderStatus", 2);

        mainContentPanel.remove(mainContentPanel.getComponent(3));
        mainContentPanel.add(createPaymentGatewayPanel(), "PaymentGateway", 3);

        mainContentPanel.remove(mainContentPanel.getComponent(4));
        mainContentPanel.add(createAllProductsPanel(), "AllProducts", 4);

        mainContentPanel.remove(mainContentPanel.getComponent(5));
        mainContentPanel.add(createAllUsersPanel(), "AllUsers", 5);

        cardLayout.show(mainContentPanel, currentPanel);
        JOptionPane.showMessageDialog(this, "Data refreshed!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private JPanel createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(CONTENT_COLOR);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel summaryPanel = new JPanel(new GridLayout(1, 1, 20, 20));
        summaryPanel.setBackground(CONTENT_COLOR);

        try {
            RevenueDAO revenueDAO = new RevenueDAO();
            JPanel revenueCard = createSummaryCard("Total Revenue", revenueDAO.getTotalRevenue());
            summaryPanel.add(revenueCard);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load dashboard statistics: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        dashboardPanel.add(summaryPanel, BorderLayout.NORTH);

        JPanel quickActionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        quickActionsPanel.setBackground(CONTENT_COLOR);

        JButton manageOrdersButton = createStyledButton("Manage Orders");
        manageOrdersButton.addActionListener(e -> {
            currentPanel = "OrderManagement";
            cardLayout.show(mainContentPanel, "OrderManagement");
        });
        quickActionsPanel.add(manageOrdersButton);

        JButton orderStatusButton = createStyledButton("Order Status");
        orderStatusButton.addActionListener(e -> {
            currentPanel = "OrderStatus";
            cardLayout.show(mainContentPanel, "OrderStatus");
        });
        quickActionsPanel.add(orderStatusButton);

        JButton paymentGatewayButton = createStyledButton("Payment Gateway");
        paymentGatewayButton.addActionListener(e -> {
            currentPanel = "PaymentGateway";
            cardLayout.show(mainContentPanel, "PaymentGateway");
        });
        quickActionsPanel.add(paymentGatewayButton);

        JButton allProductsButton = createStyledButton("All Products");
        allProductsButton.addActionListener(e -> {
            currentPanel = "AllProducts";
            cardLayout.show(mainContentPanel, "AllProducts");
        });
        quickActionsPanel.add(allProductsButton);

        JButton allUsersButton = createStyledButton("All Users");
        allUsersButton.addActionListener(e -> {
            currentPanel = "AllUsers";
            cardLayout.show(mainContentPanel, "AllUsers");
        });
        quickActionsPanel.add(allUsersButton);

        dashboardPanel.add(quickActionsPanel, BorderLayout.CENTER);

        return dashboardPanel;
    }

    private JPanel createSummaryCard(String title, double value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SIDEBAR_COLOR, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(SIDEBAR_COLOR);
        card.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel("₹" + String.format("%.2f", value), SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(SIDEBAR_COLOR);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(BUTTON_COLOR, 2));
        button.setPreferredSize(new Dimension(150, 40));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_COLOR);
            }
        });
        return button;
    }

    private JPanel createOrderManagementPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBackground(CONTENT_COLOR);
        orderPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Order Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        orderPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel filterPanel = new JPanel(new FlowLayout());
        filterPanel.setBackground(CONTENT_COLOR);

        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"All", "Placed", "Packed", "Shipped", "Delivered"});
        statusCombo.setFont(FIELD_FONT);
        JTextField startDateField = new JTextField("YYYY-MM-DD", 10);
        startDateField.setFont(FIELD_FONT);
        startDateField.setEnabled(true);
        JTextField endDateField = new JTextField("YYYY-MM-DD", 10);
        endDateField.setFont(FIELD_FONT);
        endDateField.setEnabled(true);
        JButton filterButton = createStyledButton("Filter");

        filterPanel.add(new JLabel("Status:"));
        filterPanel.add(statusCombo);
        filterPanel.add(new JLabel("Start Date:"));
        filterPanel.add(startDateField);
        filterPanel.add(new JLabel("End Date:"));
        filterPanel.add(endDateField);
        filterPanel.add(filterButton);

        JTable orderTable = new JTable();
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<OrderDTO> orders = orderDAO.getOrdersWithFilters("", "", "");
            orderTable.setModel(ListTableModel.fromOrders(orders, OrderDAO.ORDER_COLUMNS));
            orderTable.setRowHeight(25);
            orderTable.setGridColor(SIDEBAR_COLOR);
            orderTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load orders: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        filterButton.addActionListener(e -> {
            String status = statusCombo.getSelectedItem().toString().equals("All") ? "" : statusCombo.getSelectedItem().toString();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            try {
                OrderDAO orderDAO = new OrderDAO();
                List<OrderDTO> orders = orderDAO.getOrdersWithFilters(status, startDate, endDate);
                orderTable.setModel(ListTableModel.fromOrders(orders, OrderDAO.ORDER_COLUMNS));
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to filter orders: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderPanel.add(scrollPane, BorderLayout.CENTER);
        orderPanel.add(filterPanel, BorderLayout.NORTH);

        return orderPanel;
    }

    private JPanel createOrderStatusPanel() {
        JPanel orderStatusPanel = new JPanel(new BorderLayout());
        orderStatusPanel.setBackground(CONTENT_COLOR);
        orderStatusPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Order Status");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        orderStatusPanel.add(titleLabel, BorderLayout.NORTH);

        JTable orderTable = new JTable();
        try {
            OrderDAO orderDAO = new OrderDAO();
            List<OrderDTO> orders = orderDAO.getOrdersByUser("", "Admin"); // Fetch all orders for admin
            orderTable.setModel(ListTableModel.fromOrders(orders, OrderDAO.ORDER_COLUMNS));
            orderTable.setRowHeight(25);
            orderTable.setGridColor(SIDEBAR_COLOR);
            orderTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load orders: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(orderTable);
        orderStatusPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new FlowLayout());
        statusPanel.setBackground(CONTENT_COLOR);
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Placed", "Packed", "Shipped", "Delivered"});
        statusCombo.setFont(FIELD_FONT);
        JButton updateStatusButton = createStyledButton("Update Status");
        updateStatusButton.addActionListener(e -> {
            int row = orderTable.getSelectedRow();
            if (row >= 0) {
                String orderId = orderTable.getValueAt(row, 0).toString();
                String buyerId = orderTable.getValueAt(row, 1).toString();
                String farmerId = orderTable.getValueAt(row, 2).toString(); // Assuming farmer_id can be fetched via product_id if needed
                try {
                    OrderDAO orderDAO = new OrderDAO();
                    String newStatus = (String) statusCombo.getSelectedItem();
                    orderDAO.updateStatus(orderId, newStatus);
                    NotificationService notificationService = new NotificationService();
                    notificationService.notifyOrderStatusUpdate(farmerId, buyerId, orderId, newStatus);
                    List<OrderDTO> orders = orderDAO.getOrdersByUser("", "Admin");
                    orderTable.setModel(ListTableModel.fromOrders(orders, OrderDAO.ORDER_COLUMNS));
                    JOptionPane.showMessageDialog(this, "Status updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Failed to update status: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select an order to update!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        statusPanel.add(new JLabel("Change Status:"));
        statusPanel.add(statusCombo);
        statusPanel.add(updateStatusButton);
        orderStatusPanel.add(statusPanel, BorderLayout.SOUTH);

        return orderStatusPanel;
    }

    private JPanel createPaymentGatewayPanel() {
        JPanel paymentPanel = new JPanel(new BorderLayout());
        paymentPanel.setBackground(CONTENT_COLOR);
        paymentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Platform Fee Transactions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        paymentPanel.add(titleLabel, BorderLayout.NORTH);

        JTable feeTable = new JTable();
        try {
            PaymentGateway paymentGateway = new PaymentGateway();
            List<PlatformFeeDTO> fees = paymentGateway.getPlatformFees();
            feeTable.setModel(ListTableModel.fromPlatformFees(fees, PLATFORM_FEE_COLUMNS));
            feeTable.setRowHeight(25);
            feeTable.setGridColor(SIDEBAR_COLOR);
            feeTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load platform fee transactions: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(feeTable);
        paymentPanel.add(scrollPane, BorderLayout.CENTER);

        return paymentPanel;
    }

    private JPanel createAllProductsPanel() {
        JPanel productsPanel = new JPanel(new BorderLayout());
        productsPanel.setBackground(CONTENT_COLOR);
        productsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("All Products");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        productsPanel.add(titleLabel, BorderLayout.NORTH);

        JTable productsTable = new JTable();
        try {
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> products = productDAO.getProducts("", 0, Double.MAX_VALUE, "name ASC");
            productsTable.setModel(ListTableModel.fromProducts(products, ProductDAO.PRODUCT_COLUMNS));
            productsTable.setRowHeight(25);
            productsTable.setGridColor(SIDEBAR_COLOR);
            productsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load products: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(productsTable);
        productsPanel.add(scrollPane, BorderLayout.CENTER);

        return productsPanel;
    }

    private JPanel createAllUsersPanel() {
        JPanel usersPanel = new JPanel(new BorderLayout());
        usersPanel.setBackground(CONTENT_COLOR);
        usersPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("All Users");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(SIDEBAR_COLOR);
        usersPanel.add(titleLabel, BorderLayout.NORTH);

        JTable usersTable = new JTable();
        try {
            UserDAO userDAO = new UserDAO();
            List<UserDTO> users = userDAO.getAllUsers();
            usersTable.setModel(ListTableModel.fromUsers(users, USER_COLUMNS));
            usersTable.setRowHeight(25);
            usersTable.setGridColor(SIDEBAR_COLOR);
            usersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load users: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JScrollPane scrollPane = new JScrollPane(usersTable);
        usersPanel.add(scrollPane, BorderLayout.CENTER);

        return usersPanel;
    }
}

class ListTableModel extends AbstractTableModel {
    private List<Object[]> rows;
    private String[] columnNames;

    ListTableModel(List<Object[]> rows, String[] columnNames) {
        this.rows = rows;
        this.columnNames = columnNames;
    }

    public static ListTableModel fromProducts(List<ProductDTO> products, String[] columnNames) {
        List<Object[]> rows = new ArrayList<>();
        for (ProductDTO product : products) {
            rows.add(product.toRow());
        }
        return new ListTableModel(rows, columnNames);
    }

    public static ListTableModel fromOrders(List<OrderDTO> orders, String[] columnNames) {
        List<Object[]> rows = new ArrayList<>();
        for (OrderDTO order : orders) {
            rows.add(order.toRow());
        }
        return new ListTableModel(rows, columnNames);
    }

    public static ListTableModel fromNegotiations(List<NegotiationDTO> negotiations, String[] columnNames) {
        List<Object[]> rows = new ArrayList<>();
        for (NegotiationDTO negotiation : negotiations) {
            rows.add(negotiation.toRow());
        }
        return new ListTableModel(rows, columnNames);
    }

    public static ListTableModel fromPlatformFees(List<PlatformFeeDTO> fees, String[] columnNames) {
        List<Object[]> rows = new ArrayList<>();
        for (PlatformFeeDTO fee : fees) {
            rows.add(fee.toRow());
        }
        return new ListTableModel(rows, columnNames);
    }

    public static ListTableModel fromUsers(List<UserDTO> users, String[] columnNames) {
        List<Object[]> rows = new ArrayList<>();
        for (UserDTO user : users) {
            rows.add(user.toRow());
        }
        return new ListTableModel(rows, columnNames);
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows.get(rowIndex)[columnIndex];
    }
}

public class KisaanBazaar {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}