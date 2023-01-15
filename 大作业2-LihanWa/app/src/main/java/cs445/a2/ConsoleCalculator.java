package cs445.a2;

/**
 * This driver creates a Calculator that reads from standard input and evaluates
 * any input provided there.
 */
public class ConsoleCalculator {

    /**
     * Creates a Calculator using System.in, then evaluates its input
     * and prints the result.
     * @param args not used
     */
    public static void main(String[] args) {
        System.out.print("Expression (in infix): ");
        Calculator calculator = new Calculator(System.in);
        try {
            System.out.println("Result: " + calculator.evaluate());
        } catch (InvalidExpressionException e) {
            System.out.println("Invalid expression: " + e.getMessage());
        }
    }

}

