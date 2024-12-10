package org.epiccalculator;

import java.util.Scanner;

public class Probability extends Main {

    private static void options() {
        System.out.println("Select from the following");
        System.out.println("--------------------------");
        System.out.println("1 --> Basic Probability");
        System.out.println("2 --> Permutations");
        System.out.println("3 --> Combinations");
        System.out.println("4 --> Binomial Distribution");
        System.out.println("5 --> Normal Distribution");
        System.out.println("6 --> Hypothesis Testing");
        System.out.println("7 --> Confidence Intervals");
        System.out.println("8 --> Regression Analysis");
        System.out.println("9 --> Monte Carlo Simulation");
        System.out.print(">>> ");
    }

    public static void CalculateProbability() {
        // Display options
        options();

        // Determine Variables to be used
        byte mode;

        Scanner input = new Scanner(System.in);
        mode = input.nextByte();

        // Error handling to make sure user enters byte within range
        while (!Validate.isNumeric(String.valueOf(mode)) || mode > 9 || mode < 1) {
            System.out.println("Invalid -> Try again\n");
            options();
            mode = input.nextByte();
        }

        ProbabilityCalculation calculation = switch (mode) {
            case 1 -> new BasicProbability();
            case 2 -> new Permutations();
            case 3 -> new Combinations();
            case 4 -> new BinomialDistribution();
            case 5 -> new NormalDistribution();
            case 6 -> new HypothesisTesting();
            case 7 -> new ConfidenceIntervals();
            case 8 -> new RegressionAnalysis();
            case 9 -> new MonteCarloSimulation();
            default -> null;
        };

        calculation.calculate();
    }
}

interface ProbabilityCalculation {
    Scanner input = new Scanner(System.in);
    void calculate();
}


class BasicProbability implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for basic probability calculation
        int favorableOutcomes = 0; // set to null to initialise while loop
        int totalOutcomes = 0;

        while (!Main.Validate.isNumeric(String.valueOf(favorableOutcomes)) || favorableOutcomes <= 0) {
            System.out.println("Enter the number of favorable outcomes:");
            favorableOutcomes = input.nextInt();
        }

        while (!Main.Validate.isNumeric(String.valueOf(totalOutcomes)) || totalOutcomes <= 0) {
            System.out.println("Enter the number of total outcomes:");
            totalOutcomes = input.nextInt();
        }


        double probability = (double) favorableOutcomes / (double) totalOutcomes;
        System.out.printf("The probability is: %.2f%%\n", probability * 100);
    }

}

class Permutations implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for permutations calculation
    }
}

class Combinations implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for combinations calculation
    }
}

class BinomialDistribution implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for combinations calculation
    }
}

class NormalDistribution implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for normal distribution calculation
    }
}

class HypothesisTesting implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for hypothesis testing calculation
    }
}

class ConfidenceIntervals implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for regression analysis calculation
    }
}


class RegressionAnalysis implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for regression analysis calculation
    }
}

class MonteCarloSimulation implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for Monte Carlo simulation calculation
    }
}

