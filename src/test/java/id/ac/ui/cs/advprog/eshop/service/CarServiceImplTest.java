package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.exceptions.*;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    private AutoCloseable closeable;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void cleanUp() throws Exception {
        closeable.close();
    }

    @Test
    void testCreate() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);
        
        Car returnedCar = carService.create(car);

        assertEquals(car, returnedCar);
    }

    @Test
    void testCreateIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> carService.create(null));
    }

    @Test
    void testCreateIfCarNameIsNull() {
        Car car = new Car();
        car.setCarQuantity(2);

        assertThrows(NullItemNameException.class, () -> carService.create(car));
    }

    @Test
    void testCreateIfCarNameHasZeroLength() {
        Car car = new Car();
        car.setCarName("");
        car.setCarQuantity(2);

        assertThrows(ZeroLengthItemNameException.class, () -> carService.create(car));
    }

    @Test
    void testCreateIfCarQuantityIsNegative() {
        Car car = new Car();
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(-1);

        assertThrows(NegativeItemQuantityException.class, () -> carService.create(car));
    }

    @Test
    void testCreateIfProductIdIsNull() {
        Car car = new Car();
        car.setCarId(null);
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        assertDoesNotThrow(() -> carService.create(car));
    }

    @Test
    void testCreateIfCarIdHasZeroLength() {
        Car car = new Car();
        car.setCarId("");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        assertThrows(ZeroLengthItemIdException.class, () -> carService.create(car));
    }

    @Test
    void testFindAll() {
        Iterator<Car> carIteratorMock = new ArrayList<Car>().iterator();
        Mockito.when(carRepository.findAll()).thenReturn(carIteratorMock);

        List<Car> carList = carService.findAll();

        assertNotNull(carList);
    }

    @Test
    void testFindById() {
        Car car1 = new Car();
        car1.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car1.setCarName("Slamet Kopling");
        car1.setCarQuantity(2);

        Mockito.when(carRepository.findById(Mockito.any())).thenReturn(car1);

        Car car2 = new Car();
        car2.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car2.setCarName("Slamet Kopling");
        car2.setCarQuantity(2);

        Car returnedValue = null;
        try {
            returnedValue = carService.findById(car2.getCarId());
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(car1.getCarId(), returnedValue.getCarId());
        assertEquals(car1.getCarName(), returnedValue.getCarName());
        assertEquals(car1.getCarQuantity(), returnedValue.getCarQuantity());
    }

    @Test
    void testFindByIdIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> carService.findById(null));
    }

    @Test
    void testFindByIdIfNoSuchCar() {
        String expectedMessage = "No such car in repository";

        Mockito.when(carRepository.findById(Mockito.any()))
            .thenThrow(new ItemNotFoundException(expectedMessage));

        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        String carId = car.getCarId();
        assertNotNull(carId);

        assertThrows(ItemNotFoundException.class, () ->
            carService.findById(carId));
    }

    @Test
    void testDelete() {
        Car car1 = new Car();
        car1.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car1.setCarName("Slamet Kopling");
        car1.setCarQuantity(2);

        Mockito.when(carRepository.deleteById(Mockito.any())).thenReturn(car1);

        Car car2 = new Car();
        car2.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car2.setCarName("Slamet Kopling");
        car2.setCarQuantity(2);

        Car returnedValue = null;
        try {
            returnedValue = carService.deleteById(car2.getCarId());
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(car1.getCarId(), returnedValue.getCarId());
        assertEquals(car1.getCarName(), returnedValue.getCarName());
        assertEquals(car1.getCarQuantity(), returnedValue.getCarQuantity());
    }

    @Test
    void testDeleteByIdIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> carService.deleteById(null));
    }

    @Test
    void testDeleteByIdIfNoSuchCar() {
        String expectedMessage = "No such car in repository";

        Mockito.when(carRepository.deleteById(Mockito.any()))
            .thenThrow(new ItemNotFoundException(expectedMessage));

        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        assertThrows(ItemNotFoundException.class, () ->
            carService.deleteById(car.getCarId()));
    }

    @Test
    void testUpdate() {
        Car car1 = new Car();
        car1.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car1.setCarName("Slamet Kopling");
        car1.setCarQuantity(2);

        Mockito.when(carRepository.update(Mockito.any())).thenReturn(car1);

        Car car2 = new Car();
        car2.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car2.setCarName("Slamet Kopling");
        car2.setCarQuantity(2);

        Car returnedValue = null;
        try {
            returnedValue = carService.update(car2);
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(car1.getCarId(), returnedValue.getCarId());
        assertEquals(car1.getCarName(), returnedValue.getCarName());
        assertEquals(car1.getCarQuantity(), returnedValue.getCarQuantity());
    }

    @Test
    void testUpdateIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> carService.update(null));
    }

    @Test
    void testUpdateIfCarIdIsNull() {
        Car car = new Car();
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        assertThrows(NullItemIdException.class, () -> carService.update(car));
    }

    @Test
    void testUpdateIfCarIdHasZeroLength() {
        Car car = new Car();
        car.setCarId("");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        assertThrows(ZeroLengthItemIdException.class, () -> carService.update(car));
    }

    @Test
    void testUpdateIfCarNameIsNull() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarQuantity(2);

        assertThrows(NullItemNameException.class, () -> carService.update(car));
    }

    @Test
    void testUpdateIfCarNameHasZeroLength() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("");
        car.setCarQuantity(2);

        assertThrows(ZeroLengthItemNameException.class, () -> carService.update(car));
    }

    @Test
    void testUpdateIfCarQuantityIsNegative() {
        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(-1);

        assertThrows(NegativeItemQuantityException.class, () -> carService.update(car));
    }

    @Test
    void testUpdateIfNoSuchCar() {
        String expectedMessage = "An error occured in CarRepository";

        Mockito.when(carRepository.update(Mockito.any()))
            .thenThrow(new ItemNotFoundException(expectedMessage));

        Car car = new Car();
        car.setCarId("9f163aee-aeba-475d-9a92-e5a78843a118");
        car.setCarName("Slamet Kopling");
        car.setCarQuantity(2);

        assertThrows(ItemNotFoundException.class, () -> carService.update(car));
    }
}
