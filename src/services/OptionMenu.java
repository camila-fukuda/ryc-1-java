package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OptionMenu {
    private final Scanner scanner;

    public OptionMenu(Scanner scan) {
        scanner = scan;

    }

    public void runAction(int actionIndex) {
        Actions.executeAction(actionIndex);
    }

    public void runMenu() {
        List<String> actionsList = new ArrayList<>(Actions.getActionsList());
        System.out.println("\n----------------------------------");
        System.out.println("WELCOME TO THE BANKING SYSTEM!");
        System.out.println("----------------------------------\n");

        System.out.println("The options available are:");

        for (int i = 0; i < actionsList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, actionsList.get(i));
        }


        int actionChoice = -1;

        while (true) {
            System.out.print("\n -> Type the number of the desired action: ");
            try {
                actionChoice = Integer.parseInt(scanner.nextLine());
                if (actionChoice <= 0 || actionChoice > actionsList.size()) {
                    System.out.println("\nInvalid Option! Please enter a number between 1 and " + actionsList.size() + ".");
                    continue;
                }
                actionChoice -= 1;
                System.out.println("----------------------------------\n");
                System.out.printf("\nYou selected Option %d: %s\n", actionChoice + 1, actionsList.get(actionChoice).toUpperCase() + "\n");
                this.runAction(actionChoice);

                break; // Exit the loop if a valid choice is made
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid Option! Please enter a valid number.");
            }
        }
    }

}
