package org;

import org.app.InputHandler;
import org.app.OutputHandler;
import org.app.SimulationService;
import org.app.exception.CarValidationException;
import org.app.model.Car;
import org.app.model.Field;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);

        System.out.println("Welcome to Auto Driving Car Simulation!");

        while (true) {

            Field field = inputHandler.readField();

            SimulationService simulationService = new SimulationService(field, new ArrayList<>());

            while (true) {

                int option = inputHandler.readMenuOption();

                if (option == 1) {

                    while (true) {
                        try {
                            Car car = inputHandler.readCar();
                            simulationService.addCar(car);
                            break;
                        } catch (CarValidationException e) {
                            System.out.println("Error: " + e.getMessage());
                            System.out.println("Please re-enter car details.\n");
                        }
                    }

                    OutputHandler.printCars(simulationService.getCars());

                } else {

                    if (simulationService.getCars().isEmpty()) {
                        System.out.println("No cars added. Please add a car first.");
                        continue;
                    }

                    simulationService.runSimulation();
                    OutputHandler.printResults(simulationService.getCars());

                    int choice = inputHandler.readRestartOption();

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
