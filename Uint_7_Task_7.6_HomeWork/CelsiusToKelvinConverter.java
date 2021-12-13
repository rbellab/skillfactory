///-----------------------------------------------------------------------///
/// @file CelsiusToKelvinConverter.java                                   ///
/// @brief Contains the implementation of CelsiusToKelvinConverter class  ///
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
 * The {@code CelsiusToKelvinConverter} contains the implementation of
 * the CelsiusToKelvinConverter class
 *
 * @version 1.0
 */
class CelsiusToKelvinConverter implements ITemperatureValueConverter {
    // The offset value for Kelvin scala
    private final double KELVIN_SCALA_OFFSET = 273.15F;

    /**
     * @param inputValue - the input value
     * @return Returns the converted value
     */
    @Override
    public double getConvertedValue(double inputValue) {
        return inputValue + KELVIN_SCALA_OFFSET;
    }

    /**
     * @return The symbol of the converted value type
     */
    @Override
    public String getConvertedValueType() {
        return "°K";
    }
}