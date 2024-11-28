package org.epiccalculator;

import java.util.Scanner;

import org.epiccalculator.Algebra;


public class Main {
    static Scanner input = new Scanner(System.in);

    private static void SelectMode() {
        System.out.print("\nSelect Mode:\t1. Basic; 2. Algebra; 3. Finance; 4. Trigonometry; 5. Geometry; " +
                "6. Statistics; 7. Probability; 8. Number System Converter\n>>> ");
        int mode = input.nextInt();
        while (mode < 1 || mode > 8) {
            System.out.println("\nInvalid selection. Please try again.");
            System.out.print("\nSelect Mode:\t1. Basic; 2. Algebra; 3. Finance; 4. Trigonometry; 5. Geometry; " +
                    "6. Statistics; 7. Probability; 8. Number System Converter\n>>> ");
            mode = input.nextInt();
        }

        switch (mode) {
            case 1:
                System.out.print("Enter an equation (e.g., 5 + 3):\n>>>");
                String equation = input.nextLine();
                while (! Validate.isValidEquation(equation)) {
                    System.out.println("Invalid equation format. Please try again.");
                    System.out.print("Enter an equation (e.g., 5 + 3):\n>>>");
                    equation = input.nextLine();
                }
                calculate(equation);
                break;
            case 2:
                Algebra.SelectMode();
                break;
            // Add more cases for other modes
            case 8:
                System.out.println(NumberSystems.Calculate());
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

    public static double calculate(String input) {
        double result = 0;
        char operator = ' ';

        // Remove any spaces from the input string
        input = input.replaceAll("\\s+", "");

        // Split the input string into numbers and operator
        String[] parts = input.split("[+\\-*/%]");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input format.");
        }
        double num1 = Double.parseDouble(parts[0]);
        double num2 = Double.parseDouble(parts[1]);

        // Find the operator
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%') {
                operator = c;
                break;
            }
        }

        // Perform the calculation based on the operator
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                result = num1 / num2;
                break;
            case '%':
                result = num1 % num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator.");
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("\n\n\t\tMulti-Functional Calculator\n\t-----------------------------------\n");
        while (true) {
            SelectMode();
        }
    }
}
