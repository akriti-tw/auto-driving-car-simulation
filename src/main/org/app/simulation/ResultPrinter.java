package src.main.org.app.simulation;

import java.util.List;

public class ResultPrinter {
    public static void print(List<Car> cars) {
        System.out.println("\nAfter simulation, the result is:");

        for (Car car : cars) {
            if (car.collided) {
                System.out.println("- " + car.name +
                        ", collides with " + String.join(", ", car.collidedWith) +
                        " at (" + car.position.x + "," + car.position.y + ") at step " +
                        car.collisionStep);
            } else {
                System.out.println("- " + car.name +
                        ", (" + car.position.x + "," + car.position.y + ") " +
                        car.direction);
            }
        }
    }
}
