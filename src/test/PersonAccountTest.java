package test;

import entities.AbstractAccount;
import entities.Branch;
import entities.Customer;
import entities.PersonAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PersonAccountTest {

    @Test
    public void constructorWithValidArgumentsWithBalanceAndLimit() {
        Customer customer = new Customer("CustomerName", "CustomerID");
        Branch branch = new Branch("cityBranch", "ST", "BR-1");
        AbstractAccount account = new PersonAccount("ACC-1", branch, customer, 100.0, 50.0);

        assertEquals(AbstractAccount.AccountType.PERSON, account.getType());
        assertEquals("ACC-1", account.getAccCode());
        assertEquals(customer, account.getCustomer());
        assertEquals(branch, account.getBranch());
        assertEquals(100.0, account.getBalance());
        assertEquals(50.0, account.getLimit());
    }

    @Test
    public void constructorWithValidArguments() {
        Customer customer = new Customer("CustomerName", "CustomerID");
        Branch branch = new Branch("cityBranch", "ST", "BR-1");
        AbstractAccount account = new PersonAccount("ACC-1", branch, customer);

        assertEquals(AbstractAccount.AccountType.PERSON, account.getType());
        assertEquals("ACC-1", account.getAccCode());
        assertEquals(customer, account.getCustomer());
        assertEquals(branch, account.getBranch());
        assertEquals(0, account.getBalance());
        assertEquals(0, account.getLimit());
    }

    @Test
    public void testConstructorWithNullBranch() {
        Customer customer = new Customer("CustomerName", "CustomerID");
        assertThrows(IllegalArgumentException.class, () -> new PersonAccount("ACC-1", null, customer, 100.0, 50.0));
    }

    @Test
    public void testConstructorWithNullCustomer() {
        Branch branch = new Branch("cityBranch", "ST", "BR-1");
        assertThrows(IllegalArgumentException.class, () -> new PersonAccount("ACC-1", branch, null, 100.0, 50.0));
    }

    @Test
    public void testConstructorWithNullAccCode() {
        Branch branch = new Branch("cityBranch", "ST", "BR-1");
        Customer customer = new Customer("CustomerName", "CustomerID");
        assertThrows(IllegalArgumentException.class, () -> new PersonAccount(null, branch, customer, 100.0, 50.0));
    }
}
