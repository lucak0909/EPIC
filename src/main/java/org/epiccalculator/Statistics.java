package org.epiccalculator;
import java.util.*;

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
            System.out.println("6 --> Range");
            System.out.println("7 --> Change Numbers");
            System.out.println(">>> ");

            int mode = input.nextInt();
            input.nextLine();

            switch (mode) {
                case 1:
                    calculateMean(numbers);
                    System.out.printf("Mean: %.2f%n",calculateMean(numbers));
                    break;
                case 2:
                    calculateMedian(numbers);
                    System.out.printf("Median: %.2f%n",calculateMedian(numbers));
                    break;
                case 3:
                    calculateMode(numbers);
                    System.out.printf("Mode: %s%n" , calculateMode(numbers));
                    break;
                case 4:
                    calculateVariance(numbers);
                    System.out.printf("Variance: %f%n",calculateVariance(numbers));
                    break;
                case 5:
                    calculateStandardDeviation(numbers);
                    System.out.printf("Standard Deviation: %f%n",calculateStandardDeviation(numbers));
                    break;
                case 6:
                    calculateRange(numbers);
                    System.out.printf("Range: %.2f%n",calculateRange(numbers));
                    break;
                case 7:
                    numbers = getNumbers(input);
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

    private static ArrayList<Object> calculateMode(double[] numbers) {
        double max = Arrays.stream(numbers).max().orElse(0);
        List<Double> numberList = new ArrayList<>();
        for (double number : numbers) {
            numberList.add(number);
        }
        ArrayList<Object> mode = new ArrayList<>();
        int highestFrequency = 1;
        for (int i = 0; i <= max; i++) {
            int frequency = Collections.frequency(numberList, (double) i);
            System.out.println(frequency);
               if (frequency > highestFrequency) {
                highestFrequency = frequency;
                mode.clear();
                mode.add(i);
            }
               else if (frequency > 1 && frequency == highestFrequency) {
                   mode.add(i);
               }

        }

        return mode;
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
        return sumSquaredDiffs / (numbers.length - 1);
    }

    private static double calculateRange(double[] numbers){
        double min = Arrays.stream(numbers).min().orElse(0);
        double max = Arrays.stream(numbers).max().orElse(0);
        return max - min;
    }

}