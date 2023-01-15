![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
﻿# CS 445 Recitation 10: Iterators

## Introduction

In this recitation, you will implement several clients of an Iterable collection
that use iterators. You will first write two simple methods to iterate over a
collection, then you will write a method to find the most common element (mode)
in a a collection. The primary goal is to practice using the traversal behavior
that is provided by the iterator object.

## Exercise

1. The starting code for this exercise, as usual, is found in subdirectory
`app/src/main/java/`. Navigate to this subdirectory, and note the following
provided Java files:

   - `IterableUtils.java` (in package `cs445.rec10`) contains stubs for the
     methods you will write in this exercise. In each, you will complete a task
     using **only** iterators for a collection.
   - `Demo.java` (in package `cs445.rec10`) contains a client for testing the
     `printAll`, `removeShortStrings`, and `findMode` methods.
   - `ListInterface.java` (in package `cs445.list`) contains the list ADT. Note
     that the techniques you practice in this exercise are useful in any
     `Iterable` collection; we’re using a list simply as an example.
   - `ArrayList.java` (in package `cs445.list`) contains an array-based
     implementation of the ADT list, using a resizable array.

2. In the class `IterableUtils`, within the `cs445.rec10` package, write the
generic method `static <T> void printAll(Iterable<T> collection)`, which prints
the contents of any `Iterable` collection. You should rely entirely on
iterators; do not cast the collection or otherwise try to use the List’s
`.get()` method. Test that your method works properly.

3. Check if your `printAll` method works using `./gradlew run`, which will run
`Demo.java`.

4. In the same class `IterableUtils`, write the method `static void
removeShortStrings(Iterable<String> collection, int limit)`, which removes all
strings shorter than `limit`. Again, rely only on iterators for element access,
and test that your method works. Do not attempt to use the List’s `.get()` or
`.remove(int)` methods.

5. Lastly, write the method `static <T> T findMode(Iterable<T> collection)`,
which finds and returns the mode (most common element) from a collection. You
should use multiple iterators to loop through the collection concurrently, and
you should not use any other data structures to determine the mode.

6. Test that your last 2 methods pass the provided tests using `./gradlew test`
and debug any errors with these two methods. You can add your own test code to
`Demo.java` if it helps you debug.

## Conclusion

In this exercise, we have practiced using iterators, which are regarded as an
abstraction that provides us with a simple way to iterate over a given data
structure and access its elements. An iterator is sometimes an efficient object
to use as the user does **not** need to be aware of its internal structure. You
can check the input and output of the iterator’s method here: [more about
Iterators](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Iterator.html)

