///-----------------------------------------------------------------------///
/// @file Loader.java                                                     ///
/// @brief Contains the implementation of Loader                          ///
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
/// This code is distributed as a part of home work (task 5.6) in the     ///
/// hope that it will work correctly and will be useful, but WITHOUT ANY  ///
/// WARRANTY; without even the implied warranty of MERCHANTABILITY or     ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2021-11-16                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

/**
 * The {@code Loader} class contains implementation of
 * a task 5.6 of unit 5 as a part of homework @SkillFactory.
 *
 * @version 1.0
 */
public class Loader {

    /**
     * @brief Program entry point
     * @param args - Currently not used; */
    public static void main(String[] args) {
        moveMovable(new Aircraft()); // Create and move the aircraft (let's fly!)
        moveMovable(new Bicycle()); // Create and move the bicycle (let's ride!)
        moveMovable(new Box()); // Create and move the box
        moveMovable(new Ball()); // Create and move the ball (let's rolling it)
    }

    /**
     * @brief Move the "movable" object
     * @param movable - An instance of a class that implements the IMovable; */
    public static void moveMovable(IMovable movable) {
        movable.moveIt(); // Call the moveIt() method of the object
    }
}
