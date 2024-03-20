package services;

import exceptions.ActionsExceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptionMenu {
    private final Scanner scanner;
    private final Actions actions;

    public OptionMenu(Scanner scan) {
        scanner = scan;
        actions = new Actions(scan);
    }

    public void runAction(int actionIndex) throws ActionsExceptions {
        actions.executeAction(actionIndex);
    }

    public Integer runMenu(Scanner scanner) {
        List<String> actionsList = new ArrayList<>(actions.getActionsList());
        System.out.println("\n----------------------------------");
        System.out.println("WELCOME TO THE BANKING SYSTEM!");
        System.out.println("----------------------------------\n");

        System.out.println("The options available are:");

        for (int i = 0; i < actionsList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, actionsList.get(i));
        }


        int actionChoice = -1;

        while (actionChoice < 0) {
            System.out.print("\n -> Type the number of the desired action: ");

            actionChoice = scanner.nextInt() - 1;

            if (actionChoice < actionsList.size()) {
                System.out.println("----------------------------------\n");
                System.out.printf("\nYou selected Option %d: %s\n", actionChoice + 1, actionsList.get(actionChoice).toUpperCase());

            } else {
                System.out.println("\nInvalid Option! Type N to close the menu or any other key to try again.");
                String continueChoice = scanner.next().toUpperCase();

                if (continueChoice.equals("N")) {

                    return -1;
                }
                actionChoice = -1;
            }
        }

        return actionChoice;
    }
    
}
