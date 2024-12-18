package org.epiccalculator;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Statistics {

    public static void main(String[] args) {
        try{
            Statistics.calculateStatistics();
        } catch (Exception e) {
            System.out.println("An unexpected error occured: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    private static Scanner input = new Scanner(System.in);

    public static void calculateStatistics() {
        double[] numbers = null;
        while (numbers == null) {
            numbers = getNumbers(input);
        }
        while (true){
            try{
                System.out.println("Select from the following options: ");
                System.out.println("1 --> Mean");
                System.out.println("2 --> Median");
                System.out.println("3 --> Mode");
                System.out.println("4 --> Standard Deviation");
                System.out.println("5 --> Variance");
                System.out.println("6 --> Range");
                System.out.println("0 --> Exit...");
                System.out.println(">>> ");

                int mode = Integer.parseInt(input.nextLine().trim());

                switch (mode) {
                    case 1:
                        System.out.println("Mean: " + calculateMean(numbers));
                        break;
                    case 2:
                        System.out.println("Median: " + calculateMedian(numbers));
                        break;
                    case 3:
                        calculateMode(numbers);
                        break;
                    case 4:
                        System.out.println("Standard Deviation: " + calculateStandardDeviation(numbers));
                        break;
                    case 5:
                        System.out.println("Variance: " + calculateVariance(numbers));
                        break;
                    case 6:
                        System.out.println("Range: " + calculateRange(numbers));
                        break;
                    case 0:
                        System.out.println("Exiting the program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid option between 0 and 6.");
            }
        }
    }


    private static double[] getNumbers(Scanner input) {
        while(true) {
            System.out.println("Enter numbers sepaarted by spaces:");
            String line = input.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("No input provided. PLease at least one number");
                continue;
            }
            String[] tokens = line.split("\\s+");
            double[] numbers = new double[tokens.length];
            try {
                for (int i = 0; i < tokens.length; i++) {
                    numbers[i] = Double.parseDouble(tokens[i]);
                }
                return numbers;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter only numbers separated by spaces.");
            }
        }
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
        if (numbers.length == 0) {
            System.out.println("Cannot Calculate mode: No numbers provided.");
            return;
        }
        Map<Double, Integer> frequencyMap = new HashMap<>();
        for (double num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        int maxCount = 0;
        for (int count : frequencyMap.values()) {
            if (count > maxCount){
                maxCount = count;
            }
        }

        System.out.println("Mode(s): ");
        boolean foundMode = false;

        for (Map.Entry<Double, Integer>entry : frequencyMap.entrySet()){
            if (entry.getValue() == maxCount) {
                System.out.print(entry.getKey() + " ");
                foundMode = true;
            }
        }
        if (!foundMode) {
            System.out.println("No mode found.");
        } else{
            System.out.println();
        }
    }

    private static double calculateStandardDeviation(double[] numbers){
        return Math.sqrt(calculateVariance(numbers));
    }

    private static double calculateVariance(double[] numbers) {
        if (numbers.length == 0){
            throw new ArithmeticException("Cannot calculate variance: No number provided.");
        }
        double mean = calculateMean(numbers);
        double sumSquaredDiffs = 0;
        for (double num : numbers) {
            sumSquaredDiffs += Math.pow(num - mean, 2);
        }
        return sumSquaredDiffs / (numbers.length - 1);
    }

    private static double calculateRange(double[] numbers){
        double min = Arrays.stream(numbers).min().orElse(0);
        double max = Arrays.stream(numbers).max().orElse(0);
        return max - min;
    }
}