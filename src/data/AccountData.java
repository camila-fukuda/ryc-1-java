package data;

import entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountData {
    static public List<Account> accounts = new ArrayList<>();

    static {
        Branch branch = BranchData.getBranchByCode("BR004");
        Customer customer = CustomerData.getCustomerByDocument("DOC105");

        accounts.add(new PersonAccount("ACP000", branch, customer));
        accounts.add(new BusinessAccount("ACB000", branch, customer));
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

    static public List<Account> getAccountByCustomer(Customer customer) {
        return accounts.stream()
                .filter(acc -> acc.getCustomer().equals(customer)).collect(Collectors.toList());

    }

    static public List<Account> getAccountByCustomer(List<Customer> customers) {
        return accounts.stream()
                .filter(acc -> customers.stream().anyMatch(customer -> customer.equals(acc.getCustomer())))
                .collect(Collectors.toList());
    }
}
