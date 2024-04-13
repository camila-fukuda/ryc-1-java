package services.entities;

import data.BranchData;
import entities.Branch;
import services.InputManager;

import java.lang.reflect.Method;
import java.util.*;

public class BranchServices {
    private static final Map<String, String> LABEL_TO_METHOD = new LinkedHashMap<>();

    static {
        LABEL_TO_METHOD.put("BRN - List Branches", "listBranches");
        LABEL_TO_METHOD.put("BRN - Create Branch", "createBranch");
        LABEL_TO_METHOD.put("BRN - Find Branch by Code", "findBranchByCode");
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
        List<Branch> branches = BranchData.getAll();
        if (branches.isEmpty()) {
            System.out.println("\n No branches created.");
        } else {
            System.out.println("BRANCHES:");
            branches.stream().forEach(System.out::println);
        }
    }

    private static void createBranch() {
        String actionLabel = getLabel("createBranch");
        Map<String, String> fieldLabelToName = Branch.getFieldLabelToName();
        Map<String, String> userInput;

        while (true) {
            userInput = InputManager.readInput(fieldLabelToName);
            try {
                String city = userInput.get("City");
                String state = userInput.get("State");
                String code = getRandomCode();

                Branch newBranch = new Branch(city, state, code);
                isDuplicate(newBranch);
                System.out.println("\nSUCCESS! New Branch created! " + newBranch);
                BranchData.add(newBranch);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("------------------------------");
                System.out.println("ERROR - " + e.getMessage()); // Print the error message
                System.out.println("------------------------------");
                System.out.println(actionLabel.toUpperCase() + " - Please enter the input again:/n");
            } catch (Exception e) {
                System.out.println("Branch creation failed. Please try again.");
                e.printStackTrace();
            }
        }
    }

    private static void findBranchByCode() {
        Map<String, String> fieldLabelToName = Map.of("Code", "code");
        Map<String, String> userInput;

        userInput = InputManager.readInput(fieldLabelToName);

        try {
            String code = userInput.get("Code");
            Branch branch = BranchData.getBranch(code);
            if (branch != null) {
                System.out.println("\nBRANCH FOUND:");
                System.out.println(branch);
            } else {
                System.out.println("\nBRANCH NOT FOUND: the branch does not exists or the provided code is wrong.");
            }
        } catch (Exception e) {
            System.out.println("An error happened while searching for the branch.");
            e.printStackTrace();
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
                method.setAccessible(true);
                method.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Action not found: " + actionLabel);
        }
    }

    private static void isDuplicate(Branch newBranch) {
        for (Branch branch : BranchData.getAll()) {
            if (newBranch.equals(branch)) {
                throw new IllegalArgumentException("A branch with the same city and state already exists.");
            }
        }
    }

    private static String getRandomCode() {
        String code = generateRandomCode();
        while (BranchData.getBranch(code) != null) {
            code = generateRandomCode();
        }
        return code;
    }

    private static String generateRandomCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return "BR-" + String.format("%05d", randomNumber);
    }


}
