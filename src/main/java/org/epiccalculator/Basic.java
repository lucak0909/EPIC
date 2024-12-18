package org.epiccalculator;

import java.util.ArrayList;
import java.util.List;

public class Basic extends Main{
    public static void main(String[] args) {

        System.out.println("Enter a mathematical expression:");
        String equation = input.nextLine();

        try {
            double result = evaluateExpression(equation);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static double evaluateExpression(String expression) throws Exception {
        expression = expression.replaceAll("\\s+", ""); // Remove whitespace
        if (!isValidExpression(expression)) {
            throw new Exception("Invalid mathematical expression.");
        }
        return evaluate(expression);
    }

    private static boolean isValidExpression(String expression) {
        // Check for invalid characters or consecutive operators
        return expression.matches("[0-9.()+\\-*/^%]+") && !expression.matches(".*[+\\-*/^%]{2,}.*") && balancedBrackets(expression);
    }

    private static boolean balancedBrackets(String expression) {
        int BracketsCount = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == '(') BracketsCount++;
            else if (ch == ')') BracketsCount--;
            if (BracketsCount < 0) return false;
        }
        return BracketsCount == 0;
    }

    private static double evaluate(String expression) throws Exception {
        while (expression.contains("(")) {
            int open = expression.lastIndexOf('(');
            int close = expression.indexOf(')', open);
            if (close == -1) throw new Exception("Mismatched brackets.");
            String inner = expression.substring(open + 1, close);
            double innerResult = evaluateSimple(inner);
            expression = expression.substring(0, open) + innerResult + expression.substring(close + 1);
        }
        return evaluateSimple(expression);
    }

    private static double evaluateSimple(String expression) throws Exception {
        List<String> parts = splitToParts(expression);

        while (parts.size() > 1) {
            int operatorIndex = findHighestPrecedenceOperator(parts);
            if (operatorIndex == -1) {
                throw new Exception("Invalid mathematical expression.");
            }

            double left = Double.parseDouble(parts.get(operatorIndex - 1));
            double right = Double.parseDouble(parts.get(operatorIndex + 1));
            double result = applyOperator(parts.get(operatorIndex).charAt(0), left, right);

            parts.set(operatorIndex - 1, String.valueOf(result));
            parts.remove(operatorIndex); // Remove operator
            parts.remove(operatorIndex); // Remove right operand
        }

        return Double.parseDouble(parts.get(0));
    }

    private static List<String> splitToParts(String expression) {
        List<String> parts = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch) || ch == '.') {
                number.append(ch);
            } else {
                if (!number.isEmpty()) {
                    parts.add(number.toString());
                    number.setLength(0);
                }
                parts.add(String.valueOf(ch));
            }
        }

        if (!number.isEmpty()) {
            parts.add(number.toString());
        }

        return parts;
    }

    private static int findHighestPrecedenceOperator(List<String> parts) {
        int index = -1;
        int precedence = -1;

        for (int i = 0; i < parts.size(); i++) {
            char ch = parts.get(i).charAt(0);
            int currentPrecedence = getPrecedence(ch);

            if (currentPrecedence > precedence) {
                precedence = currentPrecedence;
                index = i;
            }
        }

        return index;
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

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
                return Math.pow(left, right);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
