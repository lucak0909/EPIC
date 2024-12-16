/*NEEDED: input validation loops; cleaner CLI */
package org.epiccalculator;

public class Basic extends Main{

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
}

