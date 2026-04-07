/*
Matthew Rozendaal
Modul 3.2
4/6/2026
A test program that contains a static method that returns a new ArrayList without duplicates
 */

import java.util.ArrayList;

public class RemoveDuplicatesFromArray {

    // Main method to test the removeDuplicates method
    public static void main(String[] args) {

        // Create an ArrayList with random integers, including duplicates
        ArrayList<Integer> originalList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            originalList.add((int) (Math.random() * 20) + 1);
        }
        System.out.println("Original Integer List: " + originalList);
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);
        System.out.println("Unique Integer List: " + uniqueList);

        System.out.println();
        // Create an ArrayList with random strings, including duplicates
        ArrayList<String> originalStringList = new ArrayList<>();
        String[] sampleStrings = {"apple", "banana", "orange", "grape", "kiwi"};
        for (int i = 0; i < 50; i++) {
            originalStringList.add(sampleStrings[(int) (Math.random() * sampleStrings.length)]);
        }
        System.out.println("Original String List: " + originalStringList);
        ArrayList<String> uniqueStringList = removeDuplicates(originalStringList);
        System.out.println("Unique String List: " + uniqueStringList);
    }

    // Method to remove duplicates from the ArrayList
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> uniqueList = new ArrayList<>();

        // Iterate through the original list and add unique elements to the uniqueList
        for (E element : list) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }

        //Sort the uniqueList in ascending order, doing this just because it was easier to read the output when it was sorted
        uniqueList.sort(null);

        // Return the list without duplicates
        return uniqueList;
    }
}
