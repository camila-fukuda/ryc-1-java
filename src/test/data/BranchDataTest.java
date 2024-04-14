package test.data;

import data.BranchData;
import entities.Branch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BranchDataTest {
    private List<Branch> branches;

    @BeforeEach
    void setUp() {
        branches = new ArrayList<>(BranchData.branches);

    }

    @Test
    public void getAll() {
        assertEquals(branches, BranchData.getAll());
    }


    @Test
    public void add() {
        Branch branch = new Branch("BARRETOS", "SP", "BR004");
        branches.add(branch);
        BranchData.add(branch);
        assertEquals(branches, BranchData.getAll());
    }

    @Test
    public void getBranchByCode() {
        String code = "br001";
        assertEquals(branches.get(0), BranchData.getBranchByCode(code));
    }

}
