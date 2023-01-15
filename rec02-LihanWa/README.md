![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
# CS 445 Recitation 2: Bag Client

## Introduction

In this recitation exercise, you will implement a brute-force algorithm for the
longest common subsequence problem (LCS) using a Bag. You will be using the Bag
without delving into the details of how the class is implemented; that is, you
will be writing _client code_.

The primary goal of this exercise is to practice solving problems using the
methods provided by a data structure. A secondary goal is to familiarize
yourself more deeply with the ADT Bag and its potential uses.

The ADT Bag is one of the primary collection structures defined in mathematics.
Also called a multiset, it is a generalization of a set that is allowed to hold
multiple copies of an item. Like a set, the items contained within the bag have
no particular ordering. Before completing this exercise you should review the
methods available to you in the Bag ADT.

## Exercise

1. Review the provided code in your Recitation 2 repository. As always, you will
find lots of gradle files and subfolders. As before, the code for the exercise
can be found in `app/src/main/java/`. Navigate to this subdirectory, then into
the `cs445/rec02/` package folder within it, and note the following Java files
inside:

   - `BagInterface.java` is a Java interface representing the ADT Bag. It is a
     slightly expanded version of what we developed in lecture.
   - `ArrayBag.java` is a dynamic capacity array-based implementation of ADT
      Bag. You will not need to focus on this implementation in this exercise.
   - `LongestCommonSubsequence.java` provides the skeleton of a LCS solution.

   You can run and test the exercise code using the gradle build tool. From the
   root of the repository (i.e., *without* navigating to `app/src/main/java/`),
   you can use the following command to compile and run the program
   automatically using gradle:

        ./gradlew run

   You can add `--args "first second"` at the end of the command to pass command
   line arguments `first` and `second` to `LongestCommonSubsequence`.

   You can also use replace `run` with `clean` to remove all of the generated
   files (such as `.class` files). Nothing extra needs to be installed for these
   commands to work; the included gradle wrapper will automatically download a
   copy of the tool if needed.

   (Note that the starter code will not compile and run until you complete the
   exercise.)

2. Review the following algorithm that finds the LCS of two input strings. This
algorithm takes a brute force approach of generating all possible subsequences
and checking them. Note that, while this algorithm will get the correct answer,
a more efficient approach would be to use dynamic programming (a topic you will
learn in CS 1501).

       Longest Common Subsequence (first, second):
           Create an empty bag
           Put the first string into the bag
           Create variable longest (for the longest subsequence so far)
           Initialize longest to empty string
           While the bag is not empty:
               Remove a string from the bag, call it test
               If longest is shorter than test:
                   If test is a subsequence of second:
                       Set longest to test
                   Otherwise, if test is at least 2 characters longer than longest:
                       Generate new strings from test by removing a different single
                       character each time.
                       Add each of the newly-generated strings to the bag.
               Print the bag of strings that still need to be checked, for debugging
           Print out the longest subsequence

   To demonstrate how to generate new strings from a test string ("by removing a
   different single character each time"), consider the following example. If
   the test string you removed from the bag is `ABCD`, you want to add each of
   `BCD`, `ACD`, `ABD`, and `ABC` (all subsequences that are 1 character
   shorter).

   Note that `LongestCommonSubsequence.java` already contains a method,
   `isSubsequence`, to check whether one string is a subsequence of another.

3. Once you understand the algorithm from Step 2, read through
`LongestCommonSubsequence.java`, noting the `TODO` comments. You will need to
complete these portions of the code.

4. At the first `TODO` comment, create a new reference variable named
`possibleSubsequences` for storing the bag and it assign it a value of `null`.

5. At the second `TODO` comment, create a new bag of strings, assign it to the
variable `possibleSubsequences`, and add the string `first` to the bag.

6. At the third `TODO` comment, implement the algorithm from Step 2.

7. Test the program to be sure it works. One way to test is to use this gradle command:

        ./gradlew test

   This will test your code using two provided test cases.

   To give you some other examples to test against, below are pairs of strings
   and their correct longest common subsequence:

   | First | Second      | LCS
   | ----- | -----       | -----
   | D     | ABC         |
   | AA    | ABA         | AA
   | AA    | AAA         | AA
   | AABC  | ABBC        | ABC
   | ABBC  | ABCC        | ABC
   | ABCC  | CABAC       | ABC
   | ABA   | AA          | AA
   | ABC   | CBACBA      | AB or BC or AC
   | ABC   | CBACBACBA   | ABC
   | ABC   | BCABCA      | ABC
   | ABCD  | DCBADCBA    | AB or AC or AD or BC or BD or CD
   | ABFCD | ADBAFDCBA   | ABFC or ABFD
   | ABFCD | ADBADCBA    | ABC or ABD
   | ABCDF | ADBADCBA    | ABC or ABD
   | ABADA | BADABAABDBA | ABADA

## Conclusion

In this exercise, you wrote client code using the ADT Bag to solve the LCS
problem. Writing client code is a crucial skill in Algorithms and Data
Structures 1 (and moving forward in your programming career). Throughout the
term, you should take the time to practice writing code that uses the data
structures presented, to improve your skills in solving problems using the
(often limited set of) operations available to you.

