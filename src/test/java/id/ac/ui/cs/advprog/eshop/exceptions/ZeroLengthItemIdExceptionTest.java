package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ZeroLengthItemIdExceptionTest {
    
    @Test
    void testVoidConstructor() {
        ZeroLengthItemIdException zeroLengthItemIdException = new ZeroLengthItemIdException();

        assertThrows(ZeroLengthItemIdException.class,
            () -> { throw zeroLengthItemIdException; });
    }

    @Test
    void testStringConstructor() {
        ZeroLengthItemIdException zeroLengthItemIdException =
            new ZeroLengthItemIdException("A zero length item ID exception has occured");

        Exception exception = assertThrows(ZeroLengthItemIdException.class,
            () -> { throw zeroLengthItemIdException; });

        assertEquals(zeroLengthItemIdException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthItemIdException zeroLengthItemIdException = new ZeroLengthItemIdException(throwable);

        Exception exception = assertThrows(ZeroLengthItemIdException.class,
            () -> { throw zeroLengthItemIdException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        ZeroLengthItemIdException zeroLengthItemIdException =
            new ZeroLengthItemIdException(
                "A zero length item ID exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(ZeroLengthItemIdException.class,
            () -> { throw zeroLengthItemIdException; });

        assertEquals(zeroLengthItemIdException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
