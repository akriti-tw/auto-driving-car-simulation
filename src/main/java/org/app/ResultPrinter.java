package org.app;

import org.app.model.Car;

import java.util.List;

public class ResultPrinter {
    public static void print(List<Car> cars) {
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
}
