import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserManager {
    private static UserManager instance;
    private Map<String, User> users;

    // Private constructor for Singleton pattern
    private UserManager() {
        this.users = new HashMap<>();
    }

    // Public method to get the singleton instance
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // Initialize users based on the provided array of user ids
    public void init(String[] usersIn) {
        for (String userId : usersIn) {
            User user = new User(userId);
            users.put(userId, user);
        }
    }

    // Get a randomly selected User from the collection
    public User getRandomUser() {
        if (users.isEmpty()) {
            return null;
        }

        // Get a random index
        int randomIndex = new Random().nextInt(users.size());

        // Get a random user by iterating through the values
        return users.values().toArray(new User[0])[randomIndex];
    }

    // Add an OrderDTO to the specified User
    public void addToUser(String userId, OrderDTO o) {
        User user = users.get(userId);
        if (user != null) {
            user.addOrder(o);
        }
    }

    // Override toString method
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (User user : users.values()) {
            result.append(user.toString()).append("\n");
        }

        return result.toString();
    }
}
