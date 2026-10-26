import model.Account;
import model.SavingsAccount;
import util.AccountStorage;
import util.TransactionLogger;
import util.EndOfDayReport;

public class PersistenceDemo {


public static void main(String[] args) {

    try {

        Account[] accounts = {
            new SavingsAccount("Purv Patel", 10000, 1000),
            new SavingsAccount("Rahul Patel", 5000, 500)
        };

        // Save accounts
        AccountStorage.saveAccounts(accounts);

        // Load accounts
        Account[] loadedAccounts = AccountStorage.loadAccounts();

        System.out.println("\n===== LOADED ACCOUNTS =====");

        for (Account account : loadedAccounts) {
            System.out.println(
                account.getAccountNumber()
                + " | "
                + account.getOwnerName()
                + " | Balance: "
                + account.getBalance()
            );
        }

        // Log sample transactions
        TransactionLogger.log(
            "DEPOSIT | Purv Patel | Amount: 1000"
        );

        TransactionLogger.log(
            "WITHDRAWAL | Rahul Patel | Amount: 500"
        );

        // Generate report
        EndOfDayReport.generate();

    } catch (Exception e) {
        System.out.println(
            "Persistence error: " + e.getMessage()
        );

        e.printStackTrace();
    }
}


}

