package test.data;

import data.CustomerData;
import entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerDataTest {
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        customers = new ArrayList<>(CustomerData.customers);
    }

    @Test
    public void getAll() {
        assertEquals(customers, CustomerData.getAll());
    }


    @Test
    public void add() {
        Customer customer = new Customer("CAMILA", "doc105");
        customers.add(customer);
        CustomerData.add(customer);
        assertEquals(customers, CustomerData.getAll());
    }

    @Test
    public void getCustomerByName() {
        String name = "Maria";

        assertEquals(customers.stream()
                .filter(customer -> customer.name().equalsIgnoreCase(name))
                .collect(Collectors.toList()), CustomerData.getCustomerByName(name));
    }

    @Test
    public void getCustomerByDocument() {
        assertEquals(customers.get(0), CustomerData.getCustomerByDocument(customers.get(0).document()));
    }

}
