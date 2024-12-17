
package org.epiccalculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Matrices extends Main {

    private static void options() {
        System.out.println("1 --> Add matrices");
        System.out.println("2 --> Subtract matrices");
        System.out.println("3 --> Multiply matrices");
        System.out.println("4 --> Inverse a matrix");
        System.out.println("5 --> Eigenvalues and Eigenvectors");
        System.out.println("6 --> LU Factorisation");
        System.out.print(">>> ");

    }

    protected static ArrayList<int[][]> createMatrices() {
        Scanner input = new Scanner(System.in);
        ArrayList<int[][]> matrices = new ArrayList<>();

        System.out.println("Enter the number of matrices you want to create (Max 2): ");
        System.out.println("For eigenvalues, eigenvectors and LU Factorisation, enter 1 matrix.");
        System.out.print(">>>");
        int matricesCount = input.nextInt();

        while (matricesCount <= 0 || matricesCount > 2) {
            System.out.println("Invalid. Please enter a number between 1 and 2: ");
            matricesCount = input.nextInt();
        }

        for (int i = 0; i < matricesCount; i++) {
            System.out.println("Number of rows for matrix " + (i + 1) + ": ");
            int rows = input.nextInt();
            System.out.println("Number of columns for matrix " + (i + 1) + ": ");
            int cols = input.nextInt();

            int[][] matrix = new int[rows][cols];
            System.out.println("Enter the elements of matrix " + (i + 1) + ": ");
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    matrix[r][c] = input.nextInt();
                }
            }
            matrices.add(matrix);
        }

        System.out.println(matricesCount + " matrices have been created.");
        System.out.println("The matrices are: ");
        for (int[][] matrix : matrices) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            int maxLength = 0;

            // Find the maximum length of the elements in the matrix
            for (int[] row : matrix) {
                for (int element : row) {
                    maxLength = Math.max(maxLength, String.valueOf(element).length());
                }
            }

            // Top border
            System.out.print("┌");
            for (int c = 0; c < cols; c++) {
                for (int i = 0; i < maxLength + 2; i++) {
                    System.out.print("─");
                }
                if (c < cols - 1) {
                    System.out.print("┬");
                }
            }
            System.out.println("┐");

            // Matrix rows
            for (int r = 0; r < rows; r++) {
                System.out.print("│");
                for (int c = 0; c < cols; c++) {
                    System.out.printf(" %-" + maxLength + "d │", matrix[r][c]);
                }
                System.out.println();

                // Row separator
                if (r < rows - 1) {
                    System.out.print("├");
                    for (int c = 0; c < cols; c++) {
                        for (int i = 0; i < maxLength + 2; i++) {
                            System.out.print("─");
                        }
                        if (c < cols - 1) {
                            System.out.print("┼");
                        }
                    }
                    System.out.println("┤");
                }
            }

            // Bottom border
            System.out.print("└");
            for (int c = 0; c < cols; c++) {
                for (int i = 0; i < maxLength + 2; i++) {
                    System.out.print("─");
                }
                if (c < cols - 1) {
                    System.out.print("┴");
                }
            }
            System.out.println("┘");
            System.out.println();
        }

        return matrices;
    }

    protected static boolean sameDimensions(ArrayList<int[][]> matrices) {
        if (matrices.size() != 2) {
            throw new IllegalArgumentException("Exactly two matrices are required.");
        }

        int rows1 = matrices.get(0).length;
        int cols1 = matrices.get(0)[0].length;
        int rows2 = matrices.get(1).length;
        int cols2 = matrices.get(1)[0].length;

        return rows1 == rows2 && cols1 == cols2;
    }

    protected static boolean m1ColumnsEqualM2Rows(ArrayList<int[][]> matrices) {
        if (matrices.size() != 2) {
            throw new IllegalArgumentException("Exactly two matrices are required.");
        }

        int cols1 = matrices.get(0)[0].length;
        int rows2 = matrices.get(1).length;

        return cols1 == rows2;
    }


    public static void CalculateMatrix() {
        Scanner input = new Scanner(System.in);

        int mode;
        options();
        mode = input.nextInt();

        while (!Validate.isNumeric(String.valueOf(mode)) || mode > 6 || mode < 1) {
            System.out.println("Invalid -> Try again\n");
            options();
            mode = input.nextInt();
        }

        MatrixCalculation calculation = switch (mode) {
            case 1 -> new MatricesAddition();
            case 2 -> new MatricesSubtraction();
            case 3 -> new MatricesMultiplication();
            case 4 -> new EigenValuesAndVectors();
            case 5 -> new LUFactorisation();
            default -> null;
        };

        if (calculation != null) {
            calculation.calculate();
        } else {
            System.out.println("Invalid.");
        }
    }
}


interface MatrixCalculation {
    Scanner input = new Scanner(System.in);
    void calculate();
}

class MatricesAddition implements MatrixCalculation {
    @Override
    public void calculate() {
        if (Matrices.sameDimensions(Matrices.createMatrices())) {
            // Add matrices

        } else {
            System.out.println("Matrices must have the same dimensions.");
        }
    }
}

class MatricesSubtraction implements MatrixCalculation {
    @Override
    public void calculate() {
        if (Matrices.sameDimensions(Matrices.createMatrices())) {
            // Subtraction

        } else {
            System.out.println("Matrices must have the same dimensions.");
        }
    }
}

class MatricesMultiplication implements MatrixCalculation {
    @Override
    public void calculate() {
        if (Matrices.m1ColumnsEqualM2Rows(Matrices.createMatrices())) {
            // Multiplication
        } else {
            System.out.println("Matrix 1 columns must equal Matrix 2 rows for multiplication.");
        }
    }
}

class EigenValuesAndVectors implements MatrixCalculation {
    @Override
    public void calculate() {
    }
}

class LUFactorisation implements MatrixCalculation {
    @Override
    public void calculate() {

    }
}
