package id.ac.ui.cs.advprog.eshop.exceptions.payment;

public class InvalidVoucherCodeException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Voucher code does not meet requirements";

    public InvalidVoucherCodeException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidVoucherCodeException(String message) {
        super(message);
    }

    public InvalidVoucherCodeException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public InvalidVoucherCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
