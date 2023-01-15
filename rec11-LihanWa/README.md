![Open in Codespaces](https://classroom.github.com/assets/open-in-codespaces-abfff4d4e15f9e1bd8274d9a39a0befe03a0632bb0f153d0ec72ff541cedbe34.svg)
﻿# CS 445 Recitation 11: Dictionaries

## Introduction

In this recitation, you will write several methods in the class,
`MapLandmarks.java`, that extends a double-hashing implementation of the
Dictionary ADT. This class will allow a client to store landmarks and their
coordinates in a Dictionary data structure. We have provided a list of some
famous landmarks in PA in the file `landmarks.txt`. Our program utilizes the
Dictionary ADT by using the latitude and longitude pair as the key and the
landmark name as the value. The latitude and longitude are wrapped in the class
`Coord` which will represent the key used by `MapLandmarks.java`. The `Coord`
class includes each of the following features.
   - Each `Coord` object stores the 2 components of a key (a coordinates pair)
     in a convenient wrapper class; latitude and longitude.
   - The overridden `equals` method ensures that the 2 components of the
     coordinate are considered when comparing.
   - The overridden `hashCode` method uses Horner’s method on each component
     separately, then adds their results. (An even better approach would be
     using Horner’s method on both values combined.)

You will write three methods for your dictionary of landmarks: a method to add a
new landmark (`addLandmark`), another one to remove an existing landmark
(`removeLandmark`), and a third one to print all the landmarks (the ones
imported from the .txt file while reflecting any changes that have been made via
`addLandmark` and `removeLandmark`).

## Exercise

1. The starting code for this exercise, as usual, is found in subdirectory
`app/src/main/java/`. Navigate to this subdirectory, and note the following
provided Java files:

   - `MapLandmarks.java` (in package `cs445.rec11`) contains stubs for the
     methods you will write in this exercise. Note that this class extends
     `HashingDictionary<Coord, String>`, so you’ll be able to call the
     Dictionary methods within those that you are writing.
   - `Coord.java` (in package `cs445.rec11`) a wrapper class for the 2
     components of a coordinate.
   - `DictionarytInterface.java` (in package `cs445.rec11`) contains the
     dictionary ADT.
   - `HashingDictionary.java` (in package `cs445.rec11`) is a double
     hashing-based implementation of the Dictionary ADT.
   - `CsvReader.java` (in package `cs445.rec11`) reads the `landmark.txt` file
     and stores all the lines in a list of strings.
   - `Demo.java` (in package `cs445.rec11`) a sample client for running the
     project and giving the user the choice to add a landmark, remove a
     landmark, print all the landmarks in the dictionary, or search a landmark
     by entering its latitude and longitude.
   - `landmark.txt` (in folder `app/src/main/resources/`) has some famous
     landmarks in PA. Each line has the landmark name, its latitude, and its
     longitude separated by commas.

2. In class `MapLandmarks`, write the method `void addLandmark(String value,
double firstComponent, double secondComponent)`, which adds a value and its
associated key, which is a coordinate two components. You should rely on the
methods of the in Dictionary ADT, as we are extending the `HashingDictionary`
class.

3. In the same class, write the method `void removeLandmark(String value)`,
which removes a value and its associated key from our dictionary pairs. Hint:
Consider using an iterator for this task.

4. Check if the previous `addLandmark()` work as expected using `./gradlew
test`, which will run the unit test we provided you with.

5. Lastly, write the method `public void printAllLandmarks()`, which prints all
landmarks your dictionary has. Hint: Consider using an iterator for this task.

6. Test that your last method works as expected using `./gradlew run` and
choosing “3. Print all landmarks” on the console menu. Now compare what’s
printed on the screen to what is contained in the landmark.txt file (in
`app/src/main/resources/`.) They should be the same unless you inserted or
removed any of the existing landmarks.

## Conclusion

In this exercise, you practiced using Dictionary methods along with iterators.
Dictionaries are known for their efficiency in lookups and updating their pairs
as they map any non-null keys to any values. You can check the signature (input
and output) for the JCL equivalent interface, the Map ADT, here:
[java.util.Map](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Map.html)
(Focus on the instance methods.)

