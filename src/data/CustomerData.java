package data;

import entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerData {
    static public List<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer("JOAO", "DOC101"));
        customers.add(new Customer("MARIA", "DOC102"));
        customers.add(new Customer("PEDRO", "DOC103"));
        customers.add(new Customer("MARIA", "DOC104"));
        customers.add(new Customer("CAMILA", "DOC105"));
    }


    static public List<Customer> getAll() {
        return customers;
    }

    static public void add(Customer customer) {
        customers.add(customer);
    }

    static public List<Customer> getCustomerByName(String name) {
        return customers.stream()
                .filter(customer -> customer.name().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    static public Customer getCustomerByDocument(String document) {
        return customers.stream()
                .filter(customer -> customer.document().equalsIgnoreCase(document))
                .findFirst()
                .orElse(null);
    }

}
