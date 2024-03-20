package data;

import entities.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountData {
    static public List<Account> accounts = new ArrayList<>();

    static public List<Account> getAllAccounts() {
        return accounts;
    }

    static public void add(Account account) {
        accounts.add(account);
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
