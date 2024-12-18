package org.epiccalculator;

import java.util.Scanner;

public class Probability extends Main {

    private static void options() {
        System.out.println("Select from the following");
        System.out.println("--------------------------");
        System.out.println("1 --> Basic Probability");
        System.out.println("2 --> Permutations");
        System.out.println("3 --> Combinations");
        System.out.println("4 --> Normal Distribution");
        System.out.print(">>> ");
    }

    public static void CalculateProbability() {
        // Display options
        options();

        // Determine Variables to be used
        int mode;

        Scanner input = new Scanner(System.in);
        mode = input.nextInt();

        // Error handling to make sure user enters Int within range
        while (!Validate.isNumeric(String.valueOf(mode)) || mode > 4 || mode < 1) {
            System.out.println("Invalid -> Try again\n");
            options();
            mode = input.nextInt();
        }

        ProbabilityCalculation calculation = switch (mode) {
            case 1 -> new BasicProbability();
            case 2 -> new Permutations();
            case 3 -> new Combinations();
            case 4 -> new NormalDistribution();
            default -> null;
        };

        if (calculation != null) {
            calculation.calculate();
        } else {
            System.out.println("Invalid selection.");
        }
    }
}

interface ProbabilityCalculation {
    Scanner input = new Scanner(System.in);
    void calculate();

    // method for combinations + permutations
    static int factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}

class BasicProbability implements ProbabilityCalculation {
    @Override
    public void calculate() {
        int favorableOutcomes = 0; // set to 0 to initialize while loop
        int totalOutcomes = 0;

        while (!Main.Validate.isNumeric(String.valueOf(favorableOutcomes)) || favorableOutcomes <= 0) {
            System.out.println("Enter the number of favorable outcomes:");
            favorableOutcomes = input.nextInt();
        }

        while (!Main.Validate.isNumeric(String.valueOf(totalOutcomes)) || totalOutcomes <= 0) {
            System.out.println("Enter the number of total outcomes:");
            totalOutcomes = input.nextInt();
        }

        int probability =  favorableOutcomes / totalOutcomes;
        System.out.printf("The probability is: %.2f%%\n", probability * 100);
    }
}

class Permutations implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // [ P(n, r) = \frac{n!}{(n-r)!} ] -> Formula for permutations (Repetition Allowed) :)
        /* Permutation Example: How many 3-letter words can be formed using the letters from the word "FABLE"?
        [ 5P3 = \frac{5!}{(5-3)!} = \frac{5!}{2!} = 5 \times 4 \times 3 = 60 ] Therefore, there are 60 3-letter words that can be formed
         */

        int n = 0;
        int r = 0;

        while (!Main.Validate.isNumeric(String.valueOf(n)) || n <= 0) {
            System.out.println("Number of objects to arrange: ");
            n = input.nextInt();
        }

        while (!Main.Validate.isNumeric(String.valueOf(r)) || r <= 0) {
            System.out.println("Total number of objects: ");
            r = input.nextInt();
        }

        int permutations = calculatePermutations(n, r);
        System.out.println("Number of possible permutations: " + permutations);
    }

    private static int calculatePermutations(int n, int r) {
        return ProbabilityCalculation.factorial(n) / ProbabilityCalculation.factorial(n - r);
    }
}

class Combinations implements ProbabilityCalculation {
    @Override
    public void calculate() {
        //[ C(n, r) = \frac{n!}{r!(n-r)!} ] -> Formula for combinations

        /* Combination Example: How many ways can a committee of 5 members be formed from a group of 10 people?
        [ 10C5 = \frac{10!}{5!(10-5)!} = \frac{10!}{5!5!} = 252 ] Therefore, there are 252 ways to form the committee
         */

        int n = 0;
        int r = 0;

        while (!Main.Validate.isNumeric(String.valueOf(n)) || n <= 0) {
            System.out.println("Number of objects: ");
            n = input.nextInt();
        }

        while (!Main.Validate.isNumeric(String.valueOf(r)) || r <= 0) {
            System.out.println("Objects to choose from: ");
            r = input.nextInt();
        }
        long combinations = calculateCombinations(n, r);
        System.out.println("Number of possible combinations: " + combinations);
    }

    private static long calculateCombinations(int n, int r) {
        return ProbabilityCalculation.factorial(n) / (ProbabilityCalculation.factorial(r) * ProbabilityCalculation.factorial(n - r));
    }
}

class NormalDistribution implements ProbabilityCalculation {
    @Override
    public void calculate() {
        double mean = 0.0;
        double dev = 0.0;
        double x = 0.0;

        while (!Main.Validate.isNumeric(String.valueOf(mean)) || mean <= 0) {
            System.out.println("Mean: ");
            mean = input.nextDouble();
        }

        while (!Main.Validate.isNumeric(String.valueOf(dev)) || dev <= 0) {
            System.out.println("Standard Deviation: ");
            dev = input.nextDouble();
        }

        while (!Main.Validate.isNumeric(String.valueOf(x)) || x <= 0) {
            System.out.println("Value: ");
            x = input.nextDouble();
        }

        double probabilityDensity = calculateNormalDistribution(mean, dev, x);
        System.out.printf("The probability density at x = %.2f is: %.5f\n", x, probabilityDensity);
    }

    private double calculateNormalDistribution(double mean, double dev, double x) {
        // [ \text{exponent} = \exp\left(-\frac{(x - \text{mean})^2}{2 \cdot \text{stdDev}^2}\right) ]
        double exponent = Math.exp(-Math.pow(x - mean, 2) / (2 * Math.pow(dev, 2)));
        return (1 / (dev * Math.sqrt(2 * Math.PI))) * exponent;
    }
}