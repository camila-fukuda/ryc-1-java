package entities;

public abstract class AbstractAccount implements Account {
    final protected String type;
    final protected String accCode;
    final protected Customer customer;
    private Branch branch;
    private double balance;
    private double limit;

    public AbstractAccount(String type, String accCode, Branch branch, Customer customer, double balance, double limit) {
        this.type = type;
        this.accCode = accCode;
        this.branch = branch;
        this.customer = customer;
        this.balance = balance;
        this.limit = limit;
    }

    public AbstractAccount(String type, String accCode, Customer customer, Branch branch) {
        this.type = type;
        this.accCode = accCode;
        this.branch = branch;
        this.customer = customer;
        this.balance = 0;
        this.limit = 0;
    }

    @Override
    public String toString() {
        return "AbstractAccount{" +
                "type='" + type + '\'' +
                ", accCode='" + accCode + '\'' +
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
        return accCode;
    }

    public String getBranch() {
        return branch.toString();
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getCustomer() {
        return customer.toString();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {
        balance = amount;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double amount) {
        limit = amount;
    }

//  -transactionsHistory()
//  -equals()
//  -hasCode()


}
