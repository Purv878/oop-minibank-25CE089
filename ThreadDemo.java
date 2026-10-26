import model.Account;
import model.SavingsAccount;
import model.AccountWorker;

public class ThreadDemo {

    public static void main(String[] args) {

        Account account = new SavingsAccount("Thread Test", 1000, 0);

        int numberOfThreads = 10;
        int depositsPerThread = 100;
        long depositAmount = 10;

        Thread[] threads = new Thread[numberOfThreads];

        System.out.println("Initial Balance: " + account.getBalance());

        for (int i = 0; i < numberOfThreads; i++) {
            AccountWorker worker =
                new AccountWorker(account, depositsPerThread, depositAmount);

            threads[i] = new Thread(worker, "Worker-" + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread interrupted.");
                return;
            }
        }

        System.out.println("Expected Balance: 11000");
        System.out.println("Actual Balance: " + account.getBalance());
    }
}