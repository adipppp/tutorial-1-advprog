package id.ac.ui.cs.advprog.eshop.exceptions.car;

public class ZeroLengthCarColorException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field \"color\" has 0 length";

    public ZeroLengthCarColorException() {
        super(DEFAULT_MESSAGE);
    }

    public ZeroLengthCarColorException(String message) {
        super(message);
    }

    public ZeroLengthCarColorException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public ZeroLengthCarColorException(String message, Throwable cause) {
        super(message, cause);
    }
}
