package id.ac.ui.cs.advprog.eshop.exceptions;

public class IllegalItemQuantityException extends RuntimeException {
    public IllegalItemQuantityException(String message) {
        super(message);
    }

    public IllegalItemQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
