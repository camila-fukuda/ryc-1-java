package test.data;

import data.AccountData;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AccountDataTest {
    static public List<Account> accounts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        accounts = new ArrayList<>(AccountData.accounts);
    }

    @Test
    public void getAll() {
        assertEquals(accounts, AccountData.getAll());
    }


    @Test
    public void add() {
        Branch branch = new Branch("BARRETOS", "SP", "BR006");
        Customer customer = new Customer("CAMILA", "COD105");
        PersonAccount pAcc = new PersonAccount("ACP001", branch, customer);
        accounts.add(pAcc);

        assertEquals(2, AccountData.getAll().size());

        AccountData.add(pAcc);
        assertEquals(3, AccountData.getAll().size());
        assertEquals(accounts, AccountData.getAll());
    }

    @Test
    public void getAccount() {
        assertNull(AccountData.getAccount("ACP001"));

        Branch branch = new Branch("BARRETOS", "SP", "BR006");
        Customer customer = new Customer("CAMILA", "COD105");
        PersonAccount pAcc = new PersonAccount("ACP001", branch, customer);
        AccountData.add(pAcc);

        assertEquals(pAcc, AccountData.getAccount("ACP001"));

    }

    @Test
    public void getAccountByCustomer() {
        assertNull(AccountData.getAccount("ACP001"));
        assertNull(AccountData.getAccount("ACB001"));

        Branch branch = new Branch("BARRETOS", "SP", "BR006");
        Customer customer = new Customer("CAMILA", "COD105");
        PersonAccount pAcc = new PersonAccount("ACP001", branch, customer);
        AccountData.add(pAcc);
        BusinessAccount bAcc = new BusinessAccount("ACB001", branch, customer);
        AccountData.add(bAcc);

        List<Account> accToCheck = new ArrayList<>();
        accToCheck.add(pAcc);
        accToCheck.add(bAcc);

        assertEquals(bAcc, AccountData.getAccount("ACB001"));
        assertEquals(pAcc, AccountData.getAccount("ACP001"));


        assertEquals(accToCheck, AccountData.getAccountByCustomer(customer));

    }

    @Test
    public void getAccountByCustomerList() {
        assertNull(AccountData.getAccount("ACP001"));
        assertNull(AccountData.getAccount("ACP002"));

        Branch branch = new Branch("BARRETOS", "SP", "BR006");

        Customer customer1 = new Customer("CAMILA", "COD105");
        Customer customer2 = new Customer("ALEXANDRE", "COD106");

        PersonAccount pAcc1 = new PersonAccount("ACP001", branch, customer1);
        PersonAccount pAcc2 = new PersonAccount("ACP002", branch, customer2);

        AccountData.add(pAcc1);
        AccountData.add(pAcc2);

        List<Account> accToCheck = new ArrayList<>();
        accToCheck.add(pAcc1);
        accToCheck.add(pAcc2);

        List<Customer> customerToCheck = new ArrayList<>();
        customerToCheck.add(customer1);
        customerToCheck.add(customer2);

        assertEquals(pAcc1, AccountData.getAccount("ACP001"));
        assertEquals(pAcc2, AccountData.getAccount("ACP002"));


        assertEquals(accToCheck, AccountData.getAccountByCustomer(customerToCheck));

    }

    //    @Test
//    public void getCustomerByName() {
//        String name = "Maria";
//
//        assertEquals(customers.stream()
//                .filter(customer -> customer.name().equalsIgnoreCase(name))
//                .collect(Collectors.toList()), CustomerData.getCustomerByName(name));
//    }
//
//    @Test
//    public void getCustomerByDocument() {
//        assertEquals(customers.get(0), CustomerData.getCustomerByDocument(customers.get(0).document()));
//    }

}
