package data;

import entities.Branch;

import java.util.ArrayList;
import java.util.List;

public class BranchData {
    static public List<Branch> branches = new ArrayList<>();

    static {
        branches.add(new Branch("BEBEDOURO", "SP", "BR001"));
        branches.add(new Branch("TAIUVA", "SP", "BR002"));
        branches.add(new Branch("UBERABA", "MG", "BR003"));
        branches.add(new Branch("BARRETOS", "SP", "BR004"));
    }

    static public List<Branch> getAll() {
        return branches;
    }

    static public void add(Branch branch) {
        branches.add(branch);
    }

    static public Branch getBranchByCode(String code) {
        return branches.stream()
                .filter(branch -> branch.code().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }


}
