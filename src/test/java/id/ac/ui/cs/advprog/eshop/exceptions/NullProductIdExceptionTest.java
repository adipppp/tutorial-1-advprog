package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NullProductIdExceptionTest {
    
    @Test
    void testVoidConstructor() {
        NullProductIdException nullProductIdException = new NullProductIdException();

        assertThrows(NullProductIdException.class,
            () -> { throw nullProductIdException; });
    }

    @Test
    void testStringConstructor() {
        NullProductIdException nullProductIdException =
            new NullProductIdException("A null product ID exception has occured");

        Exception exception = assertThrows(NullProductIdException.class,
            () -> { throw nullProductIdException; });

        assertEquals(nullProductIdException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullProductIdException nullProductIdException = new NullProductIdException(throwable);

        Exception exception = assertThrows(NullProductIdException.class,
            () -> { throw nullProductIdException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullProductIdException nullProductIdException =
            new NullProductIdException(
                "A null product ID exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(NullProductIdException.class,
            () -> { throw nullProductIdException; });

        assertEquals(nullProductIdException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
