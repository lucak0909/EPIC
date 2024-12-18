
package org.epiccalculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Matrices extends Main {

    private static void options() {
        System.out.println("1 --> Add matrices");
        System.out.println("2 --> Subtract matrices");
        System.out.println("3 --> Multiply matrices");
        System.out.println("4 --> LU Factorisation");
        System.out.print(">>> ");
    }

    protected static ArrayList<int[][]> createMatrices() {
        Scanner input = new Scanner(System.in);
        ArrayList<int[][]> matrices = new ArrayList<>();

        System.out.println("Enter the number of matrices you want to create (Max 2): ");
        System.out.println("For LU Factorisation, enter 1 matrix.");
        System.out.print(">>>");
        int matricesCount = input.nextInt();

        while (!Main.Validate.isNumeric(String.valueOf(matricesCount)) || matricesCount > 2 || matricesCount < 1) {
            System.out.println("Invalid");
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
            printMatrix(matrix);
        }

        return matrices;
    }

    protected static void printMatrix(int[][] matrix) {
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

    protected static boolean isSquare(int[][] matrix) {
        return matrix.length == matrix[0].length;
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
            case 4 -> new LUFactorisation();
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
        ArrayList<int[][]> matrices = Matrices.createMatrices();
        if (Matrices.sameDimensions(matrices)) {
            int[][] matrix1 = matrices.get(0);
            int[][] matrix2 = matrices.get(1);

            int rows = matrix1.length;
            int cols = matrix1[0].length;

            int[][] result = new int[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    result[r][c] = matrix1[r][c] + matrix2[r][c];
                }
            }

            System.out.println("The sum of the matrices is: ");
            Matrices.printMatrix(result);
        } else {
            System.out.println("Matrices must have the same dimensions.");
        }
    }
}

class MatricesSubtraction implements MatrixCalculation {
    @Override
    public void calculate() {
        ArrayList<int[][]> matrices = Matrices.createMatrices();
        if (Matrices.sameDimensions(matrices)) {
            int[][] matrix1 = matrices.get(0);
            int[][] matrix2 = matrices.get(1);

            int rows = matrix1.length;
            int cols = matrix1[0].length;

            int[][] result = new int[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    result[r][c] = matrix1[r][c] - matrix2[r][c];
                }
            }

            System.out.println("The difference of the matrices is: ");
            Matrices.printMatrix(result);
        } else {
            System.out.println("Matrices must have the same dimensions.");
        }
    }
}

class MatricesMultiplication implements MatrixCalculation {
    @Override
    public void calculate() {
        ArrayList<int[][]> matrices = Matrices.createMatrices();
        if (Matrices.m1ColumnsEqualM2Rows(matrices)) {
            int[][] matrix1 = matrices.get(0);
            int[][] matrix2 = matrices.get(1);

            int rows1 = matrix1.length;
            int cols1 = matrix1[0].length;
            int cols2 = matrix2[0].length;

            int[][] result = new int[rows1][cols2];
            for (int r = 0; r < rows1; r++) {
                for (int c = 0; c < cols2; c++) {
                    for (int i = 0; i < cols1; i++) {
                        result[r][c] += matrix1[r][i] * matrix2[i][c];
                    }
                }
            }

            System.out.println("The product of the matrices is: ");
            Matrices.printMatrix(result);
        }

        else {
            System.out.println("Matrix 1 columns must equal Matrix 2 rows for multiplication.");
        }
    }
}


class LUFactorisation implements MatrixCalculation {
    @Override
    public void calculate() {
        ArrayList<int[][]> matrices = Matrices.createMatrices();
        if (matrices.size() == 1) {
            int[][] matrix = matrices.get(0);
            if (Matrices.isSquare(matrix)) {
                //LU Factorisation
                int n = matrix.length;
                int[][] L = new int[n][n];
                int[][] U = new int[n][n];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j < i) {
                            L[j][i] = 0;
                        } else {
                            L[j][i] = matrix[j][i];
                            for (int k = 0; k < i; k++) {
                                L[j][i] -= L[j][k] * U[k][i];
                            }
                        }
                    }

                    for (int j = 0; j < n; j++) {
                        if (j < i) {
                            U[i][j] = 0;
                        } else if (j == i) {
                            U[i][j] = 1;
                        } else {
                            U[i][j] = matrix[i][j] / L[i][i];
                            for (int k = 0; k < i; k++) {
                                U[i][j] -= ((L[i][k] * U[k][j]) / L[i][i]);
                            }
                        }
                    }
                }

                System.out.println("Lower Triangular Matrix L:");
                Matrices.printMatrix(L);
                System.out.println("Upper Triangular Matrix U:");
                Matrices.printMatrix(U);
            }
            else {
                System.out.println("Matrix must be square for LU Factorisation.");
            }
        }
            else {
                System.out.println("One matrix is required for LU Factorisation.");
            }
        }
}

