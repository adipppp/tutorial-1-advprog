package id.ac.ui.cs.advprog.eshop.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductNotFoundExceptionTest {

    @Test
    void testVoidConstructor() {
        ProductNotFoundException productNotFoundException = new ProductNotFoundException();

        assertThrows(ProductNotFoundException.class,
            () -> { throw productNotFoundException; });
    }

    @Test
    void testStringConstructor() {
        ProductNotFoundException productNotFoundException =
            new ProductNotFoundException("A product not found exception has occured");

        Exception exception = assertThrows(ProductNotFoundException.class,
            () -> { throw productNotFoundException; });

        assertEquals(productNotFoundException.getMessage(), exception.getMessage());
    }

    @Test
    void testThrowableConstructor() {
        Throwable throwable = new Throwable();
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(throwable);

        Exception exception = assertThrows(ProductNotFoundException.class,
            () -> { throw productNotFoundException; });

        assertEquals(throwable, exception.getCause());
    }

    @Test
    void testStringAndThrowableConstructor() {
        Throwable throwable = new Throwable();
        ProductNotFoundException productNotFoundException =
            new ProductNotFoundException(
                "A product not found exception has occured because of a throwable", throwable);

        Exception exception = assertThrows(ProductNotFoundException.class,
            () -> { throw productNotFoundException; });

        assertEquals(productNotFoundException.getMessage(), exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }
}
