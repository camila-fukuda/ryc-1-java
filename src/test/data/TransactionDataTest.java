package test.data;

import data.TransactionData;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionDataTest {

    private TransactionData transactionData;

    @BeforeEach
    public void setUp() {
        TransactionData.transactions = new ArrayList<>();
    }

    @Test
    public void testAddTransaction() {
        Customer customer1 = new Customer("NAME1", "11");
        Customer customer2 = new Customer("NAME2", "12");
        Branch branch = new Branch("CITY", "ST", "BR1");
        Account account1 = new PersonAccount("ACP1", branch, customer1);
        Account account2 = new PersonAccount("ACP2", branch, customer2);

        Instant time1 = Instant.now();

        Transaction transaction1 = new Transaction(Transaction.TransactionType.WITHDRAW, 100.0, account1, time1);
        Transaction transaction2 = new Transaction(Transaction.TransactionType.WITHDRAW, 100.0, account2, time1);

        TransactionData.add(transaction1);
        TransactionData.add(transaction2);

        List<Transaction> transactions = TransactionData.getAll();
        assertEquals(2, transactions.size());
        assertEquals(transaction1, transactions.get(0));
        assertEquals(transaction2, transactions.get(1));
    }

    @Test
    public void testGetTransactionByAccount() {
        Customer customer1 = new Customer("NAME1", "11");
        Customer customer2 = new Customer("NAME2", "12");
        Branch branch = new Branch("CITY", "ST", "BR1");
        Account account1 = new PersonAccount("ACP1", branch, customer1);
        Account account2 = new PersonAccount("ACP2", branch, customer2);

        Instant time1 = Instant.now();

        Transaction transaction1 = new Transaction(Transaction.TransactionType.WITHDRAW, 100.0, account1, time1);
        Transaction transaction2 = new Transaction(Transaction.TransactionType.WITHDRAW, 100.0, account2, time1);
        TransactionData.add(transaction1);
        TransactionData.add(transaction2);

        Instant time2 = Instant.now();
        Transaction transaction3 = new Transaction(Transaction.TransactionType.WITHDRAW, 100.0, account1, time2);
        TransactionData.add(transaction3);

        List<Transaction> account1Transactions = TransactionData.getTransactionByAccount(account1);

        assertEquals(2, account1Transactions.size());
        assertEquals(transaction1, account1Transactions.get(0));
        assertEquals(transaction3, account1Transactions.get(1));

        List<Transaction> account2Transactions = TransactionData.getTransactionByAccount(account2);

        assertEquals(1, account2Transactions.size());
        assertEquals(transaction2, account2Transactions.get(0));
    }
}
