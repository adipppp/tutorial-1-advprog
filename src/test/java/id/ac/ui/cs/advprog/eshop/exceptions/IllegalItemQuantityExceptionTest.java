package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IllegalItemQuantityExceptionTest {

    @Test
    void testStringConstructor() {
        IllegalItemQuantityException illegalItemQuantityException =
            new IllegalItemQuantityException("An illegal item quantity exception has occured");

        Exception exception = assertThrows(IllegalItemQuantityException.class,
            () -> { throw illegalItemQuantityException; });

        assertEquals(illegalItemQuantityException.getMessage(), exception.getMessage());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        IllegalItemQuantityException illegalItemQuantityException =
            new IllegalItemQuantityException(
                "An illegal item quantity exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(IllegalItemQuantityException.class,
            () -> { throw illegalItemQuantityException; });

        assertEquals(illegalItemQuantityException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
