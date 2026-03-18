package org;

import org.app.InputReader;
import org.app.ResultPrinter;
import org.app.SimulationService;
import org.app.model.Car;
import org.app.model.Field;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputReader inputReader = new InputReader(scanner);

        while (true) {

            System.out.println("Welcome to Auto Driving Car Simulation!");

            Field field = inputReader.readField();

            SimulationService simulationService = new SimulationService(field, new ArrayList<>());

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
