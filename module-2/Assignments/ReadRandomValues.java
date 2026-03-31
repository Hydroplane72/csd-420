
import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Matthew Rozendaal Module 2.2 Reads the random integers and doubles from the
 * file created in UpsertRandomValues.java and prints them to the console.
 */
public class ReadRandomValues {

    public static void main(String[] args) {
        // To know where the file is being read from, we can print the current working directory
        String currentDir = Paths.get("").toAbsolutePath().toString();
        System.out.println("Current Directory: " + currentDir);

        // The filename to read from
        String filename = currentDir + "/matthewrozendaal_datafile.dat";
        System.out.println("Reading from file: " + filename);

        // Read the data from the file and print it to the console
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading from the file.");
        }
    }
}
