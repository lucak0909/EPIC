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
        if (determineShape(coefficients, generateRandomSamples(100, -1000, 1000)) == "U") {
            criticalPoint = Double.POSITIVE_INFINITY;
        }
        else if (determineShape(coefficients, generateRandomSamples(100, -1000, 1000)) == "N") {
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

        return x;
    }

    public static void main(String[] args) {
        // Example coefficients for a polynomial: 2 + 3x + x^2
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
        System.out.println("Root found using Newton's Method: " + root);
    }
}

