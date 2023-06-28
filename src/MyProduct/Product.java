package MyProduct;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Comparable<Product>,Serializable{
    static int count = 1;
    private String id;
    private String name;
    private float price;
    private int quantity;

    public Product(String name, float price, int quantity) {
        this.id = String.valueOf(count++);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && Float.compare(product.price, price) == 0 && quantity == product.quantity && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }

    @Override
    public String toString() {
        return String.format("%s%18s%16.2f%14s",id,name,price,quantity);
    }

    @Override
    public int compareTo(Product o) {
        if (this.price - o.price == 0){
            return this.quantity - o.quantity;
        }
        return (int) (this.price - o.price);
    }
}
