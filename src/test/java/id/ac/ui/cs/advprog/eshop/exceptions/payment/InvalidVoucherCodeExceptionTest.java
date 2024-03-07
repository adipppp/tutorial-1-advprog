package id.ac.ui.cs.advprog.eshop.exceptions.payment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidVoucherCodeExceptionTest {

    @Test
    void testVoidConstructor() {
        InvalidVoucherCodeException invalidVoucherCodeException =
            new InvalidVoucherCodeException();

        assertThrows(InvalidVoucherCodeException.class,
            () -> { throw invalidVoucherCodeException; });
    }

    @Test
    void testStringConstructor() {
        InvalidVoucherCodeException invalidVoucherCodeException =
            new InvalidVoucherCodeException("An invalid voucher code exception has occured");

        Exception exception = assertThrows(InvalidVoucherCodeException.class,
            () -> { throw invalidVoucherCodeException; });

        assertEquals(invalidVoucherCodeException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        InvalidVoucherCodeException invalidVoucherCodeException =
            new InvalidVoucherCodeException(throwable);

        Exception exception = assertThrows(InvalidVoucherCodeException.class,
            () -> { throw invalidVoucherCodeException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        InvalidVoucherCodeException invalidVoucherCodeException =
            new InvalidVoucherCodeException(
                "An invalid voucher code exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(InvalidVoucherCodeException.class,
            () -> { throw invalidVoucherCodeException; });

        assertEquals(invalidVoucherCodeException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
