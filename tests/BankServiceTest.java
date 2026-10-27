package tests;
import model.Account;
import model.SavingsAccount;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    @Test
    void depositShouldIncreaseBalance() throws Exception {
        Account account = new SavingsAccount("Purv", 1000, 0);

        account.deposit(500);

        assertEquals(1500, account.getBalance());
    }

    @Test
    void withdrawalBelowMinimumBalanceShouldThrowException() {
        Account account = new SavingsAccount("Purv", 1000, 500);

        assertThrows(
            Exception.class,
            () -> account.withdraw(600)
        );

        assertEquals(1000, account.getBalance());
    }

    @Test
    void concurrentDepositsShouldMaintainCorrectBalance()
            throws InterruptedException {

        Account account = new SavingsAccount("Purv", 1000, 0);

        int numberOfThreads = 10;
        long amountPerDeposit = 100;

        CountDownLatch ready = new CountDownLatch(numberOfThreads);
        CountDownLatch start = new CountDownLatch(1);
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                ready.countDown();

                try {
                    start.await();
                    account.deposit(amountPerDeposit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            threads[i].start();
        }

        ready.await();
        start.countDown();

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(2000, account.getBalance());
    }
}
