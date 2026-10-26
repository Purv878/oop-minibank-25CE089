public class BankResource implements AutoCloseable {

    public BankResource() {
        System.out.println("Bank resource opened.");
    }

    @Override
    public void close() {
        System.out.println("Bank resource closed.");
    }
}