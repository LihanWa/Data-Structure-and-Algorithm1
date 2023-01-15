package cs445.rec11;

import java.util.Iterator;

/**
 * A class to demonstrate the usage of dictionaries methods,
 * it uses keys as Coords to map landmarks.
 * @author William C. Garrison III
 * @author Brian T. Nixon
 * @author Norhan Abbas
 */
public class MapLandmarks extends HashingDictionary<Coord, String> {

    public MapLandmarks() {
        // invoke HashingDictionary constructor
        super();
    }

    /**
     * Adds a new landmark and coordinates to the hashingdictionary
     * @param value the value of the landmark
     * @param firstComponent the first component of the coordinate
     * @param secondComponent the second component of the coordinate
     */
    public void addLandmark(String value, double firstComponent, double secondComponent) {
        // TODO: implement this method
        // to add a pair of a landmark and its key (which has 2 components)
        Coord key=new Coord(firstComponent,secondComponent);
        super.add(key,value);
    }

    /**
     * Removes a landmark at the specified coordinate
     * @param firstComponent the first component of the coordinate
     * @param secondComponent the second component of the coordinate
     * @return returns the removed landmark value of the specified coordinates
     */
    public String remove(double firstComponent, double secondComponent) {
        Coord keyToFind = new Coord(firstComponent, secondComponent);
        return super.remove(keyToFind);
    }

    /**
     * Removes the first instance of a specified landmark value
     * Consider: Why are we unable to remove all cases of value here?
     * @param value the landmark value to remove
     */
    public void removeLandmark(String value) {
        // TODO: implement this method using itertors to remove a landmark
        Iterator<String> findV=super.getValueIterator();
        Iterator<Coord> findK=super.getKeyIterator();
        while(findV.hasNext()){
            if (findV.next().equals(value)){
                Coord result=findK.next();
                super.remove(result);
                break;
            }
            findK.next();
        }
    }

    /**
     * Retrieves the landmark value for a specific coordinate
     * @param firstComponent first component of the coordinate
     * @param secondComponent second component of the coordinate
     * @return the associated value for the specified coordinate
     */
    public String getValue(double firstComponent, double secondComponent) {
        Coord keyToGet = new Coord(firstComponent, secondComponent);
        return super.getValue(keyToGet);
    }

    /**
     * Checks if a specified coordinate contains a landmark value
     * @param firstComponent the first component of the coordinate
     * @param secondComponent the second component of the coordinate
     * @return true if the coordinate exists in dictionary, false otherwise
     */
    public boolean containsCoordinate(double firstComponent, double secondComponent) {
        Coord keyToCheck = new Coord(firstComponent, secondComponent);
        return super.contains(keyToCheck);
    }

    /**
     * Prints all of the landmarks and their associated coordinates to the console
     */
    public void printAllLandmarks() {
        Iterator<Coord> key=super.getKeyIterator();
        Iterator<String> value=super.getValueIterator();
        while(key.hasNext()){
            Coord a=key.next();
            System.out.print("landmark : "+value.next()+"\tCoordinates: latitude: "+ a.getFirstComponent()*Math.pow(10,-6)+"\tlongitude :"+a.getSecondComponent()*Math.pow(10,-6)+"\n");

        }
        // TODO: implement this method to print all landmarks in your dictionary
    }
}
