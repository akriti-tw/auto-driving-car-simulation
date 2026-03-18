package org.app.model;

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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

}
