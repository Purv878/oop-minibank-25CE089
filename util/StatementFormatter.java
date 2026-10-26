package util;
import model.Account;

public class StatementFormatter {


public static String buildStatement(Account account) {

    return "========== ACCOUNT STATEMENT ==========\n"
            + "Account Number: " + account.getAccountNumber() + "\n"
            + "Account Holder: " + account.getOwnerName() + "\n"
            + "Balance: " + account.getBalance() + "\n"
            + "Account Active: " + account.isActive() + "\n"
            + "=======================================";
}


}

