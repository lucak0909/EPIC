package org.epiccalculator;
import java.util.Scanner;

public class Finance extends Main {

    // User Financial Options Prompt
    private static void Options() {
        System.out.println("Select from the following");
        System.out.println("------------------------------");
        System.out.println("1 --> Simple Interest");
        System.out.println("2 --> Compound Interest");
        System.out.println("3 --> Loan Calculation");
        System.out.println("4 --> Currency Converter");
        System.out.println("5 --> Tax Calculation");
        System.out.println("6 --> Break Even Analysis");
        System.out.print(">>>");
    }

    // Find user mode selection
    public static void CalculateFinance() {
        Scanner input = new Scanner(System.in);
        //Display options
        Options();

        // Store user choice in mode var => Switch case
        byte mode;
        mode = input.nextByte();
        //Error handling | User input must be a number within option range
        while (!Validate.isNumeric(String.valueOf(mode)) || mode > 6 || mode < 1) {
            System.out.println("Invalid -> Try again\n");
            Options();
            mode = input.nextByte();

            // Switch case to perform coherent method
            switch (mode) {
                case 1:
                    SimpleInterest();
                    break;
                case 2:
                    CompundInterest();
                    break;
                case 3:
                    LoanCalculation();
                    break;
                case 4:
                    CurrencyConverter();
                    break;
                case 5:
                    TaxCalculation();
                    break;
                case 6:
                    BreakEvenAnalysis();
                    break;

            }
        }

    }

    private static void SimpleInterest() {

    }

    private static void CompundInterest() {

    }

    private static void LoanCalculation() {

    }

    private static void CurrencyConverter() {

    }

    private static void TaxCalculation() {

    }

    private static void BreakEvenAnalysis() {

    }
}