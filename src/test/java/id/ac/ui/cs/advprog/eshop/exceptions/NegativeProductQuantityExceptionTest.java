package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NegativeProductQuantityExceptionTest {
    
    @Test
    void testVoidConstructor() {
        NegativeProductQuantityException negativeProductQuantityException =
            new NegativeProductQuantityException();

        assertThrows(NegativeProductQuantityException.class,
            () -> { throw negativeProductQuantityException; });
    }

    @Test
    void testStringConstructor() {
        NegativeProductQuantityException negativeProductQuantityException =
            new NegativeProductQuantityException("A negative product quantity exception has occured");

        Exception exception = assertThrows(NegativeProductQuantityException.class,
            () -> { throw negativeProductQuantityException; });

        assertEquals(negativeProductQuantityException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        NegativeProductQuantityException negativeProductQuantityException =
            new NegativeProductQuantityException(throwable);

        Exception exception = assertThrows(NegativeProductQuantityException.class,
            () -> { throw negativeProductQuantityException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        NegativeProductQuantityException negativeProductQuantityException =
            new NegativeProductQuantityException(
                "A negative product quantity exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(NegativeProductQuantityException.class,
            () -> { throw negativeProductQuantityException; });

        assertEquals(negativeProductQuantityException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
