package service;

import java.util.Scanner;

/**
 * Example of a pattern bridge implementation
 */
public class PatternBridge {

    public static void main(String[] args) {

        String model;
        int manufacturerYear;

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the model of your vehicle: ");
            model = scanner.next();

            System.out.print("Enter the manufacture year of your vehicle: ");
            manufacturerYear = scanner.nextInt();
        }

        VehicleManager vehicleManager = new VehicleManager();
        vehicleManager.vehicle = new VehicleImpl(manufacturerYear, model);

        System.out.printf("Your %s is %d years old !!!%n", vehicleManager.getModel(), vehicleManager.getAge());

    }

}