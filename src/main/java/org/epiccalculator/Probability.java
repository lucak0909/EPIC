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

        ProbabilityCalculation calculation = null;

        switch (mode) {
            case 1:
                calculation = new BasicProbability();
                break;
            case 2:
                calculation = new Permutations();
                break;
            case 3:
                calculation = new Combinations();
                break;
            // Add cases for other calculations
        }

        if (calculation != null) {
            calculation.calculate();
        } else {
            System.out.println("Invalid selection.");
        }
    }
}

interface ProbabilityCalculation {
    void calculate();
}

class BasicProbability implements ProbabilityCalculation {
    @Override
    public void calculate() {
        // Implementation for basic probability calculation
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

// Similarly, create classes for other calculations
