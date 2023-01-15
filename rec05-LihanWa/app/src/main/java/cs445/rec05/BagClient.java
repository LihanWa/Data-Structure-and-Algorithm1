package cs445.rec05;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that utilizes the UndoableBag
 * as a simple client.
 * @author Brian Nixon
 * @author William C. Garrison III
 */

public class BagClient {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter some command line args to add to the undoable bag.");
            System.exit(0);
        }

        UndoableBag<String> bag = new UndoableBag<String>();

        for (String s : args) {
            bag.add(s);
        }
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        
        // 5 indicates to end the program
        while (choice != 5) {
            System.out.println("The bag currently contains:\t" + bag.toString());
            displayPrompt();
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch(InputMismatchException err) {
                System.out.println("Please enter your choice as a number");
            }
            String input = "";
            switch(choice) {
                case 1:
                    System.out.println("Please enter the word to insert");
                    input = scanner.nextLine();
                    bag.add(input);
                    break;
                case 2:
                    System.out.println("Please enter the word to remove");
                    input = scanner.nextLine();
                    bag.remove(input);
                    break;
                case 3:
                    bag.undo();
                    break;
                case 4:
                    bag.redo();
                    break;
                default:
                    break;
            }
        }

        scanner.close();
    }

    public static void displayPrompt() {
        System.out.println("Please make a selection (1 - 5):");
        System.out.println("1.\tInsert a word to the bag.\n2.\tRemove a word from the bag.\n3.\tUndo last action.\n4.\tRedo last undo.\n5.\tEnd program.");
    }

}