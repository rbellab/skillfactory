///-----------------------------------------------------------------------///
/// @file Unit11HomeWork.java                                             ///
/// @brief Contains the implementation of Unit11HomeWork                  ///
///-----------------------------------------------------------------------///
/// @copyright (c) 2022 by Roman Berngardt. All rights are absolutely not ///
/// reserved.                                                             ///
///                                                                       ///
/// PLEASE FEEL FREE TO ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE    ///
/// HEADER AT ALL.                                                        ///
///                                                                       ///
/// This code is free software; you can redistribute it and/or modify it  ///
/// without any restrictions.                                             ///
///                                                                       ///
/// This code is distributed as a part of home work (task 11.8) in the    ///
/// hope that it will work correctly and will be useful, but WITHOUT ANY  ///
/// WARRANTY; without even the implied warranty of MERCHANTABILITY or     ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2022-01-03                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Unit11HomeWork {

    /**
     * @brief Program entry point
     * @param args - currently not used
     */
    public static void main(String[] args) {

        // Define an array for test-purposes.
        Integer[] testArray = {5, 5, 5, 10, 1, 2, 7, 7, 3};

        // Get all values and its count and print it
        System.out.println(getCountOccurrencesEachItem(testArray));
    }

    /**
     * @brief Calculates the count of occurrences of each item
     * @param inputArray - the input array
     * @return Returns a Map < K, Integer >, where K is the value
     *         from the array and Integer is the number of occurrences
     *         in the array */
    public static <K> Map<K, Integer> getCountOccurrencesEachItem(K[] inputArray) {
        Map<K, Integer> map = new HashMap<>();
        for (K k : inputArray) {
            map.compute(k, new BiFunction<K, Integer, Integer>() {
                @Override
                public Integer apply(K k, Integer count) {
                    return count == null ? 1 : count + 1;
                }
            });
        }

        return map;
    }
}
