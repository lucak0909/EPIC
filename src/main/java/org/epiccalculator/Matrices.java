
package org.epiccalculator;

import java.util.ArrayList;
import java.util.Scanner;

public class Matrices extends Main {

    private static void options() {
        System.out.println("1 --> Add matrices");
        System.out.println("2 --> Subtract matrices");
        System.out.println("3 --> Multiply matrices");
        System.out.println("4 --> LU Factorisation");
        System.out.println("5 --> Power a Matrix");
        System.out.println("6 --> Eigenvalues and Eigenvectors (2x2 Matrix only)");
        System.out.print(">>> ");
    }

    protected static ArrayList<int[][]> createMatrices(int number) {
        Scanner input = new Scanner(System.in);
        ArrayList<int[][]> matrices = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            System.out.printf("Number of rows for matrix %d: ", i + 1);
            int rows = input.nextInt();
            System.out.printf("Number of columns for matrix %d: ", i + 1);
            int cols = input.nextInt();

            int[][] matrix = new int[rows][cols];
            System.out.printf("Enter the elements of matrix %d: \n", i + 1);
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    matrix[r][c] = input.nextInt();
                }
            }
            matrices.add(matrix);
        }

        System.out.printf("%d matrices created.\n", number);
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
            throw new IllegalArgumentException("Two matrices are required.");
        }

        int rows1 = matrices.get(0).length;
        int cols1 = matrices.get(0)[0].length;
        int rows2 = matrices.get(1).length;
        int cols2 = matrices.get(1)[0].length;

        return rows1 == rows2 && cols1 == cols2;
    }

    protected static boolean m1ColumnsEqualM2Rows(ArrayList<int[][]> matrices) {
        if (matrices.size() != 2) {
            throw new IllegalArgumentException("Two matrices are required.");
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
            case 5 -> new PowerMatrix();
            case 6 -> new Eigenvalues();
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
        ArrayList<int[][]> matrices = Matrices.createMatrices(2);
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
        ArrayList<int[][]> matrices = Matrices.createMatrices(2);
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
        ArrayList<int[][]> matrices = Matrices.createMatrices(2);
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

        ArrayList<int[][]> matrices = Matrices.createMatrices(1);
        if (matrices.size() == 1) {
            int[][] matrix = matrices.getFirst();
            if (Matrices.isSquare(matrix)) {
                int n = matrix.length;
                int[][] L = new int[n][n];
                int[][] U = new int[n][n];

                for (int i = 0; i < n; i++) {
                    // Upper Triangular
                    for (int k = i; k < n; k++) {
                        int sum = 0;
                        for (int j = 0; j < i; j++) {
                            sum += (L[i][j] * U[j][k]);
                        }
                        U[i][k] = matrix[i][k] - sum;
                    }

                    // Lower Triangular
                    for (int k = i; k < n; k++) {
                        if (i == k) {
                            L[i][i] = 1; // Diagonal as 1
                        } else {
                            int sum = 0;
                            for (int j = 0; j < i; j++) {
                                sum += (L[k][j] * U[j][i]);
                            }
                            L[k][i] = (matrix[k][i] - sum) / U[i][i];
                        }
                    }
                }

                // Display Lower and Upper Triangular Matrices
                System.out.println("Lower Triangular Matrix L:");
                Matrices.printMatrix(L);

                System.out.println("Upper Triangular Matrix U:");
                Matrices.printMatrix(U);

            } else {
                System.out.println("Matrix must be square for LU Factorisation.");
            }
        } else {
            System.out.println("One matrix is required for LU Factorisation.");
        }
    }
}

class PowerMatrix implements MatrixCalculation {
    @Override
    public void calculate() {
        ArrayList<int[][]> matrices = Matrices.createMatrices(1);
        if (matrices.size() == 1) {
            int[][] matrix = matrices.getFirst();
            if (Matrices.isSquare(matrix)) {
                System.out.println("Enter the power you want to raise the matrix to: ");
                int power = input.nextInt();

                int n = matrix.length;
                int[][] result = new int[n][n];
                for (int i = 0; i < n; i++) {
                    result[i][i] = 1;
                }

                for (int i = 0; i < power; i++) {
                    int[][] temp = new int[n][n];
                    for (int r = 0; r < n; r++) {
                        for (int c = 0; c < n; c++) {
                            for (int k = 0; k < n; k++) {
                                temp[r][c] += result[r][k] * matrix[k][c];
                            }
                        }
                    }
                    result = temp;
                }

                System.out.printf("Matrix raised to the power of %d: \n", power);
                Matrices.printMatrix(result);
            }
            else {
                System.out.println("Matrix must be square for power calculation.");
            }
        }
        else {
            System.out.println("One matrix is required for power calculation.");
        }
    }
}
class Eigenvalues implements MatrixCalculation {
    @Override
    public void calculate() {
        ArrayList<int[][]> matrices = Matrices.createMatrices(1);
        if (matrices.size() == 1) {
            int[][] matrix = matrices.get(0);
            if (Matrices.isSquare(matrix)) {
                if (matrix.length == 2) {
                    double lambda1 = (((matrix[0][0] + matrix [1][1])+(Math.sqrt(Math.pow((matrix[0][0] + matrix [1][1]), 2) - 4*(matrix[0][0]*matrix[1][1] - matrix[0][1] * matrix[1][0]))))/2);
                    double lambda2 = (((matrix[0][0] + matrix [1][1])-(Math.sqrt(Math.pow((matrix[0][0] + matrix [1][1]), 2) - 4*(matrix[0][0]*matrix[1][1] - matrix[0][1] * matrix[1][0]))))/2);

                    System.out.printf("Eigenvalues: λ1 = %.4f, λ2 = %.4f\n\n", lambda1,lambda2);

                    //displaying first matrix taking lambda1
                    int[][] eigenvalue1 = new int[2][2];
                    for (int r = 0; r < 2; r++) {
                        for (int c = 0; c < 2; c++) {
                            if (r == c) {
                                eigenvalue1[r][c] = (int) (matrix[r][c] - lambda1);
                            }
                            else {
                                eigenvalue1[r][c] = matrix[r][c];
                            }
                        }
                    }

                    System.out.println("λ1 matrix: ");
                    Matrices.printMatrix(eigenvalue1);

                    //displaying second matrix taking lambda2
                    int[][] eigenvalue2 = new int[2][2];
                    for (int r = 0; r < 2; r++) {
                        for (int c = 0; c < 2; c++) {
                            if (r == c) {
                                eigenvalue2[r][c] = (int) (matrix[r][c] - lambda2);
                            }
                            else {
                                eigenvalue2[r][c] = matrix[r][c];
                            }
                        }
                    }

                    System.out.println("λ2 matrix: ");
                    Matrices.printMatrix(eigenvalue2);

                    //eigenvector(s) from lambda1
                    double x1 = 1;
                    double y1 = -((matrix[0][0]-lambda1) / matrix[0][1]);
                    double xCoef = -(matrix[0][0]-lambda1);
                    double yCoef = matrix[0][1];
                    //simplifying ratios if possible
                    if(xCoef % yCoef == 0) {
                        xCoef = xCoef/yCoef;
                        yCoef = 1;
                    }
                    else if(yCoef % xCoef == 0) {
                        yCoef =  yCoef/xCoef;
                        xCoef = 1;
                    }
                    System.out.printf("First Eigenvector pair: %n%.2fv1 = %.2fv2%n%n",xCoef, yCoef );
                    System.out.printf("Sample Eigenvector: %n[%.4f] %n[%.4f]%n%n",x1, y1);
                    if (((matrix[0][0]-lambda1)/matrix[0][1]) != matrix[1][0]/(matrix[1][1]-lambda1)) {
                        System.out.printf("Second Eigenvector pair: %n%.2fv1 = %.2fv2%n%n",matrix[1][0], (matrix[1][1]-lambda1));
                        System.out.printf("Sample Eigenvector: %n[%.4f] %n[%.4f]%n%n",1, -(matrix[1][0]) / (matrix[1][1]-lambda1));
                    }

                    //eigenvector from lambda2 (if needed)
                    if (((matrix[0][0]-lambda2)/matrix[0][1]) != matrix[1][0]/(matrix[1][1]-lambda1) &&
                            ((matrix[0][0]-lambda2)/matrix[0][1]) != (matrix[0][0]-lambda1)/matrix[0][1]) {
                        double x2 = 1;
                        double y2 = -((matrix[0][0] - lambda2) / matrix[0][1]);
                        double x2Coef = -(matrix[0][0] - lambda2);
                        double y2Coef = matrix[0][1];
                        //simplifying ratios if possible
                        if(x2Coef % y2Coef == 0) {
                            x2Coef = x2Coef/y2Coef;
                            y2Coef = 1;
                        }
                        else if(y2Coef % x2Coef == 0) {
                            y2Coef =  y2Coef/x2Coef;
                            x2Coef = 1;
                        }
                        System.out.printf("Second Eigenvector pair: %n%.2fv1 = %.2fv2%n%n", x2Coef, y2Coef);
                        System.out.printf("Sample Eigenvector: %n[%.4f] %n[%.4f]", x2, y2);
                    }
                    if ((matrix[1][0]/(matrix[1][1]-lambda2)) != matrix[1][0]/(matrix[1][1]-lambda1) &&
                            ((matrix[0][0]-lambda2)/matrix[0][1]) != (matrix[0][0]-lambda1)/matrix[0][1] &&
                            (matrix[1][0]/(matrix[1][1]-lambda2)) != ((matrix[0][0]-lambda2)/matrix[0][1])) {
                        double x2 = 1;
                        double y2 = -((matrix[0][0] - lambda2) / matrix[0][1]);
                        double x2Coef = -(matrix[0][0] - lambda2);
                        double y2Coef = matrix[0][1];
                        //simplifying ratios if possible
                        if(x2Coef % y2Coef == 0) {
                            x2Coef = x2Coef/y2Coef;
                            y2Coef = 1;
                        }
                        else if(y2Coef % x2Coef == 0) {
                            y2Coef =  y2Coef/x2Coef;
                            x2Coef = 1;
                        }
                        System.out.printf("Second Eigenvector pair: %n%.2fv1 = %.2fv2%n%n", x2Coef, y2Coef);
                        System.out.printf("Sample Eigenvector: %n[%.4f] %n[%.4f]", x2, y2);
                    }
                }
                else {
                    System.out.println("Not possible to get Eigenvalue for a Matrix of this size.");
                }
            }
        }
    }
}

