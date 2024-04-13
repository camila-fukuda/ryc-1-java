package entities;

public interface Account {

    void withdraw(double amount);

    void deposit(double amount);

    AbstractAccount.AccountType getType();

    String getAccCode();

    Branch getBranch();

    void setBranch(Branch branch);

    Customer getCustomer();

    double getBalance();

    void setBalance(double amount);

    double getLimit();

    void setLimit(double amount);

    String getCode();

//  -transactionsHistory()
//  -equals()
//  -hasCode()

}