package test.entities;

import entities.*;
import entities.Transaction.TransactionType;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TransactionTest {

    @Test
    public void testEqualsAndHashCode() {
        Customer customer1 = new Customer("NAME1", "11");
        Customer customer2 = new Customer("NAME2", "12");
        Branch branch = new Branch("CITY", "ST", "BR1");
        Account account1 = new PersonAccount("ACP1", branch, customer1);
        Account account2 = new PersonAccount("ACP2", branch, customer2);

        Instant time1 = Instant.now();


        Transaction transaction1 = new Transaction(TransactionType.WITHDRAW, 100.0, account1, time1);
        Transaction transaction2 = new Transaction(TransactionType.WITHDRAW, 100.0, account1, time1);
        Transaction transaction3 = new Transaction(TransactionType.DEPOSIT, 100.0, account1, time1);
        Transaction transaction4 = new Transaction(TransactionType.WITHDRAW, 100.0, account2, time1);

        Instant time2 = Instant.now();
        Transaction transaction5 = new Transaction(TransactionType.WITHDRAW, 100.0, account1, time2);

        // Test equality
        assertEquals(transaction1, transaction2);
        assertNotEquals(transaction1, transaction3);
        assertNotEquals(transaction1, transaction4);
        assertNotEquals(transaction1, transaction5);

        // Test hash code consistency
        assertEquals(transaction1.hashCode(), transaction2.hashCode());
    }
}
