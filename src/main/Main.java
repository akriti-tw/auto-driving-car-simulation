package src.main;

import src.main.org.app.simulation.SimulationService;
import src.main.org.app.simulation.Car;
import src.main.org.app.simulation.InputReader;
import src.main.org.app.simulation.ResultPrinter;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader(scanner);

        while (true) {

            System.out.println("Welcome to Auto Driving src.main.org.app.Car src.main.org.app.Simulation!");

            int[] field = inputReader.readFieldSize();
            int width = field[0];
            int height = field[1];

            SimulationService simulationService = new SimulationService(width, height, new ArrayList<>());

            while (true) {

                int option = inputReader.readMenuOption();

                if (option == 1) {

                    Car car = inputReader.readCar(simulationService);

                    simulationService.addCar(car);

                    simulationService.printCars();

                } else {

                    if (simulationService.getCars().isEmpty()) {
                        System.out.println("No cars added. Please add a car first.");
                        continue;
                    }

                    simulationService.runSimulation();
                    ResultPrinter.print(simulationService.getCars());

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
