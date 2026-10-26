public class MiniBank {


public static void main(String[] args) {

    Account[] accounts = {
        new SavingsAccount("Purv Patel", 10000, 1000),
        new CurrentAccount("Rahul Patel", 5000, 2000),
        new FixedDepositAccount("Amit Patel", 20000)
    };

    System.out.println("===== MINIBANK =====");

    for (Account account : accounts) {
        System.out.println("\n" + account);
        System.out.println(
                "Interest Rate: " + account.interestRate() + "%"
        );
    }

    System.out.println("\n===== TRANSACTIONS =====");

    accounts[0].withdraw(2000);
    accounts[1].withdraw(6000);
    accounts[2].withdraw(1000);

    System.out.println("\n===== UPDATED BALANCES =====");

    for (Account account : accounts) {
        System.out.println(
                account.getAccountNumber()
                + " | " + account.getOwnerName()
                + " | Balance: " + account.getBalance()
        );
    }
}


}
