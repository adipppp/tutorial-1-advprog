package id.ac.ui.cs.advprog.eshop.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.exceptions.ItemNotFoundException;
import id.ac.ui.cs.advprog.eshop.exceptions.NegativeItemQuantityException;
import id.ac.ui.cs.advprog.eshop.exceptions.NullItemIdException;
import id.ac.ui.cs.advprog.eshop.exceptions.NullItemNameException;
import id.ac.ui.cs.advprog.eshop.exceptions.ZeroLengthItemIdException;
import id.ac.ui.cs.advprog.eshop.exceptions.ZeroLengthItemNameException;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
    private static final String NULL_CAR_EXCEPTION_MSG = "updatedCar is null";
    private static final String NULL_CAR_ID_EXCEPTION_MSG = "carId is null";

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car) {
        if (car.getCarId() == null) {
            UUID uuid = UUID.randomUUID();
            car.setCarId(uuid.toString());
        }
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
            throw new IllegalArgumentException(NULL_CAR_EXCEPTION_MSG);

        String updatedCarId = updatedCar.getCarId();
        String updatedCarName = updatedCar.getCarName();
        int updatedCarQuantity = updatedCar.getCarQuantity();

        if (updatedCarId == null)
            throw new NullItemIdException();
        if (updatedCarName == null)
            throw new NullItemNameException();
        if (updatedCarId.length() == 0)
            throw new ZeroLengthItemIdException();
        if (updatedCarName.length() == 0)
            throw new ZeroLengthItemNameException();
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

        if (carId.length() == 0)
            throw new ZeroLengthItemIdException();

        Car car;
        try {
            car = carRepository.deleteById(carId);
        } catch (ItemNotFoundException exception) {
            throw new ItemNotFoundException(exception);
        }

        return car;
    }
}
