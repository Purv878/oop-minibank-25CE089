import java.util.Scanner;

record BankInfo(String name, String branch) {}

enum MenuOption {
OPEN_ACCOUNT,
DEPOSIT,
WITHDRAW,
TRANSFER,
EXIT
}

public class MiniBank {


public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    BankInfo bank = new BankInfo("MiniBank", "CHARUSAT Branch");

    System.out.println("================================");
    System.out.println("          " + bank.name());
    System.out.println("          " + bank.branch());
    System.out.println("================================");

    MenuOption option;

    do {
        System.out.println("\n===== MINI BANK MENU =====");
        System.out.println("1. Open Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();

        option = switch (choice) {
            case 1 -> MenuOption.OPEN_ACCOUNT;
            case 2 -> MenuOption.DEPOSIT;
            case 3 -> MenuOption.WITHDRAW;
            case 4 -> MenuOption.TRANSFER;
            case 5 -> MenuOption.EXIT;
            default -> null;
        };

        if (option == null) {
            System.out.println("Invalid choice! Please try again.");
            continue;
        }

        switch (option) {
            case OPEN_ACCOUNT ->
                System.out.println("Open Account feature will be added soon.");
            case DEPOSIT ->
                System.out.println("Deposit feature will be added soon.");
            case WITHDRAW ->
                System.out.println("Withdraw feature will be added soon.");
            case TRANSFER ->
                System.out.println("Transfer feature will be added soon.");
            case EXIT ->
                System.out.println("Thank you for using MiniBank!");
        }

    } while (option != MenuOption.EXIT);

    sc.close();
}


}
