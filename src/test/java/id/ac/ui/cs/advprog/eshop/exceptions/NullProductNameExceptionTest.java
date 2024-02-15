package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NullProductNameExceptionTest {
    
    @Test
    void testVoidConstructor() {
        NullProductNameException nullProductNameException = new NullProductNameException();

        assertThrows(NullProductNameException.class,
            () -> { throw nullProductNameException; });
    }

    @Test
    void testStringConstructor() {
        NullProductNameException nullProductNameException =
            new NullProductNameException("A null product name exception has occured");

        Exception exception = assertThrows(NullProductNameException.class,
            () -> { throw nullProductNameException; });

        assertEquals(nullProductNameException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullProductNameException nullProductNameException = new NullProductNameException(throwable);

        Exception exception = assertThrows(NullProductNameException.class,
            () -> { throw nullProductNameException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullProductNameException nullProductNameException =
            new NullProductNameException(
                "A null product name exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(NullProductNameException.class,
            () -> { throw nullProductNameException; });

        assertEquals(nullProductNameException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
