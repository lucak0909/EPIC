package org.epiccalculator;
import java.util.Scanner;
import java.util.Arrays;

public class Statistics extends Main{

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
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }

    private static double calculateMedian(double[] numbers){
        Arrays.sort(numbers);
        int n = numbers.length;
        if (n % 2 == 0) {
            return (numbers[n / 2 - 1] + numbers[n / 2]) / 2.0;
        } else {
            return numbers[n / 2];
        }
    }

    private static void calculateMode(double[] numbers) {
    }

    private static double calculateStandardDeviation(double[] numbers){
        return Math.sqrt(calculateVariance(numbers));
    }

    private static double calculateVariance(double[] numbers) {
        double mean = calculateMean(numbers);
        double sumSquaredDiffs = 0;
        for (double num : numbers) {
            sumSquaredDiffs += Math.pow(num - mean, 2);
        }
        return sumSquaredDiffs / numbers.length;
    }

    private static double calculateRange(double[] numbers){
        double min = Arrays.stream(numbers).min().orElse(0);
        double max = Arrays.stream(numbers).max().orElse(0);
        return max - min;
    }
}