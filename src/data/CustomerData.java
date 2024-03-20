package data;

import entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerData {
    static public List<Customer> customers = new ArrayList<>();

    static public <Customer> List<Customer> getAllCustomers() {
        return (List<Customer>) customers;
    }

    static public void add(Customer customer) {
        customers.add(customer);
    }

//    static public boolean containsCode(String code) {
//        for (Customer customer : customers) {
//            if (customer.getCode().equals(code)) {
//                return true;
//            }
//        }
//        return false;
//    }

}
