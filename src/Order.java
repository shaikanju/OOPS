import java.util.ArrayList;

import java.util.HashMap;


public class Order {


    private final String user;
    private final String product;
    private final Price price;
    private final BookSide side;
    private  String id;
    private final int originalVolume;
    private int remainingVolume;
    private int cancelledVolume;
    private int filledVolume;

    public Order(String user, String product, Price price, int originalVolume,BookSide side) {
        validateUser(user);
        validateProduct(product);
        validateOriginalVolume(originalVolume);

        this.user = user;
        this.product = product;
        this.price = price;
        this.side = side;
        this.id = generateOrderId();
        this.originalVolume = originalVolume;

        this.remainingVolume = originalVolume;
        this.cancelledVolume = 0;
        this.filledVolume = 0;
    }

    private void validateUser(String user) {
        if (user == null || user.length() != 3 || !user.matches("[A-Z]+")) {
            throw new IllegalArgumentException("Invalid user code");
        }
    }

    private void validateProduct(String product) {
        if (product == null || product.length() < 1 || product.length() > 5 ||
                !product.matches("[A-Z0-9.]+")) {
            throw new IllegalArgumentException("Invalid product symbol");
        }
    }

    private void validateOriginalVolume(int originalVolume) {
        if (originalVolume <= 0 || originalVolume >= 10000) {
            throw new IllegalArgumentException("Original volume must be greater than 0 and less than 10,000");
        }
    }

    private String generateOrderId() {
        return user + product + price.toString() + System.nanoTime();
    }

    public String getUser() {
        return user;
    }

    public String getProduct() {
        return product;
    }
    public void setID(){
        id = user + product + price.toString() + System.nanoTime();

    }

    public Price getPrice() {
        return price;
    }

    public BookSide getSide() {
        return side;
    }

    public String getId() {
        return id;
    }

    public int getOriginalVolume() {
        return originalVolume;
    }

    public int getRemainingVolume() {
        return remainingVolume;
    }

    public void setRemainingVolume(int remainingVolume) {
        if (remainingVolume < 0 || remainingVolume > originalVolume) {
            throw new IllegalArgumentException("Remaining volume must be between 0 and the original volume");
        }
        this.remainingVolume = remainingVolume;
    }

    public int getCancelledVolume() {
        return cancelledVolume;
    }

    public void setCancelledVolume(int cancelledVolume) {
        if (cancelledVolume < 0 || cancelledVolume > originalVolume - filledVolume) {
            throw new IllegalArgumentException("Cancelled volume must be between 0 and (original volume - filled volume)");
        }
        this.cancelledVolume = cancelledVolume;
    }

    public int getFilledVolume() {
        return filledVolume;
    }

    public void setFilledVolume(int filledVolume) {
        if (filledVolume < 0 || filledVolume > originalVolume - cancelledVolume) {
            throw new IllegalArgumentException("Filled volume must be between 0 and (original volume - cancelled volume)");
        }
        this.filledVolume = filledVolume;
    }

    @Override
    public String toString() {
        return String.format("Product: %s, Price: %s, OriginalVolume: %d, RemainingVolume: %d, CancelledVolume: %d, FilledVolume: %d, User: %s, Side: %s, ID: %s",
                product, price, originalVolume, remainingVolume, cancelledVolume, filledVolume, user, side, id);
    }
    public OrderDTO makeTradableDTO() {
        return new OrderDTO(
                getUser(),
                getProduct(),
                getPrice(),
                getSide(),
                getId(),
                getOriginalVolume(),
                getRemainingVolume(),
                getCancelledVolume(),
                getFilledVolume()
        );
    }
}

