package test.entities;

import entities.Branch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BranchTest {

    @Test
    public void testConstructorValidState() {
        Branch branch = new Branch("New York", "NY", "NYC");
        assertEquals("New York", branch.city());
        assertEquals("NY", branch.state());
        assertEquals("NYC", branch.code());
    }

    @Test
    public void testConstructorInvalidState() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Branch("Los Angeles", "California", "LA");
        });

        assertEquals("State abbreviation must be exactly 2 characters long.", exception.getMessage());
    }

    @Test
    public void testValidState() {
        assertTrue(Branch.validState("NY"));
        assertFalse(Branch.validState("New York"));
        assertFalse(Branch.validState("N"));
        assertFalse(Branch.validState(""));
    }

    @Test
    public void testEquals() {
        Branch branch1 = new Branch("New York", "NY", "NYC");
        Branch branch2 = new Branch("New York", "NY", "NYC");
        Branch branch3 = new Branch("Los Angeles", "CA", "LA");

        assertEquals(branch1, branch2);
        assertNotEquals(branch1, branch3);
    }

    @Test
    public void testHashCode() {
        Branch branch1 = new Branch("New York", "NY", "NYC");
        Branch branch2 = new Branch("New York", "NY", "NYC");
        Branch branch3 = new Branch("Los Angeles", "CA", "LA");

        assertEquals(branch1.hashCode(), branch2.hashCode());
        assertNotEquals(branch1.hashCode(), branch3.hashCode());
    }

    @Test
    public void testToString() {
        Branch branch = new Branch("New York", "NY", "NYC");
        assertEquals("NYC - Branch {city='New York', state='NY', code='NYC'}", branch.toString());
    }
}
