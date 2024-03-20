package entities;

public interface Account {
    void withdraw(double amount);

    void deposit(double amount);

    String getType();

    String getAccCode();

    String getBranch();

    void setBranch(Branch branch);

    String getCustomer();

    double getBalance();

    void setBalance(double amount);

    double getLimit();

    void setLimit(double amount);

    String getCode();

//  -transactionsHistory()
//  -equals()
//  -hasCode()

}
