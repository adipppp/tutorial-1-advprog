package id.ac.ui.cs.advprog.eshop.exceptions.payment;

public class InvalidPaymentDataException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Payment data does not meet requirements";

    public InvalidPaymentDataException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidPaymentDataException(String message) {
        super(message);
    }

    public InvalidPaymentDataException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public InvalidPaymentDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
