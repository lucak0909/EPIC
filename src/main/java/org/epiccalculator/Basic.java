/*NEEDED: input validation loops; cleaner CLI */
package org.epiccalculator;

import java.util.Scanner;

public class Basic extends Main{

    public static double calculate() {
        Scanner input = new Scanner(System.in);
        double result = 0;
        char operator = ' ';

        System.out.print("Enter an equation (e.g., 5 + 3 - 4):\n>>>");
        String equation = input.nextLine();

        // Remove any spaces from the input string
        equation = equation.replaceAll("\\s+", "");

        // Split the input string into numbers and operator
        String[] parts = equation.split("[+\\-*/%]");
        double[] nums = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            System.out.println("Part " + (i + 1) + ": " + parts[i]);
            nums[i] = Double.parseDouble(parts[i]);
        }



        // Find the operator
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
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

