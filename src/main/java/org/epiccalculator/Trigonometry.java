package org.epiccalculator;

import java.util.Scanner;

public class Trigonometry extends Main {

    public static void solveTriangle(double a, double b, double c, Double A, Double B, Double C) {
        int knownAngles = 0;
        int knownSides = 0;

        if (A != null) knownAngles++;
        if (B != null) knownAngles++;
        if (C != null) knownAngles++;
        if (a > 0) knownSides++;
        if (b > 0) knownSides++;
        if (c > 0) knownSides++;

        // ASA or AAS
        if (knownAngles == 2 && knownSides == 1) {
            if (A == null) A = 180 - B - C;
            if (B == null) B = 180 - A - C;
            if (C == null) C = 180 - A - B;

            if (a > 0) {
                b = a * Math.sin(Math.toRadians(B)) / Math.sin(Math.toRadians(A));
                c = a * Math.sin(Math.toRadians(C)) / Math.sin(Math.toRadians(A));
            } else if (b > 0) {
                a = b * Math.sin(Math.toRadians(A)) / Math.sin(Math.toRadians(B));
                c = b * Math.sin(Math.toRadians(C)) / Math.sin(Math.toRadians(B));
            } else if (c > 0) {
                a = c * Math.sin(Math.toRadians(A)) / Math.sin(Math.toRadians(C));
                b = c * Math.sin(Math.toRadians(B)) / Math.sin(Math.toRadians(C));
            }
        }

        // SSS
        else if (knownSides == 3) {
            A = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c)));
            B = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c)));
            C = 180 - A - B;
        }

        // SAS
        else if (knownSides == 2 && knownAngles == 1) {
            if (A != null) {
                c = Math.sqrt(a * a + b * b - 2 * a * b * Math.cos(Math.toRadians(A)));
                B = Math.toDegrees(Math.asin(b * Math.sin(Math.toRadians(A)) / c));
                C = 180 - A - B;
            } else if (B != null) {
                c = Math.sqrt(a * a + b * b - 2 * a * b * Math.cos(Math.toRadians(B)));
                A = Math.toDegrees(Math.asin(a * Math.sin(Math.toRadians(B)) / c));
                C = 180 - A - B;
            } else if (C != null) {
                a = Math.sqrt(b * b + c * c - 2 * b * c * Math.cos(Math.toRadians(C)));
                A = Math.toDegrees(Math.asin(a * Math.sin(Math.toRadians(C)) / b));
                B = 180 - A - C;
            }
        }

        // SSA (Ambiguous case)
        else if (knownSides == 2 && knownAngles == 0) {
            if (A != null && a > 0 && b > 0) {
                B = Math.toDegrees(Math.asin(b * Math.sin(Math.toRadians(A)) / a));
                C = 180 - A - B;
                c = a * Math.sin(Math.toRadians(C)) / Math.sin(Math.toRadians(A));
            } else if (B != null && b > 0 && a > 0) {
                A = Math.toDegrees(Math.asin(a * Math.sin(Math.toRadians(B)) / b));
                C = 180 - A - B;
                c = b * Math.sin(Math.toRadians(C)) / Math.sin(Math.toRadians(B));
            }
        } else {
            System.out.println("Invalid combination of inputs.");
            return;
        }

        System.out.printf("Solved Triangle:\nA = %.2f°, B = %.2f°, C = %.2f°\na = %.2f, b = %.2f, c = %.2f\n",
                A, B, C, a, b, c);
    }

    public static void trigFunctions() {
        double angleDegrees;
        double angleRadians;
        double result;

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

            System.out.print("Are you working in degrees or radians?");
            System.out.println("1. Degrees");
            System.out.println("2. Radians");
            System.out.print(">>> ");

            int angleUnit = input.nextInt();

            if (angleUnit < 1 || angleUnit > 2) {
                System.out.println("Invalid choice. Please try again.");
                continue; // Skip to the next iteration of the loop
            }

            switch (angleUnit) {
                case 1:
                    System.out.print("Enter angle in degrees: ");
                    angleDegrees = input.nextDouble();
                    angleRadians = Math.toRadians(angleDegrees);
                    break;
                case 2:
                    System.out.print("Enter angle in radians: ");
                    angleRadians = input.nextDouble();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue; // Skip to the next iteration of the loop
            }

            result = 0.0;
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
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            System.out.println("Result: " + result);

        }

    }

}