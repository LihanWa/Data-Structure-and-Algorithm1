![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
﻿# CS 445 Recitation 7: Backtracking

## Introduction

In this recitation, you will design, implement, and test a backtracking
algorithm. The primary goal is to practice devising a backtracking solution to
solve a problem, `8 Queens`, that would be very difficult for an iterative
approach and then implementing that solution in code. Recall the main idea to
devising a backtracking solution:

- **Build up a solution recursively** What are the recursive cases? What smaller
  (yet structurally identical) subproblems will be used to solve the original
  problem? What is the base case? When is an answer known without a recursive
  call? How do we ensure termination?

- **Backtrack** When a partial solution is found to be *invalid*, undo the last
  decision (backtrack) until it’s valid again.

## Exercise

1. The starting code for this exercise, as usual, is found in subdirectory
`app/src/main/java/`. Navigate to this subdirectory, then into the
`cs445/rec07/` package folder within it. Note the following provided Java files:

   - `Queens.java` contains method stubs for today’s exercises and the
     backtracking solver template
   - `App.java` contains the `main` method used when executing `./gradlew run`.
     To print out **all** solutions, rather than just one, use
     `./gradlew run --args \'-a\'` (Note that the quote marks need to be escaped
     with `\` so gradle passes the argument properly.)
   - `QueensTest.java` contains JUnit test cases for the helper methods to be
     implemented in `Queens.java`

2. Implement the following methods in `Queens.java` to complete the backtracking
algorithm.

       /**
        * Checks if a partial solution is a complete solution.
        */
       static boolean isFullSolution(int[] partial)

       /**
        * Checks if a partial solution should be rejected because it can never
        * be extended to a complete solution.
        */
       static boolean reject(int[] partial)

       /**
        * Extends a partial solution by adding one additional queen.
        */
       static int[] extend(int[] partial)

       /**
        * Moves the most recently-placed queen to its next possible position.
        */
       static int[] next(int[] partial)

3. Test that your methods pass the provided tests using `./gradlew test` and
debug any errors with `isFullSolution()` and `extend()`. Implement the remaining
methods; you can write your own tests of `reject()` and `next()` (either in
`QueensTest` or `App.java`’s main method).

   **Hint:** A StackOverFlowException is typically caused by infinite recursion.
   To fix this, determine when a recursive call should lead to a base case but
   fails to satisfy the condition of termination.

## Conclusion

In this exercise, you practiced using backtracking in order to solve the problem
of 8 Queens by devising an algorithm that built partial solutions of the
chessboard and backtracked when a partial solution could not be extended to a
full solution. In addition to practicing backtracking, you also refreshed your
skills of utilizing recursion by breaking the 8 Queens problem into subproblems
and partial solutions. While an iterative approach to the 8 Queens problem would
be quite difficult, we were able to implement a simpler solution using a
backtracking algorithm.

