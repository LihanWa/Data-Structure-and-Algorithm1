![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
# CS 445 Assignment 1: Array Lookup

## Motivation

In CS 445, we often discuss the importance of data structure design and
implementation to the wide variety of computing applications. Despite decades of
study and development of common libraries, organizations must still regularly
develop custom data structures to fulfill their applications’ specific needs,
and as such the field remains hugely relevant to both computer scientists and
software engineers.

In this assignment, you will implement a data structure to satisfy its
specification, which is provided in the form of an interface.

> **Note:** Your goal in this assignment is to write a class that faithfully
implements the ADT described in the interface. Sample client code is provided as
*one example* of how the class may be used. This sample client can be run using
`./gradlew run`. In addition, unit tests are available for *a few methods* that
can be run using `./gradlew test`. However, the sample client and unit test **do
not test all of the capabilities you are asked to implement**, and thus your
goal is *not* simply to make this provided code work. You are *strongly
encouraged* to write your own test client as well, to further test that all
operations of your class work in all the corner cases described in the
interface.

## Provided code

First, look over the provided code in this repository. If you created it
correctly on GitHub Classroom, it should be a private repository named
`a1-abc123`, where `abc123` is your GitHub username.

The starting code for this assignment, as usual, is found in subdirectory
`app/src/main/java/`. Some files are within the `cs445/a1/` package folder
within it. Note the following provided Java files:

- The `LookupInterface<I, C>` interface (in the `cs445.a1` package) describes a
  lookup, a data structure we have not discussed previously. A lookup is a
  collection of unique identifiers, each of which is associated with a content
  object. An identifier can thus be used to “look up” its content object. Each
  identifier/content combination is referred to as a “pair”. Lookup is similar
  to a bag in that it is an unordered data structure. While identifiers must be
  unique, content objects may not be (that is, duplicate *content* is allowed,
  but duplicate *identifiers* are not). While the type of the identifiers is not
  required to match the type of the content objects, type homogeneity is
  enforced via generics so there is one common type for identifiers (`I`) and
  another for content (`C`).

  The `LookupInterface` declares abstract methods for adding a pair, removing a
  pair (specified or unspecified), replacing the content for an identifier,
  checking if the lookup is empty, determining the number of pairs in the
  lookup, fetching the identifiers from the lookup into an array, and several
  other methods. You are *not* permitted to modify this interface—doing so will
  cause all grading tests to fail!

- The `FullLookupException` class (in the `cs445.a1` package) is included to
  allow hypothetical implementations of `LookupInterface` that have a fixed
  capacity. Your implementation should **not** be fixed capacity, and thus
  should not throw this exception. You are *not* permitted to modify this class.

- The `IdentifierNotFoundException` class (in the `cs445.a1` package) is
  included as a way of telling the client that a specified identifier was not
  found. It should be used as described in the interface. You are *not*
  permitted to modify this class.

- The `PhoneLookupExample` class (which is *not* in the `cs445.a1` package)
  shows one example usage of the `Lookup` data structure. It demonstrates a few
  features, but is not a thorough test. It can be run using `./gradlew run` (or
  `gradlew.bat run` on DOS-like terminals).

- The `PhoneNumber` class (which is *not* in the `cs445.a1` package) is a simple
  way of representing a phone number. This class contains a constructor and a
  `toString` method. It is used in `PhoneLookupExample`. You are *not* permitted
  to modify this class.

In addition to the starter code, we have also provided an example test class
that can be found in subdirectory `app/src/test/java/`. The test is in the
`cs445/a1/test/` folder within it. Note the following provided Java file:

- The `SampleTest` class contains example test cases for a few of the methods;
  it tests a subset of the functionality for the constructors, `getSize()`,
  `add(I, C)`, and `getAllIdentifiers()`. This **does not** test all required
  functionality of these methods, and does not test the other methods at all. It
  can be run using `./gradlew test` (or `gradlew.bat test` on DOS-like
  terminals). It is provided to give you some feedback on your progress, but you
  are still responsible for implementing additional tests to ensure your code
  works as specified in the provided documentation.

## Tasks

### Implement Lookup

Develop the generic class, `Lookup<I, C>`, a **dynamic-capacity array-based**
implementation of the Lookup ADT described in `LookupInterface<I, C>`. Include
this class in package `cs445.a1` (and thus place the `Lookup.java` file in
subdirectory `app/src/main/java/cs445/a1/`). Read the comments in the interface
(and this README!) carefully to ensure you implement it properly; it will be
graded using tests that assume *all* of the functionality described in the
`LookupInterface<I, C>` and here, not just the behavior you need to execute
`PhoneLookupExample` or run `SampleTest`.

You are not permitted to use *any* external code when developing your `Lookup`
class, but you may use the code written in lecture and the textbook as examples.
Do not use any code you find online, even for ideas. Do not import or use any
Java Class Library classes (except `java.util.Arrays` for resizing, if you’d
like).

In your implementation, you should use two arrays, one of identifiers and one of
content objects. They should be associated via position (i.e., an identifier
referenced in index `i` of the identifier array is associated with the content
object referenced in index `i` of the content array). This technique is known as
*parallel arrays*.

You must include a constructor `public Lookup(int capacity)` that initializes
the arrays to the specified initial capacity, and a constructor `public
Lookup()` that uses a reasonable default initial capacity. Whenever the capacity
is reached, the arrays *must* resize, using the techniques discussed in lecture
(i.e., you should **never** throw `FullLookupException`).

### Testing

`PhoneLookupExample` is provided as an example client of the `Lookup` class.
`SampleTest` is provided with unit tests for a subset of the functionality of a
few methods. These **do not** exhaustively test the functionality of the data
structure you must write. You are responsible for ensuring your implementation
works properly in all cases, even those not tested by these classes, and that it
follows the ADT described in the provided interface. Thus, it is **highly
recommended** that you write additional test client code to verify your
implementation’s behavior in *all* of the corner cases described in the
interface.

> **Note:** For functionality that cannot be tested (e.g., methods that crash
or cannot be compiled), **no points will be awarded**. At this level, turning in
code that crashes or does not compile is not acceptable and will not yield
success.

## Submission

Put your `Lookup` class in the appropriate location in your GitHub repository.
Commit and push your changes to this private repository, created for you when
you accepted the assignment on GitHub Classroom. **Double check that your
submission appears on the GitHub web interface after pushing.** If you
accidentally overwrite the provided interface or exception classes, remember to
restore them to their original versions. You may not make changes to these
files. I recommend that you push your changes as often as possible (at a
minimum, every time you stop working).

Once you are satisfied with your submission, you can submit it on Gradescope for
grading. You can access Gradescope from the Canvas sidebar. Select our course,
then the correct assignment. Connect your GitHub account to Gradescope, and then
specify which repository (i.e., `cs445-a1-abc123`, where `abc123` is your GitHub
username) and which branch (most likely `main`, unless you change it) you want
graded. You will receive an email confirmation if your submission was received
on Gradescope. You can submit as many times as you would like; the latest
submission before the deadline will be graded.

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
implementing `Lookup` in a way beside parallel arrays, etc.

Your project is due at the precise date and time stated on Canvas. You should
commit and push your progress frequently, even far in advance of this deadline:
**No late submissions will be accepted.**

## Grading

As stated above, your submission will be tested and graded via a *very*
exhaustive set of unit tests using Gradle and reviewed to make sure it follows
the requirements. Be sure to review these instructions, and the details of
`LookupInterface`, carefully when making your implementation. It is **highly
recommended** that you write additional test client code to verify your
implementation’s behavior in *all* of the corner cases described in the
interface.

| **Method**                     | **Points**
| ----------                     | ----------
| `Lookup()`                     | 6
| `Lookup(int)`                  | 6
| `int getSize()`                | 6
| `boolean isEmpty()`            | 6
| `boolean add(I, C)`            | 10
| `C replace(I, C)`              | 8
| `C get(I)`                     | 10
| `C remove(I)`                  | 10
| `Object[] remove()`            | 10
| `void clear()`                 | 8
| `boolean contains(I)`          | 8
| `Object[] getAllIdentifiers()` | 12

