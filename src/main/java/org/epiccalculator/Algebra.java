package org.epiccalculator;

import java.util.Scanner;

// Mahdi and Luca are doing this
public class Algebra extends Main {

    private static Scanner input = new Scanner(System.in);

    public static void solveLinearEquation() {
        System.out.println("Solve linear equation ax + b + 0");

        System.out.print("Enter coefficient a: ");
        double a = input.nextDouble();

        System.out.print("Enter coefficient b: ");
        double b = input.nextDouble();

        if (a == 0) {
            if (b == 0) {
                System.out.println("Infinite solutions (any value of x satisfies the equation).");
            } else {
                System.out.println("No solution (contradictory equation).");
            }
        } else {
            double x = -b / a;
            System.out.printf("Solutions: x = %.2f\n", x);
        }
    }
    public static void main(String[] args){
        boolean continueCalculating = true;
        while (continueCalculating) {
            System.out.println("Select an option: ");
            System.out.println("1 --> Solve Linear Equation");
            System.out.println("2 --> Solve Quadratic Equation");
            System.out.println("3 --> Exit");
            System.out.print(">>> ");

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    solveLinearEquation();
                    break;
                case 2:
                    //solveQuadraticEquation();
                    break;
                case 3:
                    continueCalculating = false;
                    System.out.println("Exiting the Algebra Calculator");
                    break;
                default:
                    throw new IllegalArgumentException("Invalid option! Please choose a valid option.");
            }
        }
    }
}
