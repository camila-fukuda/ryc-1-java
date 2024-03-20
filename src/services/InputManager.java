package services;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Scanner;

public class InputManager {
    private final Scanner scanner;

    InputManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T> T readInput(Class<T> clazz, Map<String, String> labelMap) {
        try {
            Class<?>[] parameterTypes = new Class<?>[labelMap.size()];
            Object[] args = new Object[labelMap.size()];

            int index = labelMap.entrySet().size() - 1;
            for (Map.Entry<String, String> entry : labelMap.entrySet()) {
                parameterTypes[index] = String.class;
                System.out.println("Enter value for " + entry.getKey() + ":");
                if (index == labelMap.entrySet().size() - 1) {
                    scanner.nextLine();
                }
                args[index] = scanner.nextLine();
                index--;
            }

            Constructor<T> constructor = clazz.getDeclaredConstructor(parameterTypes);
            return constructor.newInstance(args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
