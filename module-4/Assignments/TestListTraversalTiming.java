
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/*
Matthew Rozendaal
Module 4.2
4/6/2026
Tests the list traversal vs get(index) timing of a LinkedList

OVERALL RESULTS:
When testing, it is much faster to traverse a LinkedList using an iterator than using the get(index) method.
 This is because the get(index) method has to traverse the list from the beginning to the specified index each time, resulting in O(n) time complexity for each access.
 In contrast, using an iterator allows for O(1) time complexity for each access after the initial setup. 
 As the size of the list increases, the difference in time becomes even more pronounced, with the get(index) approach taking significantly longer as it has to repeatedly
 traverse the list for each access.
 
 With just 50,000 integers the difference is noticable at the nanosecond level, converting to seconds, the difference is negligible
 With 500,000 integers, the difference is much more significant, with the get(index) approach taking several seconds while the iterator traversal remains in the nanosecond range. 
 
Official results:
Size: 50000
Iterator traversal time: 2211700 nanoseconds
get(index) traversal time: 682585200 nanoseconds
Difference (get(index) - iterator): 680373500 nanoseconds
Size: 500000
Iterator traversal time: 0 seconds
get(index) traversal time: 56 seconds
Difference (get(index) - iterator): 56 seconds

Nanoseconds to seconds conversion:
680373500 nanosecond difference is approximately 0.68 seconds for just 50,000 integers
 */

 /*
Write a test program that stores 50,000 integers in a LinkedList and test the time to traverse the list using an iterator vs. using the get(index) method.

Test your program storing first 50,000 and then 500,000 integers.
After completing this program and having tested both values, in your comments, explain the results and discuss the time taken using both values and their difference with the get(index) approach.
Write test code that ensures the code functions correctly.
 */
public class TestListTraversalTiming {

    /**
     * The main method to run the test program.
     *
     * @param args
     */
    public static void main(String[] args) {
        // Test with 50,000 integers
        LinkedList<Integer> list50k = new LinkedList<>();
        for (int i = 0; i < 50000; i++) {
            list50k.add(i);
        }
        testTraversalTiming(list50k);

        // Test with 500,000 integers
        LinkedList<Integer> list500k = new LinkedList<>();
        for (int i = 0; i < 500000; i++) {
            list500k.add(i);
        }
        testTraversalTiming(list500k);
    }

    /**
     * Tests the traversal timing of a LinkedList using both an iterator and the
     * get(index) method.
     *
     * @param list the LinkedList to be tested
     */
    private static void testTraversalTiming(LinkedList<Integer> list) {
        // Timing iterator traversal
        long startTime = System.nanoTime();
        for (Integer num : list) {
            // Just iterating through the list
        }
        long endTime = System.nanoTime();
        long iteratorTime = endTime - startTime;

        // Timing get(index) traversal
        startTime = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        endTime = System.nanoTime();
        long getIndexTime = endTime - startTime;

        System.out.println("Size: " + list.size());
        //If the time is less than 1 second, print in nanoseconds, otherwise print in seconds
        if (TimeUnit.SECONDS.convert(getIndexTime - iteratorTime, TimeUnit.NANOSECONDS) < 1) {
            System.out.println("Iterator traversal time: " + iteratorTime + " nanoseconds");
            System.out.println("get(index) traversal time: " + getIndexTime + " nanoseconds");
            System.out.println("Difference (get(index) - iterator): " + (getIndexTime - iteratorTime) + " nanoseconds");
        } else {
            System.out.println("Iterator traversal time: " + TimeUnit.SECONDS.convert(iteratorTime, TimeUnit.NANOSECONDS) + " seconds");
            System.out.println("get(index) traversal time: " + TimeUnit.SECONDS.convert(getIndexTime, TimeUnit.NANOSECONDS) + " seconds");
            System.out.println("Difference (get(index) - iterator): " + TimeUnit.SECONDS.convert(getIndexTime - iteratorTime, TimeUnit.NANOSECONDS) + " seconds");
        }
    }

}
