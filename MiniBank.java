record BankInfo(String name, String branch) {}

public class MiniBank {


public static void main(String[] args) {

    BankInfo bank = new BankInfo("MiniBank", "CHARUSAT Branch");

    System.out.println("================================");
    System.out.println("          " + bank.name());
    System.out.println("          " + bank.branch());
    System.out.println("================================");

    // Create three customers
    Customer c1 = new Customer(
        "Purv Patel", "purv@example.com", "9876543210"
    );

    Customer c2 = new Customer(
        "Rahul Patel", "rahul@example.com", "9876543211"
    );

    Customer c3 = new Customer(
        "Amit Patel", "amit@example.com", "9876543212"
    );

    // Display customer details
    System.out.println("\n===== CUSTOMERS =====");

    System.out.println(c1.getCustomerId() + " - " + c1.getName());
    System.out.println(c2.getCustomerId() + " - " + c2.getName());
    System.out.println(c3.getCustomerId() + " - " + c3.getName());

    // Create three accounts
    Account[] accounts = {
        new Account(c1.getName(), 1000),
        new Account(c2.getName(), 2000),
        new Account(c3.getName())
    };

    // Perform transactions
    accounts[0].deposit(500);
    accounts[1].withdraw(300);
    accounts[2].deposit(1000);

    // Display account details
    System.out.println("\n===== ACCOUNT DETAILS =====");

    for (Account account : accounts) {
        System.out.println(
            account.getAccountNumber()
            + " | Owner: " + account.getOwnerName()
            + " | Balance: " + account.getBalance()
            + " | Active: " + account.isActive()
        );
    }
}


}
