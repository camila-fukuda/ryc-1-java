package entities;

public class PersonAccount extends AbstractAccount {

    public PersonAccount(String accCode, Branch branch, Customer customer, double balance, double limit) {
        super("Person Account", accCode, branch, customer, balance, limit);
    }

    public PersonAccount(String accCode, Customer customer, Branch branch) {
        super("Person Account", accCode, customer, branch);
    }

    @Override
    public String getCode() {
        return accCode;
    }
}
