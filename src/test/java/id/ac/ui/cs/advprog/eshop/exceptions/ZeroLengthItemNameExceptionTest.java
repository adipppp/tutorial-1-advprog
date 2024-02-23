package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ZeroLengthItemNameExceptionTest {

    @Test
    void testVoidConstructor() {
        ZeroLengthItemNameException zeroLengthItemNameException = new ZeroLengthItemNameException();

        assertThrows(ZeroLengthItemNameException.class,
            () -> { throw zeroLengthItemNameException; });
    }

    @Test
    void testStringConstructor() {
        ZeroLengthItemNameException zeroLengthItemNameException =
            new ZeroLengthItemNameException("A zero length item name exception has occured");

        Exception exception = assertThrows(ZeroLengthItemNameException.class,
            () -> { throw zeroLengthItemNameException; });

        assertEquals(zeroLengthItemNameException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthItemNameException zeroLengthItemNameException = new ZeroLengthItemNameException(throwable);

        Exception exception = assertThrows(ZeroLengthItemNameException.class,
            () -> { throw zeroLengthItemNameException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthItemNameException zeroLengthItemNameException =
            new ZeroLengthItemNameException(
                "A zero length item name exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(ZeroLengthItemNameException.class,
            () -> { throw zeroLengthItemNameException; });

        assertEquals(zeroLengthItemNameException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
