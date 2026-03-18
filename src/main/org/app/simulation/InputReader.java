package src.main.org.app.simulation;

import java.util.*;

public class InputReader {

    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int[] readFieldSize() {

        while (true) {

            System.out.println("Please enter the width and height of the simulation field in x y format:");

            try {
                int width = scanner.nextInt();
                int height = scanner.nextInt();
                scanner.nextLine();

                if (width <= 0 || height <= 0) {
                    System.out.println("Error: Width and height must be positive integers.");
                    continue;
                }

                System.out.println("\nYou have created a field of " + width + " x " + height + ".");

                return new int[]{width, height};
            } catch (Exception e) {
                System.out.println("Error: Please enter valid numbers.");
                scanner.nextLine();
            }
        }
    }

    public int readMenuOption() {

        while (true) {

            System.out.println("\nPlease choose from the following options:");
            System.out.println("[1] Add a car to field");
            System.out.println("[2] Run simulation");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1 || option == 2) {
                    return option;
                }

            } catch (Exception e) {
                scanner.nextLine();
            }

            System.out.println("Invalid option.");
        }
    }

    public Car readCar(SimulationService simulationService) {

        String name = readUniqueName(simulationService.getCars());

        int[] pos = readPosition(name, simulationService.width, simulationService.height);

        String commands = readCommands(name);

        return new Car(name, new Coordinate(pos[0], pos[1]), Direction.valueOf(String.valueOf((char) pos[2])), commands);
    }

    private String readUniqueName(List<Car> cars) {

        while (true) {

            System.out.println("Please enter the name of the car:");
            String name = scanner.nextLine();

            if (cars.isEmpty()) return name;

            boolean duplicate = cars.stream()
                    .anyMatch(c -> c.name.equalsIgnoreCase(name));

            if (!duplicate) return name;

            System.out.println("Error: src.main.org.app.Car name must be unique.");
        }
    }

    private int[] readPosition(String name, int width, int height) {

        while (true) {

            System.out.println("Please enter initial position of car " + name + " in x y Direction format:");

            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length != 3) {
                System.out.println("Error: Format must be 'x y Direction '. Example: 1 2 N");
                continue;
            }

            try {

                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                char dir = parts[2].toUpperCase().charAt(0);

                if (x < 0 || x >= width || y < 0 || y >= height) {
                    System.out.println("Error: Position must be within field boundaries.");
                    continue;
                }

//                if (!Direction.isValid(dir)) {
//                    System.out.println("Error: Direction  must be N,S,E,W");
//                    continue;
//                }

                try {
                        Direction.fromChar(dir);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Direction invalid.");
                        continue;
                    }

                return new int[]{x, y, dir};

            } catch (Exception e) {
                System.out.println("Error: Invalid input.");
            }
        }
    }

    private String readCommands(String name) {

        while (true) {

            System.out.println("Please enter the commands for car " + name + ":");
            String commands = scanner.nextLine().toUpperCase();

            try {
                commands.chars()
                        .forEach(c -> Command.fromChar((char) c));

                return commands;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Command invalid.");
            }
        }
    }

    public int readRestartOption() {

        while (true) {

            System.out.println("\nPlease choose from the following options:");
            System.out.println("[1] Start over");
            System.out.println("[2] Exit");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1 || option == 2) return option;

            } catch (Exception e) {
                scanner.nextLine();
            }

            System.out.println("Invalid option.");
        }
    }
}

