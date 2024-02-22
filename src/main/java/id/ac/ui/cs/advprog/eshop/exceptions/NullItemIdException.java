package id.ac.ui.cs.advprog.eshop.exceptions;

public class NullItemIdException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field \"id\" is null";

    public NullItemIdException() {
        super(DEFAULT_MESSAGE);
    }

    public NullItemIdException(String message) {
        super(message);
    }

    public NullItemIdException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public NullItemIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
