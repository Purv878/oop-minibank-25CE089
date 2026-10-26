import model.Account;
import model.SavingsAccount;
import model.TransactionTask;
import service.TransactionProcessor;

public class TransactionProcessorDemo {

    public static void main(String[] args) {

        Account account = new SavingsAccount("Purv Patel", 10000, 0);

        TransactionProcessor processor = new TransactionProcessor();

        System.out.println("Initial Balance: " + account.getBalance());

        for (int i = 1; i <= 10; i++) {
            processor.submit(
                new TransactionTask(account, 100, true)
            );
        }

        for (int i = 1; i <= 5; i++) {
            processor.submit(
                new TransactionTask(account, 100, false)
            );
        }

        processor.stop();

        System.out.println("Final Balance: " + account.getBalance());
    }
}