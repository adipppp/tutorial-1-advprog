package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NullItemIdExceptionTest {
    
    @Test
    void testVoidConstructor() {
        NullItemIdException nullItemIdException = new NullItemIdException();

        assertThrows(NullItemIdException.class,
            () -> { throw nullItemIdException; });
    }

    @Test
    void testStringConstructor() {
        NullItemIdException nullItemIdException =
            new NullItemIdException("A null item ID exception has occured");

        Exception exception = assertThrows(NullItemIdException.class,
            () -> { throw nullItemIdException; });

        assertEquals(nullItemIdException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullItemIdException nullItemIdException = new NullItemIdException(throwable);

        Exception exception = assertThrows(NullItemIdException.class,
            () -> { throw nullItemIdException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullItemIdException nullItemIdException =
            new NullItemIdException(
                "A null item ID exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(NullItemIdException.class,
            () -> { throw nullItemIdException; });

        assertEquals(nullItemIdException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
