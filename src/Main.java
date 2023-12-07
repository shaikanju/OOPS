/*public class Main {
    public static void main(String[] args) throws InvalidPriceOperation {
        // Create Price objects using PriceFactory
        Price p1 = PriceFactory.makePrice(5000); // $50.00
        Price p2 = PriceFactory.makePrice("$12.34");
        Price p3 = PriceFactory.makePrice("0.75");
        Price p4 = PriceFactory.makePrice("12345.67");
        Price p5 = PriceFactory.makePrice("1,234,567.89");
        Price p6 = PriceFactory.makePrice("12");
        Price p7 = PriceFactory.makePrice(".89");
        Price p8 = PriceFactory.makePrice("-12.85");
        Price p9 = PriceFactory.makePrice("-$0.75");
        Price p10 = PriceFactory.makePrice("-1,234,567.89");
        Price p11 = PriceFactory.makePrice("-1,234,567.89456");


        // Verify the price values
        System.out.println(p1.toString()); // Expected output: $50.00
        System.out.println(p2.toString()); // Expected output: $12.34
        System.out.println(p3.toString()); // Expected output: $0.75
        System.out.println(p4.toString()); // Expected output: $12345.67
        System.out.println(p5.toString()); // Expected output: $1234567.89
        System.out.println(p6.toString()); // Expected output: $12.00
        System.out.println(p7.toString()); // Expected output: $0.89
        System.out.println(p8.toString()); // Expected output: -$12.85
        System.out.println(p9.toString()); // Expected output: -$0.75
        System.out.println(p10.toString()); // Expected output: -$1234567.89
        System.out.println(p11.toString()); // Expected output: -$1234567.89

        // Test Price methods
        System.out.println(p1.add(p2).toString()); // Test addition
        System.out.println(p1.subtract(p2).toString()); // Test subtraction
        System.out.println(p1.multiply(2).toString()); // Test multiplication
        System.out.println(p1.greaterOrEqual(p2)); // Test greaterOrEqual
        System.out.println(p1.lessOrEqual(p2)); // Test lessOrEqual
        System.out.println(p1.greaterThan(p2)); // Test greaterThan
        System.out.println(p1.lessThan(p2)); // Test lessThan
        System.out.println(p1.equals(p2)); // Test equals
        System.out.println(p1.compareTo(p2)); // Test compareTo
        System.out.println(p1.hashCode()); // Test hashCode
    }
}*/
public class Main {
    public static void main(String[] args) {

        TrafficSim.runSim();
    }}
        /*
        ProductBook pb = new ProductBook("TGT");
        System.out.println("1) --------------------------------------------------------------");
        Order o1 = new Order("AAA", "TGT", PriceFactory.makePrice(1000), 50, BookSide.BUY);
        pb.add(o1);
        System.out.println(pb);
        System.out.println("2) --------------------------------------------------------------");
        Order o2 = new Order("BBB", "TGT", PriceFactory.makePrice(1000), 60, BookSide.BUY);
        pb.add(o2);
        System.out.println(pb);
        System.out.println("3) --------------------------------------------------------------");
        Order o3 = new Order("CCC", "TGT", PriceFactory.makePrice(995), 70, BookSide.BUY);
        pb.add(o3);
        System.out.println(pb);
        System.out.println("4) --------------------------------------------------------------");
        Order o4 = new Order("DDD", "TGT", PriceFactory.makePrice(990), 25, BookSide.BUY);
        pb.add(o4);
        System.out.println(pb);
        System.out.println("5) --------------------------------------------------------------");
        Order o5 = new Order("EEE", "TGT", PriceFactory.makePrice(1010), 120, BookSide.SELL);
        pb.add(o5);
        System.out.println(pb);
        System.out.println("6) --------------------------------------------------------------");
        Order o6 = new Order("EEE", "TGT", PriceFactory.makePrice(1020), 45, BookSide.SELL);
        pb.add(o6);
        System.out.println(pb);
        System.out.println("7) --------------------------------------------------------------");
        Order o7 = new Order("FFF", "TGT", PriceFactory.makePrice(1025), 90, BookSide.SELL);
        pb.add(o7);
        System.out.println(pb);
        System.out.println("8) --------------------------------------------------------------");
        Order o8 = new Order("AAA", "TGT", PriceFactory.makePrice(1000), 200, BookSide.SELL);
        pb.add(o8);
        System.out.println(pb);
        System.out.println("9) --------------------------------------------------------------");
        Order o9 = new Order("BBB", "TGT", PriceFactory.makePrice(1010), 200, BookSide.BUY);
        pb.add(o9);
        System.out.println(pb);
        System.out.println("10) --------------------------------------------------------------");
        pb.cancel(BookSide.SELL, o6.getId());
        System.out.println(pb);
        System.out.println("11) --------------------------------------------------------------");
        Order o10 = new Order("CCC", "TGT", PriceFactory.makePrice(990), 95, BookSide.SELL);
        pb.add(o10);
        System.out.println(pb);
        System.out.println("12) --------------------------------------------------------------");
        Order o11 = new Order("DDD", "TGT", PriceFactory.makePrice(1025), 100, BookSide.BUY);
        pb.add(o11);
        System.out.println(pb);*/
