package application;

import exceptions.ActionsExceptions;
import exceptions.OptionMenuException;
import services.OptionMenu;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ActionsExceptions, OptionMenuException {

        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            OptionMenu optionMenu = new OptionMenu(scanner);
            Integer actionChoice = optionMenu.runMenu(scanner);

            if (actionChoice == -1) {
                endRun();
            }

            optionMenu.runAction(actionChoice);


            String keepRunning = null;
            while (!Objects.equals(keepRunning, "N") && !Objects.equals(keepRunning, "Y")) {

                System.out.println("\n\nType Y to execute another action or N to close the menu.");
                keepRunning = scanner.next().toUpperCase();

                if (!Objects.equals(keepRunning, "N") && !Objects.equals(keepRunning, "Y")) {
                    System.out.println("\nINVALID OPTION!");
                } else if (keepRunning.equals("N")) {
                    endRun();
                }
            }

        }


        scanner.close();
    }

    private static void endRun() {
        System.out.println("\nClosing the program...");
        System.exit(0);
    }
}