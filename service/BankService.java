package service;

import model.Account;
import model.SavingsAccount;
import util.Repository;
import util.AccountStore;
import util.StatementFormatter;
import util.AccountStorage;
import util.TransactionLogger;
import util.EndOfDayReport;
import exception.BankException;
import exception.AccountNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {

    // Practical 13: Generic repository
    private final Repository<Account> repository = new Repository<>();

    // Practical 12: Thread-safe account store
    private final AccountStore accountStore = new AccountStore();

    // Fast, thread-safe lookup for account operations
    private final ConcurrentHashMap<String, Account> accounts =
            new ConcurrentHashMap<>();

    public Account openAccount(String name, long openingBalance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (openingBalance < 0) {
            throw new IllegalArgumentException(
                "Opening balance cannot be negative."
            );
        }

        Account account = new SavingsAccount(name, openingBalance, 0);

        addAccount(account);

        log("OPEN_ACCOUNT | " + account.getAccountNumber() + " | " + name);

        return account;
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }

        accounts.put(account.getAccountNumber(), account);
        accountStore.add(account);
        repository.save(account);
    }

    public Account findAccount(String accountNumber)
            throws AccountNotFoundException {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(
                "Account not found: " + accountNumber
            );
        }

        return account;
    }

    public void displayAccount(Account account) {
        if (account == null) {
            System.out.println("Account not found.");
        } else {
            System.out.println(account);
        }
    }

    public void deposit(String accountNumber, long amount)
            throws BankException {

        Account account = findAccount(accountNumber);

        account.deposit(amount);

        log("DEPOSIT | " + accountNumber + " | " + amount);
    }

    public void withdraw(String accountNumber, long amount)
            throws BankException {

        Account account = findAccount(accountNumber);

        account.withdraw(amount);

        log("WITHDRAW | " + accountNumber + " | " + amount);
    }

    public void transfer(
            String fromNumber,
            String toNumber,
            long amount) throws BankException {

        Account from = findAccount(fromNumber);
        Account to = findAccount(toNumber);

        from.transfer(to, amount);

        log("TRANSFER | " + fromNumber + " -> "
                + toNumber + " | " + amount);
    }

    public String statement(String accountNumber)
            throws AccountNotFoundException {

        return StatementFormatter.buildStatement(
            findAccount(accountNumber)
        );
    }

    public ArrayList<Account> listAccounts() {
        return accountStore.getAll();
    }

    public ArrayList<String> getTransactionHistory() {
        return accountStore.getTransactionHistory();
    }

    public ArrayList<Account> sortedAccountsByBalance() {
        return accountStore.sortedByBalance();
    }

    public ArrayList<Account> accountsAboveBalance(long amount) {
        return accountStore.getAccountsAboveBalance(amount);
    }

    public void saveAccounts() throws IOException {
        AccountStorage.saveAccounts(
            listAccounts().toArray(new Account[0])
        );
    }

    public void loadAccounts() throws IOException, ClassNotFoundException {
        File file = new File("accounts.dat");

        if (!file.exists() || file.length() == 0) {
            return;
        }

        for (Account account : AccountStorage.loadAccounts()) {
            addAccount(account);
        }

        System.out.println("Accounts loaded into the thread-safe store.");
    }

    public void generateReport() throws IOException {
        EndOfDayReport.generate();
    }

    private void log(String message) {
        try {
            TransactionLogger.log(message);
        } catch (IOException e) {
            System.out.println(
                "Warning: transaction log failed: " + e.getMessage()
            );
        }
    }
}

