package org.epiccalculator;

import java.util.Scanner;

public class NumberSystems extends Main {

    public static String Calculate() {
        Scanner input = new Scanner(System.in);
        int mode = input.nextInt();

        // Error handling to make sure user enters int within range
        while (!Validate.isNumeric(String.valueOf(mode)) || mode < 1 || mode > 4) {
            System.out.println("\nInvalid selection. Please try again.");
            System.out.println("Select your number System");
            System.out.println("----------------------------");
            System.out.println("1 --> Decimal (Base 10)");
            System.out.println("2 --> Binary (Base 2)");
            System.out.println("3 --> Hexadecimal (Base 16)");
            System.out.println("4 --> Octal (Base 8)");
            System.out.println(">>>");
            mode = input.nextInt();
        }

        System.out.println("Enter your number:");
        String number = input.next();

        System.out.println("Select your new number system");
        System.out.println("----------------------------");
        System.out.println("1 --> Decimal (Base 10)");
        System.out.println("2 --> Binary (Base 2)");
        System.out.println("3 --> Hexadecimal (Base 16)");
        System.out.println("4 --> Octal (Base 8)");

        int newMode = input.nextInt();

        String result = "";

        switch (mode) {
            case 1: // Decimal
                int decimal = Integer.parseInt(number);
                switch (newMode) {
                    case 1:
                        result = number;
                        break;
                    case 2:
                        result = Integer.toBinaryString(decimal);
                        break;
                    case 3:
                        result = Integer.toHexString(decimal);
                        break;
                    case 4:
                        result = Integer.toOctalString(decimal);
                        break;
                }
                break;
            case 2: // Binary
                int binary = Integer.parseInt(number, 2);
                switch (newMode) {
                    case 1:
                        result = String.valueOf(binary);
                        break;
                    case 2:
                        result = number;
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
                int hex = Integer.parseInt(number, 16);
                switch (newMode) {
                    case 1:
                        result = String.valueOf(hex);
                        break;
                    case 2:
                        result = Integer.toBinaryString(hex);
                        break;
                    case 3:
                        result = number;
                        break;
                    case 4:
                        result = Integer.toOctalString(hex);
                        break;
                }
                break;
            case 4: // Octal
                int octal = Integer.parseInt(number, 8);
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
                        result = number;
                        break;
                }
                break;
        }

        return "Converted NUmber: " + result;

    }
}




