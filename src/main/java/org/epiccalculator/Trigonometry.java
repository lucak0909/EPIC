package org.epiccalculator;

// Handles trigonometric functionalities for solving triangles.
public class Trigonometry extends Main {

    // Provides methods to solve triangles.
    public static class TriangleSolver {

        // Handles user input for solving triangles.
        public static void triangleSolve() {
            // Display options for triangle data input (ASA, AAS, SSS, SAS, SSA)
            System.out.println("Choose the type of triangle data you have:");
            System.out.println("1: ASA (2 angles and 1 side)");
            System.out.println("2: AAS (2 angles and 1 side)");
            System.out.println("3: SSS (3 sides)");
            System.out.println("4: SAS (2 sides and 1 angle)");
            System.out.println("5: SSA (2 sides and 1 angle - ambiguous case)");

            // Read user's choice
            int choice = input.nextInt();

            // Initialize variables for sides and angles (using Double for angles to allow null values)
            double a = 0, b = 0, c = 0;
            Double A = null, B = null, C = null;

            // Prompt user for input based on their choice
            switch (choice) {
                case 1: // ASA
                    System.out.println("Enter angle A (degrees):");
                    A = input.nextDouble();
                    System.out.println("Enter angle B (degrees):");
                    B = input.nextDouble();
                    System.out.println("Enter side c:");
                    c = input.nextDouble();
                    break;
                case 2: // AAS
                    System.out.println("Enter angle A (degrees):");
                    A = input.nextDouble();
                    System.out.println("Enter angle B (degrees):");
                    B = input.nextDouble();
                    System.out.println("Enter side a:");
                    a = input.nextDouble();
                    break;
                case 3: // SSS
                    System.out.println("Enter side a:");
                    a = input.nextDouble();
                    System.out.println("Enter side b:");
                    b = input.nextDouble();
                    System.out.println("Enter side c:");
                    c = input.nextDouble();
                    break;
                case 4: // SAS
                    System.out.println("Enter side a:");
                    a = input.nextDouble();
                    System.out.println("Enter angle C (degrees):");
                    C = input.nextDouble();
                    System.out.println("Enter side b:");
                    b = input.nextDouble();
                    break;
                case 5: // SSA
                    System.out.println("Enter side a:");
                    a = input.nextDouble();
                    System.out.println("Enter side b:");
                    b = input.nextDouble();
                    System.out.println("Enter angle A (degrees):");
                    A = input.nextDouble();
                    break;
                default:
                    System.out.println("Invalid choice. Exiting.");
                    return; // Exit the method if the choice is invalid
            }

            // Call the method to solve the triangle
            solveTriangle(a, b, c, A, B, C);
        }

        // Solves the triangle based on provided side and angle data.
        public static void solveTriangle(double a, double b, double c, Double A, Double B, Double C) {
            // Count known angles and sides.
            int knownAngles = countNonNull(A, B, C);
            int knownSides = countNonZero(a, b, c);

            try {
                if (knownAngles == 2 && knownSides == 1) solveASAorAAS(a, b, c, A, B, C);
                else if (knownSides == 3) solveSSS(a, b, c);
                else if (knownSides == 2 && knownAngles == 1) solveSAS(a, b, c, A, B, C);
                else if (knownSides == 2 && knownAngles == 0) solveSSA(a, b, c, A, B, C); // SSA (ambiguous case)
                else throw new IllegalArgumentException("Invalid combination of inputs.");
            } catch (Exception e) {
                System.out.println("Error solving triangle: " + e.getMessage());
            }
        }

        // counts the number of non-null angles in the input parameters
        private static int countNonNull(Double... angles) {
            int count = 0;
            for (Double angle : angles) if (angle != null) count++;
            return count;
        }

        // counts the number of non-zero sides in the input parameters
        private static int countNonZero(double... sides) {
            int count = 0;
            for (double side : sides) if (side > 0) count++;
            return count;
        }

        // solves the triangle using Angle-side-Angle (ASA) or Angle-Angle-Side (AAS) method.
        private static void solveASAorAAS(double a, double b, double c, Double A, Double B, Double C) {
            // Calculates missing angle
            if (A == null) A = 180 - B - C;
            if (B == null) B = 180 - A - C;
            if (C == null) C = 180 - A - B;

            // Calculates missing side depending on the known side using sine rule
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
            // Prints results
            printResults(a, b, c, A, B, C);
        }

        // solves the triangle using Side-Side-Angle (SSS) method.
        private static void solveSSS(double a, double b, double c) {
            // Solves using cosine rule
            double A = Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c)));
            double B = Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c)));
            double C = 180 - A - B;

            printResults(a, b, c, A, B, C);
        }

        // solves the triangle using Side-Angle-Side (SAS) method.
        private static void solveSAS(double a, double b, double c, Double A, Double B, Double C) {

            // solves using cosine rule
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

            printResults(a, b, c, A, B, C);
        }

        // solves the triangle using Side-Side-Angle (SSA) method.
        private static void solveSSA(double a, double b, double c, Double A, Double B, Double C) {
            // Handle ambiguous case using sine rule
            if (A != null && a > 0 && b > 0) {
                double sinB = b * Math.sin(Math.toRadians(A)) / a;
                if (sinB > 1 || sinB < 0) throw new IllegalArgumentException("No solution for SSA case.");
                B = Math.toDegrees(Math.asin(sinB));
                C = 180 - A - B;
                c = a * Math.sin(Math.toRadians(C)) / Math.sin(Math.toRadians(A));

                printResults(a, b, c, A, B, C);
            }
        }

        // Prints the results of the solved triangle.
        private static void printResults(double a, double b, double c, Double A, Double B, Double C) {
            System.out.printf("Solved Triangle:\nA = %.2f°, B = %.2f°, C = %.2f°\na = %.2f, b = %.2f, c = %.2f\n", A, B, C, a, b, c);
        }
    }

    // Handles trigonometric functions.
    public static void trigFunctions() {
        double angleDegrees = 0;
        double angleRadians;
        double result;

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

            // switch to change the unit of the angle using Math functions
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
                // preforming trig functions based on user input
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
                    result = Math.asin(angleDegrees);
                    result = Math.toDegrees(result);
                    break;
                case 5:
                    result = Math.acos(angleDegrees);
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

    // Main method to handle user mode choice and call appropriate methods
    public static void main(String[] args) {
        System.out.println("Select Mode:");
        System.out.println("1. Solve Triangle");
        System.out.println("2. Trig Functions");
        System.out.print(">>> ");

        int mode = input.nextInt();

        // handles user mode choice
        switch (mode) {
            case 1:
                TriangleSolver.triangleSolve();
                break;
            case 2:
                trigFunctions();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

}