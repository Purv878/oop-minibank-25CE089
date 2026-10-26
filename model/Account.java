package model;
public abstract class Account implements Transactable, InterestBearing {


private final String accountNumber;
private String ownerName;
protected long balance;
private boolean active;

private static int accountCounter = 0;

private static String generateAccountNumber() {
    accountCounter++;
    return String.format("AC%04d", accountCounter);
}

public Account(String ownerName, long openingBalance) {
    this.accountNumber = generateAccountNumber();
    this.ownerName = ownerName;
    this.balance = openingBalance;
    this.active = true;
}

public Account(String ownerName) {
    this(ownerName, 0);
}

public abstract double interestRate();

public abstract boolean canWithdraw(long amount);

@Override
public void deposit(long amount) {
    if (amount > 0) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    } else {
        System.out.println("Deposit amount must be positive.");
    }
}

@Override
public boolean withdraw(long amount) {
    if (canWithdraw(amount)) {
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
        return true;
    }

    System.out.println("Withdrawal failed.");
    return false;
}

public String getAccountNumber() {
    return accountNumber;
}

public String getOwnerName() {
    return ownerName;
}

public long getBalance() {
    return balance;
}

public boolean isActive() {
    return active;
}

@Override
public String toString() {
    return "Account{" +
            "accountNumber='" + accountNumber + '\'' +
            ", ownerName='" + ownerName + '\'' +
            ", balance=" + balance +
            ", active=" + active +
            '}';
}

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
    return java.util.Objects.hash(accountNumber);
}


}
