package org.epiccalculator;

import java.util.Scanner;

public class Trigonometry extends Main {

    public static void calculateTrigonometry() {
        Scanner input = new Scanner(System.in);

        while (true) {  // Loop for multiple calculations
            System.out.println("\nTrigonometric Functions:");
            System.out.println("1. Sine (sin)");
            System.out.println("2. Cosine (cos)");
            System.out.println("3. Tangent (tan)");
            System.out.println("4. Arcsine (asin)");
            System.out.println("5. Arccosine (acos)");
            System.out.println("6. Arctangent (atan)");
            System.out.println("7. Exit");
            System.out.print(">>> ");

            int choice = input.nextInt();

            if (choice == 7) {
                break;  // Exit the loop
            }

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please try again.");
                continue; // Skip to the next iteration of the loop
            }

            System.out.print("Enter angle in degrees: ");
            double angleDegrees = input.nextDouble();
            double angleRadians = Math.toRadians(angleDegrees);


            double result = 0.0;

            switch (choice) {
                case 1:
                    result = Math.sin(angleRadians);
                    break;
                case 2:
                    result = Math.cos(angleRadians);
                    break;
                case 3:
                    result = Math.tan(angleRadians);
                    break;
                case 4:
                    result = Math.asin(angleDegrees); //Ensure input is within [-1, 1]
                    result = Math.toDegrees(result);
                    break;
                case 5:
                    result = Math.acos(angleDegrees); //Ensure input is within [-1, 1]
                    result = Math.toDegrees(result);
                    break;
                case 6:
                    result = Math.atan(angleDegrees);
                    result = Math.toDegrees(result);
                    break;
            }

            System.out.println("Result: " + result);

        }


    }

}