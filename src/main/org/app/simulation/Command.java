package src.main.org.app.simulation;

public enum Command {
    F, L, R;

    static void fromChar(char c) {
        try {
            Command.valueOf(String.valueOf(c));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid command: " + c);
        }
    }
}
