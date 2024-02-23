package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {
    private static final String CREATE_CAR = "CreateCar";
    private static final String CAR_LIST = "CarList";
    private static final String REDIRECT_CAR_LIST = "redirect:/car/list";
    private static final String EDIT_CAR = "EditCar";
    private static final String DELETE_CAR = "DeleteCar";

    private static final String RUNTIME_EXCEPTION_MSG = "An exception has occured";

    private AutoCloseable closeable;

    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void cleanUp() throws Exception {
        closeable.close();
    }

    @Test
    void testCreateCarPage() {
        Model modelMock = Mockito.mock(Model.class);

        String expectedViewName = CREATE_CAR;
        String actualViewName = carController.createCarPage(modelMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateCarPost() {
        Model modelMock = Mockito.mock(Model.class);
        Car carMock = Mockito.mock(Car.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = REDIRECT_CAR_LIST;
        String actualViewName = carController.createCarPost(modelMock, carMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateCarPostIfResultHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        Car carMock = Mockito.mock(Car.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(resultMock.hasErrors()).thenReturn(true);

        String expectedViewName = CREATE_CAR;
        String actualViewName = carController.createCarPost(modelMock, carMock, resultMock);
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateCarPostIfCreateHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        Car carMock = Mockito.mock(Car.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(carService.create(carMock))
            .thenThrow(new RuntimeException(RUNTIME_EXCEPTION_MSG));

        String expectedViewName = CREATE_CAR;
        String actualViewName = carController.createCarPost(modelMock, carMock, resultMock);
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCarListPage() {
        Model modelMock = Mockito.mock(Model.class);

        String expectedViewName = CAR_LIST;
        String actualViewName = carController.carListPage(modelMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditCarPage() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        String expectedViewName = EDIT_CAR;
        String actualViewName = carController.editCarPage(modelMock, carId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditCarPageIfFindOneHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        Mockito.when(carService.findById(carId))
            .thenThrow(new RuntimeException(RUNTIME_EXCEPTION_MSG));

        String expectedViewName = REDIRECT_CAR_LIST;
        String actualViewName = carController.editCarPage(modelMock, carId);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditCarPost() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Car carMock = Mockito.mock(Car.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = REDIRECT_CAR_LIST;
        String actualViewName = carController.editCarPost(
            modelMock, carId, carMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditCarPostIfResultHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Car carMock = Mockito.mock(Car.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(resultMock.hasErrors()).thenReturn(true);

        String expectedViewName = EDIT_CAR;
        String actualViewName = carController.editCarPost(
            modelMock, carId, carMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditCarPostIfEditHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Car carMock = Mockito.mock(Car.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(carService.update(carMock))
            .thenThrow(new RuntimeException(RUNTIME_EXCEPTION_MSG));

        String expectedViewName = EDIT_CAR;
        String actualViewName = carController.editCarPost(
            modelMock, carId, carMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteCarPage() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        String expectedViewName = DELETE_CAR;
        String actualViewName = carController.deleteCarPage(modelMock, carId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteCarPageIfFindOneHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        Mockito.when(carService.findById(carId))
            .thenThrow(new RuntimeException(RUNTIME_EXCEPTION_MSG));

        String expectedViewName = REDIRECT_CAR_LIST;
        String actualViewName = carController.deleteCarPage(modelMock, carId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteCarPost() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Car carMock = Mockito.mock(Car.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = REDIRECT_CAR_LIST;
        String actualViewName = carController.deleteCarPost(
            modelMock, carId, carMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteCarPostIfDeleteHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String carId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Car carMock = Mockito.mock(Car.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(carService.deleteById(carId))
            .thenThrow(new RuntimeException(RUNTIME_EXCEPTION_MSG));

        String expectedViewName = REDIRECT_CAR_LIST;
        String actualViewName = carController.deleteCarPost(
            modelMock, carId, carMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }
}
