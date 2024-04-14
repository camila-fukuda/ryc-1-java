package data;

import entities.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchData {
    static public List<Branch> branches = new ArrayList<>();

    static {
        branches.add(new Branch("BEBEDOURO", "SP", "BR-000001"));
        branches.add(new Branch("TAIUVA", "SP", "BR-000002"));
        branches.add(new Branch("UBERABA", "MG", "BR-000003"));
    }

    static public List<Branch> getAll() {
        return branches;
    }

    static public void add(Branch branch) {
        branches.add(branch);
    }

    static public Branch getBranch(String code) {
        return branches.stream()
                .filter(branch -> branch.code().equals(code))
                .findFirst()
                .orElse(null);
    }


}
