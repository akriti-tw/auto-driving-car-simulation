import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader(scanner);

        while (true) {

            System.out.println("Welcome to Auto Driving Car Simulation!");

            int[] field = inputReader.readFieldSize();
            int width = field[0];
            int height = field[1];

            Simulation simulation = new Simulation(width, height, new ArrayList<>());

            while (true) {

                int option = inputReader.readMenuOption();

                if (option == 1) {

                    Car car = inputReader.readCar(simulation.getCars());

                    simulation.addCar(car);

                    simulation.printCars();

                } else {

                    if (simulation.getCars().isEmpty()) {
                        System.out.println("No cars added. Please add a car first.");
                        continue;
                    }

                    simulation.runSimulation();
                    ResultPrinter.print(simulation.getCars());

                    int choice = inputReader.readRestartOption();

                    if (choice == 2) {
                        System.out.println("Thank you for running the simulation. Goodbye!");
                        return;
                    }

                    break;
                }
            }
        }
    }
}
