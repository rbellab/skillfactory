///-----------------------------------------------------------------------///
/// @file ITemperatureValueConverter.java                                 ///
/// @brief Contains the definition of ITemperatureValueConverter interface///
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

interface ITemperatureValueConverter {
    double getConvertedValue(double inputValue);
    String getConvertedValueType();
}