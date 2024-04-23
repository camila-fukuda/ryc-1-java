package entities;

import java.time.Instant;
import java.util.Objects;

public class Transaction {
    public TransactionType type;
    public double amount;
    public Account account;
    public Instant time;

    public Transaction(TransactionType type, double amount, Account account, Instant time) {
        this.type = type;
        this.amount = amount;
        this.account = account;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(amount, that.amount) == 0 && type == that.type && Objects.equals(account, that.account) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amount, account, time);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", account=" + account +
                ", time=" + time +
                '}';
    }

    public enum TransactionType {
        WITHDRAW,
        TRANSFER_WITHDRAW,
        DEPOSIT,
        TRANSFER_DEPOSIT,
        GET_LOAN,
        PAY_LOAN,
        NEW_LIMIT,
        NEW_LOAN_LIMIT
    }
}
