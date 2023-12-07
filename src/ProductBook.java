import java.util.HashMap;

public class ProductBook {
    private final String product;
    private final ProductBookSide buySide;
    private final ProductBookSide sellSide;

    public ProductBook(String product) {
        // Validate and set product
        if (product == null || product.length() < 1 || product.length() > 5 || !product.matches("[A-Za-z0-9.]+")) {
            throw new IllegalArgumentException("Invalid product symbol");
        }
        this.product = product;

        // Create and set buySide and sellSide
        this.buySide = new ProductBookSide(
                ProductBookSide.BookSide.BUY);
        this.sellSide = new ProductBookSide(
                ProductBookSide.BookSide.SELL);
    }

    public OrderDTO add(Order o) {
        if (o.getSide() == BookSide.BUY) {
            System.out.println("ADD:" + " " + o.getSide() + " " + o);
            OrderDTO add = buySide.add(o);
            tryTrade();
            return add;
        } else {
            System.out.println("ADD:" + " " + o.getSide() + " " + o);
            OrderDTO add = sellSide.add(o);
            tryTrade();
            return add;
        }
       /* ProductBookSide side = o.getSide() == BookSide.BUY ? buySide : sellSide;
        OrderDTO orderDTO = side.add(o);
        tryTrade();
        return orderDTO;*/
    }

    public OrderDTO cancel(BookSide side, String orderId) {
        if (side == BookSide.BUY) {
            System.out.println("CANCEL:" + " " + side + " " + orderId);
            return buySide.cancel(orderId);
        } else {
            System.out.println("CANCEL:" + " " + side + " " + orderId);
            return sellSide.cancel(orderId);
        }
    }

    public void tryTrade() {
        // Get Top BUY Price
        Price topBuyPrice = buySide.topOfBookPrice();

        // Get Top SELL Price
        Price topSellPrice = sellSide.topOfBookPrice();

        // While top BUY is not null, and top SELL price is not null, and top BUY price is >= top SELL price
        while (topBuyPrice != null && topSellPrice != null && topBuyPrice.compareTo(topSellPrice) >= 0) {
            // Get Top BUY Volume
            int topBuyVolume = buySide.topOfBookVolume();

            // Get Top SELL Volume
            int topSellVolume = sellSide.topOfBookVolume();

            // Volume to trade is the MIN of the Buy and Sell volumes
            int volumeToTrade = Math.min(topBuyVolume, topSellVolume);

            // Call tradeOut on the SELL side with the top Sell price and the volume to trade
            sellSide.tradeOut(topSellPrice, volumeToTrade);

            // Call tradeOut on the BUY side with the top Buy price and the volume to trade
            buySide.tradeOut(topBuyPrice, volumeToTrade);

            // Get Top BUY Price
            topBuyPrice = buySide.topOfBookPrice();

            // Get Top SELL Price
            topSellPrice = sellSide.topOfBookPrice();
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Product: %s\n", product));
        sb.append(buySide.toString());
        sb.append(sellSide.toString());
        return sb.toString();
    }
}
