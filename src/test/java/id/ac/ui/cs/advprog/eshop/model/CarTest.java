package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("34c9f8ee-0995-49d2-8863-fcf4a8b6320d");
        this.car.setCarName("Toyota");
        this.car.setCarQuantity(100);
    }

    @Test
    void testGetCarId() {
        assertEquals("34c9f8ee-0995-49d2-8863-fcf4a8b6320d", this.car.getCarId());
    }

    @Test
    void testGetCarName() {
        assertEquals("Toyota", this.car.getCarName());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(100, this.car.getCarQuantity());
    }
}
