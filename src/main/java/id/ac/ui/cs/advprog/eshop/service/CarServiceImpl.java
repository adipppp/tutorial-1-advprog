package id.ac.ui.cs.advprog.eshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.exceptions.*;
import id.ac.ui.cs.advprog.eshop.exceptions.car.*;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
    private static final String NULL_CAR_ID_EXCEPTION_MSG = "carId is null";

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        if (car == null)
            throw new IllegalArgumentException("car is null");

        String carId = car.getCarId();
        String carName = car.getCarName();
        String carColor = car.getCarColor();
        int carQuantity = car.getCarQuantity();

        if (carName == null)
            throw new NullItemNameException();
        if (carName.isEmpty())
            throw new ZeroLengthItemNameException();
        if (carColor == null)
            throw new NullCarColorException();
        if (carColor.isEmpty())
            throw new ZeroLengthCarColorException();
        if (carQuantity < 0)
            throw new NegativeItemQuantityException();

        if (carId == null) {
            UUID uuid = UUID.randomUUID();
            carId = uuid.toString();
            car.setCarId(carId);
        }
        if (carId.isEmpty())
            throw new ZeroLengthItemIdException();

        carRepository.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId) {
        if (carId == null)
            throw new IllegalArgumentException(NULL_CAR_ID_EXCEPTION_MSG);

        Car car;
        try {
            car = carRepository.findById(carId);
        } catch (ItemNotFoundException exception) {
            throw new ItemNotFoundException(exception);
        }

        return car;
    }

    @Override
    public Car update(Car updatedCar) {
        if (updatedCar == null)
            throw new IllegalArgumentException("updatedCar is null");

        String updatedCarId = updatedCar.getCarId();
        String updatedCarName = updatedCar.getCarName();
        String updatedCarColor = updatedCar.getCarColor();
        int updatedCarQuantity = updatedCar.getCarQuantity();

        if (updatedCarId == null)
            throw new NullItemIdException();
        if (updatedCarName == null)
            throw new NullItemNameException();
        if (updatedCarColor == null)
            throw new NullCarColorException();
        if (updatedCarId.isEmpty())
            throw new ZeroLengthItemIdException();
        if (updatedCarName.isEmpty())
            throw new ZeroLengthItemNameException();
        if (updatedCarColor.isEmpty())
            throw new ZeroLengthCarColorException();
        if (updatedCarQuantity < 0)
            throw new NegativeItemQuantityException();

        Car car;
        try {
            car = carRepository.update(updatedCar);
        } catch (ItemNotFoundException exception) {
            throw new ItemNotFoundException(exception);
        }

        return car;
    }

    @Override
    public Car deleteById(String carId) {
        if (carId == null)
            throw new IllegalArgumentException(NULL_CAR_ID_EXCEPTION_MSG);

        Car car;
        try {
            car = carRepository.deleteById(carId);
        } catch (ItemNotFoundException exception) {
            throw new ItemNotFoundException(exception);
        }

        return car;
    }
}
