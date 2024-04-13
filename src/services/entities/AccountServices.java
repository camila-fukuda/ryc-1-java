package services.entities;

import data.AccountData;
import data.BranchData;
import data.CustomerData;
import entities.Account;
import entities.Branch;
import entities.Customer;
import entities.PersonAccount;
import services.InputManager;

import java.lang.reflect.Method;
import java.util.*;

public class AccountServices {

    private static final Map<String, String> LABEL_TO_METHOD = new LinkedHashMap<>();

    static {
        LABEL_TO_METHOD.put("ACC - List Accounts", "listAccounts");
        LABEL_TO_METHOD.put("ACC - Find Account by Code", "findAccountByCode");
        LABEL_TO_METHOD.put("ACC - Create Account", "createAccount");
//        LABEL_TO_METHOD.put("ACC - Find Account by Customer Name", "findAccountByName");
//        LABEL_TO_METHOD.put("ACC - Deposit", "deposit");
//        LABEL_TO_METHOD.put("ACC - Find Account by Customer Document", "findAccountByDocument");
    }

    public static List<String> getAvailableServices() {
        return new ArrayList<>(LABEL_TO_METHOD.keySet());
    }

    private static String getLabel(String value) {
        for (Map.Entry<String, String> entry : LABEL_TO_METHOD.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static String getRandomCode(String type) {
        String code = generateRandomCode(type);
        while (AccountData.getAccount(code) != null) {
            code = generateRandomCode(type);
        }
        return code;
    }


    private static String generateRandomCode(String type) {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return type + "ACC-" + String.format("%05d", randomNumber);
    }

    private static void isDuplicate(PersonAccount newAcc) {
        for (Account acc : AccountData.getAll()) {
            if (acc.equals(newAcc)) {
                throw new IllegalArgumentException("DUPLICATE ACCOUNT PERSON");
            }
        }
    }

    private static void findAccountByCode() {
        String actionLabel = getLabel("findAccountByCode");
        Map<String, String> fieldLabelToName = Map.of("Code", "code");
        Map<String, String> userInput;
        userInput = InputManager.readInput(fieldLabelToName);

        try {
            String code = userInput.get("Code").toUpperCase();
            Account account = AccountData.getAccount(code);
            if (account != null) {
                System.out.println("\nACCOUNT FOUND:");
                System.out.println(account);
            } else {
                System.out.println("\nACCOUNT NOT FOUND!");
            }
        } catch (Exception e) {
            System.out.println("An error happened while searching for the account.");
            e.printStackTrace();
        }
    }

    static void deposit() {
        String actionLabel = getLabel("deposit");
        Map<String, String> fieldLabelToName = Map.of("Type (P - Person Account) / (B - Business Account)", "Type");
        Map<String, String> userInput;
        String accountType;
    }

    static void createAccount() {
        String actionLabel = getLabel("createAccount");
        Map<String, String> fieldLabelToName = Map.of("Type (P - Person Account) / (B - Business Account)", "Type");
        Map<String, String> userInput;
        String accountType;
        String errorMsg;
        String errorType;
        String customerDocument;
        String branchCode;
        String code;

        while (true) {
            userInput = InputManager.readInput(fieldLabelToName);
            accountType = userInput.get("Type (P - Person Account) / (B - Business Account)").toLowerCase();
            if (!accountType.equals("b") && !accountType.equals("p")) {
                System.out.println("Invalid Type! Type P for person account and B for business account!\n");
            } else {
                break;
            }
        }

        if (accountType.equals("p")) {
            fieldLabelToName = PersonAccount.getFieldLabelToName();
            while (true) {
                userInput = InputManager.readInput(fieldLabelToName);
                try {
                    customerDocument = userInput.get("Customer Document");
                    branchCode = userInput.get("Branch Code");
                    code = getRandomCode(accountType);

                    Branch branch = BranchData.getBranch(branchCode);
                    Customer customer = CustomerData.getCustomerByDocument(customerDocument);

                    PersonAccount newPersonAccount = new PersonAccount(code, customer, branch);

                    isDuplicate(newPersonAccount);
                    System.out.println("\nSUCCESS! New Person Account created! " + newPersonAccount);
                    AccountData.add(newPersonAccount);
                } catch (Exception e) {
                    errorType = e.getClass().getSimpleName().equals("IllegalArgumentException") ? "Invalid Information" : "Unexpected";
                    errorMsg = e.getMessage();
                    if (e.getMessage().toLowerCase().contains("branch") && e.getMessage().toLowerCase().contains("is required")) {
                        errorMsg = "Branch was not found. Branch is not registered or code provided is wrong.";
                    }
                    if (e.getMessage().toLowerCase().contains("customer") && e.getMessage().toLowerCase().contains("is required")) {
                        errorMsg = "Customer was not found. Customer is not registered or document provided is wrong.";
                    }
                    if (e.getMessage().toLowerCase().contains("duplicate")) {
                        errorType = "DUPLICATE ACCOUNT";
                        errorMsg = "This customer already has a person account. It is not possible to create another one.";
                    }
                    displayError(errorType, errorMsg);
                } finally {
                    break;
                }
            }

        }
    }

    private static void displayError(String typeOfError, String errorMsg) {
        System.out.println("------------------------------");
        System.out.println("Person Account creation failed. Please try again.");
        System.out.println(typeOfError.toUpperCase() + " -> " + errorMsg);
        System.out.println("------------------------------");
    }

    private static void listAccounts() {
        List<Account> accounts = AccountData.getAll();
        if (accounts.isEmpty()) {
            System.out.println("\n No account created.");
        } else {
            System.out.println("ACCOUNTS:");
            accounts.stream().forEach(System.out::println);
        }
    }

    public static void executeAction(String actionLabel) {
        String methodName = LABEL_TO_METHOD.get(actionLabel);
        if (methodName != null) {
            try {
                Method method = AccountServices.class.getDeclaredMethod(methodName);
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
