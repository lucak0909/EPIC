package org.epiccalculator;

import java.util.Scanner;

public class NumberSystems extends Main {

    private static void options() {
        System.out.println("Select your number System");
        System.out.println("----------------------------");
        System.out.println("1 --> Decimal (Base 10)");
        System.out.println("2 --> Binary (Base 2)");
        System.out.println("3 --> Hexadecimal (Base 16)");
        System.out.println("4 --> Octal (Base 8)");
        System.out.print(">>>");
    }

    public static void calculateNumberSystem() {
        // Display options
        options();

        // Determine Variables to be used
        int mode;
        int number;
        int newMode;
        String result = "";

        Scanner input = new Scanner(System.in);
        mode = input.nextInt();

        // Error handling to make sure user enters int within range
        while (!Main.Validate.isNumeric(String.valueOf(mode)) || mode > 4 || mode < 1) {
            System.out.println("Invalid -> Try again\n");
            options();
            mode = input.nextInt();
        }

        System.out.println("Enter number: ");
        number = input.nextInt();

        while (!Validate.isNumeric(String.valueOf(number))) {
            System.out.println("Invalid -> Try again\n");
            System.out.println("Enter number:");
            number = input.nextInt();
        }

        options();
        newMode = input.nextInt();

        while (!Main.Validate.isNumeric(String.valueOf(newMode)) || newMode > 4 || newMode < 1) {
            System.out.println("Invalid -> Try again\n");
            options();
            newMode = input.nextInt();
        }
        // create object of NumberSystemConversion (conversion)
        NumberSystemConversion conversion = switch (mode) {
            case 1 -> new DecimalConversion();
            case 2 -> new BinaryConversion();
            case 3 -> new HexadecimalConversion();
            case 4 -> new OctalConversion();
            default -> null;
        };

        if (conversion != null) {
            result = conversion.convert(number, newMode);
        } else {
            System.out.println("Invalid selection.");
        }

        System.out.println("Converted Number: " + result);
    }

}

interface NumberSystemConversion {
    String convert(int number, int newMode);
}

class DecimalConversion implements NumberSystemConversion {
    @Override
    public String convert(int number, int newMode) {
        return switch (newMode) {
            case 1 -> String.valueOf(number);
            case 2 -> Integer.toBinaryString(number);
            case 3 -> Integer.toHexString(number);
            case 4 -> Integer.toOctalString(number);
            default -> "";
        };
    }
}

class BinaryConversion implements NumberSystemConversion {
    @Override
    public String convert(int number, int newMode) {
        int binary = Integer.parseInt(String.valueOf(number), 2); // Parses the string representation of the binary number and converts it to decimal
        return switch (newMode) {
            case 1 -> String.valueOf(binary);
            case 2 -> String.valueOf(number);
            case 3 -> Integer.toHexString(binary);
            case 4 -> Integer.toOctalString(binary);
            default -> "";
        };
    }
}

class HexadecimalConversion implements NumberSystemConversion {
    @Override
    public String convert(int number, int newMode) {
        int hex = Integer.parseInt(String.valueOf(number), 16); // Parses the string representation of the hex number and converts it to decimal
        return switch (newMode) {
            case 1 -> String.valueOf(hex);
            case 2 -> Integer.toBinaryString(hex);
            case 3 -> String.valueOf(number);
            case 4 -> Integer.toOctalString(hex);
            default -> "";
        };
    }
}

class OctalConversion implements NumberSystemConversion {
    @Override
    public String convert(int number, int newMode) {
        int octal = Integer.parseInt(String.valueOf(number), 8); // Parses the string representation of octal and converts it to decimal
        return switch (newMode) {
            case 1 -> String.valueOf(octal);
            case 2 -> Integer.toBinaryString(octal);
            case 3 -> Integer.toHexString(octal);
            case 4 -> String.valueOf(number);
            default -> "";
        };
    }
}