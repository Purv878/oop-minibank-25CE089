public class MiniBank {


public static void main(String[] args) {

    System.out.println("===== MINIBANK =====");

    Customer c1 = new Customer(
        "Purv Patel", "purv@example.com", "9876543210"
    );

    c1.setAddress(
        new Customer.Address("Main Road", "Bayad", "383325")
    );

    System.out.println("\n===== CUSTOMER =====");
    System.out.println("Customer ID: " + c1.getCustomerId());
    System.out.println("Name: " + c1.getName());
    System.out.println("Address: " + c1.getAddress());

    Customer c2 = c1.clone();

    System.out.println("\n===== CLONED CUSTOMER =====");
    System.out.println("Customer ID: " + c2.getCustomerId());
    System.out.println("Name: " + c2.getName());
    System.out.println("Address: " + c2.getAddress());

    Account a1 = new Account("Purv Patel", 1500);
    Account a2 = new Account("Rahul Patel", 2000);

    System.out.println("\n===== ACCOUNT DETAILS =====");
    System.out.println(a1);
    System.out.println(a2);

    System.out.println("\n===== EQUALITY TEST =====");
    System.out.println("a1 equals a1: " + a1.equals(a1));
    System.out.println("a1 equals a2: " + a1.equals(a2));

    System.out.println("\n===== INSTANCEOF TEST =====");
    Object obj = a1;
    System.out.println("Is obj an Account? " + (obj instanceof Account));
}


}
