///-----------------------------------------------------------------------///
/// @file Circle.java                                                     ///
/// @brief Contains the implementation of circle shape                    ///
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
 * The {@code Circle} class contains implementation of
 * a Circle shape as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class Circle extends Shape {
    private int radius;

    /**
     * @brief Constructor
     * @param posX - the abscissa ot the position;
     * @param posY - the ordinate ot the position;
     * @param radius - the radius ot the circle; */
    public Circle(int posX, int posY, int radius) {
        super(posX, posY, ShapeType.ST_CIRCLE);
        this.radius = radius;
    }

    /**
     * @brief The method to "draw" the circle */
    @Override
    public void draw() {
        super.draw();
        StringBuilder sb = new StringBuilder();
        sb.append(" and radius ");
        sb.append(this.radius);
        System.out.println(sb);
    }
}
