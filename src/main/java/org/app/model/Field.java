package org.app.model;

public class Field {

    private final int width;
    private final int height;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isInside(Coordinate coordinate) {
        return coordinate.getX() >= 0 &&
                coordinate.getX() < width &&
                coordinate.getY() >= 0 &&
                coordinate.getY() < height;
    }

    public int getWidth() { return width;}

    public int getHeight() { return height;}
}

