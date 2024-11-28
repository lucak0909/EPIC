package org.epiccalculator;
import org.epiccalculator.Main;

public class Algebra extends Main {

    public static void SelectMode() {
        System.out.print("\nSelect Function: 1. Solve Polynomial; 2. Solve Simultaneous; 3. Graph Polynomial; " +
                "4. Derivation; 5. Integration; 6. Inflection Point; 7. Local Max/Min\n>>> ");
        int mode = input.nextInt();
        while (mode < 1 || mode > 8) {
            System.out.println("\nInvalid selection. Please try again.");
            System.out.print("Select Function: 1. Solve Polynomial; 2. Solve Simultaneous; 3. Graph Polynomial; " +
                    "4. Derivation; 5. Integration; 6. Inflection Point; 7. Local Max/Min\n>>> ");
            mode = input.nextInt();
        }

        switch (mode) {
            case 1:
                System.out.print("\nEnter the number of coefficients in the polynomial " +
                        "(in descending order of exponents):\n>>> ");
                int length = input.nextInt();
                while (length < 2) {
                    System.out.println("\nInvalid length. The polynomial must have at least 2 coefficients. " +
                            "Please try again.");
                    System.out.print("Enter the number of coefficients in the polynomial:\n>>> ");
                    length = input.nextInt();
                }

                double[] coefficients = new double[length + 1];
                System.out.println("\nEnter the coefficients of the polynomial:");
                for (int i = 0; i < coefficients.length - 1; i++) {
                    System.out.print("Coefficient " + (i + 1) + ": ");
                    String element = input.next();
                    if (Validate.isNumeric(element)) {
                        coefficients[i] = Double.parseDouble(element);
                    }
                    else {
                        System.out.println("\nInvalid input. Please enter a numeric value.");
                        i--;
                    }
                }

                System.out.print("\nEnter a value for x:\n>>> ");
                String x = input.next();
                while (! Validate.isNumeric(x)) {
                    System.out.println("\nInvalid input. Please enter a numeric value.");
                    System.out.print("Enter a value for x:\n>>> ");
                    x = input.next();
                }
                System.out.println("\n\nSolution:\nWhen x = " + x + "; y = " + solvePoly(coefficients, Double.parseDouble(x)));
                break;
            case 2:
                ;
                break;
            // Add more cases for other modes
            default:
                System.out.println("\nInvalid selection. Please try again.");
        }
    }

    public static double solvePoly(double[] coefficients, double x) {
        double result = 0;
        int exponent = coefficients.length - 1;

        for (double coefficient : coefficients) {
            result += coefficient * Math.pow(x, exponent);
            exponent--;
        }

        return result;
    }

    public static double[] solveSimultaneous(double[][] matrix, double[] constants) {
        return constants;
    }

    public static double[] graphPoly(double[] coefficients) {
        return coefficients;
    }

    public static double[] findDerivative(double[] coefficients) {
        return coefficients;
    }

    public static double[] integrate(double[] coefficients) {
        return coefficients;
    }

    public static double[] findInflectionPoint(double[] coefficients) {
        return coefficients;
    }

    public static double[] findMaxMin(double[] coefficients) {
        return coefficients;
    }
}
