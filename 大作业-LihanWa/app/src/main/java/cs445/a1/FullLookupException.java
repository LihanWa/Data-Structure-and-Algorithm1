package cs445.a1;

/**
 * An exception that is thrown when a lookup operation cannot be completed
 * because the lookup does not have the available capacity. Should never be
 * thrown in implementations that resize.
 */
public class FullLookupException extends RuntimeException {
    public FullLookupException() { super(); }
    public FullLookupException(String e) { super(e); }
}

