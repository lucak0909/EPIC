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
                System.out.print("Enter an equation (e.g., 5 + 3 - 4):\n>>>");
                String equation = input.nextLine();
                while (!Validate.isValidEquation(equation)) {
                    System.out.println("Invalid equation format. Please try again.");
                    System.out.print("Enter an equation (e.g., 5 + 3 - 4):\n>>>");
                    equation = input.nextLine();
                }
                System.out.println(Basic.calculate(equation));
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

        private static boolean isValidEquation(String equation) {
            // Remove spaces and check if it matches the pattern of a simple equation
            equation = equation.replaceAll("\\s+", "");
            return equation.matches("\\d+[+\\-*/%]\\d+");
        }


        public static boolean isNumeric(String str) {
            try {
                Double.parseDouble(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        public static boolean isAlpha(String str) {
            return str.matches("[a-zA-Z]+");
        }

        public static boolean isAlphanumeric(String str) {
            return str.matches("[a-zA-Z0-9]+");
        }
    }

    public static void main(String[] args) {
        Ascii.Art();
        while (true) {
            SelectMode();
        }
    }
}
