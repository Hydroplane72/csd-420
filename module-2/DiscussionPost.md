
# Sort using recursion.

	Sorting in any language can be a challenge, depending on the size and volume of the data you are dealing with. Sorting a small 10-element list of ints won't take longer than a second to go through. Once you start getting into the hundreds, thousands, and even millions of element lists of ints. You start to understand that efficient sorting algorithms are generally the name of the game, and it is a good idea to have a passing familiarity with them. I have never, in all my years, needed to handwrite a sort function because the default sorting functionality in code was too slow. I will admit that I have gotten close when dealing with millions of rows of data and needing to sort them efficiently. Even then, we realized that creating a complex sorting mechanism for the data wasn't worth it, since we could sort it as we added to the table. Knowing that efficient sorting mechanisms exist is good. Don't stress yourself out if you have trouble understanding the options. I included a video that visualizes different sorting algorithms. It is very pleasing to watch without sound.
	The simplest recursive sorting algorithm I know is called "Recursive Insertion Sort". This sorting algorithm works by removing the last element from the list, recursively sorting the remaining elements, and then inserting the removed element back into the list in its correct position as the recursion unwinds. This algorithm is mainly used to teach those new to recursion and sorting algorithms how they work in general. Considering the Big O notation of this algorithm is not good at all. I recommend using the sorting mechanism built into your language of choice compared to this.

INSERT CODE EXAMPLE HERE


# How to print a directory structure file list using recursion

	Printing a directory structure is a great way to learn and understand recursion on your own computer. Every operating system I know of has built-in functionality that lets you enter a simple command and print the structure of your files on the computer. There are cases when you may want to iterate over an unknown number of files and search for a specific item. This scenario is a very good example of a case where searching directories recursively is a better choice compared to trying to create infinite loop(s) that do the same. Years ago, I was given the task of figuring out how to load "back up" files of a system and process them. The problem was that these files were spread across multiple folders, and each folder could contain thousands of backup files and folders. The ability to just recursively call the Search directory function really saved me some code.
 Below is a quick example of how you can call the PrintDirectory function to mimic what your own PC can do in a simple statement.


INSERT DIRECTORY CODE EXAMPLE HERE


Sorting visualization
https://www.youtube.com/watch?v=kPRA0W1kECg