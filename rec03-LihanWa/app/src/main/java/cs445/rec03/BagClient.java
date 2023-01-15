package cs445.rec03;

/**
 * A class that utilizes both the ArrayBag and LinkedBag
 * as a simple client.
 * @author William C. Garrison III
 * @author Brian Nixon
 */
public class BagClient {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter some command line args to add to the bag.");
            System.exit(0);
        }

        // Beginning of ArrayBag client

        BagInterface<String> bag = new ArrayBag<String>();
        System.out.println("\n========= ArrayBag Client =========");
        System.out.println("Bag is initially empty:\t" + bag.isEmpty());

        for (String word : args) {
            bag.add(word);
        }

        System.out.println("You entered " + bag.getCurrentSize() + " words to the bag.");
        System.out.println("The bag currently contains:\t" + bag);

        bag.removeDuplicatesOf(args[0]);
        System.out.println("The bag after removing duplicates of " + args[0] + ":\t" + bag);
 
        bag.clear();
        System.out.println("After clearing the bag, bag is empty:\t" + bag.isEmpty());
        System.out.println("========= End of ArrayBag =========\n");


        //Beginning of LinkedBag client
        bag = new LinkedBag<String>();
        System.out.println("\n========= LinkedBag Client =========");
        System.out.println("Bag is initially empty:\t" + bag.isEmpty());

        for (String word : args) {
            bag.add(word);
        }

        System.out.println("You entered " + bag.getCurrentSize() + " words to the bag.");
        System.out.println("The bag currently contains:\t" + bag);


        bag.removeDuplicatesOf(args[0]);
        System.out.println("The bag after removing duplicates of " + args[0] + ":\t" + bag);

        bag.clear();
        System.out.println("After clearing the bag, bag is empty:\t" + bag.isEmpty());
        System.out.println("========= End of LinkedBag =========\n");
    }
}
