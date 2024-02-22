package id.ac.ui.cs.advprog.eshop.exceptions;

public class NullItemNameException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field \"name\" is null";

    public NullItemNameException() {
        super(DEFAULT_MESSAGE);
    }

    public NullItemNameException(String message) {
        super(message);
    }

    public NullItemNameException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public NullItemNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
