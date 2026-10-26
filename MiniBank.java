public class MiniBank {


public static void main(String[] args) {

    System.out.println("===== MINIBANK PRACTICAL 4 =====");

    // Test validation
    System.out.println("\n===== VALIDATION =====");
    System.out.println(
            "Mobile valid: "
                    + Validator.isValidMobile("9876543210"));
    System.out.println(
            "Email valid: "
                    + Validator.isValidEmail("purv@example.com"));
    System.out.println(
            "PAN valid: "
                    + Validator.isValidPan("ABCDE1234F"));
    System.out.println(
            "IFSC valid: "
                    + Validator.isValidIfsc("SBIN0001234"));

    // Create an account
    Account account = new Account("Purv Patel", 1500);

    // Parse a transaction command
    System.out.println("\n===== COMMAND PARSER =====");

    Command command = CommandParser.parse("DEPOSIT AC0001 500");

    System.out.println("Type: " + command.type());
    System.out.println("Account: " + command.accountNumber());
    System.out.println("Amount: " + command.amount());

    // Format account statement
    System.out.println("\n===== STATEMENT =====");
    System.out.println(
            StatementFormatter.buildStatement(account));
}


}
