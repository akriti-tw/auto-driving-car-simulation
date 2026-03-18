package org.app.model;

public enum Direction {
    N, S, E, W;

    public static Direction fromChar(char c) {
        return switch (Character.toUpperCase(c)) {
            case 'N' -> N;
            case 'S' -> S;
            case 'E' -> E;
            case 'W' -> W;
            default -> throw new IllegalArgumentException("Invalid direction: " + c);
        };
    }

    public Direction left() {
        return switch (this) {
            case N -> W;
            case W -> S;
            case S -> E;
            case E -> N;
        };
    }

    public Direction right() {
        return switch (this) {
            case N -> E;
            case E -> S;
            case S -> W;
            case W -> N;
        };
    }
}