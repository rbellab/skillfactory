///-----------------------------------------------------------------------///
/// @file MatrixTransposer.java                                           ///
/// @brief Contains the implementation of MatrixTransposer                ///
///-----------------------------------------------------------------------///
/// @copyright (c) 2021 by Roman Berngardt. All rights are absolutely not ///
/// reserved.                                                             ///
///                                                                       ///
/// PLEASE FEEL FREE TO ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE    ///
/// HEADER AT ALL.                                                        ///
///                                                                       ///
/// This code is free software; you can redistribute it and/or modify it  ///
/// without any restrictions.                                             ///
///                                                                       ///
/// This code is distributed as a part of home work (practicum task, unit ///
/// 3) in the hope that it will work correctly and will be useful, but    ///
/// WITHOUT ANY WARRANTY; without even the implied warranty of            ///
/// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                  /// 
/// JUST ENJOY IT! :)                                                     ///
///-----------------------------------------------------------------------///
/// File created on: 2021-10-29                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The {@code MatrixTransposer} class contains implementation of
 * a simple matrix transposer as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class MatrixTransposer {

    /**
     * @brief Program entry point
     * @param args - Currently not used; */
    public static void main(String[] args) {
        MatrixTransposer mt = MatrixTransposer.getInstance();

        mt.printBanner();
        mt.run();
        mt.sayGoodBye();
    }

    /**
     * @brief The method, where all the most interesting happens */
    private void run() {
        int m = getValue("Введите число m [1..10]: ", 1, 10);
        int n = getValue("Введите число n [1..10]: ", 1, 10);
        int[][] originalMatrix = getOriginamMatrix(m, n);
        int[][] transposedMatrix = transposeMatrix(m, n,originalMatrix);
        printMatrix("Оригинальная матрица:", originalMatrix, m, n);
        printMatrix("Транспонированная матрица:", transposedMatrix, n, m);
    }

    /**
     * @brief Prints the given matrix to the console
     * @param prompt: input - Prompt string for user
     * @param matrix: input - The given matrix M x N
     * @param m: input - Count of rows of the matrix
     * @param n: input - Count of columns of the matrix */
    private void printMatrix(String prompt, int[][] matrix, int m, int n) {
        System.out.println(prompt);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.print(String.format("%02d ", matrix[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @brief Transposes the matrix diagonally
     * @param m: input - Count of rows of the matrix
     * @param n: input - Count of columns of the matrix
     * @return The transposed matrix N x M */
    private int[][] transposeMatrix(int m, int n, int[][] inputMatrix) {
        int[][] returnValue = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                returnValue[i][j] = inputMatrix[j][i];
            }
        }
        return returnValue;
    }

    /**
     * @brief Creates and fills a matrix
     * @param m: input - Count of rows of the matrix
     * @param n: input - Count of columns of the matrix
     * @return The matrix M x N */
    private int[][] getOriginamMatrix(int m, int n) {
        int[][] returnValue = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                returnValue[i][j] = i*j+i;
            }
        }
        return returnValue;
    }

    /**
     * @brief Get user input value
     * @param prompt: input - Prompt string for user
     * @param min: input - The expected min. value
     * @param max: input - The expected max. value
     * @return The value, which user has given */
    private int getValue(String prompt, int min, int max) {
        boolean succeeded = false;
        int returnValue = 1;
        while (!succeeded) {
            System.out.print(prompt);
            try {
                Scanner scanner = new Scanner(System.in);
                returnValue = scanner.nextInt();
                if (!isInRange(min, max, returnValue)) {
                    throw new InputMismatchException();
                }
                succeeded = true;
            } catch (InputMismatchException ime) {
                System.out.println("ОШИБКА: Это не число в заданном диапазоне!");
            }
        }
        return returnValue;
    }

    /**
     * @brief Check if the value is in range
     * @param min: input - The minimum value of the range
     * @param max: input - The maximum value of the range
     * @param val: input - The valuch, which has to be checked
     * @return True if the value is in range [min..max] */
    private boolean isInRange(int min, int max, int val) {
        return ((val >= min) && (val <= max));
    }

    /**
     * @brief Get the instance of this class
     * @return The instance of the MatrixTransposer-class */
    public static MatrixTransposer getInstance() {
        return INSTANCE_OF_ME;
    }

    /**
     * @brief Just a bit more courtesy. :) */
    private void sayGoodBye() {
        System.out.print(
                "\r\nСпасибо, что воспользовались этим замечательным иструментом!\r\n" +
                        "До новых встречь!\r\n\n"
        );
    }

    /**
     * @brief Print the name of the program and some version information */
    private void printBanner() {
        System.out.print(
                "\r\n**********************************************************************\r\n" +
                        "*                    \"ПЕРЕВОРАЧИВАТЕЛЬ\" МАТРИЦ                       *\r\n" +
                        "*--------------------------------------------------------------------*\r\n" +
                        "*   ВЕРСИЯ: 1.0              Copyright (C) 2021 by Roman Berngardt   *\r\n" +
                        "**********************************************************************\r\n\n"
        );
    }

    /**
     * @brief Private constructor of the class.
     *        Don't let anyone from outside to instantiate this class */
    private MatrixTransposer() {}

    /**
     * @brief Create private instance of the class */
    private static final MatrixTransposer INSTANCE_OF_ME = new MatrixTransposer();
}
