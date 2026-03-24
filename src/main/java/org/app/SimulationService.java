package org.app;

import org.app.exception.CarValidationException;
import org.app.model.Car;
import org.app.model.Coordinate;
import org.app.model.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationService {

    private final Field field;
    private final List<Car> cars;

    public SimulationService(Field field, List<Car> cars) {
        this.field = field;
        this.cars = cars;
    }

    public void runSimulation() {

        int maxSteps = cars.stream()
                .mapToInt(Car::getTotalCommandsLength)
                .max()
                .orElse(0);

        for (int step = 1; step <= maxSteps; step++) {

            Map<Car, Coordinate> nextPositions = new HashMap<>();

            for (Car car : cars) {

                if (!car.isCollided()) {
                    car.executeNextCommand(field);
                }

                nextPositions.put(car, car.getPosition());
            }

            detectCollisions(step, nextPositions);
        }
    }

    private void detectCollisions(int step, Map<Car, Coordinate> nextPositions) {

        Map<Coordinate, List<Car>> positionMap = new HashMap<>();

        // group cars by position
        for (Car car : cars) {

            Coordinate pos = nextPositions.get(car);

            positionMap.putIfAbsent(pos, new ArrayList<>());
            positionMap.get(pos).add(car);
        }

        for (List<Car> carsAtSamePosition : positionMap.values()) {
            if (carsAtSamePosition.size() > 1) {
                for (Car car : carsAtSamePosition) {
                    if (car.isCollided()) continue;

                    List<String> others = new ArrayList<>();

                    for (Car other : carsAtSamePosition) {
                        if (!other.equals(car)) {
                            others.add(other.getName());
                        }
                    }

                    car.markCollided(step, others);
                }
            }
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        validateName(car);
        validatePosition(car);
        cars.add(car);
    }

    private void validateName(Car car) {

        boolean duplicate = cars.stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(car.getName()));

        if (duplicate) {
            throw new CarValidationException("Car name must be unique.");
        }
    }

    private void validatePosition(Car car) {

        if (!field.isInside(car.getPosition())) {
            throw new CarValidationException("Car position must be inside field.");
        }
    }
}
