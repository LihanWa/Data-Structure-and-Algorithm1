import cs445.a1.Lookup;

public class PhoneLookupExample {
    /**
     * This method demonstrates a few methods of a Lookup class.
     */
    public static void main(String[] args) {
        Lookup<String, PhoneNumber> phoneLookup = new Lookup<>(2);
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
        System.out.print("In phone lookup: ");
        for (Object s: phoneLookup.getAllIdentifiers()) {
            System.out.print((String)s + " ");
        }
        System.out.println();

        System.out.println("Tom's number is: " + phoneLookup.get("tom"));
    }
}

