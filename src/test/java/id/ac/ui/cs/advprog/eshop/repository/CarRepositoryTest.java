package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exceptions.*;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    private AutoCloseable closeable;

    @InjectMocks
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void cleanUp() throws Exception {
        closeable.close();
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("34c9f8ee-0995-49d2-8863-fcf4a8b6320d");
        car.setCarName("Toyota");
        car.setCarQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("34c9f8ee-0995-49d2-8863-fcf4a8b6320d");
        car1.setCarName("Toyota");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("Nissan");
        car2.setCarQuantity(50);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);
        carRepository.create(car);

        assertEquals(car, carRepository.findById("9f163aee-aeba-475d-9a92-e5a78843a118"));
    }

    @Test
    void testFindByIdIfEmpty() {
        assertThrows(ItemNotFoundException.class, () ->
            carRepository.findById("9f163aee-aeba-475d-9a92-e5a78843a118"));
    }

    @Test
    void testFindByIdIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car1.setCarName("Slamet Kopling");
        car1.setCarQuantity(2);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("8418c357-32d1-4c14-8195-086f17ba1399");
        car2.setCarName("");
        car2.setCarQuantity(1);
        carRepository.create(car2);

        assertEquals(car1, carRepository.findById("9f163aee-aeba-475d-9a92-e5a78843a118"));
        assertEquals(car2, carRepository.findById("8418c357-32d1-4c14-8195-086f17ba1399"));
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        carRepository.create(car);

        Car newCar = new Car();
        newCar.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        newCar.setCarName("Icikiwir");
        newCar.setCarQuantity(1);

        Car returnedCar = carRepository.update(newCar);

        assertEquals(newCar.getCarId(), returnedCar.getCarId());
        assertEquals(newCar.getCarName(), returnedCar.getCarName());
        assertEquals(newCar.getCarQuantity(), returnedCar.getCarQuantity());
    }

    @Test
    void testUpdateIfNoSuchCar() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        assertThrows(ItemNotFoundException.class, () -> carRepository.update(car));
    }

    @Test
    void testDelete() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        carRepository.create(car);

        Car returnedCar = carRepository.deleteById("9f163aee-aeba-475d-9a92-e5a78843a118");

        assertEquals(car.getCarId(), returnedCar.getCarId());
        assertEquals(car.getCarName(), returnedCar.getCarName());
        assertEquals(car.getCarQuantity(), returnedCar.getCarQuantity());
    }

    @Test
    void testDeleteIfNoSuchCar() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        assertThrows(ItemNotFoundException.class, () ->
            carRepository.deleteById("9f163aee-aeba-475d-9a92-e5a78843a118"));
    }
}
