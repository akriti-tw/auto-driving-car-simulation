package org.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car {

    private final String name;
    private Coordinate position;
    private Direction direction;
    private final String commands;
    private int commandIndex = 0;

    private boolean collided = false;
    private final List<String> collidedWith = new ArrayList<>();
    private int collisionStep = -1;

    public Car(String name, Coordinate position, Direction direction, String commands) {
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public Coordinate getPosition() {
        return position;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getTotalCommandsLength() {
        return commands == null ? 0 : commands.length();
    }

    public boolean hasNextCommand() {
        return commandIndex < (commands == null ? 0 : commands.length());
    }

    public char getNextCommand() {
        return commands.charAt(commandIndex++);
    }

    public boolean isCollided() {
        return collided;
    }

    public void markCollided(int step, List<String> otherNames) {
        if (!this.collided) {
            this.collided = true;
            this.collisionStep = step;
            this.collidedWith.addAll(otherNames);
        }
    }

    public List<String> getCollidedWith() {
        return Collections.unmodifiableList(collidedWith);
    }

    public int getCollisionStep() {
        return collisionStep;
    }

    public String getCommands() {
        return commands;
    }

    public void executeNextCommand(Field field) {

        if (!hasNextCommand() || collided) return;

        Command command = Command.fromChar(getNextCommand());

        switch (command) {
            case L -> turnLeft();
            case R -> turnRight();
            case F -> moveForward(field);
        }
    }

    public void turnLeft() {
        direction = direction.left();
    }

    public void turnRight() {
        direction = direction.right();
    }

    public void moveForward(Field field) {
        Coordinate next = position.move(direction);

        if (field.isInside(next)) {
            position = next;
        }
    }

}
