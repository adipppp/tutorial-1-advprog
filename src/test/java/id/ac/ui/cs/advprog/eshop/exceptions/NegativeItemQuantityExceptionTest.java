package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NegativeItemQuantityExceptionTest {
    
    @Test
    void testVoidConstructor() {
        NegativeItemQuantityException negativeItemQuantityException =
            new NegativeItemQuantityException();

        assertThrows(NegativeItemQuantityException.class,
            () -> { throw negativeItemQuantityException; });
    }

    @Test
    void testStringConstructor() {
        NegativeItemQuantityException negativeItemQuantityException =
            new NegativeItemQuantityException("A negative item quantity exception has occured");

        Exception exception = assertThrows(NegativeItemQuantityException.class,
            () -> { throw negativeItemQuantityException; });

        assertEquals(negativeItemQuantityException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        NegativeItemQuantityException negativeItemQuantityException =
            new NegativeItemQuantityException(throwable);

        Exception exception = assertThrows(NegativeItemQuantityException.class,
            () -> { throw negativeItemQuantityException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        NegativeItemQuantityException negativeItemQuantityException =
            new NegativeItemQuantityException(
                "A negative item quantity exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(NegativeItemQuantityException.class,
            () -> { throw negativeItemQuantityException; });

        assertEquals(negativeItemQuantityException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
