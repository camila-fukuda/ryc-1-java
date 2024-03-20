package services.entities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerServices {
    private static final Map<String, String> LABEL_TO_METHOD = new LinkedHashMap<>();

    static {
        LABEL_TO_METHOD.put("List Customers", "listCustomers");
        LABEL_TO_METHOD.put("Create Customer", "createCustomer");
        LABEL_TO_METHOD.put("Find Customer by Document", "findCustomerByDocument");
        LABEL_TO_METHOD.put("Find Customer by Name", "findCustomerByName");
    }

    public static void listCustomers() {
        System.out.println("listBranches");
    }

    public static void createCustomer() {
        System.out.println("createBranch");
    }

    public static void findCustomerByDocument() {
        System.out.println("findCustomerByDocument");
    }

    public static void findCustomerByName() {
        System.out.println("findCustomerByName");
    }

    public static List<String> getAvailableServices() {
        return new ArrayList<>(LABEL_TO_METHOD.keySet());
    }


}
