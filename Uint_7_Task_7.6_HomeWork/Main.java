///-----------------------------------------------------------------------///
/// @file Main.java                                                       ///
/// @brief Contains the implementation of Main class                      ///
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
/// This code is distributed as a part of home work @SkillFsctory in      ///
/// the hope that it will work correctly and will be useful, but WITHOUT  ///
/// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2021-12-13                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The {@code Main} contains the implementation of the home work task
 * of unit 7 @SkillFactory
 *
 * @version 1.0
 */
public class Main {

    /**
     * @brief Program entry point
     * @param args - Currently not used; */
    public static void main(String[] args) {
        /* Array of temperature converters */
        ITemperatureValueConverter[] converters = {
           new CelsiusToCelsiusConverter(),    // A new Celsius-to-Celsius converter
           new CelsiusToKelvinConverter(),     // A new Celsius-to-Kelvin converter
           new CelsiusToFahrenheitConverter()  // A new Celsius-to-Fahrenheit converter
        };

        try {
            System.out.print("Введите значение температуры в °C: ");
            // Read the input temperature value in °C
            double inputValue = new Scanner(System.in).nextDouble();
            // Check, if the temperature is above absolute zero
            if (new SubZeroChecker().checkTemperatureValue(inputValue)) {
                // Let's run through all converters ...
                for (int i = 0; i < converters.length; i++) {
                    // ... and print the converted values
                    printString(buildOutputString(converters[i], inputValue));
                }
            } else {
                // Say, that it can't be so cold at all!
                printErrorMsg("Ну, так холодно не бывает!");
            }
        } catch (InputMismatchException ex) {
            // The input is not a numeric value!
            printErrorMsg("Вы ввели не число!");
        }
    }

    /**
     * @brief Builds a result string for output to the console
     * @param converter - a temperature value converter
     * @param value - the input temperature value which has to be converted
     * @return Returns the result string for output to the console
     */
    private static String buildOutputString(ITemperatureValueConverter converter, double value) {
        StringBuilder result = new StringBuilder();
        result.append("Температура в ");
        result.append(converter.getConvertedValueType());
        result.append(": ");
        result.append(converter.getConvertedValue(value));
        return result.toString();
    }

    /**
     * @brief Prints the error message into the stderr
     * @param errMsg - The error message which has to be printed out
     */
    private static void printErrorMsg(String errMsg) {
        System.err.println("ОШИБКА: " + errMsg);
    }

    /**
     * @brief Prints a strinc into the console
     * @param string - The string which has to be printed out
     */
    private static void printString(String string) {
        System.out.println(string);
    }
}