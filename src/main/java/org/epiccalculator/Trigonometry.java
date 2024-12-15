package org.epiccalculator;

import java.util.Scanner;

public class Trigonometry extends Main {

    static double angleRadians;
    static double angleDegrees;
    static double result;

    public static void calculateTrigonometry() {
        Scanner input = new Scanner(System.in);

        while (true) {  // Loop for multiple calculations
            System.out.println("\nTrigonometric Functions:");
            System.out.println("1. Sine (sin)");
            System.out.println("2. Sine inverse (sin-1)");
            System.out.println("3. Cosine (cos)");
            System.out.println("4. Cosine inverse (cos-1)");
            System.out.println("5. Tangent (tan)");
            System.out.println("6. Tangent inverse (tan-1)");
            System.out.println("7. Arcsine (asin)");
            System.out.println("8. Arccosine (acos)");
            System.out.println("9. Arctangent (atan)");
            System.out.println("0. Exit");
            System.out.print(">>> ");

            int choice = input.nextInt();

            if (choice == 0) {break;}
            else if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please try again.");
                continue; // Skip to the next iteration of the loop
            }

            System.out.println("Are you entering the angle in degrees or radians?");
            System.out.println("1. Degrees");
            System.out.println("2. Radians");
            System.out.print(">>> ");

            int angleUnit = input.nextInt();

            switch (angleUnit) {
                case 1:
                    System.out.print("Enter angle in degrees: ");
                    angleDegrees = input.nextDouble();
                    angleRadians = Math.toRadians(angleDegrees);
                case 2:
                    System.out.print("Enter angle in radians: ");
                    angleRadians = input.nextDouble();
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            result = 0.0;
            switch (choice) {
                case 1:
                    result = Math.sin(angleRadians);
                    break;
                case 2:


            }

            System.out.println("Result: " + result);

        }


    }

}