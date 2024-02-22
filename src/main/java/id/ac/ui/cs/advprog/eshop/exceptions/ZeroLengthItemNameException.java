package id.ac.ui.cs.advprog.eshop.exceptions;

public class ZeroLengthItemNameException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field \"name\" has 0 length";

    public ZeroLengthItemNameException() {
        super(DEFAULT_MESSAGE);
    }

    public ZeroLengthItemNameException(String message) {
        super(message);
    }

    public ZeroLengthItemNameException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ZeroLengthItemNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
