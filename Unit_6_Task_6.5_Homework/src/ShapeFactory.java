///-----------------------------------------------------------------------///
/// @file ShapeFactory.java                                               ///
/// @brief Contains the implementation of teh shape factory               ///
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
 * The {@code ShapeFactory} class contains implementation of
 * the shape factory as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class ShapeFactory {
    private static final int MIN_X = -10;
    private static final int MAX_X = 10;
    private static final int MIN_RAD = 1;
    private static final int MAX_RAD = 100;
    private static final int MIN_EDGE = 1;
    private static final int MAX_EDGE = 50;

    /**
     * @brief A method to create a shape
     * @param shapeType - the type of desired shape;*/
    public static Shape createShape(ShapeType shapeType) {
        switch (shapeType) {
            case ST_CIRCLE   -> { return createCircle(); }
            case ST_SQUARE   -> { return createSquare(); }
            case ST_TRIANGLE -> { return createTriangle(); }
            default          -> { return null; }
        }
    }

    /**
     * @brief A method to get a random value in range [min, max]
     * @param min - the min value of the range;
     * @param max - the max value of the range;*/
    public static int getRandomValue(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    /**
     * @brief A method to create a Circle object */
    private static Circle createCircle() {
        return new Circle(getRandomValue(MIN_X, MAX_X),
                          getRandomValue(MIN_X, MAX_X),
                          getRandomValue(MIN_RAD, MAX_RAD));
    }

    /**
     * @brief A method to create a Square object */
    private static Square createSquare() {
        return new Square(getRandomValue(MIN_X, MAX_X),
                          getRandomValue(MIN_X, MAX_X),
                          getRandomValue(MIN_EDGE, MAX_EDGE));
    }

    /**
     * @brief A method to create a Triangle object */
    private static Triangle createTriangle() {
        return new Triangle(getRandomValue(MIN_X, MAX_X),
                            getRandomValue(MIN_X, MAX_X),
                            (getRandomValue(0, 1) == 0));
    }
}
