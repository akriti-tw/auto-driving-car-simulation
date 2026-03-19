package org.app.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void shouldMoveForward() {

        Field field = new Field(10,10);

        Car car = new Car(
                "A",
                new Coordinate(1,2),
                Direction.N,
                "F"
        );

        car.executeNextCommand(field);

        assertEquals(1, car.getPosition().getX());
        assertEquals(3, car.getPosition().getY());
    }


    @Test
    void shouldTurnLeft() {

        Field field = new Field(10,10);

        Car car = new Car(
                "A",
                new Coordinate(1,2),
                Direction.N,
                "L"
        );

        car.executeNextCommand(field);

        assertEquals(Direction.W, car.getDirection());
    }


    @Test
    void shouldTurnRight() {

        Field field = new Field(10,10);

        Car car = new Car(
                "A",
                new Coordinate(1,2),
                Direction.N,
                "R"
        );

        car.executeNextCommand(field);

        assertEquals(Direction.E, car.getDirection());
    }


    @Test
    void shouldNotMoveOutsideField() {

        Field field = new Field(10,10);

        Car car = new Car(
                "A",
                new Coordinate(0,0),
                Direction.S,
                "F"
        );

        car.executeNextCommand(field);

        assertEquals(0, car.getPosition().getX());
        assertEquals(0, car.getPosition().getY());
    }


    @Test
    void shouldExecuteMultipleCommandsInSequence() {

        Field field = new Field(10,10);

        Car car = new Car(
                "A",
                new Coordinate(1,2),
                Direction.N,
                "FFR"
        );

        while(car.hasNextCommand()) {
            car.executeNextCommand(field);
        }

        assertEquals(1, car.getPosition().getX());
        assertEquals(4, car.getPosition().getY());
        assertEquals(Direction.E, car.getDirection());
    }


    @Test
    void handlesNullCommands_forLengthAndHasNext() {
        Car car = new Car("A", new Coordinate(0,0), Direction.N, null);

        assertEquals(0, car.getTotalCommandsLength());
        assertFalse(car.hasNextCommand());
    }

    @Test
    void getNextCommand_advancesIndexAndReturnsChars() {
        Car car = new Car("A", new Coordinate(0,0), Direction.N, "LRF");

        assertEquals('L', car.getNextCommand());
        assertTrue(car.hasNextCommand());
        assertEquals('R', car.getNextCommand());
        assertEquals('F', car.getNextCommand());
        assertFalse(car.hasNextCommand());
    }

    @Test
    void executeNextCommand_noCommands_isNoOp() {
        Field field = new Field(5,5);
        Car car = new Car("A", new Coordinate(2,2), Direction.N, "");

        car.executeNextCommand(field);

        assertEquals(2, car.getPosition().getX());
        assertEquals(2, car.getPosition().getY());
    }

    @Test
    void markCollided() {
        Car car = new Car("A", new Coordinate(1,1), Direction.N, "");
        car.markCollided(3, List.of("B"));

        assertTrue(car.isCollided());
        assertEquals(3, car.getCollisionStep());
        assertEquals(List.of("B"), car.getCollidedWith());
    }

}
