///-----------------------------------------------------------------------///
/// @file ArraySorter.java                                                ///
/// @brief Contains the implementation of ArraySorter                     ///
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
/// 8) in the hope that it will work correctly and will be useful, but    ///
/// WITHOUT ANY WARRANTY; without even the implied warranty of            ///
/// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                  ///
/// JUST ENJOY IT! :)                                                     ///
///-----------------------------------------------------------------------///
/// File created on: 2021-12-20                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

import java.util.Arrays;

/**
 * The {@code ArraySorter} class contains implementation of
 * a simple array sorter as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class ArraySorter {
    // A constant which specifies if the own sorting algo (true) or the one form
    // standard library hast to be used (false).
    private static final boolean useOwnAlgo = true;

    /**
     * @brief The program entry point
     * @param args - currently not used
     */
    public static void main(String[] args) {
        // The array which has to be sorted
        int[] myArray = {5, 15, 7, 10, 3, 0, 9, 2, 4, 11, 8, 1, 12, 6, 14, 13};

        printArray("ДО: ", myArray);
        if (true == useOwnAlgo) {
            System.out.println("Сортировка с использованием своей реализации алгоритма!");
            // call the onw sorting algo
            bubbleSort(myArray);
        } else {
            System.out.println("Сортировка с использованием стандратной библиотеки!");
            // call the standard library sorting algo
            Arrays.sort(myArray);
        }
        printArray("ПОСЛЕ: ", myArray);
    }

    /**
     * @brief Prints the array into the console
     * @param prompt - The user prompt, which will be printed out into the console
     * @param array - The array, which has to be printed out
     */
    public static void printArray(String prompt, int[] array) {
        System.out.print(prompt);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    /**
     * @brief Sorting the array using so-called "bubble" algo
     * @param array - The array, which has to be sorted
     */
    public static void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

}
