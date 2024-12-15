package org.epiccalculator;
import java.util.*;


public class Statistics {

    private static Scanner input = new Scanner(System.in);

    public static void calculateStatistics() {
        double[] numbers = getNumbers();
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

    private static double[] getNumbers(String input) {
        return getNumbers(input);
    }

    private static double calculateMean(double[] numbers) {
        return calculateMean(numbers);
    }

    private static double calculateMedian(double[] numbers){
        return calculateMedian(numbers);
    }

    private static void calculateMode(double[] numbers) {
        return;
    }

    private static double calculateStandardDeviation(double[] numbers){
        return calculateStandardDeviation(numbers);
    }

    private static double calculateVariance(double[] numbers){
        return calculateVariance(numbers);
    }

    private static double calculateRange(double[] numbers){
        return calculateRange(numbers);
    }
}
