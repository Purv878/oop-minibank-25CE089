public class ProducerConsumerDemo {

static class SharedBuffer {
    private final int[] buffer = new int[5];
    private int count = 0;
    private int in = 0;
    private int out = 0;

    public synchronized void produce(int value)
            throws InterruptedException {

        while (count == buffer.length) {
            wait();
        }

        buffer[in] = value;
        in = (in + 1) % buffer.length;
        count++;

        System.out.println("Produced: " + value);

        notify();
    }

    public synchronized int consume()
            throws InterruptedException {

        while (count == 0) {
            wait();
        }

        int value = buffer[out];
        out = (out + 1) % buffer.length;
        count--;

        System.out.println("Consumed: " + value);

        notify();

        return value;
    }
}

public static void main(String[] args) {

    SharedBuffer buffer = new SharedBuffer();
    int totalItems = 10;

    Thread producer = new Thread(() -> {
        try {
            for (int i = 1; i <= totalItems; i++) {
                buffer.produce(i);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }, "Producer");

    Thread consumer = new Thread(() -> {
        try {
            for (int i = 1; i <= totalItems; i++) {
                buffer.consume();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }, "Consumer");

    producer.start();
    consumer.start();

    try {
        producer.join();
        consumer.join();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }

    System.out.println("Producer-consumer completed.");
}
}
