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
                    CompoundInterest();
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
                default:
                    System.out.println("Invalid Option!");

            }
        }

    }

    private static void SimpleInterest() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Principal (P): ");
        double principal = input.nextDouble();

        System.out.print("Enter Annual Interest Rate (APR) (%) (R): ");
        double rate = input.nextDouble();

        System.out.print("Enter Time in years (T): ");
        double time = input.nextDouble();

        double interest = (principal * rate * time) / 100;
        System.out.printf("Simple Interest: %.2f\n", interest);
    }

    private static void CompoundInterest() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Principal (P): ");
        double principal = input.nextDouble();

        System.out.print("Enter Annual Interest Rate (APR) (%) (R): ");
        double rate = input.nextDouble();

        System.out.print("Enter Number of Times Interest is Compounded per years (n): ");
        int n = input.nextInt();

        System.out.print("Enter Time in years (T): ");
        double time = input.nextDouble();

        double amount = principal * Math.pow(1 + (rate / 100) / n, n * time);
        System.out.printf("Compound Interest: %.2f\n", amount - principal);
        System.out.printf("Total Amount: %.2f\n", amount);
    }

    private static void LoanCalculation() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Loan Amount: ");
        double loanAmount = input.nextDouble();

        System.out.print("Enter Annual Interest Rate (%) (R): ");
        double annualRate = input.nextDouble() / 100 / 12;

        System.out.print("Enter Loan Term in Months: ");
        int months = input.nextInt();

        double payment = (loanAmount * annualRate * Math.pow(1 + annualRate, months)) /
                (Math.pow(1 + annualRate, months) - 1);

        System.out.printf("Monthly Loan Payment: %.2f\n", payment);
    }

    private static void CurrencyConverter() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter amount in USD ($): ");
        double usd = input.nextDouble();

        System.out.print("Enter Conversion rate to EUR (): ");
        double conversionRate = input.nextDouble();

        double eur = usd * conversionRate;
        System.out.printf("Equivalent Amount in EUR: %.2f\n", eur);

    }

    private static void TaxCalculation() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Total Income: ");
        double income = input.nextDouble();

        System.out.print("Enter Tax Rate (%) (R): ");
        double taxRate = input.nextDouble();

        double tax = (income * taxRate) / 100;
        System.out.printf("Tax Amount: %.2f\n", tax);
        System.out.printf("Net Income After Tax: %.2f\n", income - tax);

    }

    private static void BreakEvenAnalysis() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Fixed Costs: ");
        double fixedCosts = input.nextDouble();

        System.out.print("Enter variable cosst per unit: ");
        double variableCosts = input.nextDouble();

        System.out.print("Enter price per unit: ");
        double pricePerUnit = input.nextDouble();

        if (pricePerUnit <=variableCosts){
            System.out.println("No break-even point; variable costs exceed or equal price");
            return;
        }

        double breakEvenPoint = fixedCosts / (pricePerUnit- variableCosts);
        System.out.printf("Break-Even Point (units) : %.2f%n", breakEvenPoint);
    }
}