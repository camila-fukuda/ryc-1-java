package entities;

import java.util.HashMap;
import java.util.Map;

public class BusinessAccount extends AbstractAccount {
    private static final Map<String, String> FIELD_LABEL_TO_NAME = new HashMap<>();
    private static final AccountType type = AccountType.BUSINESS;

    static {
        FIELD_LABEL_TO_NAME.put("Branch Code", "branchCode");
        FIELD_LABEL_TO_NAME.put("Customer Document", "customerDocument");
    }

    public BusinessAccount(String accCode, Branch branch, Customer customer, double balance, double limit) {
        super(type, accCode, branch, customer, balance, limit);
    }

    public BusinessAccount(String accCode, Branch branch, Customer customer) {
        super(type, accCode, branch, customer);
    }

    public static Map<String, String> getFieldLabelToName() {
        return FIELD_LABEL_TO_NAME;
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
