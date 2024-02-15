package id.ac.ui.cs.advprog.eshop.exceptions;

public class ZeroLengthProductIdException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field Product.productId has 0 length";

    public ZeroLengthProductIdException() {
        super(DEFAULT_MESSAGE);
    }

    public ZeroLengthProductIdException(String message) {
        super(message);
    }

    public ZeroLengthProductIdException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ZeroLengthProductIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
