import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProductManager {
    private static ProductManager instance;
    private Map<String, ProductBook> productBooks;

    // Private constructor for Singleton pattern
    private ProductManager() {
        this.productBooks = new HashMap<>();
    }

    // Public method to get the singleton instance
    public static ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    // Add a new ProductBook for the given symbol
    public void addProduct(String symbol) {
        if (!productBooks.containsKey(symbol)) {
            ProductBook productBook = new ProductBook(symbol);
            productBooks.put(symbol, productBook);
        }
    }

    // Return a randomly selected product symbol
    public String getRandomProduct() {
        int randomIndex = new Random().nextInt(productBooks.size());

       // int randomIndex = (int) (Math.random() * productBooks.size());
        return productBooks.keySet().toArray(new String[0])[randomIndex];
    }

    // Add an order to the corresponding ProductBook
    public OrderDTO addOrder(Order order) {
        String productSymbol = order.getProduct();
        ProductBook productBook = productBooks.get(productSymbol);

        if (productBook != null) {
            return productBook.add(order);
        }

        return null; // Handle the case when the ProductBook doesn't exist
    }

    // Cancel an order in the corresponding ProductBook
    public OrderDTO cancel(OrderDTO orderDTO) {
        String productSymbol = orderDTO.product;
        ProductBook productBook = productBooks.get(productSymbol);

        if (productBook != null) {
            return productBook.cancel(orderDTO.side, orderDTO.id);
        } else {
            System.out.println("Failed to cancel order. ProductBook not found for symbol: " + productSymbol);
            return null;
        }
    }

    // Override toString method
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (ProductBook productBook : productBooks.values()) {
            result.append(productBook.toString()).append("\n");
        }

        return result.toString();
    }
}
