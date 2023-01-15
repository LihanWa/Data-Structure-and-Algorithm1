package cs445.rec11;

/**
 A class that introduces a key as a Coord,
 where each Coord is composed of 2 components latitude and longitude to map landmarks later.
 @author William C. Garrison III
 @author Norhan Abbas
 @author Brian T. Nixon
 */

public class Coord {

    private double firstComponent;
    private double secondComponent;

    public Coord (double latitude, double longitude) {
        this.firstComponent = latitude;
        this.secondComponent = longitude;
    }

    public double getFirstComponent() {
        return this.firstComponent;
    }

    public double getSecondComponent() {
        return this.secondComponent;
    }

    public void setFirstComponent(double newEntry) {
        firstComponent = newEntry;
    }

    public void setSecondComponent(double newEntry) {
        secondComponent = newEntry;
    }

    @Override
    public boolean equals(Object ob) {

        if (ob == null) {
            return false;
        }
        if (!(ob instanceof Coord)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Coord newCoord = (Coord) ob;
        if ((this.getFirstComponent() != newCoord.getFirstComponent()) || (this.getSecondComponent() != newCoord.getSecondComponent())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {

        // convert the first component into a string
        // String str = Double.toString(key.getFirstComponent);
        // convert the string into an array of characters
        // char[] chars = str.toCharArray();

        return hornerMethod((Double.toString(getFirstComponent())).toCharArray(), 10) +
               hornerMethod((Double.toString(getSecondComponent())).toCharArray(), 10);
    }

    public int hornerMethod(char arr[], int base) {
        int len = arr.length;
        int result = 0;

        for (int i=len-1; i >= 0; i--) {
            // extract the numeric value from each character in the array
            result += Character.getNumericValue(arr[i]) * Math.pow(base, i);
        }
        return result;
    }
}
