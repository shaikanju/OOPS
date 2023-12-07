import java.util.HashMap;

public class User {
    private String userId;
    private HashMap<String, OrderDTO> orders;

    // Constructor
    public User(String userId) {
        setUserId(userId);
        this.orders = new HashMap<>();
    }

    // Accessor for userId
    public String getUserId() {
        return userId;
    }

    // Private modifier for userId
    private void setUserId(String userId) {
        // Check if userId meets the specified requirements
        if (userId != null && userId.matches("^[a-zA-Z]{3}$")) {
            this.userId = userId;
        } else {
            throw new IllegalArgumentException("Invalid userId");
        }
    }

    // Add an order to the user's order hashmap
    public void addOrder(OrderDTO order) {
        if (order != null) {
            orders.put(order.id, order);
        }
    }

    // Check if the user has any order with remaining quantity
    public boolean hasOrderWithRemainingQty() {
        for (OrderDTO order : orders.values()) {
            if (order.remainingVolume > 0) {
                return true;
            }
        }
        return false;
    }

    // Get any order with remaining quantity
    public OrderDTO getOrderWithRemainingQty() {
        for (OrderDTO order : orders.values()) {
            if (order.remainingVolume > 0) {
                return order;
            }
        }
        return null;
    }

    // Override toString method
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("User Id: " + userId + "\n");

        for (OrderDTO order : orders.values()) {
            result.append(order.toString()).append("\n");
        }

        return result.toString();
    }
}
