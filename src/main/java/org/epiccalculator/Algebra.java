package org.epiccalculator;

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
}

