///-----------------------------------------------------------------------///
/// @file ChessPieceMoveData.java                                         ///
/// @brief Contains the implementation of the chess piece move data class ///
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
 * The {@code ChessPieceMoveData} Contains the implementation of the chess
 * piece move data class
 *
 * @version 1.0
 */
public class ChessPieceMoveData {
    // Chess piece position from which it should be moved
    public ChessBoardPosition from;
    // Chess piece position to which it has to be moved to
    public ChessBoardPosition to;

    /**
     * @brief The default constructor
     */
    public ChessPieceMoveData() {
        this(0, 0, 0, 0);
    }

    /**
     * @brief The constructor
     * @param startRowId - start row number
     * @param startColId - start column number
     * @param targetRowId - target row number
     * @param targetColId - target column number
     */
    public ChessPieceMoveData(int startRowId, int startColId, int targetRowId, int targetColId) {
        this(new ChessBoardPosition(startRowId, startColId),
                new ChessBoardPosition(targetRowId, targetColId));
    }

    /**
     * @brief The constructor
     * @param fromPositon - the start position
     * @param toPositon - the target position
     */
    public ChessPieceMoveData(ChessBoardPosition fromPositon, ChessBoardPosition toPositon) {
        this.from = fromPositon;
        this.to = toPositon;
    }
}