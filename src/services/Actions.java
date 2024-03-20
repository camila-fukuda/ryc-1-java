package services;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Actions {

    private static final List<String> SERVICE_CLASS_LIST = List.of("BranchServices", "CustomerServices", "AccountServices");
    private static final Map<String, String> ACTION_TO_SERVICE_CLASS_NAME = new LinkedHashMap<>();
    private static final List<String> ACTION_LABELS = new ArrayList<>();

    static {
        for (String serviceClass : SERVICE_CLASS_LIST) {
            try {
                Class<?> clazz = Class.forName("services.entities." + serviceClass);
                Method method = clazz.getMethod("getAvailableServices");
                List<String> services = (List<String>) method.invoke(null);
                for (String service : services) {
                    ACTION_TO_SERVICE_CLASS_NAME.put(service, serviceClass);
                    ACTION_LABELS.add(service);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Handle any exceptions
            }
        }
    }

    public static List<String> getActionsList() {
        return ACTION_LABELS;
    }

    public static void executeAction(int actionIndex) {
        if (actionIndex >= 0 && actionIndex < ACTION_LABELS.size()) {
            String actionLabel = ACTION_LABELS.get(actionIndex);
            String serviceClassName = ACTION_TO_SERVICE_CLASS_NAME.get(actionLabel);
            if (serviceClassName != null) {
                try {
                    Class<?> serviceClass = Class.forName("services.entities." + serviceClassName);
                    Method method = serviceClass.getMethod("executeAction", String.class);
                    method.invoke(null, actionLabel);
                } catch (ClassNotFoundException e) {
                    System.out.println("Service class not found: " + serviceClassName);
                } catch (NoSuchMethodException e) {
                    System.out.println("Method 'executeAction' not found in service class: " + serviceClassName);
                } catch (Exception e) {
                    e.printStackTrace(); // Handle any other exceptions
                }
            } else {
                System.out.println("Service class not found for action: " + actionLabel);
            }
        } else {
            System.out.println("Invalid action index: " + actionIndex);
        }
    }


}
