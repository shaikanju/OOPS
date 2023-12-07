import java.util.*;

public class ProductBookSide {
    public enum BookSide {
        BUY,
        SELL
    }

    private final BookSide side;
    private final HashMap<Price, ArrayList<Order>> bookEntries;

    public ProductBookSide(BookSide side) {
        this.side = side;
        this.bookEntries = new HashMap<>();
    }

    public void tradeOut(Price price, int vol) {
        ArrayList<Order> ordersAtPrice = bookEntries.get(price);

        if (ordersAtPrice == null || ordersAtPrice.isEmpty()) {
            return;
        }

        int remainingVol = vol;
        for (int i = 0; i < ordersAtPrice.size() && remainingVol > 0; i++) {
            Order order = ordersAtPrice.get(i);
            int orderRemainingVol = order.getRemainingVolume();

            if (orderRemainingVol <= remainingVol) {
                ordersAtPrice.remove(i);
                order.setFilledVolume(order.getFilledVolume() + orderRemainingVol);
                order.setRemainingVolume(0);

                // Print FILL message
                System.out.println("FILL: (" + order.getSide() + " " + orderRemainingVol + ")  " + order.getUser()+"order: "+
                         order.getSide()+" " + order.getProduct() +"at"+ price +
                        ", Orig Vol: " + order.getOriginalVolume() +
                        ", Rem Vol: " + order.getRemainingVolume() +
                        ", Fill Vol: " + order.getFilledVolume() +
                        ", CXL Vol: 0, ID: " + order.getId() );

                remainingVol -= orderRemainingVol;
                i--; // Adjust the index since we removed an element
            } else {
                order.setFilledVolume(order.getFilledVolume() + remainingVol);
                order.setRemainingVolume(orderRemainingVol - remainingVol);

                // Print PARTIAL FILL message
                System.out.println("PARTIAL FILL: (" + order.getSide() + " " + remainingVol + ") " +
                        order.getUser()+"order: "+  order.getSide() + " "+ order.getProduct() +"at " + price +
                        ", Orig Vol: " + order.getOriginalVolume() +
                        ", Rem Vol: " + order.getRemainingVolume() +
                        ", Fill Vol: " + order.getFilledVolume() +
                        ", CXL Vol: 0, ID: " + order.getId() );

                remainingVol = 0;
            }
        }

       /* for (int i = 0; i < ordersAtPrice.size() && remainingVol > 0; i++) {
            Order order = ordersAtPrice.get(i);
            int orderRemainingVol = order.getRemainingVolume();

            if (orderRemainingVol <= remainingVol) {
                ordersAtPrice.remove(i);
                order.setFilledVolume(order.getFilledVolume() + orderRemainingVol);
                order.setRemainingVolume(0);
                remainingVol -= orderRemainingVol;
                System.out.println("FILL message: " + order.getId() + " filled at price " + price + " with volume " + orderRemainingVol);
                i--; // Adjust the index since we removed an element
            } else {
                order.setFilledVolume(order.getFilledVolume() + remainingVol);
                order.setRemainingVolume(orderRemainingVol - remainingVol);
                System.out.println("PARTIAL FILL message: " + order.getId() + " partially filled at price " + price + " with volume " + remainingVol);
                remainingVol = 0;
            }
        }*/

        if (ordersAtPrice.isEmpty()) {
            bookEntries.remove(price);
        }
    }


    public OrderDTO add(Order order) {
        // Get the price of the order
        Price orderPrice = order.getPrice();

        // Check if there is an existing list of orders for this price
        ArrayList<Order> ordersAtPrice = bookEntries.get(orderPrice);

        // If there is no existing list, create a new one
        if (ordersAtPrice == null) {
            ordersAtPrice = new ArrayList<>();
            bookEntries.put(orderPrice, ordersAtPrice);
        }

        // Add the order to the list
        ordersAtPrice.add(order);



        // Return an OrderDTO for the added order
        return  order.makeTradableDTO();
    }



    public OrderDTO cancel(String orderId) {
        for (ArrayList<Order> ordersAtPrice : bookEntries.values()) {
            for (Order order : ordersAtPrice) {
                if (order.getId().equals(orderId)) {
                    int remainingVolume = order.getRemainingVolume();
                    int cancelledVolume = order.getCancelledVolume() + remainingVolume;

                    order.setCancelledVolume(cancelledVolume);
                    order.setRemainingVolume(0);

                    ordersAtPrice.remove(order);

                    // If the ArrayList is empty after removing the order, remove the price
                    if (ordersAtPrice.isEmpty()) {
                        bookEntries.remove(order.getPrice());
                    }

                    return  order.makeTradableDTO();
                }
            }
        }

        return null; // Order not found
    }

    public Price topOfBookPrice() {
        if (side == BookSide.BUY) {
            return bookEntries.keySet().stream().max(Price::compareTo).orElse(null);
        } else {
            return bookEntries.keySet().stream().min(Price::compareTo).orElse(null);
        }
    }

    public int topOfBookVolume() {
        Price topPrice = topOfBookPrice();
        if (topPrice != null) {
            return bookEntries.get(topPrice).stream().mapToInt(Order::getRemainingVolume).sum();
        }
        return 0;
    }

    // Implement the tradeOut method later

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Side: ").append(side).append("\n");

        List<Price> sortedPrices = new ArrayList<>(bookEntries.keySet());
        // Sort in descending order for BUY side
        if (side == BookSide.BUY) {
            sortedPrices.sort(Collections.reverseOrder());
        } else {
            // Sort in ascending order for SELL side
            Collections.sort(sortedPrices);
        }

        for (Price price : sortedPrices) {
            result.append("Price: ").append(price).append("\n");
            for (Order order : bookEntries.get(price)) {
                result.append(order.toString()).append("\n");
            }
        }

        return result.toString();
    }
}
