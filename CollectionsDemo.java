import model.Account;
import model.SavingsAccount;
import util.AccountStore;

public class CollectionsDemo {

    public static void main(String[] args) {

        AccountStore store = new AccountStore();

        Account a1 = new SavingsAccount("Purv Patel", 5000, 1000);
        Account a2 = new SavingsAccount("Rahul Patel", 2000, 500);
        Account a3 = new SavingsAccount("Amit Patel", 9000, 1000);

        store.add(a1);
        store.add(a2);
        store.add(a3);

        System.out.println("===== SEARCH ACCOUNT =====");
        Account found = store.get("AC0002");

        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Account not found.");
        }

        System.out.println("\n===== ACCOUNTS SORTED BY BALANCE =====");

        for (Account account : store.sortedByBalance()) {
            System.out.println(
                account.getAccountNumber() + " | " +
                account.getOwnerName() + " | Balance: " +
                account.getBalance()
            );
        }

        System.out.println("\n===== TRANSACTION HISTORY =====");

        for (String transaction : store.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}

