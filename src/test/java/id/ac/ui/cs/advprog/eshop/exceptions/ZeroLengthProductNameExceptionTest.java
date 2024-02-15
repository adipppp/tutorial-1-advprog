package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ZeroLengthProductNameExceptionTest {

    @Test
    void testVoidConstructor() {
        ZeroLengthProductNameException zeroLengthProductNameException = new ZeroLengthProductNameException();

        assertThrows(ZeroLengthProductNameException.class,
            () -> { throw zeroLengthProductNameException; });
    }

    @Test
    void testStringConstructor() {
        ZeroLengthProductNameException zeroLengthProductNameException =
            new ZeroLengthProductNameException("A zero length product name exception has occured");

        Exception exception = assertThrows(ZeroLengthProductNameException.class,
            () -> { throw zeroLengthProductNameException; });

        assertEquals(zeroLengthProductNameException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthProductNameException zeroLengthProductNameException = new ZeroLengthProductNameException(throwable);

        Exception exception = assertThrows(ZeroLengthProductNameException.class,
            () -> { throw zeroLengthProductNameException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthProductNameException zeroLengthProductNameException =
            new ZeroLengthProductNameException(
                "A zero length product name exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(ZeroLengthProductNameException.class,
            () -> { throw zeroLengthProductNameException; });

        assertEquals(zeroLengthProductNameException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
