package services;

import data.AccountData;
import data.BranchData;
import data.CustomerData;
import entities.Branch;
import entities.Customer;
import exceptions.ActionsExceptions;

import java.lang.reflect.Method;
import java.util.*;


public class Actions {
    private static final Map<String, String> ACTION_MAP = new LinkedHashMap<>();
    private static final List<String> ACTION_LIST = new ArrayList<>();

    private final Scanner scanner;

    public Actions(Scanner scanner) {

        ACTION_MAP.put("List Branches", "listBranches");
        ACTION_MAP.put("List Customers", "listCustomers");
        ACTION_MAP.put("List Accounts", "listAccounts");
        ACTION_MAP.put("Create Account", "createAccount");
        ACTION_MAP.put("Create Customer", "createCustomer");
        ACTION_MAP.put("Create Branch", "createBranch");
        ACTION_MAP.put("Withdraw", "withdraw");
        ACTION_MAP.put("Deposit", "deposit");
        ACTION_MAP.put("Consult Limit", "consultLimit");
        ACTION_MAP.put("Consult Balance", "consultBalance");
        ACTION_MAP.put("Find Account by Name", "findAccByName");
        ACTION_MAP.put("Find Account by Code", "findAccByCode");

        ACTION_LIST.addAll(ACTION_MAP.keySet());
        this.scanner = scanner;
    }

    public void listBranches() {
        System.out.println("\nALL BRANCHES:\n");
        System.out.println(BranchData.getAllBranches());
    }

    public void listCustomers() {
        System.out.println("\nALL CUSTOMERS:\n");
        System.out.println(CustomerData.getAllCustomers());
    }

    public void listAccounts() {
        System.out.println("\nALL ACCOUNTS:\n");
        System.out.println(AccountData.getAllAccounts());
    }

    public void createAccount() {
        System.out.println("createAccount");
    }

    public void createCustomer() {
        InputManager im = new InputManager(scanner);

        Customer customer = im.readInput(Customer.class, Customer.getLabelMap());
        System.out.println("New Customer created: " + customer);
    }

    public void createBranch() {
        InputManager im = new InputManager(scanner);

        Branch branch = im.readInput(Branch.class, Branch.getLabelMap());
        System.out.println("New Branch created: " + branch);


//        Branch newBranch = null;
//        System.out.println("\nCREATING BRANCH -> Type the required data:\n");
//
//        if (newBranch != null) {
//            System.out.println("\nThere's already :\n");
//        }
//
//        System.out.print("Enter the city name: ");
//        String cityName = scanner.next();
//
//        String state;
//        do {
//            System.out.print("Enter the state abbreviation (2 letters): ");
//            state = scanner.next();
//        } while (!Branch.validState(state));
//
//        newBranch = new Branch(cityName, state);
    }

    public void withdraw() {
        System.out.println("withdrawWithdraw");
    }

    public void deposit() {
        System.out.println("depositDeposit");
    }

    public void consultLimit() {
        System.out.println("consultLimit");
    }

    public void consultBalance() {
        System.out.println("\n ACTION consultBalance\n");

    }

    public void findAccByName() {
        System.out.println("findAccByName");
    }

    public void findAccByCode() {
        System.out.println("findAccByCode");
    }

    public void executeAction(Integer actionIndex) throws ActionsExceptions {
        String methodName = ACTION_MAP.get(ACTION_LIST.get(actionIndex));

        try {
            Method method = this.getClass().getMethod(methodName);
            method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionsExceptions("Action Error");
        }
    }

    public Set<String> getActionsList() {
        return ACTION_MAP.keySet();
    }


}
