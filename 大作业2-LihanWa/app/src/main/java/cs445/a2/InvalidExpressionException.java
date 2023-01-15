package cs445.a2;

/**
 * An exception to be thrown when an expression is found to be invalid
 */
public class InvalidExpressionException extends RuntimeException {
    InvalidExpressionException(String msg) {
        super(msg);
    }
}

