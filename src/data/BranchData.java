package data;

import entities.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchData {
    static public List<Branch> branches = new ArrayList<>();

    static public List<Branch> getAllBranches() {
        return branches;
    }

    static public void add(Branch branch) {
        branches.add(branch);
    }

    static public boolean containsCode(String code) {
        for (Branch branch : branches) {
            if (branch.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

//    public static boolean containsEqual(Branch newBranch) {
//        for (Branch branch : branches) {
//            if (branch.equals(newBranch)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
