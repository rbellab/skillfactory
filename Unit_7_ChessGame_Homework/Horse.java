//-----------------------------------------------------------------------///
/// @file Horse.java                                                     ///
/// @brief Contains the implementation of Horse chess piece              ///
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
 * The {@code Horse} contains the implementation of the Horse chess piece
 *
 * @version 1.0
 */
public class Horse extends ChessPiece {

    /**
     * @brief The constructor of the Horse object
     * @param chessColor - Specifies the color of the Horse
     */
    public Horse(ChessColor chessColor) {
        super(chessColor);
    }

    /**
     * @brief Checks if the horse can be moved to the desired position
     * @param chessBoard - the chess board object
     * @param chessPieceMoveData - contains start and target positions
     * @return true, if succeeded, otherwise - false
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, ChessPieceMoveData chessPieceMoveData) {
        // Perform the base checks for all pieces
        if (super.canMoveToPosition(chessBoard, chessPieceMoveData)) {
            // Check if target position is acceptable for a horse
            if (((2 == Math.abs(chessPieceMoveData.to.colId - chessPieceMoveData.from.colId)) &&
                 (1 == Math.abs(chessPieceMoveData.to.rowId - chessPieceMoveData.from.rowId))) ||
               ((2 == Math.abs(chessPieceMoveData.to.rowId - chessPieceMoveData.from.rowId)) &&
                 (1 == Math.abs(chessPieceMoveData.to.colId - chessPieceMoveData.from.colId)))) {
                return true;
            }
        }

        // If we're here, then some checks are fails -> return false
        return false;
    }

    /**
     * @return The symbol of the Horse -> "H"
     */
    @Override
    public String getSymbol() {
        return "H";
    }
}
