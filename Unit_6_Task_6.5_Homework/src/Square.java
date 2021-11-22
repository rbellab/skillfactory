///-----------------------------------------------------------------------///
/// @file Square.java                                                     ///
/// @brief Contains the implementation of square shape                    ///
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
 * The {@code Square} class contains implementation of
 * a Square shape as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class Square extends Shape {
    private int edge;

    /**
     * @brief Constructor
     * @param posX - the abscissa ot the position;
     * @param posY - the ordinate ot the position;
     * @param edge - the length ot the edge of square; */
    public Square(int posX, int posY, int edge) {
        super(posX, posY, ShapeType.ST_SQUARE);
        this.edge = edge;
    }

    /**
     * @brief The method to "draw" the square */
    @Override
    public void draw() {
        super.draw();
        StringBuilder sb = new StringBuilder();
        sb.append(" and edge ");
        sb.append(this.edge);
        System.out.println(sb);
    }
}