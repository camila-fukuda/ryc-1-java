package data;

import entities.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchData {
    static public List<Branch> branches = new ArrayList<>();

    static {
        branches.add(new Branch("BEBEDOURO", "SP"));
        branches.add(new Branch("TAIUVA", "SP"));
        branches.add(new Branch("UBERABA", "MG"));
    }

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

    static public Branch getBranch(String code) {
        for (Branch branch : branches) {
            if (branch.getCode().equals(code)) {
                return branch;
            }
        }
        return null;
    }


}
