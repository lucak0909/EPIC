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

}

