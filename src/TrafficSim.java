import java.util.HashMap;
import java.util.Map;

public class TrafficSim {
    private static final int NUM_USERS = 5;
    private static final int NUM_PRODUCTS = 4;
    private static final int NUM_ORDERS = 40;

    private static final HashMap<String, Double> basePrices = new HashMap<>();

    public static void runSim() {
        UserManager userManager = UserManager.getInstance();
        ProductManager productManager = ProductManager.getInstance();

        // Initialize users
        String[] userIDs = {"ANN", "BOB", "CAT", "DOG", "EGG"};
        userManager.init(userIDs);

        // Initialize products
        String[] productSymbols = {"WMT", "TGT", "AMZN", "TSLA"};
        for (String productSymbol : productSymbols) {
            productManager.addProduct(productSymbol);
        }

        // Initialize base prices
        basePrices.put("WMT", 140.98);
        basePrices.put("TGT", 174.76);
        basePrices.put("AMZN", 102.11);
        basePrices.put("TSLA", 196.81);

        // Run simulation
        for (int i = 0; i < NUM_ORDERS; i++) {
            User randomUser = userManager.getRandomUser();

            if (Math.random() < 0.9) {
                String randomProductBook = productManager.getRandomProduct();
                BookSide randomSide = (Math.random() < 0.5) ? BookSide.BUY : BookSide.SELL;
                int volume = (int) (25 + (Math.random() * 300));
                volume = (int) Math.round(volume / 5.0) * 5;

                Price price = getPrice(randomProductBook, randomSide);
                System.out.print((i+1 ) + "\t");
                Order order = new Order(randomUser.getUserId(), randomProductBook, price, volume, randomSide);

                OrderDTO orderDTO = productManager.addOrder(order);

                randomUser.addOrder(orderDTO);}

                else{ if(randomUser.hasOrderWithRemainingQty() ) {

                    OrderDTO randomOrderDTO = randomUser.getOrderWithRemainingQty();
                    System.out.print((i +1) + "\t");
                    OrderDTO cancelledOrderDTO = productManager.cancel(randomOrderDTO);
                    if (cancelledOrderDTO != null) {
                        randomUser.addOrder(cancelledOrderDTO);
                    }}
                }

        }

        // Print results
        System.out.println("\n\nProduct Manager:\n" + productManager);
        System.out.println("\n\nUser Manager:\n" + userManager);
    }

    private static Price getPrice(String symbol, BookSide side) {
        double basePrice = basePrices.get(symbol);

        // Declare variables for price calculation
        double priceWidth = 0.02;
        double startPoint = 0.01;
        double tickSize = 0.1;

        // Calculate variance from the base price
        double gapFromBase = basePrice * priceWidth;
        double priceVariance = gapFromBase * (Math.random());

        // Calculate price value to use
        double priceToUse;
        double priceToTick;

        if (side == BookSide.BUY) {
            priceToUse = basePrice * (1 - startPoint);
            priceToUse += priceVariance;
            priceToTick = Math.round(priceToUse * 1 / tickSize) / 20.0;
        } else { // side == BookSide.SELL
            priceToUse = basePrice * (1 + startPoint);
            priceToUse -= priceVariance;
            priceToTick = Math.round(priceToUse * 1 / tickSize) / 20.0;
        }

        // Use PriceFactory to create a Price object
        return PriceFactory.makePrice((int) priceToTick);
    }



}
