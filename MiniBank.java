import model.Account;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.SavingsAccount;
import model.WithdrawRule;
import service.BankService;
import util.AnnotationValidator;

import static util.Validator.*;

public class MiniBank {


public static void main(String[] args) {

    // Annotation validation
    System.out.println("===== ANNOTATION VALIDATION =====");

    Account invalidAccount =
            new SavingsAccount("Test User", -100, 0);

    String[] errors = AnnotationValidator.validate(invalidAccount);

    if (errors.length == 0) {
        System.out.println("No validation errors found.");
    } else {
        for (String error : errors) {
            System.out.println(error);
        }
    }

    // Main application
    System.out.println("\n===== MINIBANK =====");

    System.out.println("Valid mobile: " + isValidMobile("9876543210"));
    System.out.println("Invalid mobile: " + isValidMobile("12345"));

    Account[] accounts = {
        new SavingsAccount("Purv Patel", 10000, 1000),
        new CurrentAccount("Rahul Patel", 5000, 2000),
        new FixedDepositAccount("Amit Patel", 20000)
    };

    BankService bankService = new BankService();

    for (Account account : accounts) {
        System.out.println();
        bankService.displayAccount(account);
    }

    // Anonymous class and lambda
    System.out.println("\n===== WITHDRAWAL RULES =====");

    WithdrawRule rule1 = new WithdrawRule() {
        @Override
        public boolean allow(Account account, long amount) {
            return account.canWithdraw(amount);
        }
    };

    WithdrawRule rule2 =
            (account, amount) -> account.canWithdraw(amount);

    System.out.println(
            "Anonymous class: " + rule1.allow(accounts[0], 2000)
    );

    System.out.println(
            "Lambda expression: " + rule2.allow(accounts[0], 2000)
    );

    // Transactions
    System.out.println("\n===== TRANSACTIONS =====");

    accounts[0].withdraw(2000);
    accounts[1].withdraw(6000);
    accounts[2].withdraw(1000);

    // Updated balances
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
