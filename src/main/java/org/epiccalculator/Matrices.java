/*package org.epiccalculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Matrices extends Main {

    public static void CalculateMatrix() {
    }

    private static Integer MatrixCount() {
        byte matricesCount = 0; // initalise loop

        System.out.println("Number of Matrices (Max 5): ");

        while (!Main.Validate.isNumeric(String.valueOf(matricesCount)) || matricesCount <= 0) {
            System.out.println("Please enter a number: ");
            System.out.print(">>>");
            matricesCount = input.nextByte();

            ArrayList<ArrayList<Integer>> matrices = new ArrayList<>();

            for (int i = matricesCount; i < 6; i++) {
                matrices.add(new ArrayList<>());
            }

            System.out.println(matricesCount + " matrices have been created.");
            return matricesCount;
    }
}

interface MatrixCalculation {
    Scanner input = new Scanner(System.in);
    void calculate();
}
*/