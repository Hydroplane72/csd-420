Benefits of generics
	There are plenty of reasons why Generics will be both your greatest ally and also your number one enemy at times. The main benefit you should know about Generics is the ability to reduce code reuse without sacrificing code readability. When using Generics in code, I generally like to always have Generic Type Filters (AKA Boundary Type Parameters) in place for functions and classes that use Generics. Otherwise, it gets quite hard to account for every possible Type that could be thrown at your code. Adding a filter at least narrows down what you need to defend against. Below is a list of other benefits to using Generics in your code. 

No need to cast a type from the Object class
An alternative to Generics is to pass the Object class to the function and then cast the Type. The object Cast Method can be resource-intensive and is just not needed with Generic Types. 
Allows Type checking at compile time rather than run time.
Creation of Generic Algorithms
You can create a function that takes a Generic Type input and does the same logic, no matter the actual input. I generally use this for Database Access Objects, which we will get into later ... hopefully.
Easier readability and documentation
With just a single Function instead of multiple, it is easier to limit the noise in a function and actually make good comments in the function to make it more readable.

Use of generic methods.
	I love using generics for a lot of what I do day to day. Mostly because of the code reusability that is available when you start talking about using Generics in utility functions. A "utility function" is a function that can be reused throughout a project to reduce code duplication. For example, in the APIs I create, I have a utility function that uses filtered Generics so I can return the correct status code when returning data. If I didn't have this functionality, I would have to manually copy and paste the same structure for every response I wanted to give. The trick I use is to create a very simple interface that all the objects I return via the API use. This method also allows me to force others to follow a general guideline for updating the API. I like to do that because then the API is more stable, since you know what to expect at the very least with every response you get from an API I create.

Here is an example in Java of how to create a function that takes a Generic Filter (aka a bounded type parameter). Now, I admit that what I provided will not work exactly correctly because I am basing it on how I would code it in C# compared to Java. In C#, there are ways to instantiate a new HttpResponse in a return statement. In Java, you are not allowed to create a new HttpResponse. I am not going hunting for the correct code to return an HttpResponse based on whether the response had an error during processing.

INSERT GENERIC FUNCTION EXAMPLE HERE DON'T FORGET