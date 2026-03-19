package org.app;

import org.app.model.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResultPrinterTest {

    @Test
    void shouldPrintCarWithoutCollision() {

        Car car = new Car(
                "A",
                new Coordinate(5,4),
                Direction.N,
                "FF"
        );

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(output));

        ResultPrinter.print(List.of(car));

        System.setOut(original);

        String result = output.toString();

        assertTrue(result.contains("After simulation, the result is:"));
        assertTrue(result.contains("- A, (5,4) N"));
    }

    @Test
    void shouldPrintCarWithCollision() {

        Car car = new Car(
                "A",
                new Coordinate(5,4),
                Direction.N,
                "FF"
        );

        car.markCollided(7, List.of("B"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(output));

        ResultPrinter.print(List.of(car));

        System.setOut(original);

        String result = output.toString();

        assertTrue(result.contains("A, collides with B"));
        assertTrue(result.contains("(5,4)"));
        assertTrue(result.contains("step 7"));
    }

    @Test
    void shouldPrintMultipleCollisions() {

        Car car = new Car(
                "A",
                new Coordinate(3,3),
                Direction.N,
                "FF"
        );

        car.markCollided(4, List.of("B", "C"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(output));

        ResultPrinter.print(List.of(car));

        System.setOut(original);

        String result = output.toString();

        assertTrue(result.contains("collides with B, C"));
    }
}
