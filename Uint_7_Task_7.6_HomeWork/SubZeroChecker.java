///-----------------------------------------------------------------------///
/// @file SubZeroChecker.java                                             ///
/// @brief Contains the implementation of SubZeroChecker class            ///
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

/**
 * The {@code SubZeroChecker} contains the implementation of the SubZeroChecker
 * class
 *
 * @version 1.0
 */
class SubZeroChecker {
    // An epsilon value to check a double value against zero
    // (as the double value can not be exact equals zero
    private final double EPSILON = 0.00000001F;

    /**
     * @brief Checking if the input parameter value is greater than zero
     * @param value - the input value
     * @return true if the input parameter value is greater than zero, otherwise false
     */
    private boolean greaterThanZero(double value) {
        return value >= (0.0F - EPSILON);
    }

    /**
     * @brief Converts the inpitvalue into Kelvin scala value and checking if
     *        it is abouve absoulte zero
     * @param temperatureValue - the input temperature value in °C
     * @return true if the input parameter value is above absolute zero, otherwise false
     */
    public boolean checkTemperatureValue(double temperatureValue) {
        return greaterThanZero(new CelsiusToKelvinConverter().getConvertedValue(temperatureValue));
    }
}