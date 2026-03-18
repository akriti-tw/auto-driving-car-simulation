package src.main.org.app.simulation;

import java.util.ArrayList;
import java.util.List;

public class Car {

    String name;
    Coordinate position;
    Direction direction;
    String commands;
    int commandIndex = 0;

    boolean collided = false;
    List<String> collidedWith = new ArrayList<>();
    int collisionStep = -1;

    public Car(String name, Coordinate position, Direction direction, String commands) {
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.commands = commands;
    }

    public boolean hasNextCommand() {
        return commandIndex < commands.length();
    }

    public char getNextCommand() {
        return commands.charAt(commandIndex++);
    }
}
