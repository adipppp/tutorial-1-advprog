package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ZeroLengthProductIdExceptionTest {
    
    @Test
    void testVoidConstructor() {
        ZeroLengthProductIdException zeroLengthProductIdException = new ZeroLengthProductIdException();

        assertThrows(ZeroLengthProductIdException.class,
            () -> { throw zeroLengthProductIdException; });
    }

    @Test
    void testStringConstructor() {
        ZeroLengthProductIdException zeroLengthProductIdException =
            new ZeroLengthProductIdException("A zero length product ID exception has occured");

        Exception exception = assertThrows(ZeroLengthProductIdException.class,
            () -> { throw zeroLengthProductIdException; });

        assertEquals(zeroLengthProductIdException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthProductIdException zeroLengthProductIdException = new ZeroLengthProductIdException(throwable);

        Exception exception = assertThrows(ZeroLengthProductIdException.class,
            () -> { throw zeroLengthProductIdException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthProductIdException zeroLengthProductIdException =
            new ZeroLengthProductIdException(
                "A zero length product ID exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(ZeroLengthProductIdException.class,
            () -> { throw zeroLengthProductIdException; });

        assertEquals(zeroLengthProductIdException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
