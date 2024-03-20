package services.entities;

import data.BranchData;
import entities.Branch;
import services.InputManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BranchServices {
    private static final Map<String, String> LABEL_TO_METHOD = new LinkedHashMap<>();

    static {
        LABEL_TO_METHOD.put("List Branches", "listBranches");
        LABEL_TO_METHOD.put("Create Branch", "createBranch");
        LABEL_TO_METHOD.put("Find Branch by Code", "findAccByCode");
    }

    private static String getLabel(String value) {
        for (Map.Entry<String, String> entry : LABEL_TO_METHOD.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void listBranches() {
        List<Branch> branches = BranchData.getAllBranches();
        if (branches.isEmpty()) {
            System.out.println("\n No branches created.");
        } else {
            System.out.println("BRANCHES:");
            branches.stream().forEach(System.out::println);
        }
    }


    private static void createBranch() {
        String actionLabel = getLabel("createBranch");

        Map<String, String> branchLabelMap = Branch.getLabelMap();
        Branch newBranch = InputManager.readInput(Branch.class, branchLabelMap, actionLabel);

        if (newBranch != null) {
            System.out.println("\nSUCCESS! New Branch created! " + newBranch);
            BranchData.add(newBranch);
        } else {
            System.out.println("Branch creation failed.");
        }
    }

    private static void findBranchByCode() {
        String actionLabel = getLabel("findBranchByCode");
        Map<String, String> branchLabelMap = Map.of("Code", "Code");
        Branch newBranch = InputManager.readInput(Branch.class, branchLabelMap, actionLabel);

        if (newBranch != null) {
            System.out.println("\nSUCCESS! New Branch created! " + newBranch);
            BranchData.add(newBranch);
        } else {
            System.out.println("Branch creation failed.");
        }

    }

    public static List<String> getAvailableServices() {
        return new ArrayList<>(LABEL_TO_METHOD.keySet());
    }

    public static void executeAction(String actionLabel) {
        String methodName = LABEL_TO_METHOD.get(actionLabel);
        if (methodName != null) {
            try {
                Method method = BranchServices.class.getDeclaredMethod(methodName);
                method.setAccessible(true); // Allow access to private methods
                method.invoke(null);
            } catch (Exception e) {
                e.printStackTrace(); // Handle any exceptions
            }
        } else {
            System.out.println("Action not found: " + actionLabel);
        }
    }


}
