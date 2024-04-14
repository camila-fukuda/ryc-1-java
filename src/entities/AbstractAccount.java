package entities;

import java.util.Objects;

public abstract class AbstractAccount implements Account {

    protected final AccountType type;
    protected final String code;
    protected final Customer customer;
    private Branch branch;
    private double balance;
    private double limit;

    public AbstractAccount(AccountType type, String accCode, Branch branch, Customer customer, double balance, double limit) {
        validateCreation(type, accCode, customer, branch);
        this.type = type;
        this.code = accCode;
        this.branch = branch;
        this.customer = customer;
        this.balance = balance;
        this.limit = limit;
    }

    public AbstractAccount(AccountType type, String accCode, Branch branch, Customer customer) {
        validateCreation(type, accCode, customer, branch);
        this.type = type;
        this.code = accCode;
        this.branch = branch;
        this.customer = customer;
        this.balance = 0;
        this.limit = 0;
    }

    private void validateCreation(AccountType type, String accCode, Customer customer, Branch branch) {
        if (branch == null) {
            throw new IllegalArgumentException("The branch is required.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("The customer is required.");
        }
        if (accCode == null) {
            throw new IllegalArgumentException("The code is required.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance + limit) {
            balance -= amount;
        } else {
            System.out.print("ERROR: no limit.");
        }

    }

    public void deposit(double amount) {
        balance += amount;
    }

    public AccountType getType() {
        return type;
    }

    public String getAccCode() {
        return code;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        balance = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAccount that = (AbstractAccount) o;
        return Objects.equals(getType(), that.getType()) && Objects.equals(getCustomer(), that.getCustomer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getCustomer());
    }

    @Override
    public String toString() {
        return type +
                " ACCOUNT - {Code:" + code +
                " | " + customer +
                " | " + branch +
                " | Balance: " + balance +
                " | Limit: " + limit +
                '}';
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double amount) {
        limit = amount;
    }

    public void transactions() {
        System.out.println("transactions");
    }

    public enum AccountType {
        BUSINESS,
        PERSON
    }


}
