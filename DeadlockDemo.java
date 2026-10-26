public class DeadlockDemo {


private static final Object accountA = new Object();
private static final Object accountB = new Object();

public static void main(String[] args) throws InterruptedException {

    System.out.println("=== DEADLOCK DEMONSTRATION ===");

    Thread t1 = new Thread(() -> {
        synchronized (accountA) {
            System.out.println("Thread 1 locked Account A");
            sleep(100);

            System.out.println("Thread 1 waiting for Account B");

            synchronized (accountB) {
                System.out.println("Transfer A -> B completed");
            }
        }
    }, "Thread-1");

    Thread t2 = new Thread(() -> {
        synchronized (accountB) {
            System.out.println("Thread 2 locked Account B");
            sleep(100);

            System.out.println("Thread 2 waiting for Account A");

            synchronized (accountA) {
                System.out.println("Transfer B -> A completed");
            }
        }
    }, "Thread-2");

    // Daemon threads do not prevent the JVM from exiting.
    t1.setDaemon(true);
    t2.setDaemon(true);

    t1.start();
    t2.start();

    t1.join(1000);
    t2.join(1000);

    if (t1.isAlive() && t2.isAlive()) {
        System.out.println("Deadlock detected!");
    }

    System.out.println("\n=== DEADLOCK PREVENTION ===");

    Thread safe1 = new Thread(() -> safeTransfer("A -> B"));
    Thread safe2 = new Thread(() -> safeTransfer("B -> A"));

    safe1.start();
    safe2.start();

    safe1.join();
    safe2.join();

    System.out.println("Both safe transfers completed.");
}

private static void safeTransfer(String transfer) {
    // Use separate locks for the prevention demonstration.
    synchronized (DeadlockDemo.class) {
        System.out.println(
            Thread.currentThread().getName()
            + ": Transfer " + transfer + " completed safely."
        );
    }
}

private static void sleep(long milliseconds) {
    try {
        Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}


}
