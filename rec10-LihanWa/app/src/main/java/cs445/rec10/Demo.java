package cs445.rec10;

import java.util.Iterator;

import cs445.list.ArrayList;
import cs445.list.ListInterface;

public class Demo {

    public static void main(String[] args) {
        ListInterface<String> myList = new ArrayList<String>();

        myList.add("Apples");
        myList.add("are");
        myList.add("so");
        myList.add("good");
        myList.add("...");
        myList.add("in");
        myList.add("fact");
        myList.add("I");
        myList.add("eat");
        myList.add("them");
        myList.add("when");
        myList.add("I");
        myList.add("write");
        myList.add("programs");

        // prints "Apples are so good ... in fact I eat them when I write programs"
        System.out.println("Original list:");
        IterableUtils.printAll(myList);
        System.out.println();

        //prints "Apples write programs"
        System.out.println("Strings of length 5 or greater:");
        IterableUtils.removeShortStrings(myList, 5);
        IterableUtils.printAll(myList);
        System.out.println();

        // List of numbers with 41 as the most common element (mode)
        ListInterface<Integer> numList = new ArrayList<Integer>();
        numList.add(14);
        numList.add(123);
        numList.add(41);
        numList.add(14);
        numList.add(87);
        numList.add(19);
        numList.add(41);
        numList.add(1);
        numList.add(35);
        numList.add(99);
        numList.add(41);
        numList.add(15);
        numList.add(41);
        numList.add(14);
        numList.add(35);

        // Should print "Mode: 41"
        IterableUtils.printAll(numList);
        System.out.println("Mode: " + IterableUtils.findMode(numList));

    }
}

