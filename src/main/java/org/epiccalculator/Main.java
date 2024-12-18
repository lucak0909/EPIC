package org.epiccalculator;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    protected static void SelectMode() {
        System.out.print("\nSelect Mode:\t1. Basic; 2. Algebra; 3. Finance; 4. Trigonometry; 5. Geometry; " +
                "6. Statistics; 7. Probability; 8. Number System Converter; 9. Matrix Calculation\n>>> ");
        int mode = input.nextInt();
        input.nextLine(); // consume leftover newline for debugging, taken from chatGPT
        while (mode < 1 || mode > 9) {
            System.out.println("\nInvalid selection. Please try again.");
            System.out.print("\nSelect Mode:\t1. Basic; 2. Algebra; 3. Finance; 4. Trigonometry; 5. Geometry; " +
                    "6. Statistics; 7. Probability; 8. Number System Converter; 9. Matrix Calculator\n>>> ");
            mode = input.nextInt();
            input.nextLine(); // consume leftover newline for debugging, taken from chatGPT
        }

        switch (mode) {
            case 1:
                Basic.main(null);
                break;
            case 2:
                Algebra.main(null);
                break;
            case 3:
                Finance.calculateFinance();
                break;
            case 4:
                Trigonometry.main(null);
                break;
            case 5:
                Geometry.main(null);
                break;
            case 6:
                Statistics.calculateStatistics();
                break;
            case 7:
                Probability.CalculateProbability();
                break;
            case 8:
                NumberSystems.CalculateNumberSystem();
                break;
            case 9:
                Matrices.CalculateMatrix();
                break;
            default:
                System.out.println("\nInvalid selection. Please try again.");

        }
    }

    public static class Validate {

        // checks if number is numeric and returns true if so
        public static boolean isNumeric(String str) {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

    }

    public static void main(String[] args) {
        Ascii.Art();
        while (true) {
            SelectMode();
        }
    }
}
