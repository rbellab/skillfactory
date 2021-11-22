///-----------------------------------------------------------------------///
/// @file ShapeType.java                                                  ///
/// @brief Contains the implementation of ShapeType enum                  ///
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
 * The {@code ShapeType} class contains implementation of
 * the shape type enum as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public enum ShapeType {
    ST_NONE(0),
    ST_CIRCLE(1),
    ST_SQUARE(2),
    ST_TRIANGLE(3);

    final int value;

    /**
     * @brief Constructor
     * @param value - the int value for the ShapeType; */
    ShapeType(int value) {
        this.value = value;
    }

    /**
     * @brief The getter for the "value" */
    public int getValue() {
        return value;
    }

    /**
     * @brief The method to convert the int value to corresponding ShapeType enum value */
    public static ShapeType convert(int n) {
        for(ShapeType shapeType : values())
            if(shapeType.getValue() == n)
                return shapeType;
        return null;
    }

    /**
     * @brief The method to convert the enum value to string */
    @Override
    public String toString() {
        switch (this) {
            case ST_NONE     -> { return "None"; }
            case ST_CIRCLE   -> { return "circle"; }
            case ST_SQUARE   -> { return "square"; }
            case ST_TRIANGLE -> { return "triangle"; }
            default          -> { return "<UNKNOWN>"; }
        }
    }
}