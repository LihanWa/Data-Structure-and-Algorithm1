import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cs445.a4.LookupInterface;
import cs445.a4.HashingLookup;

public class PhoneLookupExample {
    /**
     * This method demonstrates a few methods of a Lookup class.
     */
    public static void main(String[] args) {
        LookupInterface<String, PhoneNumber> phoneLookup = new HashingLookup<>();
        phoneLookup.add("bill", new PhoneNumber(412, 123, 4567));
        phoneLookup.add("jill", new PhoneNumber(412, 388, 2222));
        phoneLookup.add("gill", new PhoneNumber(412, 388, 2222)); // dup contents allowed
        phoneLookup.add("phil", new PhoneNumber(412, 100, 3333));
        phoneLookup.add("tom", new PhoneNumber(900, 900, 900));

        if(phoneLookup.add("phil", new PhoneNumber(412, 999, 9999)) == true) {
            System.err.println("Oops, duplicate add succeeded");
        }
        System.out.println("Phil's old number was: " +
                phoneLookup.replace("phil", new PhoneNumber(412, 888, 7777)));
        System.out.println("Phil's new number is: " + phoneLookup.get("phil"));

        phoneLookup.remove("bill");
        System.out.print("Listing all names in phone lookup, 2 ways:\n\t");
        // First, use getAllIdentifiers
        for (Object s: phoneLookup.getAllIdentifiers()) {
            System.out.print((String)s + " ");
        }
        System.out.print("\n\t");
        // Then, since it's Iterable, do the same using foreach loop; much cleaner!
        for (String s: phoneLookup) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("Tom's number is: " + phoneLookup.get("tom"));

        // Create an iterator
        Iterator<String> it = phoneLookup.iterator();
        // Modify the lookup
        phoneLookup.add("johnny", new PhoneNumber(614, 123, 4321));
        // Make sure CME is thrown
        try {
            it.next();
            System.out.println("This should not be printed! CME not thrown.");
        } catch (ConcurrentModificationException e) {
            System.out.println("CME caught as expected");
        }

        // Create a new iterator
        it = phoneLookup.iterator();
        // Exhaust the iterator
        while (it.hasNext()) it.next();
        // Make sure NSEE is thrown
        try {
            it.next();
            System.out.println("This should not be printed! NSEE not thrown.");
        } catch (NoSuchElementException e) {
            System.out.println("NSEE caught as expected");
        }

    }
}

