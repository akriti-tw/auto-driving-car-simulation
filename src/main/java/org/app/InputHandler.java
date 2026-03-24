package org.app;

import org.app.model.*;

import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public Field readField() {

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

                return new Field(width, height);
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

    public Car readCar() {

        String name = readName();

        int[] pos = readPosition(name);

        String commands = readCommands(name);

        return new Car(name, new Coordinate(pos[0], pos[1]), Direction.fromChar((char) pos[2]), commands);
    }

    private String readName() {
        while (true) {
            System.out.println("Please enter the name of the car:");
            String name = scanner.nextLine();

            if (!name.trim().isEmpty()) return name;
        }
    }

    private int[] readPosition(String name) {

        while (true) {
            System.out.println("Please enter initial position of car " + name + " in x y Direction format:");

            String input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (parts.length != 3) {
                System.out.println("Error: Format must be 'x y Direction '. Example: 1 2 N");
                continue;
            }

            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            char dir = parts[2].toUpperCase().charAt(0);
            try {
                Direction.fromChar(dir);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Direction invalid.");
                continue;
            }

            return new int[]{x, y, dir};
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
