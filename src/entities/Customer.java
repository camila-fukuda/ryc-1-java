package entities;

import data.BranchData;

import java.util.Objects;
import java.util.Random;

public class Customer {
    private final String name;
    private final String document;
    private final String code;

    public Customer(String name, String document) {
        this.name = name;
        this.document = document;
        this.code = getRandomCode();
    }

    private static String getRandomCode() {
        String code = generateRandomCode();
        while (BranchData.containsCode(code)) {
            code = generateRandomCode();
        }
        return code;
    }

    private static String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return "CS-" + String.format("%05d", randomNumber);
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

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
