package util;

import model.Account;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class AccountStore {

    private final HashMap<String, Account> accounts = new HashMap<>();
    private final ArrayList<String> transactionHistory = new ArrayList<>();

    public void add(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }

        accounts.put(account.getAccountNumber(), account);
        transactionHistory.add(
            "Account added: " + account.getAccountNumber()
        );
    }

    public Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public ArrayList<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    public ArrayList<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> sortedAccounts = getAll();

        sortedAccounts.sort(
            Comparator.comparingLong(Account::getBalance).reversed()
        );

        return sortedAccounts;
    }

    public ArrayList<Account> getAccountsAboveBalance(long amount) {
        ArrayList<Account> result = new ArrayList<>();

        for (Account account : accounts.values()) {
            if (account.getBalance() > amount) {
                result.add(account);
            }
        }

        return result;
    }
}
