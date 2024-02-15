package id.ac.ui.cs.advprog.eshop.exceptions;

public class IllegalProductQuantityException extends RuntimeException {
    public IllegalProductQuantityException(String message) {
        super(message);
    }

    public IllegalProductQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
