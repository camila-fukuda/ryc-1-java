package data;

import entities.Account;
import entities.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionData {
    static public List<Transaction> transactions = new ArrayList<>();

    static public List<Transaction> getAll() {
        return transactions;
    }

    static public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    static public List<Transaction> getTransactionByAccount(Account acc) {
        return transactions.stream()
                .filter(trans -> trans.account.equals(acc)).collect(Collectors.toList());

    }
}
