package application;

import services.InputManager;
import services.OptionMenu;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scanner = InputManager.getScanner();

        boolean running = true;
        while (running) {
            OptionMenu optionMenu = new OptionMenu(scanner);
            optionMenu.runMenu();

            String keepRunning = null;
            while (!Objects.equals(keepRunning, "N") && !Objects.equals(keepRunning, "Y")) {

                System.out.println("\n\nType Y to execute another action or N to close the menu.");
                keepRunning = scanner.nextLine().toUpperCase();

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