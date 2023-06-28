package MyProduct;

public class Sim extends Product {
    private int number;
    private String provider;

    public Sim(String name, float price, int quantity, int number, String provider) {
        super(name, price, quantity);
        this.number = number;
        this.provider = provider;
    }

    public Sim() {
    }

    @Override
    public String toString() {
        return String.format("%s%45s%15s", super.toString(), number, provider);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
