package org.app;

import org.app.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationServiceTest {

    @Test
    void shouldMoveSingleCarCorrectly() {

        Field field = new Field(10, 10);

        Car car = new Car(
                "A",
                new Coordinate(1,2),
                Direction.N,
                "FF"
        );

        SimulationService service =
                new SimulationService(field, new ArrayList<>(List.of(car)));

        service.runSimulation();

        assertEquals(1, car.getPosition().getX());
        assertEquals(4, car.getPosition().getY());
        assertFalse(car.isCollided());
    }

    @Test
    void shouldDetectCollisionBetweenTwoCars() {
        Field field = new Field(10, 10);

        Car carA = new Car(
                "A",
                new Coordinate(0,0),
                Direction.N,
                "F"
        );
        Car carB = new Car(
                "B",
                new Coordinate(0,2),
                Direction.S,
                "F"
        );

        SimulationService service =
                new SimulationService(field, new ArrayList<>(List.of(carA, carB)));

        service.runSimulation();

        assertTrue(carA.isCollided());
        assertTrue(carB.isCollided());
        assertEquals(List.of("B"), carA.getCollidedWith());
        assertEquals(List.of("A"), carB.getCollidedWith());
        assertEquals(1, carA.getCollisionStep());
        assertEquals(1, carB.getCollisionStep());
    }

    @Test
    void shouldStopCarsAfterCollision() {
        Field field = new Field(10, 10);

        Car carA = new Car(
                "A",
                new Coordinate(0,0),
                Direction.N,
                "FFFF"
        );
        Car carB = new Car(
                "B",
                new Coordinate(0,2),
                Direction.S,
                "FFFF"
        );

        SimulationService service =
                new SimulationService(field, new ArrayList<>(List.of(carA, carB)));

        service.runSimulation();

        assertTrue(carA.isCollided());
        assertTrue(carB.isCollided());
        assertEquals(1, carA.getCollisionStep());

        Coordinate collisionPoint = carA.getPosition();

        assertEquals(new Coordinate(0,1), collisionPoint);
        assertEquals(collisionPoint.getX(), carB.getPosition().getX());
        assertEquals(collisionPoint.getY(), carB.getPosition().getY());
    }

    @Test
    void shouldHandleCarsWithDifferentCommandLengths() {
        Field field = new Field(10, 10);

        Car carA = new Car(
                "A",
                new Coordinate(1,1),
                Direction.N,
                "F"
        );

        Car carB = new Car(
                "B",
                new Coordinate(3,3),
                Direction.N,
                "FFFF"
        );

        SimulationService service =
                new SimulationService(field, new ArrayList<>(List.of(carA, carB)));

        service.runSimulation();

        assertEquals(1, carA.getPosition().getX());
        assertEquals(2, carA.getPosition().getY());
        assertEquals(3, carB.getPosition().getX());
        assertEquals(7, carB.getPosition().getY());
    }

    @Test
    void shouldDetectCollisionBetweenMultipleCars() {
        Field field = new Field(10, 10);

        Car carA = new Car(
                "A",
                new Coordinate(0,0),
                Direction.E,
                "F"
        );
        Car carB = new Car(
                "B",
                new Coordinate(2,0),
                Direction.W,
                "F"
        );
        Car carC = new Car(
                "C",
                new Coordinate(1,2),
                Direction.S,
                "FF"
        );

        SimulationService service =
                new SimulationService(field, new ArrayList<>(List.of(carA, carB, carC)));

        service.runSimulation();

        assertTrue(carA.isCollided());
        assertEquals(1, carA.getCollisionStep());
        assertTrue(carB.isCollided());
        assertEquals(1, carB.getCollisionStep());
        assertTrue(carC.isCollided());
        assertEquals(2, carC.getCollisionStep());
        assertEquals(List.of("B"), carA.getCollidedWith());
        assertEquals(List.of("A"), carB.getCollidedWith());
        assertEquals(List.of("A", "B"), carC.getCollidedWith());
    }

}
