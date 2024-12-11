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

    public static void CalculateNumberSystem() {
        // Display options
        options();

        // Determine Variables to be used
        byte mode;
        int number;
        byte newMode;
        String result = "";

        Scanner input = new Scanner(System.in);
        mode = input.nextByte();

        // Error handling to make sure user enters byte within range
        while (!Validate.isNumeric(String.valueOf(mode)) || mode > 4 || mode < 1) {
            System.out.println("Invalid -> Try again\n");
            options();
            mode = input.nextByte();
        }

        System.out.println("Enter an integer: ");
        number = input.nextInt();

        while (!Validate.isNumeric(String.valueOf(number))) {
            System.out.println("Invalid -> Try again\n");
            System.out.println("Enter your number:");
            number = input.nextInt();
        }

        options();
        newMode = input.nextByte();

        while (!Validate.isNumeric(String.valueOf(newMode)) || newMode > 4 || newMode < 1) {
            System.out.println("Invalid -> Try again\n");
            options();
            newMode = input.nextByte();
        }

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
    String convert(int number, byte newMode);
}


class DecimalConversion implements NumberSystemConversion {
    @Override
    public String convert(int number, byte newMode) {
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
    public String convert(int number, byte newMode) {
        int binary = Integer.parseInt(String.valueOf(number), 2);
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
    public String convert(int number, byte newMode) {
        int hex = Integer.parseInt(String.valueOf(number), 16);
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
    public String convert(int number, byte newMode) {
        int octal = Integer.parseInt(String.valueOf(number), 8);
        return switch (newMode) {
            case 1 -> String.valueOf(octal);
            case 2 -> Integer.toBinaryString(octal);
            case 3 -> Integer.toHexString(octal);
            case 4 -> String.valueOf(number);
            default -> "";
        };
    }
}



