///-----------------------------------------------------------------------///
/// @file RookMoveChecker.java                                            ///
/// @brief Contains the implementation of RookMoveChecker  s               ///
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
 * The {@code RookMoveChecker} contains the implementation of the
 * BishopMoveChecker
 *
 * @version 1.0
 */
public class RookMoveChecker {

    private enum MoveMode {
        MOVE_MODE_HORIZONTAL,
        MOVE_MODE_VERTICAL
    }

    private ChessBoard chessBoard;

    public RookMoveChecker(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public boolean canMoveToPosition(ChessPieceMoveData chessPieceMoveData) {
        boolean retVal = true;

        if (chessPieceMoveData.from.rowId == chessPieceMoveData.to.rowId) {
            // changes only in colId -> move horizontal
            retVal = performCheck(MoveMode.MOVE_MODE_HORIZONTAL, chessPieceMoveData);
        } else if (chessPieceMoveData.from.colId == chessPieceMoveData.to.colId) {
            // changes only in rowId -> move vertical
            retVal = performCheck(MoveMode.MOVE_MODE_VERTICAL, chessPieceMoveData);
        } else {
            // Looks like we have changes in rowId as well as in colId -> not able to move
            retVal = false;
        }

        return retVal;
    }

    private boolean performCheck(MoveMode moveMode, ChessPieceMoveData chessPieceMoveData) {
        boolean retVal = true;
        int stepCount;
        int dir;

        if (moveMode == MoveMode.MOVE_MODE_HORIZONTAL) {
            stepCount = Math.abs(chessPieceMoveData.to.colId - chessPieceMoveData.from.colId);
            dir = (chessPieceMoveData.to.colId - chessPieceMoveData.from.colId) / stepCount;
        } else {
            stepCount = Math.abs(chessPieceMoveData.to.rowId - chessPieceMoveData.from.rowId);
            dir = (chessPieceMoveData.to.rowId - chessPieceMoveData.from.rowId) / stepCount;
        }

        for (int stepCounter = 1; stepCounter <= stepCount; stepCounter++) {
            ChessBoardPosition chessBoardPosition;

            if (moveMode == MoveMode.MOVE_MODE_HORIZONTAL) {
                int nextRowId = chessPieceMoveData.from.colId + (stepCounter * dir);
                chessBoardPosition = new ChessBoardPosition(nextRowId, chessPieceMoveData.to.colId);
            } else {
                int nextColId = chessPieceMoveData.from.rowId + (stepCounter * dir);
                chessBoardPosition = new ChessBoardPosition(chessPieceMoveData.to.rowId, nextColId);
            }

            ChessPiece chessPiece = chessBoard.getPieceAt(chessBoardPosition);
            if (!chessBoardPosition.equals(chessPieceMoveData.to)) {
                if (chessPiece != null) {
                    retVal = false;
                    break;
                }
            }
        }

        return retVal;
    }

}
