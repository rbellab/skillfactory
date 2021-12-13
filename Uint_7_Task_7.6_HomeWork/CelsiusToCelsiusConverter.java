///-----------------------------------------------------------------------///
/// @file CelsiusToCelsiusConverter.java                                  ///
/// @brief Contains the implementation of CelsiusToCelsiusConverter class ///
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
 * The {@code CelsiusToCelsiusConverter} contains the implementation of
 * the CelsiusToCelsiusConverter class
 *
 * @version 1.0
 */
public class CelsiusToCelsiusConverter implements ITemperatureValueConverter {

    /**
     * @param inputValue - the input value
     * @return Returns the converted value
     */
    @Override
    public double getConvertedValue(double inputValue) {
        return inputValue;
    }

    /**
     * @return The symbol of the converted value type
     */
    @Override
    public String getConvertedValueType() {
        return "°C";
    }
}
