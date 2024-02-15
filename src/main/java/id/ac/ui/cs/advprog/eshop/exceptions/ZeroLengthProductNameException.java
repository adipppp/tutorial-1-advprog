package id.ac.ui.cs.advprog.eshop.exceptions;

public class ZeroLengthProductNameException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field Product.productName has 0 length";

    public ZeroLengthProductNameException() {
        super(DEFAULT_MESSAGE);
    }

    public ZeroLengthProductNameException(String message) {
        super(message);
    }

    public ZeroLengthProductNameException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ZeroLengthProductNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
