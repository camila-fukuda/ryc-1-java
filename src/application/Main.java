package application;

import services.Actions;
import services.InputManager;
import services.OptionMenu;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = InputManager.getScanner();

        boolean running = true;
        while (running) {
            List<String> actionsList = Actions.getActionsList();
            OptionMenu optionMenu = new OptionMenu(scanner, actionsList);
            optionMenu.runMenu();

            String keepRunning = null;
            while (!Objects.equals(keepRunning, "N") && !Objects.equals(keepRunning, "Y")) {

                System.out.println("\n\nType Y to execute another action or N to close the menu.");
                keepRunning = scanner.nextLine().toUpperCase();

                if (!Objects.equals(keepRunning, "N") && !Objects.equals(keepRunning, "Y")) {
                    System.out.println("\nINVALID OPTION!");
                } else if (keepRunning.equals("N")) {
                    running = false;
                    break;
                }
            }

        }

        System.out.println("\nClosing the program...");
        scanner.close();
        System.exit(0);


    }
}