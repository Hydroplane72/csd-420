/*
Matthew Rozendaal
Module 6.2
Bubble Sort with Generics by using Comparable and Comparator interfaces
 */
import java.util.Arrays;
import java.util.Comparator;

public class BubbleSortGenerics {

    /**
     * Sorts an array in ascending order using each element's natural ordering.
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] array) {
        // Tracks whether any swap happened in the current pass.
        boolean swapped;

        // Each outer-loop pass places the next-largest element at the end.
        for (int outerloop = 0; outerloop < array.length - 1; outerloop++) {
            swapped = false;

            // The last i elements are already in final position.
            for (int innerLoop = 0; innerLoop < array.length - 1 - outerloop; innerLoop++) {
                // Swap adjacent elements that are out of order.
                if (array[innerLoop].compareTo(array[innerLoop + 1]) > 0) {
                    // Standard adjacent swap.
                    T temp = array[innerLoop];
                    array[innerLoop] = array[innerLoop + 1];
                    array[innerLoop + 1] = temp;
                    // Mark that this pass changed the array.
                    swapped = true;
                }
            }

            // Stop early if the array is sorted.
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Sorts an array using a caller-provided comparison strategy.
     */
    public static <T> void bubbleSort(T[] array, Comparator<? super T> comparator) {
        // Tracks whether any swap happened in the current pass.
        boolean swapped;

        // Each outer-loop pass places the next-largest element at the end.
        for (int outerloop = 0; outerloop < array.length - 1; outerloop++) {
            swapped = false;

            // The last i elements are already in final position.
            for (int innerLoop = 0; innerLoop < array.length - 1 - outerloop; innerLoop++) {
                // Comparator decides ordering instead of natural Comparable order.
                if (comparator.compare(array[innerLoop], array[innerLoop + 1]) > 0) {
                    // Standard adjacent swap.
                    T temp = array[innerLoop];
                    array[innerLoop] = array[innerLoop + 1];
                    array[innerLoop + 1] = temp;
                    // Mark that this pass changed the array.
                    swapped = true;
                }
            }

            // Stop early if no swaps happened in this pass.
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Example 1: natural ascending order (Comparable).
        Integer[] numbers = {5, 2, 9, 1, 5, 6};
        bubbleSort(numbers);
        System.out.println("Sorted with Comparable: " + Arrays.toString(numbers));

        // Example 2: custom order by string length (Comparator).
        String[] words = {"pear", "apple", "banana", "kiwi"};
        bubbleSort(words, Comparator.comparingInt(String::length));
        System.out.println("Sorted with Comparator (by length): " + Arrays.toString(words));

        // Example 3: order by first character (Comparator).
        bubbleSort(words, Comparator.comparingInt(s -> s.charAt(0)));
        System.out.println("Sorted with Comparator (by first character): " + Arrays.toString(words));
    }
}
