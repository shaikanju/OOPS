public class OrderDTO {
    public final String user;
    public final String product;
    public final Price price;
    public final BookSide side;
    public final String id;
    public final int originalVolume;
    public final int remainingVolume;
    public final int cancelledVolume;
    public final int filledVolume;

    // Constructor accepting and setting all the data fields
    public OrderDTO(String user, String product, Price price, BookSide side,
                    String id, int originalVolume, int remainingVolume,
                    int cancelledVolume, int filledVolume) {
        this.user = user;
        this.product = product;
        this.price = price;
        this.side = side;
        this.id = id;
        this.originalVolume = originalVolume;
        this.remainingVolume = remainingVolume;
        this.cancelledVolume = cancelledVolume;
        this.filledVolume = filledVolume;
    }

    // Override the toString method
    @Override
    public String toString() {
        return String.format("Product: %s, Price: %s, OriginalVolume: %d, RemainingVolume: %d, CancelledVolume: %d, FilledVolume: %d, User: %s, Side: %s, ID: %s",
                product, price, originalVolume, remainingVolume, cancelledVolume, filledVolume, user, side, id);
    }
}
