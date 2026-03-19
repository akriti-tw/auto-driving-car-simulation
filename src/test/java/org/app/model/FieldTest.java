package org.app.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void shouldReturnTrueForCoordinateInsideField() {

        Field field = new Field(10, 10);

        Coordinate coordinate = new Coordinate(5, 5);

        assertTrue(field.isInside(coordinate));
    }

    @ParameterizedTest
    @CsvSource({
            "-1, 5",
            "5, -1",
            "-2, -3",
            "10, 5",
            "5, 10"
    })
    void shouldReturnFalseForInvalidCoordinates(int x, int y) {

        Field field = new Field(10, 10);

        Coordinate coordinate = new Coordinate(x, y);

        assertFalse(field.isInside(coordinate));
    }
}
