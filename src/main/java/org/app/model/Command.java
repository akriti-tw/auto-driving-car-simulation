package org.app.model;

public enum Command {
    F, L, R;

    public static Command fromChar(char c) {
        return switch (Character.toUpperCase(c)) {
            case 'F' -> F;
            case 'L' -> L;
            case 'R' -> R;
            default -> throw new IllegalArgumentException("Invalid command: " + c);
        };
    }
}
