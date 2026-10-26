package util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class EndOfDayReport {


public static void generate() throws IOException {

    Path logFolder = Paths.get("logs");
    Path reportFile = Paths.get("end-of-day-report.txt");

    StringBuilder report = new StringBuilder();

    report.append("===== MINI BANK END-OF-DAY REPORT =====\n");

    if (Files.exists(logFolder)) {

        try (Stream<Path> paths = Files.walk(logFolder)) {

            for (Path file : paths
                    .filter(Files::isRegularFile)
                    .toList()) {

                BasicFileAttributes attributes =
                        Files.readAttributes(
                                file,
                                BasicFileAttributes.class
                        );

                report.append("\nFile: ")
                        .append(file.getFileName())
                        .append("\nSize: ")
                        .append(attributes.size())
                        .append(" bytes\n");

                report.append("Contents:\n");

                report.append(Files.readString(file))
                        .append("\n");
            }
        }
    } else {
        report.append("\nNo log directory found.\n");
    }

    Files.writeString(reportFile, report.toString());

    System.out.println("End-of-day report generated: "
            + reportFile.toAbsolutePath());
}


}
