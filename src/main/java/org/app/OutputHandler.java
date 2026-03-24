package org.app;

import org.app.model.Car;

import java.util.List;

public class OutputHandler {
    public static void printResults(List<Car> cars) {
        System.out.println("\nAfter simulation, the result is:");

        for (Car car : cars) {
            if (car.isCollided()) {
                System.out.println("- " + car.getName() +
                        ", collides with " + String.join(", ", car.getCollidedWith()) +
                        " at (" + car.getPosition().getX() + "," + car.getPosition().getY() + ") at step " +
                        car.getCollisionStep());
            } else {
                System.out.println("- " + car.getName() +
                        ", (" + car.getPosition().getX() + "," + car.getPosition().getY() + ") " +
                        car.getDirection());
            }
        }
    }

    public static void printCars(List<Car> cars) {

        System.out.println("\nYour current list of cars are:");

        for (Car c : cars) {
            System.out.println("- " + c.getName() +
                    ", (" + c.getPosition().getX() + "," + c.getPosition().getY() + ") " +
                    c.getDirection() + ", " + c.getCommands());
        }
    }
}
