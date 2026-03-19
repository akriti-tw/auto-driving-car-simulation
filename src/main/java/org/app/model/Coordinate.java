package org.app.model;

import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public Coordinate move(Direction direction) {

        return switch (direction) {
            case N -> new Coordinate(x, y + 1);
            case S -> new Coordinate(x, y - 1);
            case E -> new Coordinate(x + 1, y);
            case W -> new Coordinate(x - 1, y);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
