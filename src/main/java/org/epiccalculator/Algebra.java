package org.epiccalculator;
import org.epiccalculator.Main;

public class Algebra extends Main {

    public static void SelectMode() {
        System.out.print("\nSelect Function: 1. Evaluate Polynomial; 2. Find Roots of a Polynomial; 3. Solve Simultaneous;" +
                " 4. Graph Polynomial; 5. Derivation; 6. Integration; 7. Inflection Point; 8. Local Max/Min\n>>> ");
        int mode = input.nextInt();
        while (mode < 1 || mode > 8) {
            System.out.println("\nInvalid selection. Please try again.");
            System.out.print("Select Function: 1. Evaluate Polynomial; 2. Find Roots of a Polynomial; 3. Solve Simultaneous;" +
                    " 4. Graph Polynomial; 5. Derivation; 6. Integration; 7. Inflection Point; 8. Local Max/Min\n>>> ");
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
                System.out.print("\nEnter the number of coefficients in the polynomial " +
                        "(in descending order of exponents):\n>>> ");
                length = input.nextInt();
                while (length < 2) {
                    System.out.println("\nInvalid length. The polynomial must have at least 2 coefficients. " +
                            "Please try again.");
                    System.out.print("Enter the number of coefficients in the polynomial:\n>>> ");
                    length = input.nextInt();
                }

                coefficients = new double[length + 1];
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

                System.out.println("Enter your initial guess: ");
                String initialGuess = input.next();
                while (!Validate.isNumeric(initialGuess)) {
                    System.out.println("\nInvalid input. Please enter a numeric value.");
                    System.out.print("Enter your initial guess: ");
                    initialGuess = input.next();
                }

                System.out.print("\nEnter the tolerance level (recommended tolerance < 1): ");
                String tolerance = input.next();
                while (!Validate.isNumeric(tolerance)) {
                    System.out.println("\nInvalid input. Please enter a numeric value.");
                    System.out.print("Enter the tolerance level: ");
                    tolerance = input.next();
                }

                System.out.println("\nSolution:\nRoot = " + findRoots(coefficients, Double.parseDouble(initialGuess), Double.parseDouble(tolerance), 500));
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

    public static double[] graphPoly(double[] coefficients) {
        return coefficients;
    }

    public static double evaluateDerivative(double[] coefficients, double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length - 1; i++) {
            result += (coefficients.length - i - 1) * coefficients[i] * Math.pow(x, coefficients.length - i - 2);
        }
        return result;
    }

    public static double findRoots(double[] coefficients, double initialGuess, double tolerance, int maxIterations) {
        double x = initialGuess;
        for (int i = 0; i < maxIterations; i++) {
            double fx = solvePoly(coefficients, x);
            double dfx = evaluateDerivative(coefficients, x);

            if (Math.abs(dfx) < 1e-10) {  // Avoid division by zero or very small values
                throw new IllegalStateException("Derivative is too small. Newton's method failed.");
            }

            double x1 = x - fx / dfx;
            if (Math.abs(x1 - x) < tolerance) {
                return x1;
            }
            x = x1;
        }
        throw new IllegalStateException("Newton's method did not converge within the given iterations.");
    }

    public static double[] solveSimultaneous(double[][] matrix, double[] constants) {
        return constants;
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
