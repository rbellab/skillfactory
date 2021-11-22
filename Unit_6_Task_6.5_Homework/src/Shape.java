///-----------------------------------------------------------------------///
/// @file Shape.java                                                      ///
/// @brief Contains the implementation of shape base class                ///
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
 * The {@code Shape} class contains implementation of
 * the shape base class as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class Shape {
    private int posX;
    private int posY;
    private ShapeType shapeType;

    /**
     * @brief Default constructor */
    public Shape() {
        this(0, 0, ShapeType.ST_NONE);
    }

    /**
     * @brief Constructor
     * @param posX - the abscissa ot the position;
     * @param posY - the ordinate ot the position;
     * @param type - the type ot the shape; */
    public Shape(int posX, int posY, ShapeType type) {
        this.posX = posX;
        this.posY = posY;
        this.shapeType = type;
    }

    /**
     * @brief The method to "draw" the shape */
    public void draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("I'm a ");
        sb.append(this.shapeType.toString());
        sb.append(" at position [");
        sb.append(this.posX);
        sb.append(", ");
        sb.append(this.posY);
        sb.append("]");
        System.out.print(sb);
    }
}