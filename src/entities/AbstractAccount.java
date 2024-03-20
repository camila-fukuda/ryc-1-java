package entities;

import java.util.Objects;

public abstract class AbstractAccount implements Account {

    final protected String type;
    final protected String code;
    final protected Customer customer;
    private Branch branch;
    private double balance;
    private double limit;

    public AbstractAccount(String type, String accCode, Branch branch, Customer customer, double balance, double limit) {
        validateCreation(type, accCode, customer, branch);
        this.type = type;
        this.code = accCode;
        this.branch = branch;
        this.customer = customer;
        this.balance = balance;
        this.limit = limit;
    }

    public AbstractAccount(String type, String accCode, Customer customer, Branch branch) {
        validateCreation(type, accCode, customer, branch);
        this.type = type;
        this.code = accCode;
        this.branch = branch;
        this.customer = customer;
        this.balance = 0;
        this.limit = 0;
    }

    private void validateCreation(String type, String accCode, Customer customer, Branch branch) {
        if (branch == null) {
            throw new IllegalArgumentException("The branch is not valid.");
        }
        if (customer == null) {
            throw new IllegalArgumentException("The customer is not valid");
        }
        if (accCode == null) {
            throw new IllegalArgumentException("The account code is required");
        }
    }

    @Override
    public String toString() {
        return "AbstractAccount{" +
                "type='" + type + '\'' +
                ", accCode='" + code + '\'' +
                ", customer=" + customer +
                ", branch=" + branch +
                '}';
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

    public String getType() {
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

    public double getLimit() {
        return limit;
    }

    public void setLimit(double amount) {
        limit = amount;
    }

    public void transactions() {
        System.out.println("transactions");
    }


}
