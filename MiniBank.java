import model.Account;
import service.BankService;
import service.TransactionProcessor;
import exception.BankException;

import java.util.Scanner;
import java.io.IOException;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        try {
            bank.loadAccounts();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load accounts: " + e.getMessage());
        }

        boolean running = true;

        while (running) {
            System.out.println("\n===== MINI BANK MENU =====");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Account Statement");
            System.out.println("6. List Accounts");
            System.out.println("7. Generate End-of-Day Report");
            System.out.println("8. Batch Deposit Demo");
            System.out.println("0. Save and Exit");
            System.out.print("Choose option: ");

            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1": {
                        System.out.print("Owner name: ");
                        String name = sc.nextLine();

                        System.out.print("Opening balance: ");
                        long balance = Long.parseLong(sc.nextLine().trim());

                        Account account = bank.openAccount(name, balance);
                        System.out.println("Account created: "
                                + account.getAccountNumber());
                        break;
                    }

                    case "2": {
                        System.out.print("Account number: ");
                        String id = sc.nextLine().trim();

                        System.out.print("Deposit amount: ");
                        long amount = Long.parseLong(sc.nextLine().trim());

                        bank.deposit(id, amount);
                        System.out.println("Deposit successful.");
                        break;
                    }

                    case "3": {
                        System.out.print("Account number: ");
                        String id = sc.nextLine().trim();

                        System.out.print("Withdrawal amount: ");
                        long amount = Long.parseLong(sc.nextLine().trim());

                        bank.withdraw(id, amount);
                        System.out.println("Withdrawal successful.");
                        break;
                    }

                    case "4": {
                        System.out.print("From account: ");
                        String from = sc.nextLine().trim();

                        System.out.print("To account: ");
                        String to = sc.nextLine().trim();

                        System.out.print("Transfer amount: ");
                        long amount = Long.parseLong(sc.nextLine().trim());

                        bank.transfer(from, to, amount);
                        System.out.println("Transfer successful.");
                        break;
                    }

                    case "5": {
                        System.out.print("Account number: ");
                        String id = sc.nextLine().trim();
                        System.out.println(bank.statement(id));
                        break;
                    }

                    case "6": {
                        System.out.println("\n===== ALL ACCOUNTS =====");

                        for (Account account : bank.listAccounts()) {
                            bank.displayAccount(account);
                        }
                        break;
                    }

                    case "7": {
                        bank.generateReport();
                        break;
                    }

                    case "8": {
                        System.out.print("Account number: ");
                        String id = sc.nextLine().trim();

                        System.out.print("Deposit amount per transaction: ");
                        long amount = Long.parseLong(sc.nextLine().trim());

                        System.out.print("Number of deposits: ");
                        int count = Integer.parseInt(sc.nextLine().trim());

                        if (amount <= 0 || count <= 0 || count > 1000) {
                            System.out.println(
                                "Amount must be positive; count must be 1-1000."
                            );
                            break;
                        }

                        bank.findAccount(id);

                        try (BatchDeposit batch =
                                new BatchDeposit(bank, id, amount, count)) {
                            batch.runBatch();
                        }

                        System.out.println("Batch processing completed.");
                        break;
                    }

                    case "0": {
                        bank.saveAccounts();
                        System.out.println("Accounts saved. Goodbye!");
                        running = false;
                        break;
                    }

                    default:
                        System.out.println("Invalid menu option.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }

        sc.close();
    }

    private static class BatchDeposit implements AutoCloseable {
        private final BankService bank;
        private final String accountNumber;
        private final long amount;
        private final int count;

        BatchDeposit(BankService bank, String accountNumber,
                     long amount, int count) {
            this.bank = bank;
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.count = count;
        }

        public void runBatch() {
            TransactionProcessor processor = new TransactionProcessor();

            try {
                for (int i = 0; i < count; i++) {
                    processor.submit(() -> {
                        try {
                            bank.deposit(accountNumber, amount);
                        } catch (BankException e) {
                            System.out.println(
                                "Batch transaction failed: " + e.getMessage()
                            );
                        }
                    });
                }
            } finally {
                processor.stop();
            }
        }

        @Override
        public void close() {
            System.out.println("Batch deposit session closed.");
        }
    }
}

