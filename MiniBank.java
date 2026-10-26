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
        System.out.println("Interest Rate: " + account.interestRate() + "%");
        System.out.println("Yearly Interest: " + account.yearlyInterest());
    }

    System.out.println("\n===== WITHDRAWAL RULES =====");

    // Anonymous class
    WithdrawRule rule1 = new WithdrawRule() {
        @Override
        public boolean allow(Account account, long amount) {
            return account.canWithdraw(amount);
        }
    };

    // Lambda expression
    WithdrawRule rule2 = (account, amount) ->
            account.canWithdraw(amount);

    System.out.println(
            "Anonymous class: " + rule1.allow(accounts[0], 2000)
    );

    System.out.println(
            "Lambda expression: " + rule2.allow(accounts[0], 2000)
    );

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
