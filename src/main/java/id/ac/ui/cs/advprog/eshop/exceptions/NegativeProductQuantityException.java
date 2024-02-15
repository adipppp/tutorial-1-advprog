package id.ac.ui.cs.advprog.eshop.exceptions;

public class NegativeProductQuantityException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field Product.productQuantity is less than 0";

    public NegativeProductQuantityException() {
        super(DEFAULT_MESSAGE);
    }

    public NegativeProductQuantityException(String message) {
        super(message);
    }

    public NegativeProductQuantityException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public NegativeProductQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
