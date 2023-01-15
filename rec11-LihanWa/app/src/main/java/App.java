import java.util.List;
import java.util.Scanner;

import cs445.rec11.CsvReader;
import cs445.rec11.MapLandmarks;

/**
 * A class to demonstrate the usage of the methods built in MapLandmarks.
 * @author William C. Garrison III
 * @author Norhan Abbas
 * @author Brian T. Nixon
 */
public class App {

    public static void main(String[] args) {
        List<String> lines = CsvReader.readIn("landmarks.txt");
        MapLandmarks map = new MapLandmarks();

        // remove header line from List of strings
        lines.remove(0);

        for (String line : lines) {
            String[] eachLandMark = line.split(",");
            // add them to the dictionary
            String name = eachLandMark[0];
            Double firstComponent = Double.valueOf(eachLandMark[1]);
            Double secondComponent = Double.valueOf(eachLandMark[2]);
            map.addLandmark(name, firstComponent* Math.pow(10,6), secondComponent* Math.pow(10,6));
        }

        Scanner scanner = new Scanner(System.in);

        int userInput = -1;
        String str = "";
        double latitude = 0;
        double longitude = 0;

        while (userInput != 5) {
            userDisplay();
            try {
                userInput = scanner.nextInt();
                scanner.nextLine(); // clear dangling \n
            } catch (Exception error) {
                System.out.println("Enter a valid option");
            }

            switch (userInput) {
            case 1:
                System.out.println("Please enter the landmark name");
                str = scanner.nextLine();

                System.out.println("Please enter its Latitude");
                latitude = scanner.nextDouble();

                System.out.println("Please enter its Longitude");
                longitude = scanner.nextDouble();

                // add them to the dictionary
                map.addLandmark(str, latitude * Math.pow(10,6), longitude * Math.pow(10,6));
                break;
            case 2:
                System.out.println("Please enter the landmark name");
                str = scanner.nextLine();
                // remove that landmark
                map.removeLandmark(str);
                break;

            case 3:
                // print all landmarks
                map.printAllLandmarks();
                break;

            case 4:
                // search a landmark by latitude and longitude
                System.out.println("Please enter X coordinate");
                latitude = scanner.nextDouble();

                System.out.println("Please enter Y coordinate");
                longitude = scanner.nextDouble();
                System.out.println("Returned landmark is:\t" + map.getValue(latitude* Math.pow(10,6), longitude* Math.pow(10,6)));
                break;

            default:
                break;
            }
        }

        scanner.close();
    }

    public static void userDisplay() {
        System.out.println("Please choose from the following (1 - 5):");
        System.out.println("1.\tInsert a landmark by coordinates.\n2.\tRemove a landmark by name.\n3.\tPrint all landmarks.\n4.\tSearch a landmark by latitude and longitude. \n5.\tEnd program.");
    }

}

