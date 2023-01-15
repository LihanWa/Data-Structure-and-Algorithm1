package cs445.rec07;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equals("-a")) {
            // Find all solutions starting from the empty solution
            Queens.solve(new int[] {0, 0, 0, 0, 0, 0, 0, 0});
        } else {
            // Find one solution starting from the empty solution
            int[] solution = Queens.solveOnce(new int[] {0, 0, 0, 0, 0, 0, 0, 0});
            System.out.println(Arrays.toString(solution));
        }
    }

}

