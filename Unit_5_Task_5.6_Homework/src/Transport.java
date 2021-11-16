///-----------------------------------------------------------------------///
/// @file Transport.java                                                  ///
/// @brief Contains the implementation of ITransport                      ///
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
 * The {@code Transport} contains the implementation of a transport
 *
 * @version 1.0
 */
public abstract class Transport implements ITransport {
    protected String transportType = "None";
    protected String movingType = "None";

    private Transport() {
        this("None", "None");
    }

    public Transport(String newTransportType, String newMovingType) {
        this.transportType = newTransportType;
        this.movingType = newMovingType;
    }

    @Override
    public void moveIt() {
        System.out.println(this.transportType + " is " + this.movingType + " right now!");
    }
}
