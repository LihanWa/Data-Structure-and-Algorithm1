![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
# CS 445 Recitation 1: Introduction to Git, Gradle, and GitHub

## Introduction

In this recitation exercise, you will learn about the “tech stack” of this
course and practice the technical steps that you’ll need to complete when
working on programming assignments. It is important that you test your
assignment submissions from the standard environment, as it is built to closely
match the environment in which your code will be evaluated.

## Exercise

1) After your TA finishes their presentation on Git and the other tools in the
course, navigate to your own Recitation 1 repository (named as `rec01-whoami`,
where `whoami` is your GitHub username).

   Launch a Codespace and review the structure of the repository.

2) Run `./gradlew test` and note that the tests do not pass.

3) Read the contents of `./app/src/main/java/cs445/rec01/Status.java`. In
particular, note the `package` line at the top, which specifies that this class
is within package `cs445.rec01`.

4) Create a new file within `./app/src/main/java/cs445/rec01/` named
`Message.java`. Write a simple class within package `cs445.rec01`. This class
should include a `main` method that uses `System.out.println()` to print a
message. The message should contain the string `"CS445"`, and its length should
be at least 20 characters, but less than 140 characters. Add this file to the
staging area of your repository using `git add`.

5) Edit `Status.java` so that it returns `"DONE"` rather than `"NOT DONE"`. Add
this file to the staging area of your repository using `git add`.

6) Run `./gradlew run` to run the main method you wrote within `Message`. Verify
that your message is printed successfully.

7) Run `./gradlew test` again and verify that the tests pass.

8) Once you’ve verified that your code is working correctly, use `git commit` to
commit your changes from the staging area.

9) Use `git push` to push your changes to your repository on GitHub. Use the
GitHub web interface to verify that your commit appears.

10) Use `git log` to observe the changes that have been made since this
repository was created. If you want to undo a commit, try `git revert` followed
by the first few characters of the commit id. If you want to see details of what
changed in a commit, use `git show` (again followed by the beginning of the id).

11) To complete the exercise, submit your repository on Gradescope for
Recitation 1.

## Conclusion

More importantly than just following these steps, the goal of these exercises is
to prepare you for your programming assignments. If you have trouble completing
these steps, or you do not understand them, ask your TA or come to office hours!
Understanding these concepts will be crucial to success in your assignments!

