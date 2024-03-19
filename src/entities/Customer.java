package entities;

import java.util.Objects;

public class Customer {
    private final String name;
    private final String document;

    public Customer(String name, String document) {
        this.name = name;
        this.document = document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(document, customer.document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, document);
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
    
}
