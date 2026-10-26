import model.Account;
import service.BankService;

public class CapstoneDemo {

    public static void main(String[] args) {

        BankService bank = new BankService();

        Account a1 = bank.openAccount("Purv Patel", 5000);
        Account a2 = bank.openAccount("Rahul Patel", 2000);

        System.out.println("===== MINI BANK CAPSTONE =====");

        System.out.println("\n--- Initial Accounts ---");
        for (Account account : bank.listAccounts()) {
            System.out.println(account);
        }

        try {
            bank.deposit(a1.getAccountNumber(), 1000);
            bank.withdraw(a2.getAccountNumber(), 500);

            System.out.println("\n--- Account Statement ---");
            System.out.println(bank.statement(a1.getAccountNumber()));

            bank.transfer(
                a1.getAccountNumber(),
                a2.getAccountNumber(),
                500
            );

        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }

        System.out.println("\n--- Final Accounts ---");
        for (Account account : bank.listAccounts()) {
            System.out.println(account);
        }

        System.out.println("\nCapstone demo completed.");
    }
}

