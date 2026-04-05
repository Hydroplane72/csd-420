
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
/**
 * Matthew Rozendaal 
 * Module 6.2 
 * Reads a collection of words from a file and
 * finds the unique words, counts them, and prints them in both ascending and
 * descending order.
 */
public class FindUniqueWords {

    public static void main(String[] args) {
        // To know where the file is being read from, we can print the current working directory
        String currentDir = Paths.get("").toAbsolutePath().toString();
        System.out.println("Current Directory: " + currentDir);

        // The filename to read from
        String filename = currentDir + "/collection_of_words.txt";

        // open the file and read the words into a list
        List<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return;
        }

        // use a set to find unique words
        Set<String> uniqueWords = new HashSet<>(words);

        // print the number of words read and the number of unique words
        System.out.println("Total Words Read: " + words.size());
        System.out.println("Unique Words Found: " + uniqueWords.size());
        System.out.println();

        // convert the set back to a list and sort it in ascending order
        List<String> sortedUniqueWords = new ArrayList<>(uniqueWords);
        Collections.sort(sortedUniqueWords);
        System.out.println("Unique Words in Ascending Order:");
        sortedUniqueWords.forEach(System.out::println);

        // sort the list in descending order
        Collections.sort(sortedUniqueWords, Collections.reverseOrder());
        System.out.println("\nUnique Words in Descending Order:");
        sortedUniqueWords.forEach(System.out::println);

    }
}
