package id.ac.ui.cs.advprog.eshop.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "No such product in repository";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
