///-----------------------------------------------------------------------///
/// @file NetworkAddressChecker.java                                      ///
/// @brief Contains the implementation of NetworkAddressChecker           ///
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
/// This code is distributed as a part of home work (task 4.4) in the     ///
/// hope that it will work correctly and will be useful, but WITHOUT ANY  ///
/// WARRANTY; without even the implied warranty of MERCHANTABILITY or     ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2021-11-03                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code NetworkAddressChecker} class contains implementation of
 * a simple network address checker as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class NetworkAddressChecker {

    private static final String USER_PROMPT = "Введите сетевой адресс IPv4 с маской подсети \r\n"+
                                              "в виде [xy]z.[xy]z.[xy]z.[xy]z/[x]y где x, y, z -"+
                                              "десятичные цифры.\r\n=> ";
    private static final String ANSWER = "\r\nОТВЕТ: Введённая Вами строка '%s' %sявляется сетевым" +
                                         " адресом IPv4!\r\n";
    private static final String NETWORK_ADDRES_REGEX_PATTERN = "(\\d{1,3}\\.){3}\\d{1,3}/\\d{1,2}";
    private static final int MIN_VALUE        = 0;
    private static final int MAX_NADDR_VALUE  = 255;
    private static final int MAX_MASK_VALUE   = 32;

    /**
     * @brief Program entry point
     * @param args - Currently not used; */
    public static void main(String[] args) {
        NetworkAddressChecker nac = new NetworkAddressChecker();
        nac.printBanner();
        String userInput = nac.getInputValue(USER_PROMPT);
        String tmp = nac.isCorrectNetworkAddress(userInput) ? "" : "НЕ ";
        System.out.println(String.format(ANSWER, userInput, tmp));
        nac.sayGoodBye();
    }

    /**
     * @brief Reads the user input
     * @param prompt: input - Prompt string for user;
     * @return The user input string */
    public String getInputValue(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * @brief Checks whether the input string is a correct IPv4 network address
     * @param userInput: input - The input string;
     * @return True if the input string is a correct IPv4 network address, otherwise - False */
    public boolean isCorrectNetworkAddress(String userInput) {
        boolean returnValue = true;
        Pattern pattern = Pattern.compile(NETWORK_ADDRES_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(userInput);
        if (matcher.matches()) {
            String[] strNumbers = userInput.split("\\.");
            for (byte idx = 0; idx < (byte)strNumbers.length; ++idx)
            {
                if (strNumbers[idx].contains("/")) {
                    String[] strNumbers2 = strNumbers[idx].split("/");
                    returnValue &= isCorrectNumber(strNumbers2[0], MIN_VALUE, MAX_NADDR_VALUE);
                    returnValue &= isCorrectNumber(strNumbers2[1], MIN_VALUE, MAX_MASK_VALUE);
                } else {
                    returnValue &= isCorrectNumber(strNumbers[idx], MIN_VALUE, MAX_NADDR_VALUE);
                }
            }
        } else {
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * @brief Checks whether the input string is a correct number in range [minValue..maxValue]
     * @param inputString: input - The input string;
     * @param minValue: input - The minimum value of an allowed range;
     * @param maxValue: input - The maximum value of an allowed range;
     * @return True if the input string is a correct number in a range [minValue..maxValue], otherwise - False */
    public boolean isCorrectNumber(String inputString, int minValue, int maxValue) {
        boolean returnValue = true;
        int value = Integer.parseInt(inputString);
        returnValue &= value >= minValue;
        returnValue &= value <= maxValue;
        return returnValue;
    }

    /**
     * @brief Print the name of the program and some version information */
    private void printBanner() {
        System.out.print(
                "\r\n**********************************************************************\r\n" +
                    "*                \"ПРОВЕРЯТЕЛЬ\" СЕТЕВЫХ АДРЕСОВ IPv4                  *\r\n" +
                    "*--------------------------------------------------------------------*\r\n" +
                    "*   ВЕРСИЯ: 1.0              Copyright (C) 2021 by Roman Berngardt   *\r\n" +
                    "**********************************************************************\r\n\n"
        );
    }

    /**
     * @brief Just a bit more courtesy. :) */
    private void sayGoodBye() {
        System.out.print(
            "\r\nСпасибо, что воспользовались этим замечательным иструментом!\r\n" +
            "До новых встречь!\r\n\n"
        );
    }
}
