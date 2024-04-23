package entities;

import exceptions.InsufficientLoanLimitException;

import java.util.HashMap;
import java.util.Map;

public class BusinessAccount extends AbstractAccount {
    private static final Map<String, String> FIELD_LABEL_TO_NAME = new HashMap<>();
    private static final AccountType type = AccountType.BUSINESS;

    static {
        FIELD_LABEL_TO_NAME.put("Branch Code", "branchCode");
        FIELD_LABEL_TO_NAME.put("Customer Document", "customerDocument");
    }

    private double loanedAmount;
    private double loanLimit;

    public BusinessAccount(String accCode, Branch branch, Customer customer, double balance, double limit) {
        super(type, accCode, branch, customer, balance, limit);
        this.loanLimit = 0;
        this.loanedAmount = 0;
    }

    public BusinessAccount(String accCode, Branch branch, Customer customer, double balance) {
        super(type, accCode, branch, customer, balance);
        this.loanLimit = 0;
        this.loanedAmount = 0;
    }

    public BusinessAccount(String accCode, Branch branch, Customer customer, double balance, double limit, double loanLimit) {
        super(type, accCode, branch, customer, balance, limit);
        this.loanLimit = loanLimit;
        this.loanedAmount = 0;
    }

    public BusinessAccount(String accCode, Branch branch, Customer customer) {
        super(type, accCode, branch, customer);
        this.loanLimit = 0;
        this.loanedAmount = 0;
    }

    public static Map<String, String> getFieldLabelToName() {
        return FIELD_LABEL_TO_NAME;
    }

    public void setLoanLimit(double newLimit) {
        if (newLimit < loanedAmount) {
            throw new InsufficientLoanLimitException("The loaned amount is greater than the new limit, this operation is not possible..");
        } else {
            loanLimit = newLimit;
        }

    }

    public void getLoan(double amount) {
        if (loanLimit < loanedAmount + amount) {
            throw new InsufficientLoanLimitException("The loan limit is not sufficient.");
        } else {
            loanedAmount += amount;
        }

    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String getCode() {
        return code;
    }
}
