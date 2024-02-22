package id.ac.ui.cs.advprog.eshop.exceptions;

public class ZeroLengthItemIdException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field \"id\" has 0 length";

    public ZeroLengthItemIdException() {
        super(DEFAULT_MESSAGE);
    }

    public ZeroLengthItemIdException(String message) {
        super(message);
    }

    public ZeroLengthItemIdException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ZeroLengthItemIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
