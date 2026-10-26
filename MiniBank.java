import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

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

        System.out.println("\n===== EXCEPTION HANDLING =====");

        Account testAccount = new SavingsAccount("Test Customer", 1000, 0);

        try {
            testAccount.withdraw(5000);

        } catch (InsufficientFundsException e) {
            System.out.println(
                "Withdrawal failed: short by " + e.getShortfall()
            );

        } catch (InvalidAmountException e) {
            System.out.println("Invalid withdrawal: " + e.getMessage());

        } finally {
            System.out.println("Withdrawal attempt completed.");
        }

        try {
            testAccount.deposit(-500);

        } catch (InvalidAmountException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }

        System.out.println(
            "Test account balance: " + testAccount.getBalance()
        );

        System.out.println("\n===== MONEY TRANSFER =====");

        try {
            accounts[0].transfer(accounts[1], 2000);

        } catch (BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }

        System.out.println("\n===== TRY-WITH-RESOURCES =====");

        try (BankResource resource = new BankResource()) {
            System.out.println("Processing banking operation.");

        } catch (Exception e) {
            System.out.println("Resource error: " + e.getMessage());
        }

        System.out.println("\n===== UPDATED BALANCES =====");

        for (Account account : accounts) {
            System.out.println(
                account.getAccountNumber()
                + " | " + account.getOwnerName()
                + " | Balance: " + account.getBalance()
            );
        }

        System.out.println("\n===== MINIBANK COMPLETED =====");
    }
}