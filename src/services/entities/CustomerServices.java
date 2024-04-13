package services.entities;

import data.CustomerData;
import entities.Customer;
import services.InputManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerServices {
    private static final Map<String, String> LABEL_TO_METHOD = new LinkedHashMap<>();

    static {
        LABEL_TO_METHOD.put("CST - List Customers", "listCustomers");
        LABEL_TO_METHOD.put("CST - Create Customer", "createCustomer");
        LABEL_TO_METHOD.put("CST - Find Customer by Document", "findCustomerByDocument");
        LABEL_TO_METHOD.put("CST - Find Customer by Name", "findCustomerByName");
    }

    private static void listCustomers() {
        List<Customer> customers = CustomerData.getAll();
        if (customers.isEmpty()) {
            System.out.println("\n No branches created.");
        } else {
            System.out.println("CUSTOMERS:");
            customers.stream().forEach(System.out::println);
        }
    }

    private static void isDuplicate(Customer newCustomer) {
        for (Customer customer : CustomerData.getAll()) {
            if (newCustomer.equals(customer)) {
                throw new IllegalArgumentException("A customer with the document already exists.");
            }
        }
    }

    private static String getLabel(String value) {
        for (Map.Entry<String, String> entry : LABEL_TO_METHOD.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void createCustomer() {
        String actionLabel = getLabel("createCustomer");
        Map<String, String> fieldLabelToName = Customer.getFieldLabelToName();
        Map<String, String> userInput;

        while (true) {
            userInput = InputManager.readInput(fieldLabelToName);
            try {
                String name = userInput.get("Name");
                String document = userInput.get("Document");

                Customer newCustomer = new Customer(name, document);
                isDuplicate(newCustomer);
                System.out.println("\nSUCCESS! New Customer created! " + newCustomer);
                CustomerData.add(newCustomer);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("------------------------------");
                System.out.println("ERROR - " + e.getMessage()); // Print the error message
                System.out.println("------------------------------");
                System.out.println(actionLabel.toUpperCase() + " - Please enter the input again:/n");
            } catch (Exception e) {
                System.out.println("Customer creation failed. Please try again.");
                e.printStackTrace();
            }
        }
    }

    private static void findCustomerByDocument() {
        Map<String, String> fieldLabelToName = Map.of("Document", "document");
        Map<String, String> userInput;

        userInput = InputManager.readInput(fieldLabelToName);

        try {
            String document = userInput.get("Document").toUpperCase();
            Customer customer = CustomerData.getCustomerByDocument(document);
            if (customer != null) {
                System.out.println("\nCUSTOMER FOUND:");
                System.out.println(customer);
            } else {
                System.out.println("\nCUSTOMER NOT FOUND!");
            }
        } catch (Exception e) {
            System.out.println("An error happened while searching for the customer.");
            e.printStackTrace();
        }
    }

    private static void findCustomerByName() {
        Map<String, String> fieldLabelToName = Map.of("Name", "name");
        Map<String, String> userInput;

        userInput = InputManager.readInput(fieldLabelToName);

        try {
            String name = userInput.get("Name").toUpperCase();
            List<Customer> customers = CustomerData.getCustomerByName(name);
            if (customers.isEmpty()) {
                System.out.println("\nCUSTOMER NOT FOUND!");
            } else {
                System.out.println("\nCUSTOMERS FOUND:");
                customers.stream().forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("An error happened while searching for the customer.");
            e.printStackTrace();
        }
    }

    public static List<String> getAvailableServices() {
        return new ArrayList<>(LABEL_TO_METHOD.keySet());
    }

    public static void executeAction(String actionLabel) {
        String methodName = LABEL_TO_METHOD.get(actionLabel);
        if (methodName != null) {
            try {
                Method method = CustomerServices.class.getDeclaredMethod(methodName);
                method.setAccessible(true);
                method.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Action not found: " + actionLabel);
        }
    }

}
