package org.epiccalculator;
import java.util.Scanner;

public class Statistics {
}
/*
    private static Scanner input = new Scanner(System.in);

    public static void calculateStatistics() {
        double[] numbers = getNumbers(input);
        while (true) {
            System.out.println("Select from the following options: ");
            System.out.println("1 --> Mean");
            System.out.println("2 --> Median");
            System.out.println("3 --> Mode");
            System.out.println("4 --> Variance");
            System.out.println("5 --> Standard Deviation");
            System.out.println(">>> ");

            int mode = input.nextInt();
            input.nextLine();

            switch (mode) {
                case 1:
                    calculateMean(numbers);
                    break;
                case 2:
                    calculateMedian(numbers);
                    break;
                case 3:
                    calculateMode(numbers);
                    break;
                case 4:
                    calculateStandardDeviation(numbers);
                    break;
                case 5:
                    calculateVariance(numbers);
                    break;
                case 6:
                    calculateRange(numbers);
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private static double[] getNumbers(Scanner input) {
        System.out.println("Enter numbers separated by spaces: ");
        String[] tokens = input.nextLine().split(" ");
        double[] numbers = new double[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = Double.parseDouble(tokens[i]);
        }
        return numbers;
    }

    private static double calculateMean(double[] numbers) {
        return 0;
    }

    private static double calculateMedian(double[] numbers){
        return 0;
    }

    private static void calculateMode(double[] numbers) {
    }

    private static double calculateStandardDeviation(double[] numbers){
        return 0;
    }

    private static double calculateVariance(double[] numbers){
        return 0;
    }

    private static double calculateRange(double[] numbers){
        return 0;
    }
}