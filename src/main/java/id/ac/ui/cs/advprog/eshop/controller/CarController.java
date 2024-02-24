package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.exceptions.*;
import id.ac.ui.cs.advprog.eshop.exceptions.car.*;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    private static final String CAR_ATTR_NAME = "car";
    private static final String ERR_ATTR_NAME = "error";

    private static final String CREATE_CAR = "CreateCar";
    private static final String CAR_LIST = "CarList";
    private static final String REDIRECT_CAR_LIST = "redirect:/car/listCar";
    private static final String EDIT_CAR = "EditCar";
    private static final String DELETE_CAR = "DeleteCar";

    private static final String ILLEGAL_QUANTITY_EXCEPTION_MSG = "Car quantity is not an integer";
    private static final String NEGATIVE_QUANTITY_EXCEPTION_MSG = "Car quantity cannot be negative";
    private static final String ZERO_LENGTH_NAME_EXCEPTION_MSG = "Car name should not be left empty";
    private static final String NULL_NAME_EXCEPTION_MSG = "Request body is invalid";
    private static final String ZERO_LENGTH_COLOR_EXCEPTION_MSG = "Car color should not be left empty";
    private static final String NULL_COLOR_EXCEPTION_MSG = "Request body is invalid";
    private static final String INVALID_ID_MSG = "Invalid car ID";
    private static final String RUNTIME_EXCEPTION_MSG = "An unknown exception has occured";

    private CarService service;

    @Autowired
    CarController(CarService service) {
        this.service = service;
    }

    @GetMapping({"/createCar", "/createCar/"})
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute(CAR_ATTR_NAME, car);
        return CREATE_CAR;
    }

    @PostMapping({"/createCar", "/createCar/"})
    public String createCarPost(Model model, @ModelAttribute Car car, BindingResult result) {
        try {
            if (result.hasErrors())
                throw new IllegalItemQuantityException(ILLEGAL_QUANTITY_EXCEPTION_MSG);

            service.create(car);

        } catch (IllegalItemQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, ILLEGAL_QUANTITY_EXCEPTION_MSG);
            return CREATE_CAR;

        } catch (NegativeItemQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, NEGATIVE_QUANTITY_EXCEPTION_MSG);
            return CREATE_CAR;

        } catch (ZeroLengthCarColorException exception) {

            model.addAttribute(ERR_ATTR_NAME, ZERO_LENGTH_COLOR_EXCEPTION_MSG);
            return CREATE_CAR;

        } catch (NullCarColorException exception) {

            model.addAttribute(ERR_ATTR_NAME, NULL_COLOR_EXCEPTION_MSG);
            return CREATE_CAR;

        } catch (ZeroLengthItemNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, ZERO_LENGTH_NAME_EXCEPTION_MSG);
            return CREATE_CAR;

        } catch (NullItemNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, NULL_NAME_EXCEPTION_MSG);
            return CREATE_CAR;

        } catch (RuntimeException exception) {

            model.addAttribute(ERR_ATTR_NAME, RUNTIME_EXCEPTION_MSG);
            return CREATE_CAR;

        }

        return REDIRECT_CAR_LIST;
    }

    @GetMapping({"/listCar", "/listCar/"})
    public String carListPage(Model model) {
        List<Car> allCars = service.findAll();
        model.addAttribute("cars", allCars);
        return CAR_LIST;
    }

    @GetMapping({"/editCar", "/editCar/", "/editCar/{carId}", "/editCar/{carId}/"})
    public String editCarPage(Model model, @PathVariable(required=false) String carId) {
        Car car;
        try {
            car = service.findById(carId);
        } catch (RuntimeException exception) {
            return REDIRECT_CAR_LIST;
        }

        model.addAttribute(CAR_ATTR_NAME, car);
        return EDIT_CAR;
    }

    @PostMapping({"/editCar", "/editCar/", "/editCar/{carId}", "/editCar/{carId}/"})
    public String editCarPost(Model model, @PathVariable(required=false) String carId, @ModelAttribute Car car, BindingResult result) {

        car.setCarId(carId);

        try {
            if (result.hasErrors())
                throw new IllegalItemQuantityException(ILLEGAL_QUANTITY_EXCEPTION_MSG);

            service.update(car);

        } catch (IllegalItemQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, ILLEGAL_QUANTITY_EXCEPTION_MSG);
            return EDIT_CAR;

        } catch (ItemNotFoundException | ZeroLengthItemIdException exception) {

            model.addAttribute(ERR_ATTR_NAME, INVALID_ID_MSG);
            return EDIT_CAR;

        } catch (NegativeItemQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, NEGATIVE_QUANTITY_EXCEPTION_MSG);
            return EDIT_CAR;

        } catch (ZeroLengthCarColorException exception) {

            model.addAttribute(ERR_ATTR_NAME, ZERO_LENGTH_COLOR_EXCEPTION_MSG);
            return EDIT_CAR;

        } catch (ZeroLengthItemNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, ZERO_LENGTH_NAME_EXCEPTION_MSG);
            return EDIT_CAR;

        } catch (NullCarColorException exception) {

            model.addAttribute(ERR_ATTR_NAME, NULL_COLOR_EXCEPTION_MSG);
            return EDIT_CAR;

        } catch (NullItemNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, NULL_NAME_EXCEPTION_MSG);
            return EDIT_CAR;

        } catch (NullItemIdException exception) {

            return REDIRECT_CAR_LIST;

        } catch (RuntimeException exception) {

            model.addAttribute(ERR_ATTR_NAME, RUNTIME_EXCEPTION_MSG);
            return EDIT_CAR;

        }

        return REDIRECT_CAR_LIST;
    }

    @GetMapping({"/deleteCar", "/deleteCar/", "/deleteCar/{carId}", "/deleteCar/{carId}"})
    public String deleteCarPage(Model model, @PathVariable(required=false) String carId) {
        Car car;
        try {
            car = service.findById(carId);
        } catch (RuntimeException exception) {
            return REDIRECT_CAR_LIST;
        }

        model.addAttribute(CAR_ATTR_NAME, car);
        return DELETE_CAR;
    }

    @PostMapping({"/deleteCar", "/deleteCar/", "/deleteCar/{carId}", "/deleteCar/{carId}"})
    public String deleteCarPost(Model model, @PathVariable(required=false) String carId, @ModelAttribute Car car, BindingResult result) {

        try {
            service.deleteById(carId);
        } catch (RuntimeException exception) {
            return REDIRECT_CAR_LIST;
        }

        return REDIRECT_CAR_LIST;
    }
}
