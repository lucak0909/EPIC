package org.epiccalculator;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import java.util.Random;

public class Algebra extends Main {

    public static int[] differentiate(int[] coefficients) {
        int n = coefficients.length;
        if (n <= 1) {
            return new int[]{0};
        }

        int[] derivative = new int[n - 1];
        for (int i = 1; i < n; i++) {
            derivative[i - 1] = coefficients[i] * i;
        }
        return derivative;
    }

    public static int[] findNthDerivative(int[] coefficients, int degree) {
        for (int i = 0; i < degree; i++) {
            coefficients = differentiate(coefficients);
        }
        return coefficients;
    }

    public static double evaluatePolynomial(int[] coefficients, double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public static double findCriticalPoint(int[] coefficients, double start, double end, double step) throws Exception {
        double criticalPoint = 0;
        if (determineShape(coefficients, generateRandomSamples(100, -1000, 1000)).equals("U")) {
            criticalPoint = Double.POSITIVE_INFINITY;
        } else if (determineShape(coefficients, generateRandomSamples(100, -1000, 1000)).equals("N")) {
            criticalPoint = Double.NEGATIVE_INFINITY;
        } else {
            throw new Exception("Could not determine shape of function.");
        }

        for (double x = start; x <= end; x += step) {
            double value = evaluatePolynomial(coefficients, x);
            if (value < criticalPoint) {
                criticalPoint = value;
            }
        }
        if (criticalPoint < 0.25 && criticalPoint > -0.25) {
            criticalPoint = 0;
        }

        return criticalPoint;
    }

    public static String findCriticalType(int[] coefficients, double x) {
        String type;
        coefficients = findNthDerivative(coefficients, 2);

        if (evaluatePolynomial(coefficients, x) > 0) {
            type = "Minimum";
        } else if (evaluatePolynomial(coefficients, x) < 0) {
            type = "Maximum";
        } else {
            type = "Saddle Point";
        }

        return type;
    }

    // used for finding shape of curve
    public static double[] generateRandomSamples(int numSamples, double rangeMin, double rangeMax) {
        Random random = new Random();
        double[] samples = new double[numSamples];
        for (int i = 0; i < numSamples; i++) {
            samples[i] = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
        }
        return samples;
    }

    public static String determineShape(int[] coefficients, double[] samplePoints) {
        int positiveCount = 0;
        int negativeCount = 0;

        for (double x : samplePoints) {
            double secondDerivative = evaluatePolynomial(findNthDerivative(coefficients, 2), x);
            if (secondDerivative > 0) {
                positiveCount++;
            } else if (secondDerivative < 0) {
                negativeCount++;
            }
        }

        // Analyze the counts
        if (positiveCount > negativeCount) {
            return "U";
        } else if (negativeCount > positiveCount) {
            return "N";
        } else {
            return "Inconclusive";
        }
    }

    public static double NewtonsMethod(int[] coefficients, double initialGuess) {
        double x = initialGuess;
        double tolerance = 0.05;
        int maxIterations = 1000;
        int iterations = 0;

        while (iterations < maxIterations) {
            double fx = evaluatePolynomial(coefficients, x); // f(x)
            int[] derivative = differentiate(coefficients); // f'(x)
            double fPrimeX = evaluatePolynomial(derivative, x);

            if (Math.abs(fPrimeX) < 1e-10) {
                throw new ArithmeticException("Derivative is too small; Newton's Method may fail.");
            }

            double xNew = x - fx / fPrimeX;

            if (Math.abs(xNew - x) < tolerance) { // to allow a small error margin
                return xNew;
            }

            x = xNew;
            iterations++;
        }

        throw new ArithmeticException("Newton's Method did not converge within the maximum number of iterations. " +
                "Try a different initial guess.");
    }

    public static int[] inputPolynomial() {
        System.out.print("\nEnter the degree of the polynomial: ");
        int degree = input.nextInt();
        while (!Validate.isNumeric(String.valueOf(degree))) {
            System.out.println("\nInvalid input. Please enter a numeric value for the degree.");
            System.out.print("Enter the degree of the polynomial: ");
            degree = input.nextInt();
        }
        int[] coefficients = new int[degree + 1];

        System.out.println("Enter the coefficients starting from the constant term:");
        for (int i = 0; i <= degree; i++) {
            System.out.print("Coefficient of x^" + i + ": ");
            coefficients[i] = input.nextInt();
        }

        return coefficients;
    }

    public static void plotGraph(int[] coefficients) {
        double[] xData = new double[1000]; // large range for ease of use
        double[] yData = new double[1000];
        for (int i = 0; i < xData.length; i++) {
            xData[i] = i - 500; // Example x values from -500 to 499
            yData[i] = evaluatePolynomial(coefficients, xData[i]);
        }

        XYChart chart = QuickChart.getChart("Function Plot", "X", "Y", "f(x)", xData, yData);
        new SwingWrapper<>(chart).displayChart();
    }

    public static void main(String[] args) {

        System.out.println("\nThe algebra calculator can be used to:" +
                "\n - Evaluate any polynomial at a given x" +
                "\n - Find the shape of a Quadratic polynomial curve" +
                "\n - Find the Critical Point of a polynomial within the domain of +- 1000" +
                "\n - Find the first (or nth) derivative of a polynomial" +
                "\n - Estimate the root(s) of a polynomial" +
                "\n - Polt the graph of a polynomial");
        System.out.println("Would you like to continue with the algebraic calculator? (Y/N)");
        System.out.print(">>> ");

        String Continue = input.next();

        while (!Continue.equalsIgnoreCase("Y") && !Continue.equalsIgnoreCase("N")) {
            System.out.println("\nThis is not a valid response. Please enter 'Y' or 'N'.");
            System.out.println(">>> ");
            Continue = input.next();
        }

        if (Continue.equalsIgnoreCase("Y")) {

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Evaluate a Polynomial");
                System.out.println("2. Find the Shape of a Quadratic Polynomial Curve");
                System.out.println("3. Find the First (or nth) Derivative of a Polynomial");
                System.out.println("4. Find Maxima/Minima of a Quadratic Polynomial");
                System.out.println("5. Estimate the Root(s) of a Polynomial");
                System.out.println("6. Plot The graph of a Polynomial");
                System.out.println("7. Exit");
                System.out.print(">>> ");

                int mode = input.nextInt();

                switch (mode) {
                    case 1:
                        int[] coefficients1 = inputPolynomial();
                        System.out.print("\nEnter a value for x: ");
                        double x = input.nextDouble();
                        double result = evaluatePolynomial(coefficients1, x);
                        System.out.println("Result: " + result);
                        break;

                    case 2:
                        int[] coefficients2 = new int[3];
                        System.out.println("\nEnter the coefficients starting from the constant term:");
                        for (int i = 0; i <= 2; i++) {
                            System.out.print("Coefficient of x^" + i + ": ");
                            coefficients2[i] = input.nextInt();
                        }
                        System.out.println("Shape: " + determineShape(coefficients2, generateRandomSamples(100, -1000, 1000)));
                        break;

                    case 3:
                        int[] coefficients3 = inputPolynomial();
                        System.out.println("\nTo what degree do you want to find the derivative: ");
                        int degree = input.nextInt();
                        int[] derivative = findNthDerivative(coefficients3, degree);
                        System.out.print("Derivative (polynomial form): ");
                        for (int i = derivative.length - 1; i >= 0; i--) {
                            if (derivative[i] != 0) {
                                System.out.print((derivative[i] > 0 && i != derivative.length - 1 ? "+" : "") + derivative[i] + (i > 0 ? "x^" + i : ""));
                            }
                        }
                        System.out.println();
                        break;

                    case 4:
                        int[] coefficients4 = new int[3];
                        System.out.println("\nEnter the coefficients starting from the constant term:");
                        for (int i = 0; i <= 2; i++) {
                            System.out.print("Coefficient of x^" + i + ": ");
                            coefficients4[i] = input.nextInt();
                        }
                        double criticalPoint = 0;
                        try {
                            criticalPoint = findCriticalPoint(coefficients4, -100, 100, 0.1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("Critical Point: " + criticalPoint + ", Type: " + findCriticalType(coefficients4, criticalPoint));
                        break;

                    case 5:
                        int[] coefficients5 = inputPolynomial();
                        System.out.println("Enter an initial guess for a root: ");
                        double initialGuesses = input.nextDouble();
                        double root = NewtonsMethod(coefficients5, initialGuesses);
                        System.out.println("Estimated Root: " + root);
                        break;

                    case 6:
                        int[] coefficients6 = inputPolynomial();
                        plotGraph(coefficients6);
                        break;

                    case 7:
                        java.lang.System.exit(0);
                }
            }

        } else if (Continue.equalsIgnoreCase("N")) {
            SelectMode();
        }
    }
}
