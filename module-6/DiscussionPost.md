The following topics cover Java features. Choose one topic from Group 1 and one topic from Group 2 for your initial post. For each of the topics you have selected, provide code examples to elaborate on your thoughts.



Group 1

Enums
Wrapper Classes
Regular Expressions


Group 2

Comparable
Comparator


# Enums
Enums, short for enumerations, are a special data type in Java that allows you to define a collection of constants. They are useful for representing a fixed set of related values, such as days of the week, months of the year, or directions. I personally like to use enums for representing "SubTypes" in objects. For example, if I were to reference the movie database from the database class from earlier in our schooling. There were a set of ratings that a movie could be given. These ratings were G, PG, PG-13, R, and NC-17. I could create an enum to represent these ratings as follows:

```java
public enum MovieRating {
    G= 1,
    PG = 2,
    PG_13 = 3,
    R = 4,
    NC_17 = 5
}
```

A little word of advice I do have for using enums is to be careful when you are using them in a switch statement. It is a good practice to include setting the numeric values for each enum constant yourself instead of letting Java assign them automatically. If you add a new value to the enum, you will need to update all of the switch statements that reference that enum. Otherwise, if you add a value in the middle of the enum, it will shift the numeric values of the subsequent constants. This can lead to very difficult to identify bugs if you forget to update one of the switch statements. Please don't ask me how and why I know this, just learn from my mistakes and don't let it happen to you.


# Comparable
The Comparable interface in Java is used to define the natural ordering of objects. It provides a single method, compareTo(), which compares the current object with another object of the same type. The compareTo() method returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object. To be honest I don't really use this day to day because that would require me to make my plain old objects do more than just be plain old objects. Normally if I needed to sort by something like Movie year, I would do a LINQ statement to do so. 

In the interest of this assignment though, here is an example of if I had a list of movies and I wanted to sort them by their release year, I could implement the Comparable interface in my Movie class as follows:

```java
public class Movie implements Comparable<Movie> {
    private String title;
    private int releaseYear;

    public Movie(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    @Override
    public int compareTo(Movie other) {
        return Integer.compare(this.releaseYear, other.releaseYear);
    }
}
```