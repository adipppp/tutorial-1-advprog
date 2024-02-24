package id.ac.ui.cs.advprog.eshop.exceptions.car;

public class NullCarColorException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field \"color\" is null";

    public NullCarColorException() {
        super(DEFAULT_MESSAGE);
    }

    public NullCarColorException(String message) {
        super(message);
    }

    public NullCarColorException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public NullCarColorException(String message, Throwable cause) {
        super(message, cause);
    }
}
