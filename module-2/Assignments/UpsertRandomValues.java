
/**
 * Matthew Rozendaal
 * Module 2.2
 * Creates a file and writes random integers and doubles to it. If the file already exists, it appends the new data to the existing file.
 */
import java.io.FileWriter;
import java.nio.file.Paths;

public class UpsertRandomValues {

    public static void main(String[] args) {

        //To know where the file is being created, we can print the current working directory
        String currentDir = Paths.get("").toAbsolutePath().toString();
        System.out.println("Current Directory: " + currentDir);

        // Create an array of five random integers
        int[] randomIntegers = new int[5];
        for (int i = 0; i < randomIntegers.length; i++) {
            randomIntegers[i] = (int) (Math.random() * 100); // Random integers between 0 and 99
        }

        // Create an array of five random double values
        double[] randomDoubles = new double[5];
        for (int i = 0; i < randomDoubles.length; i++) {
            randomDoubles[i] = Math.random() * 100; // Random doubles between 0.0 and 100.0
        }

        // Write the data to a file titled "matthew_datafile.dat"
        String filename = currentDir + "/matthewrozendaal_datafile.dat";
        System.out.println("Writing to file: " + filename);
        try (FileWriter writer = new FileWriter(filename, true)) { // 'true' for appending

            // Start with [ for integers
            writer.write("[");
            // Write random integers to the file
            for (int i = 0; i < randomIntegers.length; i++) {
                writer.write(randomIntegers[i] + (i < randomIntegers.length - 1 ? ", " : "")); // Add comma and space between integers, but not after the last one
            }
            // End with ] for integers
            writer.write("]");

            // Write a new line after the integers
            writer.write("\n");

            // Start with [ for doubles
            writer.write("[");
            // Write random doubles to the file
            for (int i = 0; i < randomDoubles.length; i++) {
                writer.write(randomDoubles[i] + (i < randomDoubles.length - 1 ? ", " : "")); // Add comma and space between doubles, but not after the last one
            }
            // End with ] for doubles
            writer.write("]");

            // Write a new line after the doubles
            writer.write("\n");
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }
}
