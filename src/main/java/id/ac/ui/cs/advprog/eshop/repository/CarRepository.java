package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.exceptions.ItemNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Car;

@Repository
public class CarRepository implements ItemRepository<Car> {
    static int id = 0;

    private List<Car> carData;

    public CarRepository() {
        carData = new ArrayList<>();
    }

    @Override
    public Car create(Car car) {
        carData.add(car);
        return car;
    }

    @Override
    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    @Override
    public Car findById(String id) {
        for (Car car : carData) {
            if (car.getCarId().equals(id))
                return car;
        }
        throw new ItemNotFoundException();
    }

    @Override
    public Car update(Car updatedCar) {
        String carId = updatedCar.getCarId();
        for (Car car : carData) {
            if (car.getCarId().equals(carId)) {
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }
        }
        throw new ItemNotFoundException();
    }

    @Override
    public Car deleteById(String carId) {
        Iterator<Car> carIterator = findAll();
        while (carIterator.hasNext()) {
            Car car = carIterator.next();
            if (car.getCarId().equals(carId)) {
                carIterator.remove();
                return car;
            }
        }
        throw new ItemNotFoundException();
    }
}
