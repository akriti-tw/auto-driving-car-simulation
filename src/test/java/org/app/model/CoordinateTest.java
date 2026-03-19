package org.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void shouldMoveNorth() {

        Coordinate start = new Coordinate(2, 3);

        Coordinate result = start.move(Direction.N);

        assertEquals(2, result.getX());
        assertEquals(4, result.getY());
    }

    @Test
    void shouldMoveSouth() {

        Coordinate start = new Coordinate(2, 3);

        Coordinate result = start.move(Direction.S);

        assertEquals(2, result.getX());
        assertEquals(2, result.getY());
    }

    @Test
    void shouldMoveEast() {

        Coordinate start = new Coordinate(2, 3);

        Coordinate result = start.move(Direction.E);

        assertEquals(3, result.getX());
        assertEquals(3, result.getY());
    }

    @Test
    void shouldMoveWest() {

        Coordinate start = new Coordinate(2, 3);

        Coordinate result = start.move(Direction.W);

        assertEquals(1, result.getX());
        assertEquals(3, result.getY());
    }

    @Test
    void moveShouldReturnNewCoordinate() {

        Coordinate start = new Coordinate(5, 5);

        Coordinate result = start.move(Direction.N);

        assertNotSame(start, result); // ensures immutability behavior
    }

    @Test
    void shouldFormatToStringCorrectly() {

        Coordinate coordinate = new Coordinate(4, 7);

        assertEquals("(4,7)", coordinate.toString());
    }
}
