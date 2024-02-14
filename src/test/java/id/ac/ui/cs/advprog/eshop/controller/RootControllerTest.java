package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RootControllerTest {

    private AutoCloseable closeable;

    @InjectMocks
    private RootController rootController;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void cleanUp() {
        try {
            closeable.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testHomePage() {
        String expectedReturnValue = "homePage";
        String actualReturnValue = rootController.homePage();

        assertEquals(expectedReturnValue, actualReturnValue);
    }
}
