///-----------------------------------------------------------------------///
/// @file Triangleava                                                     ///
/// @brief Contains the implementation of triangle shape                  ///
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
 * The {@code Triangle} class contains implementation of
 * a triangle shape as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class Triangle extends Shape {
    private boolean isIsosceles;

    /**
     * @brief Constructor
     * @param posX - the abscissa ot the position;
     * @param posY - the ordinate ot the position;
     * @param isIsosceles - specifies whether the triangle is isosceles; */
    public Triangle(int posX, int posY, boolean isIsosceles) {
        super(posX, posY, ShapeType.ST_TRIANGLE);
        this.isIsosceles = isIsosceles;
    }

    /**
     * @brief The method to "draw" the triangle */
    @Override
    public void draw() {
        super.draw();
        StringBuilder sb = new StringBuilder();
        sb.append(" and I'm ");
        if (!this.isIsosceles) sb.append("not ");
        sb.append("isosceles");
        System.out.println(sb);
    }
}