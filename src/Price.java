public class Price implements Comparable<Price> {

    private final int priceInCents;


    public Price(int priceInCents) {
        this.priceInCents = priceInCents;
    }

    public boolean isNegative() {

        return priceInCents < 0;
    }

    public Price add(Price p) throws InvalidPriceOperation {
        if (p == null) throw new InvalidPriceOperation("Invalid operation: Price is null");
        return new Price(this.priceInCents + p.priceInCents);
    }

    public Price subtract(Price p) throws InvalidPriceOperation {
        if (p == null) throw new InvalidPriceOperation("Invalid operation: Price is null");
        return new Price(this.priceInCents - p.priceInCents);
    }

    public Price multiply(int n) {
        return new Price(this.priceInCents * n);
    }

    public boolean greaterOrEqual(Price p) throws InvalidPriceOperation {
        if (p == null) throw new InvalidPriceOperation("Invalid operation: Price is null");
        return this.priceInCents >= p.priceInCents;
    }

    public boolean lessOrEqual(Price p) throws InvalidPriceOperation {
        if (p == null) throw new InvalidPriceOperation("Invalid operation: Price is null");
        return this.priceInCents <= p.priceInCents;
    }

    public boolean greaterThan(Price p) throws InvalidPriceOperation {
        if (p == null) throw new InvalidPriceOperation("Invalid operation: Price is null");
        return this.priceInCents > p.priceInCents;
    }

    public boolean lessThan(Price p) throws InvalidPriceOperation {
        if (p == null) throw new InvalidPriceOperation("Invalid operation: Price is null");
        return this.priceInCents < p.priceInCents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price = (Price) o;
        return priceInCents == price.priceInCents;
    }

    @Override
    public int compareTo( Price p) {
        return Double.compare((double) this.priceInCents, (double) p.priceInCents);
    }

    @Override
    public String toString() {
        int dollars = Math.abs(priceInCents / 100);
        int cents = Math.abs(priceInCents % 100);
        if (isNegative())
        {return String.format("-$%d.%02d", dollars , cents );}
        else
            return String.format("$%d.%02d", dollars , cents );
    }

    @Override
    public int hashCode() {
        return Double.hashCode(priceInCents);
    }
}
