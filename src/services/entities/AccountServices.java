package services.entities;

import data.AccountData;
import data.BranchData;
import data.CustomerData;
import data.TransactionData;
import entities.*;
import exceptions.InsufficientFundsException;
import services.InputManager;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.*;

public class AccountServices {

    private static final Map<String, String> LABEL_TO_METHOD = new LinkedHashMap<>();

    static {
        LABEL_TO_METHOD.put("ACC - List Accounts", "listAccounts");
        LABEL_TO_METHOD.put("ACC - Find Account by Code", "findAccountByCode");
        LABEL_TO_METHOD.put("ACC - Create Account", "createAccount");
        LABEL_TO_METHOD.put("ACC - Find Accounts by Customer Name", "findAccountsByName");
        LABEL_TO_METHOD.put("ACC - Find Accounts by Customer Document", "findAccountsByDocument");
        LABEL_TO_METHOD.put("ACC - Deposit", "deposit");
        LABEL_TO_METHOD.put("ACC - Withdraw", "withdraw");
        LABEL_TO_METHOD.put("ACC - Get limit", "getLimit");
        LABEL_TO_METHOD.put("ACC - Get balance", "getBalance");
        LABEL_TO_METHOD.put("ACC - Set limit", "setLimit");
        LABEL_TO_METHOD.put("ACC - Transfer to another Account", "transfer");
        LABEL_TO_METHOD.put("ACC - Transactions by Account", "transactionsByAccount");

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
        return "ACC" + type.substring(0, 1).toUpperCase() + String.format("%05d", randomNumber);
    }

    private static void isDuplicate(PersonAccount newAcc) {
        for (Account acc : AccountData.getAll()) {
            if (acc.equals(newAcc)) {
                throw new IllegalArgumentException("DUPLICATE ACCOUNT PERSON");
            }
        }
    }

    static void transfer() {
        String actionLabel = getLabel("transfer");
        String fromAccount = "FROM Account CODE";
        String howMuch = "Amount to transfer";
        String toAccount = "TO Account Code";
        Account accountTransferring;
        Account accountReceiving;
        Double amount;

        Map<String, String> fieldLabelToName = Map.of(fromAccount, "from", howMuch, "amount", toAccount, "to");
        Map<String, String> userInput;

        userInput = InputManager.readInput(fieldLabelToName);
        fromAccount = userInput.get(fromAccount);
        amount = Double.valueOf(userInput.get(howMuch));
        toAccount = userInput.get(toAccount);
        accountTransferring = AccountData.getAccount(fromAccount);
        accountReceiving = AccountData.getAccount(toAccount);
        double accountTransferringOldBalance;
        double accReceivingOldBalance;

        if (accountTransferring == null) {
            System.out.println("The code provided for the account to transfer the amount from does not exist. Please check and try again.");
            System.out.println("----------------------------------");
        }

        if (accountReceiving == null) {
            System.out.println("The code provided for the account receiving the amount does not exist. Please check and try again.");
            System.out.println("----------------------------------");
        }

        if (accountReceiving != null && accountTransferring != null) {

            try {
                accountTransferringOldBalance = accountTransferring.getBalance();
                accReceivingOldBalance = accountReceiving.getBalance();
                accountTransferring.withdraw(amount);
                accountReceiving.deposit(amount);

                Transaction transDeposit = new Transaction(Transaction.TransactionType.TRANSFER_DEPOSIT, amount, accountReceiving, Instant.now());
                Transaction transWithdraw = new Transaction(Transaction.TransactionType.TRANSFER_WITHDRAW, amount, accountTransferring, Instant.now());
                TransactionData.add(transDeposit);
                TransactionData.add(transWithdraw);
                System.out.println("----");
                System.out.println("The transference was SUCCESSFUL!");
                System.out.println("The balance for the account " + accountTransferring.getCode() + " is: " + accountTransferring.getBalance());
                System.out.println("The balance for the account " + accountReceiving.getCode() + " is: " + accountReceiving.getBalance());

            } catch (InsufficientFundsException e) {
                System.out.println("ERROR - Insufficient Funds!");
                System.out.println("The account " + accountTransferring.getAccCode() + " does not have the limit/balance to transfer the amount.");
            } catch (Exception e) {
                accountTransferring.setBalance(amount);
                accountReceiving.setBalance(amount);
                System.out.println("UNEXPECTED PROBLEM, the transfer was not completed: " + e);
                throw e;
            }
        }

    }

    static void transactionsByAccount() {
        Map<String, String> fieldLabelToName = Map.of("Account Code", "accCode");
        Map<String, String> userInput;

        userInput = InputManager.readInput(fieldLabelToName);

        try {
            String code = userInput.get("Account Code").toUpperCase();
            Account acc = AccountData.getAccount(code);
            List<Transaction> transactions = TransactionData.getTransactionByAccount(acc);
            if (transactions.isEmpty()) {
                System.out.println("\nNo transactions found!");
            } else {
                System.out.println("\nTRANSACTIONS:");
                transactions.stream().forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("An error happened while searching for the customer.");
            e.printStackTrace();
        }
    }

    static void setLimit() {
        String actionLabel = getLabel("setLimit");
        String howMuch = "How much is the new limit";
        String toWhichAccount = "Account Code";
        Map<String, String> fieldLabelToName = Map.of(howMuch, "amount", toWhichAccount, "code");
        Map<String, String> userInput;
        Double amount;
        Double oldLimit;
        String accountCode;

        userInput = InputManager.readInput(fieldLabelToName);
        accountCode = userInput.get(toWhichAccount);
        amount = Double.valueOf(userInput.get(howMuch));
        Account acc = AccountData.getAccount(accountCode);

        if (acc != null) {
            System.out.println();
            oldLimit = acc.getLimit();
            try {
                acc.setLimit(amount);
                System.out.println("The new limit is: " + acc.getLimit());

            } catch (InsufficientFundsException e) {
                System.out.println("ERROR - Insufficient Funds!");
                System.out.println("The limit is not sufficient for the current account's balance: " + acc.getBalance() + ".");
            } catch (Exception e) {
                acc.setBalance(oldLimit);
                System.out.println("UNEXPECTED PROBLEM: " + e);
                throw e;
            }
        } else {
            System.out.println("NO ACCOUNTS FOUND for the code provided, check and try again.");
        }
        System.out.println("----------------------------------");
    }

    static void getBalance() {
        Map<String, String> userInput;
        String accCode;
        Map<String, String> fieldLabelToName = Map.of("Type the Account's code", "code");
        userInput = InputManager.readInput(fieldLabelToName);
        accCode = userInput.get("Type the Account's code");
        System.out.println("\n");
        Account account = AccountData.getAccount(accCode);
        if (account != null) {
            System.out.println("ACCOUNT FOUND!");
            System.out.println("The balance for the account " + account.getCode() + " is: " + account.getBalance());
            System.out.println(account);
        } else {
            System.out.println("NO ACCOUNT FOUND, check the code provided.");
        }
    }

    static void getLimit() {
        Map<String, String> userInput;
        String accCode;
        Map<String, String> fieldLabelToName = Map.of("Type the Account's code", "code");
        userInput = InputManager.readInput(fieldLabelToName);
        accCode = userInput.get("Type the Account's code");
        System.out.println("\n");
        Account account = AccountData.getAccount(accCode);
        if (account != null) {
            System.out.println("ACCOUNT FOUND!");
            System.out.println("The limit for the account " + account.getCode() + " is: " + account.getLimit());
            System.out.println(account);
        } else {
            System.out.println("NO ACCOUNT FOUND, check the code provided.");
        }
    }

    static void withdraw() {
        String actionLabel = getLabel("deposit");
        String howMuch = "How much do you want to withdraw";
        String toWhichAccount = "Account Code";
        Map<String, String> fieldLabelToName = Map.of(howMuch, "amount", toWhichAccount, "code");
        Map<String, String> userInput;
        Double amount;
        Double oldBalance;
        String accountCode;

        userInput = InputManager.readInput(fieldLabelToName);
        accountCode = userInput.get(toWhichAccount);
        amount = Double.valueOf(userInput.get(howMuch));
        Account acc = AccountData.getAccount(accountCode);

        if (acc != null) {
            System.out.println();
            oldBalance = acc.getBalance();
            try {
                acc.withdraw(amount);
                System.out.println("WITHDRAW SUCCESS. The new balance is: " + acc.getBalance());

            } catch (InsufficientFundsException e) {
                acc.setBalance(oldBalance);
                System.out.println("ERROR - Insufficient Funds!");
                System.out.println("The current limit for the account is: " + acc.getLimit() + " and the balance is: " + acc.getBalance());
            } catch (Exception e) {
                acc.setBalance(oldBalance);
                System.out.println("UNEXPECTED PROBLEM: " + e);
                throw e;
            }
        } else {
            System.out.println("NO ACCOUNTS FOUND for the code provided, check and try again.");
        }
        System.out.println("----------------------------------");
    }

    static void deposit() {
        String actionLabel = getLabel("deposit");
        String howMuch = "How much do you want to deposit";
        String toWhichAccount = "Account Code";
        Map<String, String> fieldLabelToName = Map.of(howMuch, "amount", toWhichAccount, "code");
        Map<String, String> userInput;
        Double amount;
        Double oldBalance;
        String accountCode;

        userInput = InputManager.readInput(fieldLabelToName);
        accountCode = userInput.get(toWhichAccount);
        amount = Double.valueOf(userInput.get(howMuch));
        Account acc = AccountData.getAccount(accountCode);

        if (acc != null) {
            oldBalance = acc.getBalance();
            try {
                acc.deposit(amount);
                System.out.println("DEPOSIT SUCCESS. The new balance is: " + acc.getBalance());

            } catch (Exception e) {
                acc.setBalance(oldBalance);
                System.out.println("UNEXPECTED PROBLEM: " + e);
                throw e;
            }
        } else {
            System.out.println("NO ACCOUNTS FOUND for the code provided, check and try again.");
        }
        System.out.println("----------------------------------");
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

                    Branch branch = BranchData.getBranchByCode(branchCode);
                    Customer customer = CustomerData.getCustomerByDocument(customerDocument);

                    PersonAccount newPersonAccount = new PersonAccount(code, branch, customer);

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

    private static void findAccountsByName() {
        Map<String, String> userInput;
        String customerName;
        Map<String, String> fieldLabelToName = Map.of("Type the customer's name", "name");
        userInput = InputManager.readInput(fieldLabelToName);
        customerName = userInput.get("Type the customer's name");
        System.out.println("\n");
        System.out.println("----------------------------------");
        System.out.println("Searching for Accounts from Customers with name " + customerName.toUpperCase() + " ...");
        System.out.println("----------------------------------");
        List<Customer> customer = CustomerData.getCustomerByName(customerName);
        List<Account> accounts = AccountData.getAccountByCustomer(customer);
        if (accounts.size() > 0) {
            System.out.println("ACCOUNTS FOUND:");
            System.out.println(accounts);
        } else {
            System.out.println("NO ACCOUNTS FOUND, check the name provided.");
        }
        System.out.println("----------------------------------");
    }

    private static void findAccountsByDocument() {
        Map<String, String> userInput;
        String customerDocument;
        Map<String, String> fieldLabelToName = Map.of("Type the customer's document", "document");
        userInput = InputManager.readInput(fieldLabelToName);
        customerDocument = userInput.get("Type the customer's document");
        System.out.println("\n");
        System.out.println("----------------------------------");
        System.out.println("Searching for Accounts from Customer with document " + customerDocument.toUpperCase() + " ...");
        System.out.println("----------------------------------");
        Customer customer = CustomerData.getCustomerByDocument(customerDocument);
        List<Account> accounts = AccountData.getAccountByCustomer(customer);
        if (accounts.size() > 0) {
            System.out.println("ACCOUNTS FOUND:");
            System.out.println(accounts);
        } else {
            System.out.println("NO ACCOUNTS FOUND, check the document provided.");
        }
        System.out.println("----------------------------------");
    }

    private static void findAccountByCode() {
        Map<String, String> userInput;
        String accCode;
        Map<String, String> fieldLabelToName = Map.of("Type the Account's code", "code");
        userInput = InputManager.readInput(fieldLabelToName);
        accCode = userInput.get("Type the Account's code");
        System.out.println("\n");
        System.out.println("----------------------------------");
        System.out.println("Searching for Account with code " + accCode.toUpperCase() + " ...");
        System.out.println("----------------------------------");
        Account account = AccountData.getAccount(accCode);
        if (account != null) {
            System.out.println("ACCOUNT FOUND:");
            System.out.println(account);
        } else {
            System.out.println("NO ACCOUNT FOUND, check the code provided.");
        }

    }

}
