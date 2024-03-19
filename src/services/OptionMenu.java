package services;

import exceptions.ActionsExceptions;

import java.lang.reflect.Method;
import java.util.*;

public class OptionMenu {
    public static final Map<String, String> MENU_OPTIONS_MAP = new LinkedHashMap<>();
    public static final List<String> MENU_OPTIONS_LIST = new ArrayList<>();


    static {
        MENU_OPTIONS_MAP.put("List Branches", "listBranches");
        MENU_OPTIONS_MAP.put("List Customers", "listCustomers");
        MENU_OPTIONS_MAP.put("List Accounts", "listAccounts");
        MENU_OPTIONS_MAP.put("Create Account", "createAccount");
        MENU_OPTIONS_MAP.put("Create Customer", "createCustomer");
        MENU_OPTIONS_MAP.put("Create Branch", "createBranch");
        MENU_OPTIONS_MAP.put("Withdraw", "withdrawWithdraw");
        MENU_OPTIONS_MAP.put("Deposit", "depositDeposit");
        MENU_OPTIONS_MAP.put("Consult Limit", "consultLimit");
        MENU_OPTIONS_MAP.put("Consult Balance", "consultBalance");
        MENU_OPTIONS_MAP.put("Find Account by Name", "findAccByName");
        MENU_OPTIONS_MAP.put("Find Account by Code", "findAccByCode");

        MENU_OPTIONS_LIST.addAll(MENU_OPTIONS_MAP.keySet());
    }

    public static void runAction(Integer actionIndex) {
        Actions actions = new Actions();
        String methodName = MENU_OPTIONS_MAP.get(MENU_OPTIONS_LIST.get(actionIndex));

        try {
            Method method = actions.getClass().getMethod(methodName);

            method.invoke(actions);
        } catch (Exception e) {
            throw new ActionsExceptions("error");
        }

    }

    public static Integer runMenu() {
        System.out.println("\n----------------------------------");
        System.out.println("WELCOME TO THE BANKING SYSTEM!");
        System.out.println("----------------------------------\n");

        System.out.println("The options available are:");

        for (int i = 0; i < MENU_OPTIONS_LIST.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, MENU_OPTIONS_LIST.get(i));
        }


        Scanner scanner = new Scanner(System.in);
        int actionChoice = -1;

        while (actionChoice < 0) {
            System.out.print("\n -> Type the number of the desired action: ");

            actionChoice = scanner.nextInt() - 1;

            if (actionChoice < MENU_OPTIONS_LIST.size()) {
                System.out.println("----------------------------------\n");
                System.out.printf("\nYou selected Option %d: %s\n", actionChoice + 1, MENU_OPTIONS_LIST.get(actionChoice).toUpperCase());

            } else {
                System.out.println("\nInvalid Option! Type N to close the menu or any other key to try again.");
                String continueChoice = scanner.next().toUpperCase();

                if (continueChoice.equals("N")) {
                    scanner.close();
                    return -1;
                }
                actionChoice = -1;
            }
        }

        scanner.close();

        return actionChoice;
    }

    public static void endRun() {
        System.out.println("\nClosing the program...");
        System.exit(0);
    }
}
