package id.ac.ui.cs.advprog.eshop.exceptions.car;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ZeroLengthCarColorExceptionTest {

    @Test
    void testVoidConstructor() {
        ZeroLengthCarColorException zeroLengthCarColorException = new ZeroLengthCarColorException();

        assertThrows(ZeroLengthCarColorException.class,
            () -> { throw zeroLengthCarColorException; });
    }

    @Test
    void testStringConstructor() {
        ZeroLengthCarColorException zeroLengthCarColorException =
            new ZeroLengthCarColorException("A zero length car color exception has occured");

        Exception exception = assertThrows(ZeroLengthCarColorException.class,
            () -> { throw zeroLengthCarColorException; });

        assertEquals(zeroLengthCarColorException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthCarColorException zeroLengthCarColorException = new ZeroLengthCarColorException(throwable);

        Exception exception = assertThrows(ZeroLengthCarColorException.class,
            () -> { throw zeroLengthCarColorException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthCarColorException zeroLengthCarColorException =
            new ZeroLengthCarColorException(
                "A zero length car color exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(ZeroLengthCarColorException.class,
            () -> { throw zeroLengthCarColorException; });

        assertEquals(zeroLengthCarColorException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
