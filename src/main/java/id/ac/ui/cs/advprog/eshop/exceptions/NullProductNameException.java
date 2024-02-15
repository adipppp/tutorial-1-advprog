package id.ac.ui.cs.advprog.eshop.exceptions;

public class NullProductNameException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field Product.productName is null";

    public NullProductNameException() {
        super(DEFAULT_MESSAGE);
    }

    public NullProductNameException(String message) {
        super(message);
    }

    public NullProductNameException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public NullProductNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
