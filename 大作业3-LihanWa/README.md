![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
# CS 445 Assignment 3: Backtracking Sudoku Solver

## Motivation

In lecture, we discussed a framework for backtracking algorithms. In this
assignment, you will use this framework to solve the Sudoku puzzle. If you are
not already familiar with this puzzle, review the rules here:

[https://www.sudoku.name/rules/en](https://www.sudoku.name/rules/en)

## Provided files

### Code

First, look over the provided code in this repository. If you created it
correctly on GitHub Classroom, it should be a private repository named
`a3-abc123`, where `abc123` is your GitHub username.

The starting code for this assignment, as usual, is found in subdirectory
`app/src/main/java/`. The relevant files are within the `cs445/a3/` package
folder within it. You are allowed to modify the provided code in any way, as
long as you do not change how it is used (e.g., do not change the input format
for initial board files). Note the following provided Java files:

- The `SudokuSolver` class (in the `cs445.a3` package) is a skeleton of your
  project. This class includes the backtracking `solve` method and stubs for the
  helper methods, `reject`, `isFullSolution`, `extend`, and `next`. You will
  need to complete **and test** these helper methods.

  This class also includes method `printBoard`, which prints out a Sudoku board
  to the terminal.

  Finally, this class includes a `main` method, which executes the (standard,
  non-JUnit) test methods for the backtracking helper methods you will write
  (`reject`, `isFullSolution`, `extend`, and `next`). See [below](#test-methods)
  for more details on these test methods.

- The `SudokuFromFile` class (in the `cs445.a3` package) allows you to test your
  `SudokuSolver` class using a Sudoku board from a file. This class includes the
  method `readBoard`, which reads in a Sudoku board and returns it as a
  two-dimensional 9 × 9 `int` array, with `0` designating each empty cell (yet
  to be filled). Several example boards are included (see
  [below](#example-sudoku-boards)).

You may also want to review your solution to Recitation 7. The `Queens` class
from this exercise should implement a backtracking solution to the 8 Queens
problem that we discussed in lecture. This is a suggested reference and is not
(directly) needed in this project.

### Example Sudoku Boards

Example Sudoku boards are available for you to test your code. They are
available in `app/src/main/resources`.

Each of these files is plaintext (i.e., can be opened with a text editor such as
Notepad or TextEdit). A board file contains 9 lines of text, each containing 9
characters. Each character is either a digit (1 through 9) to designate a filled
cell, or a non-numeric character (such as a space or dot) to designate an empty
cell. You can also use this format to create Sudoku boards of your own, for more
testing.

In order to run `SudokuFromFile` on one of these boards, pass it as an argument
to the class. For instance, use this command to test your `SudokuSolver` using
the board in `2-easy.su`:

    ./gradlew run --args 2-easy.su

## Tasks

You must write a backtracking program to find values for all of the empty cells
in a Sudoku puzzle, without changing any of the cells specified in the original
puzzle. You must also satisfy the Sudoku rules: each digit 1–9 must appear
exactly once in each row, column, and region. You *must* accomplish this using
the backtracking techniques discussed and demonstrated in lecture and recitation
(and `Queens.java`). That is, you need to build up a solution recursively, one
cell at a time, until you determine that the current board assignment is
impossible to complete (in which case the solver will backtrack and try another
option), or that the current board assignment is complete and valid (in which
case the solver will return it).

Your solver will be created with an initial board (e.g., from a file). The
initial board will have several cells already filled. The remaining cells will
be empty. The goal is to find the values of all the empty cells in the puzzle,
without changing any of the cells specified in the original puzzle.

As described above, `SudokuFromFile` allows you to read in an initial board from
a text file containing 9 lines of text, each containing 9 characters. Digits 1
through 9 indicate filled cells (that cannot be changed), while non-numeric
characters indicate empty cells (that need to be filled). When running
`SudokuFromFile`, you will need to specify the file to read from, as shown
below.

    ./gradlew run --args board_file.su

If there is a way to solve the puzzle described in the `board_file.su`, your
program should output the completed puzzle. You do not need to find every
possible solution, if there are multiple—output a single solution. If there is
no solution, a message should be output indicating as such.

### Implement helper methods

As stated above, you **must** use the techniques we discussed in lecture for
recursive backtracking. As such, you will need to write the following methods to
support your backtracking algorithm. Note that none of these methods is expected
to use recursion itself; the `solve` method that uses them implements the
backtracking recursion.

- `boolean isFullSolution(int[][])`, a method that accepts a partial solution
  and returns `true` if it is a complete, valid solution.

- `boolean reject(int[][])`, a method that accepts a partial solution and
  returns `true` if it should be rejected because it can **never** be extended
  into a complete solution.

- `int[][] extend(int[][])`, a method that accepts a partial solution and
  returns another partial solution that includes one additional decision added
  on. This method will return `null` if no more decisions can be added to the
  solution.

  > **Note:** Be sure that your `extend` method creates a **new** partial
  > solution, rather than modifying its argument in-place (recall that the
  > runtime stack can only contain references, not objects themselves!).

- `int[][] next(int[][])`, a method that accepts a partial solution and returns
  another partial solution in which the *most recent* decision that was added
  has been changed to its next option. This method will return `null` if there
  are no more options for the *most recent* decision that was made (even if
  there are other options for other decisions!).

> **Hints:**
>
> 1. You should implement the above methods as efficiently as possible. At a
> minimum, you should be able to solve each of the examples provided within 1
> minute.
>
> 2. One key challenge in this assignment is differentiating between cells that
> were filled by your program (and thus *can* be changed) and those that were
> specified in the provided file (and thus *cannot* be changed). If you were
> solving a Sudoku by hand on paper, how would you differentiate between these
> two types of cells? I can think of two main approaches, each of which has a
> programming analogue.
>
> 3. Parameters and local variables in `solve()` (such as `board` and `attempt`)
> can be “reverted” to their previous values by returning. Avoid using static
> variables or instance variables in your class for any values that might change
> as your program executes. Since these values won’t be stored on the runtime
> stack, you won’t be able to undo any changes by returning (and popping off of
> the runtime stack).

You are not permitted to use *any* external code when developing your solution,
but you may use the code written in lecture and the textbook as examples. Do not
use any code you find online, even for ideas. Do not import or use any Java
Class Library classes. Finally, do not use any stack-like data structure to keep
track of changes to the solution; you should rely completely on the runtime
stack and backtracking recursion for storing the history of decisions made.

> **Note:** For functionality that cannot be tested (e.g., methods that crash or
> cannot be compiled), **no points will be awarded**.

### Test Methods

When developing a complex program such as this one, it is important to test your
progress as you go. For this reason, in addition to the backtracking-supporting
methods above, you will be required to test your methods **as you develop
them**. To test the backtracking methods, you need to write the following test
methods. For each one, you should create a variety of partial solutions that
cover as many corner cases as you can think of. Then, call the method you are
testing on each partial solution, and ensure that the result is as expected.
Include enough test cases that the correct output *convinces* you that your
method works properly in all situations. Your tests should print out the test
cases and their results, to demonstrate their effectiveness. Your TA should not
have to read your code to understand your tests.

- `testIsFullSolution()`, a method that generates partial solutions and ensures
  that the `isFullSolution` method correctly determines whether each is a
  complete solution.

- `testReject()`, a method that generates partial solutions and ensures that the
  `reject` method correctly determines whether each should be rejected.

- `testExtend()`, a method that generates partial solutions and ensures that the
  `extend` method correctly extends each with the correct next decision.

- `testNext()`, a method that generates partial solutions and ensures that the,
  in each, `next` method correctly changes the most recent decision that was
  added to its next option.

For simplicity, you do not need to implement JUnit tests. Instead, these should
be run whenever the `SudokuSolver` is run directly (as opposed to using
`SudokuFromFile`). Your Gradle project is set up to run `SudokuSolver` (and thus
your test methods) whenever you call `./gradlew runTestMethods`.

You may either hardcode your test partial solutions as `int[][]` directly in
your code, or submit additional `board.su` files containing the boards you want
to test (which you can read in using `int[][] SudokuFromFile.readBoard(String)`). If
you submit board files, include them in `app/src/main/resources` so they are
available when executed via Gradle. In either case, when your `SudokuSolver` is
run directly, your program must run each of the above four test methods and
output the results without user intervention.

## Submission

Put your edited `SudokuSolver` class (and any other required code) in the
appropriate location in your GitHub repository. Commit and push your changes to
this private repository, created for you when you accepted the assignment on
GitHub Classroom. **Double check that your submission appears on the GitHub web
interface after pushing.** I recommend that you push your changes as often as
possible (at a minimum, every time you stop working).

When you’d like to submit your code, do so via Gradescope. You can access
Gradescope from the Canvas sidebar. Select our course, then the correct
assignment. Connect your GitHub account to Gradescope, and then specify which
repository (i.e., `a3-abc123`, where `abc123` is your GitHub username) and which
branch (most likely `main`, unless you change it) you want graded. You will
receive an email confirmation if your submission was received on Gradescope. You
can submit as many times as you would like; we will grade the last submission
before the deadline.

Your submission will be tested and graded via a *very* exhaustive set of unit
tests using Gradle, so you must make sure your code runs properly with this
build system. If you use a local IDE to develop your program rather than
Codespaces (not recommended), you must export the java files from the IDE and
ensure that they compile and run in our standard environment. Do not submit any
IDE project files. The file-based solver should run when you execute `./gradlew
run --args board.su`. The internal test methods should run when you execute
`./gradlew runTestMethods`. Finally, a few of the usual JUnit tests are provided
(of the full solver overall) that will run when you execute `./gradlew test`.

In addition to the automated testing, your code will be reviewed to make sure it
follows the requirements. Very steep penalties (including potentially 0 on the
assignment) will be given for using external code, importing Java Class Library
classes, implementing `SudokuSolver` in a way beside the techniques described
here, etc.

Your project is due at the precise date and time stated on Canvas. You should
commit and push your progress frequently, even far in advance of this deadline:
**No late submissions will be accepted.**

## Grading

As stated above, your submission will be tested and graded via a *very*
exhaustive set of unit tests using Gradle and reviewed to make sure it follows
the requirements. The following points breakdown will be used to assign grades:

- **60 points** are allocated for your program’s success at finding solutions to
  a series of unknown Sudoku puzzles. This includes correctly stating that there
  is no solution, where applicable.

- **40 points** are allocated for the thoroughness of your test methods (even if
  the tests do not pass because you couldn’t get the helper methods working
  properly).

