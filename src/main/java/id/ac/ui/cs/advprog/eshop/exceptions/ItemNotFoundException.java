package id.ac.ui.cs.advprog.eshop.exceptions;

public class ItemNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "No such item in repository";

    public ItemNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
