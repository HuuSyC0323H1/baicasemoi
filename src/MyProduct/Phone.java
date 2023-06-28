package MyProduct;

import java.io.Serializable;

public class Phone extends Product  implements Serializable {
    private String describe;
    private String color;

    public Phone() {
    }

    public Phone(String name, float price, int quantity, String describe, String color) {
        super(name, price, quantity);
        this.describe = describe;
        this.color = color;
    }

    public Phone(String describe, String color) {
        this.describe = describe;
        this.color = color;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescription(String describe) {
        this.describe = describe;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s%14.2s%15s",super.toString(),describe,color);
    }
}
