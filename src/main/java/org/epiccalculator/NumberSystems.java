package org.epiccalculator;

import java.util.Scanner;

public class NumberSystems extends Main {

    // User NumberSystem Options Prompt
    private static void Options() {
        System.out.println("Select your number System");
        System.out.println("----------------------------");
        System.out.println("1 --> Decimal (Base 10)");
        System.out.println("2 --> Binary (Base 2)");
        System.out.println("3 --> Hexadecimal (Base 16)");
        System.out.println("4 --> Octal (Base 8)");
        System.out.print(">>>");
    }

    // Method to listen for user mode/input and calculate answer
    public static void CalculateNumberSystem() {
        // Determine Variables to be used
        byte mode;
        int number;
        byte newMode;
        String result = "";

        Scanner input = new Scanner(System.in);
        mode = input.nextByte();
        //Display Options
        Options();

        // Error handling to make sure user enters byte within range
        while (!Validate.isNumeric(String.valueOf(mode)) || mode > 5 || mode < 1) {
            System.out.println("Invalid -> Try again\n");
            Options();
            mode = input.nextByte();
        }

        System.out.println("Enter your number:");
        number = input.nextInt();

        while (!Validate.isNumeric(String.valueOf(number))) {
            System.out.println("Invalid -> Try again\n");
            System.out.println("Enter your number:");
            number = input.nextInt();
        }

        Options();
        newMode = input.nextByte();

        while (!Validate.isNumeric(String.valueOf(newMode)) || newMode > 5 || newMode < 1) {
            System.out.println("Invalid -> Try again\n");
            Options();
            newMode = input.nextByte();
        }

        switch (mode) {
            case 1: // Decimal
                switch (newMode) {
                    case 1:
                        result = String.valueOf(number);
                        break;
                    case 2:
                        result = Integer.toBinaryString((int) number);
                        break;
                    case 3:
                        result = Integer.toHexString((int) number);
                        break;
                    case 4:
                        result = Integer.toOctalString((int) number);
                        break;
                }
                break;
            case 2: // Binary
                int binary = Integer.parseInt(String.valueOf((int) number), 2);
                switch (newMode) {
                    case 1:
                        result = String.valueOf(binary);
                        break;
                    case 2:
                        result = String.valueOf((int) number);
                        break;
                    case 3:
                        result = Integer.toHexString(binary);
                        break;
                    case 4:
                        result = Integer.toOctalString(binary);
                        break;
                }
                break;
            case 3: // Hexadecimal
                int hex = Integer.parseInt(String.valueOf((int) number), 16);
                switch (newMode) {
                    case 1:
                        result = String.valueOf(hex);
                        break;
                    case 2:
                        result = Integer.toBinaryString(hex);
                        break;
                    case 3:
                        result = String.valueOf((int) number);
                        break;
                    case 4:
                        result = Integer.toOctalString(hex);
                        break;
                }
                break;
            case 4: // Octal
                int octal = Integer.parseInt(String.valueOf((int) number), 8);
                switch (newMode) {
                    case 1:
                        result = String.valueOf(octal);
                        break;
                    case 2:
                        result = Integer.toBinaryString(octal);
                        break;
                    case 3:
                        result = Integer.toHexString(octal);
                        break;
                    case 4:
                        result = String.valueOf((int) number);
                        break;
                }
                break;
        }

        System.out.println("Converted Number: " + result);
    }
}



