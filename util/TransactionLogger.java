package util;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;

public class TransactionLogger {

private static final Path LOG_FILE =
        Paths.get("logs", "transactions.log");

public static void log(String message) throws IOException {

    Files.createDirectories(LOG_FILE.getParent());

    String entry = LocalDateTime.now()
            + " | " + message
            + System.lineSeparator();

    Files.writeString(
            LOG_FILE,
            entry,
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
    );

    System.out.println("Transaction logged.");
}


}
