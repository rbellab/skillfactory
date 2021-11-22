///-----------------------------------------------------------------------///
/// @file Loader.java                                                     ///
/// @brief Contains the implementation of the Loader class                ///
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
/// This code is distributed as a part of home work (task 6.5) in the     ///
/// hope that it will work correctly and will be useful, but WITHOUT ANY  ///
/// WARRANTY; without even the implied warranty of MERCHANTABILITY or     ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2021-11-22                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

/**
 * The {@code Loader} class contains implementation of
 * the Loader as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class Loader {

    /**
     * @brief Program entry point
     * @param args - Currently not used; */
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        for (int i = 0; i < 20; ++i) {
            int rndVal = shapeFactory.getRandomValue(1, ShapeType.values().length-1);
            ShapeType shapeType = ShapeType.convert(rndVal);
            Shape shape = shapeFactory.createShape(shapeType);
            if (null != shape) {
                shape.draw();
            } else {
                System.err.println("Something goes wrong here!");
            }
        }

    }
}
