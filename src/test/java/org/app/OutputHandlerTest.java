package org.app;

import org.app.model.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutputHandlerTest {
        @Test
        void printResults_withoutCollision() {

            Car car = createCar("A", 5, 4, Direction.N, "FF");

            String result = captureOutput(() ->
                    OutputHandler.printResults(List.of(car))
            );

            assertTrue(result.contains("After simulation, the result is:"));
            assertTrue(result.contains("- A, (5,4) N"));
        }

        @Test
        void printResults_withCollision() {

            Car car = createCar("A", 5, 4, Direction.N, "FF");
            car.markCollided(7, List.of("B"));

            String result = captureOutput(() ->
                    OutputHandler.printResults(List.of(car))
            );

            assertTrue(result.contains("A, collides with B"));
            assertTrue(result.contains("(5,4)"));
            assertTrue(result.contains("step 7"));
        }

        @Test
        void printResults_withMultipleCollisions() {

            Car car = createCar("A", 3, 3, Direction.N, "FF");
            car.markCollided(4, List.of("B", "C"));

            String result = captureOutput(() ->
                    OutputHandler.printResults(List.of(car))
            );

            assertTrue(result.contains("collides with B, C"));
        }

        @Test
        void printCars_successfully() {

            Car car1 = createCar("A", 1, 2, Direction.N, "FF");
            Car car2 = createCar("B", 3, 4, Direction.E, "LR");

            String result = captureOutput(() ->
                    OutputHandler.printCars(List.of(car1, car2))
            );

            assertTrue(result.contains("A, (1,2) N, FF"));
            assertTrue(result.contains("B, (3,4) E, LR"));
        }

        private String captureOutput(Runnable action) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;

        System.setOut(new PrintStream(output));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }

        return output.toString();
    }

        private Car createCar(String name, int x, int y, Direction direction, String commands) {
        return new Car(name, new Coordinate(x, y), direction, commands);
    }
}
