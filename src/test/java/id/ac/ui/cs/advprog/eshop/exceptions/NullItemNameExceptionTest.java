package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NullItemNameExceptionTest {
    
    @Test
    void testVoidConstructor() {
        NullItemNameException nullItemNameException = new NullItemNameException();

        assertThrows(NullItemNameException.class,
            () -> { throw nullItemNameException; });
    }

    @Test
    void testStringConstructor() {
        NullItemNameException nullItemNameException =
            new NullItemNameException("A null item name exception has occured");

        Exception exception = assertThrows(NullItemNameException.class,
            () -> { throw nullItemNameException; });

        assertEquals(nullItemNameException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullItemNameException nullItemNameException = new NullItemNameException(throwable);

        Exception exception = assertThrows(NullItemNameException.class,
            () -> { throw nullItemNameException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullItemNameException nullItemNameException =
            new NullItemNameException(
                "A null item name exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(NullItemNameException.class,
            () -> { throw nullItemNameException; });

        assertEquals(nullItemNameException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
