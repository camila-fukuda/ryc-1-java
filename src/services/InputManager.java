package services;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Scanner;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    private InputManager() {
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static <T> T readInput(Class<T> clazz, Map<String, String> labelMap, String actionName) {
        while (true) {
            try {
                Class<?>[] parameterTypes = new Class<?>[labelMap.size()];
                Object[] args = new Object[labelMap.size()];

                int index = labelMap.entrySet().size() - 1;
                for (Map.Entry<String, String> entry : labelMap.entrySet()) {
                    parameterTypes[index] = String.class;
                    System.out.println("-> Enter value for " + entry.getKey() + ":");
                    args[index] = scanner.nextLine();
                    index--;
                }

                Constructor<T> constructor = clazz.getDeclaredConstructor(parameterTypes);
                return constructor.newInstance(args);
            } catch (Exception e) {
                if (e.getCause() instanceof IllegalArgumentException) {
                    System.out.println("------------------------------");
                    System.out.println("ERROR - " + e.getCause().getMessage()); // Print the error message
                    System.out.println("------------------------------");
                    System.out.println(actionName.toUpperCase() + " - Please enter the input again:/n");
                } else {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


}
