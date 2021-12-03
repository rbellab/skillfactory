///-----------------------------------------------------------------------///
/// @file Bishop.java                                                     ///
/// @brief Contains the implementation of Bishop chess piece              ///
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
 * The {@code Bishop} contains the implementation of the Bishop chess piece
 *
 * @version 1.0
 */
public class Bishop extends ChessPiece {

    /**
     * @brief The constructor of the Bishop object
     * @param chessColor - Specifies the color of the Bishop
     */
    public Bishop(ChessColor chessColor) {
        super(chessColor);
    }

    /**
     * @brief Specifies if the Bishop can be moved to the given position on the board
     * @param chessBoard - The chess board object
     * @param chessPieceMoveData - contains the start and the target positions
     * @return true if the Bishop can be moved to the given position, otherwise false
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, ChessPieceMoveData chessPieceMoveData) {
        // Perform basic checks first
        boolean retVal = super.canMoveToPosition(chessBoard, chessPieceMoveData);
        // Create the checker object
        BishopMoveChecker bishopMoveChecker = new BishopMoveChecker(chessBoard);
        // Check, if the Bishop can be moved to the given position
        retVal &= bishopMoveChecker.canMoveToPosition(chessPieceMoveData);
        return retVal;
    }

    /**
     * @brief Returns the symbol of the Bishop */
    @Override
    public String getSymbol() {
        return "B";
    }
}
