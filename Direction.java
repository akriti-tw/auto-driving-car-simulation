public enum Direction {
    N, S, E, W;

    public static Direction fromChar(char c) {
        return switch (c) {
            case 'N' -> N;
            case 'S' -> S;
            case 'E' -> E;
            case 'W' -> W;
            default -> throw new IllegalArgumentException("Invalid direction: " + c);
        };
    }

    public static boolean isValid(char dir) {
        return dir == 'N' || dir == 'S' || dir == 'E' || dir == 'W';
    }
}