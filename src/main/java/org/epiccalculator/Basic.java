package org.epiccalculator;

import java.util.ArrayList;
import java.util.List;

public class Basic extends Main{

    // Main method that prompts the user for input and prints the result
    public static void main(String[] args) {

        System.out.println("Enter a mathematical expression:");
        String equation = input.nextLine(); // Read the user input expression

        try {
            double result = evaluateExpression(equation); // Evaluate the mathematical expression
            System.out.println("Result: " + result);    // Output the result
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage()); // Output any error message thrown back to main
        }
    }

    // Method to evaluate the mathematical expression
    public static double evaluateExpression(String expression) throws Exception {
        expression = expression.replaceAll("\\s+", ""); // Remove whitespace from the expression
        if (!isValidExpression(expression)) { // Check if the expression is valid
            throw new Exception("Invalid mathematical expression.");
        }
        return evaluate(expression); // Evaluate the expression and return the result
    }

    // Method to validate the mathematical expression (checks for invalid characters and operator usage)
    private static boolean isValidExpression(String expression) {
        // Ensure that the expression contains only valid characters and has no consecutive operators
        return expression.matches("[0-9.()+\\-*/^%]+") && !expression.matches(".*[+\\-*/^%]{2,}.*") && balancedBrackets(expression);
    }

    // Method to check if the brackets in the expression are balanced
    private static boolean balancedBrackets(String expression) {
        int BracketsCount = 0;  // Counter for open and close parentheses
        for (char ch : expression.toCharArray()) {
            if (ch == '(') BracketsCount++;  // Increment for open bracket
            else if (ch == ')') BracketsCount--;  // Decrement for close bracket
            if (BracketsCount < 0) return false; // If there are more closing brackets than opening, return false
        }
        return BracketsCount == 0;  // Return true if the brackets are balanced (count is zero)
    }

    // Method to evaluate an expression with parentheses by solving inner expressions first
    private static double evaluate(String expression) throws Exception {
        while (expression.contains("(")) {  // While there are parentheses in the expression
            int open = expression.lastIndexOf('('); // Find the last opening parenthesis
            int close = expression.indexOf(')', open); // Find the corresponding closing parenthesis
            if (close == -1) throw new Exception("Mismatched brackets."); // Error if no matching closing parenthesis
            String inner = expression.substring(open + 1, close);   // Extract the inner expression
            double innerResult = evaluateSimple(inner); // Recursively evaluate the inner expression
            expression = expression.substring(0, open) + innerResult + expression.substring(close + 1); // Replace inner expression with result
        }
        return evaluateSimple(expression);  // Evaluate the remaining expression without parentheses
    }

    // Method to evaluate an expression without parentheses by applying operators
    private static double evaluateSimple(String expression) throws Exception {
        List<String> parts = splitToParts(expression);   // Split the expression into parts (numbers and operators)

        while (parts.size() > 1) {  // While there are still multiple parts to evaluate
            int operatorIndex = findHighestPrecedenceOperator(parts);    // Find the operator with the highest precedence
            if (operatorIndex == -1) {
                throw new Exception("Invalid mathematical expression.");    // Error if no valid operator is found
            }

            // Parse the left and right operands and apply the operator
            double left = Double.parseDouble(parts.get(operatorIndex - 1));
            double right = Double.parseDouble(parts.get(operatorIndex + 1));
            double result = applyOperator(parts.get(operatorIndex).charAt(0), left, right);

            // Update the list by replacing the operands and operator with the result
            parts.set(operatorIndex - 1, String.valueOf(result));
            parts.remove(operatorIndex); // Remove operator
            parts.remove(operatorIndex); // Remove right operand
        }

        return Double.parseDouble(parts.get(0)); // Return the final result (only one part remaining)
    }

    // Method to split the expression into a list of numbers and operators
    private static List<String> splitToParts(String expression) {
        List<String> parts = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') { // If character is part of a number
                number.append(ch);
            } else {
                if (!number.isEmpty()) { // If a number has been built, add it to the parts list
                    parts.add(number.toString());
                    number.setLength(0); // Reset the number builder
                }
                parts.add(String.valueOf(ch)); // Add the operator to the parts list
            }
        }

        if (!number.isEmpty()) { // Add the last number if any
            parts.add(number.toString());
        }

        return parts; // Return the list of parts
    }

    // Method to find the operator with the highest precedence
    private static int findHighestPrecedenceOperator(List<String> parts) {
        int index = -1;
        int precedence = -1;

        for (int i = 0; i < parts.size(); i++) {
            char ch = parts.get(i).charAt(0);   // Get the operator character
            int currentPrecedence = getPrecedence(ch);  // Get the precedence of the operator

            if (currentPrecedence > precedence) {  // If the current operator has higher precedence
                precedence = currentPrecedence;
                index = i;  // Update the index of the highest precedence operator
            }
        }

        return index;   // Return the index of the highest precedence operator
    }

    // Method to return the precedence level of an operator
    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1; // Lowest precedence
            case '*':
            case '/':
            case '%':
                return 2; // Medium precedence
            case '^':
                return 3;  // Highest precedence
            default:
                return -1; // Invalid operator
        }
    }

    // Method to apply the operator to two operands and return the result
    private static double applyOperator(char operator, double left, double right) {
        switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right;
            case '%':
                return left % right;
            case '^':
                return Math.pow(left, right); // Power operator
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator); // Error for unknown operator
        }
    }
}
