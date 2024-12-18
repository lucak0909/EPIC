package org.epiccalculator;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Finance extends Main {

    public static void main(String[] args) {
        Finance.calculateFinance();
    }

    private static Scanner input = new Scanner(System.in);

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
    public static void calculateFinance() {

        try {
            Options();
            byte mode = input.nextByte();
            input.nextLine();

            // Display options
            if (mode < 1 || mode > 6) {
                throw new IllegalArgumentException("Invalid option. Please enter enter a valid option from 1-6.");
            }

            FinanceCalculation calculation = switch (mode) {
                case 1 -> new SimpleInterest();
                case 2 -> new CompoundInterest();
                case 3 -> new LoanCalculation();
                case 4 -> new CurrencyConverter();
                case 5 -> new TaxCalculation();
                case 6 -> new BreakEvenAnalysis();
                default -> throw new IllegalArgumentException("Invalid option. Please enter a valid option from 1-6.");
            };

            calculation.calculate();
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a number.");
            input.nextLine();
        }
    }
}
abstract class FinanceCalculation {
    protected Scanner input = new Scanner(System.in);

    public abstract void calculate();

    protected double getValidPositiveDouble(String prompt) {
        while (true) {
            try {
                System.out.print(input);
                double value = input.nextDouble();
                if (value > 0) {
                    return value;
                } else {
                    throw new IllegalArgumentException("Value must be greater than zero (0).");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error:Invalid input. Please enter a positive number.");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
class SimpleInterest extends FinanceCalculation {
    @Override
    public void calculate() {
        System.out.println("Enter Principal (P): ");
        double principal = input.nextDouble();
        System.out.println("Enter Annual Interest Rate (APR) (%):");
        double rate = input.nextDouble();
        System.out.println("Enter Time in Years (t):");
        double time = input.nextDouble();

        double interest = (principal * rate * time) / 100;
        System.out.printf("Simple Interest: %.2f\n", interest);
        System.out.printf("Total Amount: %.2f\n", (interest+principal));
    }
}

class CompoundInterest extends FinanceCalculation {
    @Override
    public void calculate() {
        System.out.println("Enter Principal (P): ");
        double principal = input.nextDouble();
        System.out.println("Enter Annual Interest Rate (APR) (%):");
        double rate = input.nextDouble();
        System.out.println("Enter Number of Times Interest is Compounded per year (n): ");
        int n = input.nextInt();
        System.out.println("Enter Time in Years (t):");
        double time = input.nextDouble();

        double amount = principal * Math.pow(1 + (rate / 100) / n, n * time);
        System.out.printf("Compound Interest: %.2f\n", amount - principal);
        System.out.printf("Total Amount: %.2f\n", amount);
    }
}

class LoanCalculation extends FinanceCalculation {
    @Override
    public void calculate() {
        System.out.println("Enter Loan Amount:");
        double loanAmount = input.nextDouble();
        System.out.println("Enter Annual Interest Rate (%): ");
        double annualRate = input.nextDouble();
        System.out.println("Enter Loan Term in Months:");
        int months = input.nextInt();

        annualRate = annualRate / 100 / 12;

        double payment = (loanAmount * annualRate * Math.pow(1 + annualRate, months)) /
                (Math.pow(1 + annualRate, months) - 1);

        System.out.printf("Monthly Loan Payment: %.2f\n", payment);
    }
}

class CurrencyConverter extends FinanceCalculation {
    @Override
    public void calculate() {
        System.out.println("Enter amount in current currency:");
        double usd = input.nextDouble();
        System.out.println("Enter current exchange rate to EUR:");
        double conversionRate = input.nextDouble();

        double eur = usd * conversionRate;
        System.out.printf("Equivalent Amount in EUR: %.2f\n", eur);
    }
}

class TaxCalculation extends FinanceCalculation {
    @Override
    public void calculate() {
        System.out.println("Enter Total Income:");
        double income = input.nextDouble();
        System.out.println("Enter Tax Rate (%):");
        double taxRate = input.nextDouble();

        double tax = (income * taxRate) / 100;
        System.out.printf("Tax Amount: %.2f\n", tax);
        System.out.printf("Net Income after Tax: %.2f\n", income - tax);
    }
}

class BreakEvenAnalysis extends FinanceCalculation {
    @Override
    public void calculate() {
        System.out.println("Enter Fixed Costs: ");
        double fixedCosts = input.nextDouble();
        System.out.println("Enter Variable Costs per Unit: ");
        double variableCosts = input.nextDouble();
        System.out.println("Enter Price per Unit: ");
        double pricePerUnit = input.nextDouble();

        if (pricePerUnit <= variableCosts) {
            throw new IllegalArgumentException("Error: Price per Unit must be greater than variable costs.");
        }

        double breakEvenPoint = fixedCosts / (pricePerUnit - variableCosts);
        System.out.printf("Break-Even Point (Units): %.2f\n", breakEvenPoint);
    }
}