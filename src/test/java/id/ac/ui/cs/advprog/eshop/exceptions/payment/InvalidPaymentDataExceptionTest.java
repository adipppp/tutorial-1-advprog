package id.ac.ui.cs.advprog.eshop.exceptions.payment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidPaymentDataExceptionTest {

    @Test
    void testVoidConstructor() {
        InvalidPaymentDataException invalidVoucherCodeException =
            new InvalidPaymentDataException();

        assertThrows(InvalidPaymentDataException.class,
            () -> { throw invalidVoucherCodeException; });
    }

    @Test
    void testStringConstructor() {
        InvalidPaymentDataException invalidVoucherCodeException =
            new InvalidPaymentDataException("An invalid payment data exception has occured");

        Exception exception = assertThrows(InvalidPaymentDataException.class,
            () -> { throw invalidVoucherCodeException; });

        assertEquals(invalidVoucherCodeException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        InvalidPaymentDataException invalidVoucherCodeException =
            new InvalidPaymentDataException(throwable);

        Exception exception = assertThrows(InvalidPaymentDataException.class,
            () -> { throw invalidVoucherCodeException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        InvalidPaymentDataException invalidVoucherCodeException =
            new InvalidPaymentDataException(
                "An invalid payment data exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(InvalidPaymentDataException.class,
            () -> { throw invalidVoucherCodeException; });

        assertEquals(invalidVoucherCodeException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
