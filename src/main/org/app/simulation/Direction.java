package src.main.org.app.simulation;

public enum Direction {
    N, S, E, W;

    public static Direction fromChar(char c) {
        try {
            return Direction.valueOf(String.valueOf(c));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid direction: " + c);
        }
    }
}