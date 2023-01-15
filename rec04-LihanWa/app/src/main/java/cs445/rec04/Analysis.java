package cs445.rec04;

public class Analysis {

    /**
     * Sample "ordering" question and answer
     *
     * When putting growth rates in order (as with Questions 1 and 2), use this as an example.
     * Access items by array index to avoid typos in copying strings manually. Give answers in
     * ascending order.
     * Here, n^x represents "n to the power of x", and log(n) represents "the logarithm of n."
     */

    String[] sampleQuestion = {"2.5nlog(n)", "2n+1", "23n^0.5)"};
    String[] sampleAnswer = {sampleQuestion[2], sampleQuestion[1], sampleQuestion[0]};


    /*--------------------------------------------------------------------------------------------*/

    /**
     * Question 1: Sort the following expressions in ascending order of their growth rates.
     *
     * The first item is provided for you. Replace each null with a growth rate from question1 using
     * the same notation.
     */

    String[] question1 = {"n^3", "4n+7", "n!", "3^n", "2nlog(n)"};
    String[] answer1 = {question1[1], question1[4], question1[0], question1[3], question1[2]};


    /*--------------------------------------------------------------------------------------------*/

    /**
     * Question 2: Sort the following expressions in ascending order of their growth rates. Replace
     * each null with a growth rate from question2 using indexing notation as with Question 1.
     */

    String[] question2 = {"(1/2)n", "n^2", "256", "n^4", "log(n^2)"};
    String[] answer2 = {question2[2], question2[4],question2[0], question2[1], question2[3]};


    /*--------------------------------------------------------------------------------------------*/

    /**
     * Question 3: Determine the growth rate of each of the following functions.
     *
     * Remember that you should remove lower order terms and multiplicative constants. Each function
     * is in a variable of the form 'question3x', and for each you should put the growth rate in
     * 'answer3x' (replacing the null value currently assigned). Omit the "O()" notation in this
     * problem.
     *
     * Use ^ for power and log() for logarithm. Put fractions in parenthesis as in "n^(5/4)".
     * Express n*log(n) as "nlog(n)".
     */

    String question3a = "n^2 + 3^n + 28";
    String answer3a = "3^n";

    String question3b = "100 + 80nlog(n) + 123n";
    String answer3b = "nlog(n)";

    String question3c= "4n + (n^3)/4";
    String answer3c= "n^3";

    String question3d = "(n/2 + 5n^3)^(1/2)";
    String answer3d = "n^(3/2)";


    /*--------------------------------------------------------------------------------------------*/

    /**
     * Question 4: Determine the big-O runtime for each code block, and justify your answer.
     *
     * Note that the problems solved by these code blocks are irrelevant; consider only how they
     * scale as the problem size increases.
     *
     * Each code block is defined in a method of the form 'question4x', and for each you should put
     * the growth rate in 'answer4x' (replacing the provided null value) and a brief explanation in
     * 'explanation4x' (again, replacing the provided null value). Each growth rate should be one of
     * the following examples:
     *
     * "O(log(n))", "O(n)", "O(nlog(n))", "O(n^2)", "O(n^3)"
     */

    void question4a(int[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] % 2 == 0)
            counter++;
        }
    }
    String answer4a = "O(n)";
    String explanation4a = "the time for the loop is n";


    void question4b(int n) {
        // print a square checkerboard of size n
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i+j) % 2 == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
    String answer4b = "O(n^2)";
    String explanation4b = "For outer loop, it's n times, for inner loop is also n, n*n";


    void question4c(int n) {
        // Prints out a -fun- ramp (with base length of n)
        // for this stick figure to skateboard down
        System.out.println("   O    ");
        System.out.println("  /|\\/ ");
        System.out.println(" / |\\  ");
        System.out.println(" _/__|_ ");
        System.out.println("  o  o  ");

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("O");
            }
            System.out.println();
        }
    }
    String answer4c = "O(n^2)";
    String explanation4c = "(n-1)+(n-2)+...+1";


    int question4d(int n) {
        int counter = 0;
        while(n > 1) {
            for(int i = 0; i < n; i++) {
                counter++;
            }
            n = n / 2;
        }
        return counter;
    }
    String answer4d = "O(n)";
    String explanation4d = "n+n/2+n/4+...+2";


    int question4e(int[] a, int e) {
        // Binary search!
        int begin = 0, end = a.length, mid;
        while (begin < end) {
            mid = (begin + end) / 2;
            if (e > a[mid]) {
                begin = mid + 1;
            } else if (e < a[mid]) {
                end = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
    String answer4e = "O(log(n))";
    String explanation4e = "2^x=n;x=log(n)";

}

