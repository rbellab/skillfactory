///-----------------------------------------------------------------------///
/// @file Subject.java                                                    ///
/// @brief Contains the implementation of ISubject                        ///
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
 * The {@code Subject} contains the implementation of a subject
 *
 * @version 1.0
 */
public class Subject implements ISubject {
    protected String subjectType;
    protected String movingType;

    public Subject() {
        this("None", "None");
    }

    public Subject(String newSubjectType, String newMovingType) {
        this.subjectType = newSubjectType;
        this.movingType = newMovingType;
    }

    @Override
    public void moveIt() {
        System.out.println(this.subjectType + " is " + this.movingType + " right now!");
    }
}
