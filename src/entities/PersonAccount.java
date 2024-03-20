package entities;

import java.util.HashMap;
import java.util.Map;

public class PersonAccount extends AbstractAccount {
    private static final Map<String, String> FIELD_LABEL_TO_NAME = new HashMap<>();

    static {
        FIELD_LABEL_TO_NAME.put("Branch Code", "branchCode");
        FIELD_LABEL_TO_NAME.put("Customer Document", "customerDocument");
    }

    public PersonAccount(String accCode, Branch branch, Customer customer, double balance, double limit) {
        super("Person Account", accCode, branch, customer, balance, limit);
    }

    public PersonAccount(String accCode, Customer customer, Branch branch) {
        super("Person Account", accCode, customer, branch);
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
