package model;

public class TransactionTask implements Runnable {

    private final Account account;
    private final long amount;
    private final boolean isDeposit;

    public TransactionTask(Account account, long amount, boolean isDeposit) {
        this.account = account;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    @Override
    public void run() {
        try {
            if (isDeposit) {
                account.deposit(amount);

                System.out.println(
                    Thread.currentThread().getName()
                    + " deposited " + amount
                );
            } else {
                account.withdraw(amount);

                System.out.println(
                    Thread.currentThread().getName()
                    + " withdrew " + amount
                );
            }
        } catch (Exception e) {
            System.out.println("Transaction failed: " + e.getMessage());
        }
    }
}