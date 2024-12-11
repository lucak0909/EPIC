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
        int mode;

        do {
            Options(); // Display options
            while (!input.hasNextInt()) { // Ensure input is an integer
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                Options();
            }
            mode = input.nextInt();
            input.nextLine(); // Clear buffer after number input

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
        } while( mode <1 || mode > 6);

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

        System.out.print("Enter Amount in USD ($): ");
        double usd = input.nextDouble();

        System.out.print("Enter Conversion Rate to EUR (€): ");
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
        System.out.printf("Net Income after Tax: %.2f\n", income - tax);

    }

    private static void BreakEvenAnalysis() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Fixed Costs: ");
        double fixedCosts = input.nextDouble();

        System.out.print("Enter Variable Costs per Unit: ");
        double variableCosts = input.nextDouble();

        System.out.print("Enter Price per Unit: ");
        double pricePerUnit = input.nextDouble();

        if (pricePerUnit <=variableCosts){
            System.out.println("No Break-Even Point; Variable Costs Exceed or Equal Price");
            return;
        }

        double breakEvenPoint = fixedCosts / (pricePerUnit- variableCosts);
        System.out.printf("Break-Even Point (Units) : %.2f%n", breakEvenPoint);
    }
}