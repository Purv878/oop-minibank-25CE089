package model;

import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import model.annotation.Id;
import model.annotation.Positive;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

public abstract class Account
        implements Transactable, InterestBearing, Serializable, Comparable<Account> {

    private static final long serialVersionUID = 1L;

    @Id
    private final String accountNumber;

    private String ownerName;

    @Positive
    protected long balance;

    private boolean active;

    private static int accountCounter = 0;

    // Generate a unique account number safely.
    private static synchronized String generateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    // Update the counter using the number of an existing account.
    private static synchronized void updateAccountCounter(String accountNumber) {
        if (accountNumber != null && accountNumber.matches("AC\\d+")) {
            try {
                int number = Integer.parseInt(accountNumber.substring(2));

                if (number > accountCounter) {
                    accountCounter = number;
                }
            } catch (NumberFormatException e) {
                // Ignore malformed account numbers.
            }
        }
    }

    // Constructor
    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    // Update the counter whenever an account is deserialized.
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        in.defaultReadObject();
        updateAccountCounter(accountNumber);
    }

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);

    // Deposit money
    @Override
    public synchronized void deposit(long amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                "Deposit amount must be greater than zero."
            );
        }

        if (balance > Long.MAX_VALUE - amount) {
            throw new InvalidAmountException(
                "Deposit exceeds the maximum allowed balance."
            );
        }

        balance += amount;

        System.out.println("Deposited: " + amount);
    }

    // Withdraw money
    @Override
    public synchronized boolean withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                "Withdrawal amount must be greater than zero."
            );
        }

        if (!canWithdraw(amount)) {
            long shortfall;

            if (balance < 0 && amount > Long.MAX_VALUE + balance) {
                shortfall = Long.MAX_VALUE;
            } else {
                shortfall = Math.max(amount - balance, 0);
            }

            throw new InsufficientFundsException(
                "Insufficient funds for withdrawal.",
                shortfall
            );
        }

        balance -= amount;

        System.out.println("Withdrawn: " + amount);

        return true;
    }

    // Transfer money between accounts
    public void transfer(Account to, long amount) throws BankException {

        if (to == null) {
            throw new BankException(
                "Destination account cannot be null."
            );
        }

        if (to == this) {
            throw new BankException(
                "Cannot transfer money to the same account."
            );
        }

        if (amount <= 0) {
            throw new InvalidAmountException(
                "Transfer amount must be greater than zero."
            );
        }

        synchronized (this) {
            synchronized (to) {

                if (to.balance > Long.MAX_VALUE - amount) {
                    throw new InvalidAmountException(
                        "Transfer exceeds the recipient's maximum balance."
                    );
                }

                boolean withdrawn = false;

                try {
                    withdraw(amount);
                    withdrawn = true;

                    to.deposit(amount);

                    System.out.println(
                        "Transfer successful: " + amount
                    );

                } catch (BankException e) {

                    if (withdrawn) {
                        balance += amount;
                    }

                    throw e;

                } finally {
                    System.out.println("Transfer attempt finished.");
                }
            }
        }
    }

    // Get account number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Get owner name
    public String getOwnerName() {
        return ownerName;
    }

    // Get balance safely
    public synchronized long getBalance() {
        return balance;
    }

    // Check whether account is active
    public boolean isActive() {
        return active;
    }

    // Display account details
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", active=" + active +
                '}';
    }

    // Compare accounts using account number
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Account)) {
            return false;
        }

        Account other = (Account) obj;

        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }
}

