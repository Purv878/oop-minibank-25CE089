import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
public static void main(String[] args) {

    Path folder = Paths.get("logs");
    String keyword = "ERROR";

    try {
        Files.createDirectories(folder);

        // Create sample log files if they do not exist
        Path file1 = folder.resolve("app1.txt");
        Path file2 = folder.resolve("app2.txt");

        if (!Files.exists(file1)) {
            Files.write(file1, List.of(
                "Application started",
                "ERROR: Database connection failed",
                "User logged in"
            ));
        }

        if (!Files.exists(file2)) {
            Files.write(file2, List.of(
                "Server started",
                "ERROR: Invalid request",
                "Application stopped",
                "ERROR: Timeout occurred"
            ));
        }

        int totalLines = 0;
        int matchingLines = 0;

        try (DirectoryStream<Path> files =
                     Files.newDirectoryStream(folder, "*.txt")) {

            for (Path file : files) {

                List<String> lines = Files.readAllLines(file);

                int fileMatches = 0;

                for (String line : lines) {
                    totalLines++;

                    if (line.contains(keyword)) {
                        matchingLines++;
                        fileMatches++;
                    }
                }

                BasicFileAttributes attributes =
                        Files.readAttributes(
                            file, BasicFileAttributes.class
                        );

                System.out.println("\nFile: " + file.getFileName());
                System.out.println("Size: " + attributes.size() + " bytes");
                System.out.println("Total lines: " + lines.size());
                System.out.println(
                    "Lines containing " + keyword + ": " + fileMatches
                );
            }
        }

        System.out.println("\n===== SUMMARY =====");
        System.out.println("Total lines across files: " + totalLines);
        System.out.println(
            "Total lines containing " + keyword + ": " + matchingLines
        );

    } catch (IOException e) {
        System.out.println("File operation failed: " + e.getMessage());
    }
}


}
