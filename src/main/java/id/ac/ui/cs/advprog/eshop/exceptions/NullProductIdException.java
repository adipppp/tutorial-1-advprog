package id.ac.ui.cs.advprog.eshop.exceptions;

public class NullProductIdException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field Product.productId is null";

    public NullProductIdException() {
        super(DEFAULT_MESSAGE);
    }

    public NullProductIdException(String message) {
        super(message);
    }

    public NullProductIdException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public NullProductIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
