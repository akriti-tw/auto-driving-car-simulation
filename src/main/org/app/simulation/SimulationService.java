package src.main.org.app.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulationService {

    int width;
    int height;
    List<Car> cars;

    public SimulationService(int width, int height, List<Car> cars) {
        this.width = width;
        this.height = height;
        this.cars = cars;
    }

    public void runSimulation() {

        int maxSteps = 0;
        for (Car c : cars) {
            maxSteps = Math.max(maxSteps, c.commands.length());
        }

        for (int step = 1; step <= maxSteps; step++) {

            Map<Car, int[]> nextPositions = new HashMap<>();

            for (Car car : cars) {

                // Cars that already collided or have no commands left remain stationary
                if (car.collided || !car.hasNextCommand()) {
                    nextPositions.put(car, new int[]{car.position.x, car.position.y});
                    continue;
                }

                char command = car.getNextCommand();

                int newX = car.position.x;
                int newY = car.position.y;

                if (command == 'L') {
                    car.direction = turnLeft(car.direction);
                } else if (command == 'R') {
                    car.direction = turnRight(car.direction);
                } else if (command == 'F') {
                    int[] move = moveForward(car.direction);

                    newX += move[0];
                    newY += move[1];

                    // Ignore move if it goes outside the field
                    if (!isInside(newX, newY)) {
                        newX = car.position.x;
                        newY = car.position.y;
                    }
                }

                nextPositions.put(car, new int[]{newX, newY});
            }

            Map<String, List<Car>> map = new HashMap<>();

            // Group cars by their next position to detect collisions
            for (Car car : cars) {
                int[] pos = nextPositions.get(car);
                String key = pos[0] + "," + pos[1];

                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(car);
            }

            for (List<Car> samePosCars : map.values()) {
                if (samePosCars.size() > 1) {
                    for (Car c : samePosCars) {
                        if (c.collided) continue; // skip if already collided in a previous step

                        c.collided = true;
                        c.collisionStep = step;

                        for (Car other : samePosCars) {
                            if (other != c) {
                                c.collidedWith.add(other.name);
                            }
                        }
                    }
                }
            }

            for (Car car : cars) {
                int[] pos = nextPositions.get(car);
                car.position.x = pos[0];
                car.position.y = pos[1];
            }
        }
    }

    private boolean isInside(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private int[] moveForward(Direction dir) {
        if (dir == Direction.N) return new int[]{0, 1};
        if (dir == Direction.S) return new int[]{0, -1};
        if (dir == Direction.E) return new int[]{1, 0};
        if (dir == Direction.W) return new int[]{-1, 0};
        return new int[]{0, 0};
    }

    private Direction turnLeft(Direction dir) {
        return switch (dir) {
            case N -> Direction.W;
            case W -> Direction.S;
            case S -> Direction.E;
            case E -> Direction.N;
        };
    }

    private Direction turnRight(Direction dir) {
        return switch (dir) {
            case N -> Direction.E;
            case E -> Direction.S;
            case S -> Direction.W;
            case W -> Direction.N;
        };
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void printCars() {

        System.out.println("\nYour current list of cars are:");

        for (Car c : cars) {
            System.out.println("- " + c.name + ", (" + c.position.x + "," + c.position.y + ") " + c.direction + ", " + c.commands);
        }
    }

}
