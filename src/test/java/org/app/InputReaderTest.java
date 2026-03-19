package org.app;

import org.app.model.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputReaderTest {

    @Test
    void shouldReadValidField() {
        String input = "10 9\n";
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        Field field = reader.readField();

        assertEquals(10, field.getWidth());
        assertEquals(9, field.getHeight());
    }


    @Test
    void shouldRetryWhenInputIsOutOfBound() {
        String input = "-1 -1\n10 10\n"; // first input is invalid, second is valid
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        Field field = reader.readField();

        assertNotEquals(-1, field.getWidth());
        assertNotEquals(-1, field.getHeight());
        assertEquals(10, field.getWidth());
        assertEquals(10, field.getHeight());
    }

    @Test
    void shouldRetryWhenInputIsNotInCorrectFormat() {
        String input = "invalid input\n10 10\n"; // first input is invalid, second is valid
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        Field field = reader.readField();

        assertEquals(10, field.getWidth());
        assertEquals(10, field.getHeight());
    }


    @Test
    void shouldReadMenuOption() {
        String input = "1\n";
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        int option = reader.readMenuOption();

        assertEquals(1, option);
    }


    @Test
    void shouldRetryMenuOptionUntilValid() {
        String input = "5\n2\n";
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        int option = reader.readMenuOption();

        assertNotEquals(5, option);
        assertEquals(2, option);
    }

    @Test
    void shouldRetryWhenExceptionThrown() {
        String input = "invalid input\n2\n";
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        int option = reader.readRestartOption();

        assertDoesNotThrow(() -> {});
        assertEquals(2, option);
    }

    @Test
    void shouldReadRestartOption() {
        String input = "2\n";
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        int option = reader.readRestartOption();

        assertEquals(2, option);
    }

    @Test
    void shouldRetryRestartOptionUntilValid() {
        String input = "5\n1\n";
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        int option = reader.readRestartOption();

        assertEquals(1, option);
    }

    @Test
    void shouldRetryRestartOptionWhenExceptionThrown() {
        String input = "invalid input\n1\n";
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        int option = reader.readRestartOption();

        assertDoesNotThrow(() -> {});
        assertEquals(1, option);
    }


    @Test
    void shouldReadCarSuccessfully() {
        String input =
                "A\n" +       // name
                        "1 2 N\n" +   // position
                        "FFR\n";      // commands
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        Field field = new Field(10,10);
        SimulationService service = new SimulationService(field, new ArrayList<>());

        Car car = reader.readCar(service);

        assertEquals("A", car.getName());
        assertEquals(1, car.getPosition().getX());
        assertEquals(2, car.getPosition().getY());
        assertEquals(Direction.N, car.getDirection());
        assertEquals("FFR", car.getCommands());
    }

    @Test
    void shouldRetryWhenCarNameIsDuplicate() {

        String input =
                "A\n" +     // duplicate name
                        "B\n" +     // valid name
                        "1 2 N\n" +
                        "FF\n";

        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        Field field = new Field(10, 10);

        List<Car> existingCars = new ArrayList<>();
        existingCars.add(new Car("A", new Coordinate(0,0), Direction.N, "F"));

        SimulationService service = new SimulationService(field, existingCars);

        Car car = reader.readCar(service);

        assertEquals("B", car.getName());
    }

    @Test
    void shouldRetryWhenPositionFormatInvalid() {

        String input =
                "A\n" +
                        "1 2\n" +      // invalid format
                        "0 0 N\n" +
                        "FF\n";

        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        Field field = new Field(10, 10);
        SimulationService service = new SimulationService(field, new ArrayList<>());

        Car car = reader.readCar(service);

        assertEquals(0, car.getPosition().getX());
        assertEquals(0, car.getPosition().getY());
    }

    @Test
    void shouldRetryCommandsWhenInvalid() {
        String input =
                "A\n" +
                        "1 2 N\n" +
                        "FX\n" +     // invalid
                        "FFR\n";     // valid
        InputReader reader = new InputReader(
                new Scanner(new ByteArrayInputStream(input.getBytes()))
        );

        Field field = new Field(10,10);
        SimulationService service = new SimulationService(field, new ArrayList<>());

        Car car = reader.readCar(service);

        assertEquals("FFR", car.getCommands());
    }
}
