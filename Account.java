public class Account {


private final String accountNumber;
private String ownerName;
private long balance;
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

public void deposit(long amount) {
    if (amount > 0) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    } else {
        System.out.println("Deposit amount must be positive.");
    }
}

public boolean withdraw(long amount) {
    if (amount > 0 && balance >= amount) {
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
        return true;
    }

    System.out.println(
        "Withdrawal failed: insufficient balance or invalid amount."
    );
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


}
