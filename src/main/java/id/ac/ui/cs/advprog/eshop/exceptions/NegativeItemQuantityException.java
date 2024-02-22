package id.ac.ui.cs.advprog.eshop.exceptions;

public class NegativeItemQuantityException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field \"quantity\" is less than 0";

    public NegativeItemQuantityException() {
        super(DEFAULT_MESSAGE);
    }

    public NegativeItemQuantityException(String message) {
        super(message);
    }

    public NegativeItemQuantityException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public NegativeItemQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
