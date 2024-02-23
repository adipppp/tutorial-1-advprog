package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ItemNotFoundExceptionTest {

    @Test
    void testVoidConstructor() {
        ItemNotFoundException itemNotFoundException = new ItemNotFoundException();

        assertThrows(ItemNotFoundException.class,
            () -> { throw itemNotFoundException; });
    }

    @Test
    void testStringConstructor() {
        ItemNotFoundException itemNotFoundException =
            new ItemNotFoundException("An item not found exception has occured");

        Exception exception = assertThrows(ItemNotFoundException.class,
            () -> { throw itemNotFoundException; });

        assertEquals(itemNotFoundException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        ItemNotFoundException itemNotFoundException = new ItemNotFoundException(throwable);

        Exception exception = assertThrows(ItemNotFoundException.class,
            () -> { throw itemNotFoundException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        ItemNotFoundException itemNotFoundException =
            new ItemNotFoundException(
                "An item not found exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(ItemNotFoundException.class,
            () -> { throw itemNotFoundException; });

        assertEquals(itemNotFoundException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
