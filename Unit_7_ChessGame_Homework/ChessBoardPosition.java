///-----------------------------------------------------------------------///
/// @file ChessBoardPosition.java                                         ///
/// @brief Contains the implementation of chess board position            ///
///        data object                                                    ///
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
/// This code is distributed as a part of home work project "Chess" in    ///
/// the hope that it will work correctly and will be useful, but WITHOUT  ///
/// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or ///
/// FITNESS FOR A PARTICULAR PURPOSE. JUST ENJOY IT! :)                   ///
///-----------------------------------------------------------------------///
/// File created on: 2021-12-03                                           ///
/// @author Roman Berngardt  [mailto: roman@berngardt.eu]                 ///
///-----------------------------------------------------------------------///

/**
 * The {@code ChessBoardPosition} contains the implementation of the chess
 * board position data object
 *
 * @version 1.0
 */
public class ChessBoardPosition {
    // The row number
    public int rowId;
    // The coulmn number
    public int colId;

    /**
     * @brief The constructor
     * @param rowId - row number
     * @param colId - column number
     */
    public ChessBoardPosition(int rowId, int colId) {
        this.rowId = rowId;
        this.colId = colId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChessBoardPosition) {
            boolean retVal = (this.rowId == ((ChessBoardPosition) obj).rowId);
            retVal &= (this.colId == ((ChessBoardPosition) obj).colId);
            return retVal;
        }
        return false;
    }
}
