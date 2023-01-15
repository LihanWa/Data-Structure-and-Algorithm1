![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
# CS 445 Assignment 2: Stack Calculator

## Motivation

In lecture, we discussed a stack algorithm for converting an infix expression to
a postfix expression (known as “shunting-yard”), and another for evaluating
postfix expressions. We briefly described how one might pipeline these
algorithms to evaluate an infix expression in a single pass using two stacks,
without generating the full postfix expression in between. In this assignment,
you will implement this combined algorithm. Your completed program will evaluate
an infix arithmetic expression using two stacks.

You may wish to review the revelant algorithms in Chapter 5 of Carrano & Henry,
including the combined `evaluateInfix`, which is similar to the algorithm
discussed in this assignment.

## Provided code

First, look over the provided code in this repository. If you created it
correctly on GitHub Classroom, it should be a private repository named
`a2-abc123`, where `abc123` is your GitHub username.

The starting code for this assignment, as usual, is found in subdirectory
`app/src/main/java/`. The relevant files are within the `cs445/a2/` package
folder within it. You are allowed to modify the provided code in any way, as
long as you do not change how it is used (e.g., do not accept multiple
expressions in one execution or read them from a file rather than a `Reader`).
Note the following provided Java files:

- The `Calculator` class (in the `cs445.a2` package) is a skeleton of your
  project. This class accepts input from a `Reader` object and parses it into
  tokens. When it detects an invalid token, it throws an
  `InvalidExpressionException` to report the error. `Calculator` uses
  composition to store the operator and operand stacks, and calls several
  private helper methods to manipulate these stacks when handling various
  tokens. You will need to complete these helper methods and add error checking
  to ensure the expression is valid.

- The `InvalidExpressionException` class (in the `cs445.a2` package) is included
  as a way of reporting an exception as malformed or invalid.

- The `ConsoleCalculator` class (in the `cs445.a2` package) is a driver provided
  for ease of use. This class contains a `main` method and is run by default
  using `./gradlew run`. It instantiates an object of type `Calculator` to read
  from `System.in` (standard input), then evaluates whatever expression is
  typed. It also catches any `InvalidExpressionException` that is thrown, and
  prints the reason for the error.

- The `ArrayStack` class (in the `cs445.stack` package) is an array-based
  implementation of the Stack ADT.

- The `StackInterface` interface (in the `cs445.stack` package) is a Java
  representation of the Stack ADT.

## Tasks

### Implement helper methods

As tokens are encountered, `evaluate()` calls one of various helper methods
to handle each one. In the included code, these helper methods do not yet do
anything. You must implement the following methods to handle the various
types of tokens.

- `handleOperand(double)`: Each time `evaluate()` encounters an operand, it
  passes it (as a double) to this method, which should handle it by manipulating
  the operand and/or operator stack according to the combined infix-to-postfix
  and postfix-evaluation algorithm.

- `handleOperator(char)`: Each time `evaluate()` encounters an operator, it
  passes it (as a char) to this method, which should handle it by manipulating
  the operand and/or operator stack according to the combined infix-to-postfix
  and postfix-evaluation algorithm.

  Each of the following operators must be supported. Follow standard operator
  precedence. You can assume that any occurrence of `-` is the binary
  subtraction operator (e.g., no negative operands in the expression, use
  `(0-k)` instead of `-k`). You do not need to handle “right associativity” for
  exponentiation. (That is, you can assume `a^b^c` is equivalent to `(a^b)^c`,
  as we did when we learned the algorithm in lecture, even though it should
  really be `a^(b^c)`.)

  | **Symbol** | **Operation**
  | ---------- | -------------
  | `+`        | Addition
  | `-`        | Subtraction
  | `*`        | Multiplication
  | `/`        | Division
  | `^`        | Exponentiation

- `handleOpenBracket(char)`: Each time `evaluate()` encounters an open bracket,
  it passes it (as a char) to this method, which should handle it by
  manipulating the operand and/or operator stack according to the combined
  infix-to-postfix and postfix-evaluation algorithm.

  You *must* support both round brackets `()` and square brackets `[]`. These
  brackets mean the same thing, but must be nested properly; a `(` cannot be
  closed with a `]`, and vice-versa.

- `handleCloseBracket(char)`: Each time `evaluate()` encounters a close bracket,
  it passes it (as a char) to this method, which should handle it by
  manipulating the operand and/or operator stack according to the combined
  infix-to-postfix and postfix-evaluation algorithm.

- `handleRemainingOperators()`: When `evaluate()` encounters the end of the
  expression, it calls this method to handle the remaining operators on the
  operator stack.

You are not permitted to use *any* external code when developing your solution,
but you may use the code written in lecture and the textbook as examples. Do not
use any code you find online, even for ideas. Do not import or use any Java
Class Library classes (except for those already imported and used in the
skeleton, **and** `Math.pow()` for exponentiation).

### Error checking

This task requires that you modify your program to account for errors in the
input expression. The provided code throws `InvalidExpressionException` when
encountering an unknown token (for instance, `&` or `xor`). You must modify your
program so that, whenever it encounters any other type of syntax error in an
expression, it throws `InvalidExpressionException` with a **specific error
message**. The message should explain what is wrong with the expression (i.e.,
what the user did wrong), and *not* what internal implementation detail allowed
you to detect it. For instance, your error message should never mention the
stacks, as the user should not be required to know how the program works.

This requires careful consideration of all the possible syntax errors. What if
an operand follows another operand? An operator following an open bracket? What
about brackets that do not nest properly? All such syntax errors must be
reported using `InvalidExpressionException`.

> **Hints:**
>
> 1. Which pairs of adjacent token types are valid vs. invalid? Consider adding
> a data member that tracks the most recent token type. When handling a new
> token, first ensure that it is legal for the current token type to follow the
> previous token type.
>
> 2. Trace through the algorithm by hand using expressions with various bracket
> errors. When during the process can each error be detected?

### Testing

`ConsoleCalculator` is provided as a simple client of the `Calculator` class.
`SampleTest` is provided with a few unit tests of increasingly complex (valid)
expressions. It is highly recommended that you test your code thoroughly with a
wide range of both valid and invalid expressions.

> **Note:** For functionality that cannot be tested (e.g., methods that crash
or cannot be compiled), **no points will be awarded**. At this level, turning in
code that crashes or does not compile is not acceptable and will not yield
success.

## Submission

Put your edited `Calculator` class (and any other required code) in the
appropriate location in your GitHub repository. Commit and push your changes to
this private repository, created for you when you accepted the assignment on
GitHub Classroom. **Double check that your submission appears on the GitHub web
interface after pushing.** I recommend that you push your changes as often as
possible (at a minimum, every time you stop working).

When you’d like to submit your code, do so via Gradescope. You can access
Gradescope from the Canvas sidebar. Select our course, then the correct
assignment. Connect your GitHub account to Gradescope, and then specify which
repository (i.e., `a2-abc123`, where `abc123` is your GitHub username) and which
branch (most likely `main`, unless you change it) you want graded. You will
receive an email confirmation if your submission was received on Gradescope. You
can submit as many times as you would like; we will grade the last submission
before the deadline.

Your submission will be tested and graded via a *very* exhaustive set of unit
tests using Gradle, so you must make sure your code runs properly with this
build system. If you use a local IDE to develop your program rather than
Codespaces (not recommended), you must export the java files from the IDE and
ensure that they compile and run in our standard environment. Do not submit any
IDE project files. The sample client should run when you execute `./gradlew
run`. The test cases should run when you execute `./gradlew test`. In addition
to the automated testing, your code will be reviewed to make sure it follows the
requirements. Very steep penalties (including potentially 0 on the assignment)
will be given for using external code, importing Java Class Library classes,
implementing `Calculator` in a way beside using two stacks, producing the
postfix expression and evaluating it in two separate sequential steps, etc.

Your project is due at the precise date and time stated on Canvas. You should
commit and push your progress frequently, even far in advance of this deadline:
**No late submissions will be accepted.**

## Grading

As stated above, your submission will be tested and graded via a *very*
exhaustive set of unit tests using Gradle and reviewed to make sure it follows
the requirements. The following points breakdown will be used to assign grades:

- **70 points** are allocated for correctly evaluating **valid** expressions.
  These expressions will range from very simple to very complex, but none will
  contain syntax errors. You should test your program extensively to ensure that
  it works properly even for complex expressions with multiple sets of nested
  brackets and combinations of all operators.

- **30 points** are allocated for correctly identifying the syntax errors in
  **invalid** expressions. These expressions will contain a wide variety of
  syntax errors. You should test your program with many invalid expressions to
  ensure that it correctly detects all possible syntax errors and gives a
  specific error message each time.

