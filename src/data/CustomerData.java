package data;

import entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerData {
    static public List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer("JOAO", "101"));
        customers.add(new Customer("MARIA", "102"));
        customers.add(new Customer("PEDRO", "103"));
        customers.add(new Customer("MARIA", "104"));
    }


    static public List<Customer> getAll() {
        return customers;
    }

    static public void add(Customer customer) {
        customers.add(customer);
    }

    static public List<Customer> getCustomerByName(String name) {
        return customers.stream()
                .filter(customer -> customer.getName().equals(name))
                .collect(Collectors.toList());
    }

    static public Customer getCustomerByDocument(String document) {
        return customers.stream()
                .filter(customer -> customer.getDocument().equals(document))
                .findFirst()
                .orElse(null);
    }

}
