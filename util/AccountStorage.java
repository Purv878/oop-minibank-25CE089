package util;

import model.Account;
import java.io.*;
import java.nio.file.*;

public class AccountStorage {


private static final String FILE_NAME = "accounts.dat";

public static void saveAccounts(Account[] accounts)
        throws IOException {

    try (ObjectOutputStream out = new ObjectOutputStream(
            Files.newOutputStream(Paths.get(FILE_NAME)))) {

        out.writeObject(accounts);
        System.out.println("Accounts saved successfully.");
    }
}

public static Account[] loadAccounts()
        throws IOException, ClassNotFoundException {

    try (ObjectInputStream in = new ObjectInputStream(
            Files.newInputStream(Paths.get(FILE_NAME)))) {

        Account[] accounts = (Account[]) in.readObject();

        System.out.println("Accounts loaded successfully.");

        return accounts;
    }
}


}
