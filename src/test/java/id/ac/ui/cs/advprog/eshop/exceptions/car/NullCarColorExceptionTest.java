package id.ac.ui.cs.advprog.eshop.exceptions.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NullCarColorExceptionTest {

    @Test
    void testVoidConstructor() {
        NullCarColorException nullCarColorException = new NullCarColorException();

        assertThrows(NullCarColorException.class,
            () -> { throw nullCarColorException; });
    }

    @Test
    void testStringConstructor() {
        NullCarColorException nullCarColorException =
            new NullCarColorException("A null car color exception has occured");

        Exception exception = assertThrows(NullCarColorException.class,
            () -> { throw nullCarColorException; });

        assertEquals(nullCarColorException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullCarColorException nullCarColorException = new NullCarColorException(throwable);

        Exception exception = assertThrows(NullCarColorException.class,
            () -> { throw nullCarColorException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        NullCarColorException nullCarColorException =
            new NullCarColorException(
                "A null car color exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(NullCarColorException.class,
            () -> { throw nullCarColorException; });

        assertEquals(nullCarColorException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
