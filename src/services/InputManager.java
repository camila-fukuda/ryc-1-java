package services;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    private InputManager() {
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static Map<String, String> readInput(Map<String, String> labelMap) {
        Map<String, String> userInput = new HashMap<>();

        for (Map.Entry<String, String> entry : labelMap.entrySet()) {
            System.out.println("-> Enter value for " + entry.getKey() + ":");
            userInput.put(entry.getKey(), scanner.nextLine());
        }

        return userInput;
    }


}
