package data;

import entities.Account;
import entities.Branch;
import entities.Customer;
import entities.PersonAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountData {
    static public List<Account> accounts = new ArrayList<>();

    static {
        BranchData.add(new Branch("BARRETOS", "SP", "BR-000006"));
        CustomerData.add(new Customer("CAMILA", "105"));
        Branch branch = BranchData.getBranch("BR-000006");
        Customer customer = CustomerData.getCustomerByDocument("105");

        accounts.add(new PersonAccount("AC001", customer, branch));
    }

    static public List<Account> getAll() {
        return accounts;
    }

    static public void add(Account account) {
        accounts.add(account);
    }

    static public Account getAccount(String code) {
        return accounts.stream()
                .filter(acc -> acc.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

//    static public boolean containsCode(String code) {
//        for (Account account : accounts) {
//            if (account.getCode().equals(code)) {
//                return true;
//            }
//        }
//        return false;
//    }

//    public static boolean containsEqual(Account newAccount) {
//        for (Account branch : accounts) {
//            if (branch.equals(newAccount)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
