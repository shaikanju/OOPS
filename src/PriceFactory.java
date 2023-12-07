/*
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PriceFactory {

    public static Price makePrice(int value) {
        return new Price(value);
    }

    public static Price makePrice(String stringValueIn) {
        int cents = parseStringValue(stringValueIn);
        return new Price(cents);
    }

    private static int parseStringValue(String stringValue) {
        // Removing commas and dollar signs if present
        String cleanedString = stringValue.replaceAll("[,$]", "");

        // Extracting the numerical value
        Pattern pattern = Pattern.compile("(-?\\d*)\\.?(\\d{0,2})?");
        Matcher matcher = pattern.matcher(cleanedString);

        if (matcher.find()) {
            int dollars = 0;
            if (!matcher.group(1).isEmpty()) {
                dollars = Integer.parseInt(matcher.group(1));
            }
            int cents = 0;
            if (matcher.groupCount() == 2 && matcher.group(2).length() > 0) {
                String centsString = matcher.group(2);
                if (centsString.length() == 1) {
                    cents = Integer.parseInt(centsString) * 10;
                } else if (centsString.length() >= 2) {
                    cents = Integer.parseInt(centsString.substring(0, 2));
                }
            }
            return dollars * 100 + cents;
        } else {
            throw new IllegalArgumentException("Invalid price format: " + stringValue);
        }
    }




}
*/
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class PriceFactory {
    private static final HashMap<Integer, Price> priceCache = new HashMap<>();

    public static Price makePrice(int value) {
        int cents = value;
        return getOrCreatePrice(cents);
    }

    public static Price makePrice(String stringValueIn) {
        int cents = parseStringValue(stringValueIn);
        return getOrCreatePrice(cents);
    }
    private static Price getOrCreatePrice(int cents) {
        if (priceCache.containsKey(cents)) {
            return priceCache.get(cents);
        } else {
            Price newPrice = new Price(cents);
            priceCache.put(cents, newPrice);
            return newPrice;
        }
    }

    private static int parseStringValue(String stringValue) {
        if (stringValue == null || stringValue.isEmpty()) {
            throw new IllegalArgumentException("Invalid price format: " + stringValue);
        }
        boolean isNegative = false;
        if (stringValue.startsWith("-")) {
            isNegative = true;
            stringValue = stringValue.replace("-", "");
        }

        String cleanedString = stringValue.replace("$", "").replace(",", "");

        int dollars;
        int cents;

        int dotIndex = cleanedString.indexOf('.');
        if (dotIndex == -1) {
            dollars = Integer.parseInt(cleanedString);
            cents = 0;
        }

        else {
            if (dotIndex == 0) {
                dollars = 0;
                String centsStr = cleanedString.substring(dotIndex + 1);
                if (centsStr.length() == 1) {
                    cents = Integer.parseInt(centsStr) * 10;
                } else if (centsStr.length() >= 2) {
                    cents = Integer.parseInt(centsStr.substring(0, 2));
                } else {
                    cents = 0;
                }

            } else {
                dollars = Integer.parseInt(cleanedString.substring(0, dotIndex));
                String centsStr = cleanedString.substring(dotIndex + 1);
                if (centsStr.length() == 1) {
                    cents = Integer.parseInt(centsStr) * 10;
                } else if (centsStr.length() >= 2) {
                    cents = Integer.parseInt(centsStr.substring(0, 2));
                } else {
                    cents = 0;
                }
            }
        }

        if (isNegative) {
            return -1 * (dollars * 100 + cents);
        } else {
            return dollars * 100 + cents;
        }
    }

}
