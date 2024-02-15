package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IllegalProductQuantityExceptionTest {

    @Test
    void testStringConstructor() {
        IllegalProductQuantityException illegalProductQuantityException =
            new IllegalProductQuantityException("An illegal product quantity exception has occured");

        Exception exception = assertThrows(IllegalProductQuantityException.class,
            () -> { throw illegalProductQuantityException; });

        assertEquals(illegalProductQuantityException.getMessage(), exception.getMessage());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        IllegalProductQuantityException illegalProductQuantityException =
            new IllegalProductQuantityException(
                "An illegal product quantity exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(IllegalProductQuantityException.class,
            () -> { throw illegalProductQuantityException; });

        assertEquals(illegalProductQuantityException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
