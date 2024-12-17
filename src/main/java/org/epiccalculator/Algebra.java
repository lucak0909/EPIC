package org.epiccalculator;

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
        }
        else if (determineShape(coefficients, generateRandomSamples(100, -1000, 1000)).equals("N")) {
            criticalPoint = Double.NEGATIVE_INFINITY;
        }
        else {throw new Exception("Could not determine shape of function.");}

        for (double x = start; x <= end; x += step) {
            double value = evaluatePolynomial(coefficients, x);
            if (value < criticalPoint) {
                criticalPoint = value;
            }
        }
        if (criticalPoint < 0.25 && criticalPoint > -0.25) {criticalPoint = 0;}

        return criticalPoint;
    }

    public static String findCriticalType(int[] coefficients, double x) {
        String type;
        coefficients = findNthDerivative(coefficients, 2);

        if (evaluatePolynomial(coefficients, x) > 0) {type = "Minimum";}
        else if (evaluatePolynomial(coefficients, x) < 0) {type = "Maximum";}
        else {type = "Saddle Point";}

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
        double tolerance = 1e-7;
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

        throw new ArithmeticException("Newton's Method did not converge within the maximum number of iterations.");
    }

    public static int[] inputPolynomial() {
        System.out.print("Enter the degree of the polynomial: ");
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

    public static void main(String[] args) {

        System.out.println("The algebra calculator can be used to:\n- Evaluate any polynomial at a given x" +
                "\n - Find the shape of a Quadratic polynomial curve\n - Find the Critical Point of a polynomial within the domain of +- 1000" +
                "\n - Find the first (or nth) derivative of a polynomial\n - Estimate the root(s) of a polynomial");
        System.out.println("Would you like to continue with the algebraic calculator? (Y/N)");
        System.out.println(">>> ");

        String Continue = input.next();

        while (!Continue.equalsIgnoreCase("Y") && !Continue.equalsIgnoreCase("N")) {
        System.out.println("This is not a valid response. Please enter 'Y' or 'N'.");
        System.out.println(">>> ");
        Continue = input.next();
        }
    }
        if (Continue.equalsIgnoreCase("Y")) {
            System.out.println("Choose an option:");
            System.out.println("1. Evaluate a Polynomial");
            System.out.println("2. Find the Shape of a Quadratic Polynomial Curve");
            System.out.println("3. Find the First (or nth) Derivative of a Polynomial");
            System.out.println("4. Find Maxima/Minima of a Quadratic");
            System.out.println("4. Estimate the Root(s) of a Polynomial");
            System.out.println("5. All of the above");
            System.out.println("6. Exit");
            System.out.println(">>> ");

            int mode = input.nextInt();

            switch (mode) {
                case 1:
                    int[] coefficients1 = inputPolynomial();
                    System.out.print("Enter a value for x: ");
                    double x = input.nextDouble();
                    double result = evaluatePolynomial(coefficients1, x);
                    System.out.println("Result: " + x);
                    break;

                case 2:
                    int[] coefficients2 = new int[3];
                    System.out.println("Enter the coefficients starting from the constant term:");
                    for (int i = 0; i <= 2; i++) {
                        System.out.print("Coefficient of x^" + i + ": ");
                        coefficients2[i] = input.nextInt();
                    }
                    System.out.println("Shape: " + determineShape(coefficients2, generateRandomSamples(100, -1000, 1000)));
                    break;

                case 3:
                    int[] coefficients3 = inputPolynomial();
                    System.out.println("To what degree do you want to find the derivative: ");
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

            }

        }
        else if (Continue.equalsIgnoreCase("N")) {
            SelectMode();
        }



    }
}

/*// Example coefficients for a polynomial: 2 + 3x + x^2
        int[] coefficients = {2, 3, 1};

        // Differentiate the polynomial
        int[] derivative = differentiate(coefficients);
        System.out.print("Derivative: ");
        for (int coef : derivative) {
            System.out.print(coef + " ");
        }
        System.out.println();

        // Find the nth derivative (2nd derivative in this example)
        int degree = 2;
        int[] nthDerivative = findNthDerivative(coefficients, degree);
        System.out.print(degree + "-th Derivative: ");
        for (int coef : nthDerivative) {
            System.out.print(coef + " ");
        }
        System.out.println();

        // Evaluate the polynomial at x = 2
        double x = 2;
        double value = evaluatePolynomial(coefficients, x);
        System.out.println("Polynomial evaluated at x = " + x + ": " + value);

        // Find a critical point
        try {
            double criticalPoint = findCriticalPoint(coefficients, -10, 10, 0.1);
            System.out.println("Critical Point: " + criticalPoint);

            // Determine the type of critical point
            String criticalType = findCriticalType(coefficients, criticalPoint);
            System.out.println("Critical Point Type: " + criticalType);
        } catch (Exception e) {
            System.err.println("Error finding critical point: " + e.getMessage());
        }

        // Generate random samples and determine the shape of the curve
        double[] samples = generateRandomSamples(10, -10, 10);
        String shape = determineShape(coefficients, samples);
        System.out.println("Shape of the curve: " + shape);

        // Newton's Method placeholder
        double initialGuess = 1;
        double root = NewtonsMethod(coefficients, initialGuess);
        System.out.println("Root found using Newton's Method: " + root); */