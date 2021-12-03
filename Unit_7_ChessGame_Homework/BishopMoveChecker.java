///-----------------------------------------------------------------------///
/// @file BishopMoveChecker.java                                          ///
/// @brief Contains the implementation of BishopMoveChecker               ///
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
 * The {@code BishopMoveChecker} contains the implementation of the
 * BishopMoveChecker
 *
 * @version 1.0
 */
public class BishopMoveChecker {
    private ChessBoard chessBoard;

    /**
     * @brief The constructor of the BishopMoveChecker object
     * @param chessBoard - the chess board object
     */
    public BishopMoveChecker(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    /**
     * @brief Specifies if the Bishop chess piece can be moved to the given position
     * @param chessPieceMoveData - contains the start and the target positions
     * @return true if the chess Bishop can be moved to the given position, otherwise false
     */
    public boolean canMoveToPosition(ChessPieceMoveData chessPieceMoveData) {
        boolean retVal = true;

        // The Bishop chess piece can only move diagonally.
        // So, let's build a square, diagonally of which, it moves.

        // The top-left point of the square
        ChessBoardPosition ptl = new ChessBoardPosition(
                Math.max(chessPieceMoveData.from.rowId, chessPieceMoveData.to.rowId),
                Math.min(chessPieceMoveData.from.colId, chessPieceMoveData.to.colId)
        );

        // The bottom-right point of the square
        ChessBoardPosition pbr = new ChessBoardPosition(
                Math.min(chessPieceMoveData.from.rowId, chessPieceMoveData.to.rowId),
                Math.max(chessPieceMoveData.from.colId, chessPieceMoveData.to.colId)
        );

        // Check, if we really have a case with a square.
        if ((pbr.colId - ptl.colId) == (ptl.rowId - pbr.rowId)) {
            // Column number change direction
            int colDir = (chessPieceMoveData.to.colId - chessPieceMoveData.from.colId) / (pbr.colId - ptl.colId);
            // Row (line) number change direction
            int rowDir = (chessPieceMoveData.from.rowId - chessPieceMoveData.to.rowId) / (pbr.rowId - ptl.rowId);
            // For each point of the diagonal...
            for (int stepCounter = 1; stepCounter <= (pbr.colId - ptl.colId); stepCounter++) {
                // The row number of the next point
                int nextRowId = chessPieceMoveData.from.rowId + (stepCounter * rowDir);
                // The column number of the next point
                int nextColId = chessPieceMoveData.from.colId + (stepCounter * colDir);
                // Build the position of the next point
                ChessBoardPosition chessBoardPosition = new ChessBoardPosition(nextRowId, nextColId);
                // Get the chess piece at the next point
                ChessPiece chessPiece = chessBoard.getPieceAt(chessBoardPosition);
                // Check if the target position is not the start position
                if (!chessBoardPosition.equals(chessPieceMoveData.to)) {
                    // Check if there is any chess piece at the position
                    if (chessPiece != null) {
                        retVal = false;
                        break;
                    }
                }
            }
        } else {
            retVal = false;
        }

        return retVal;
    }
}
