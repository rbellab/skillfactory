///-----------------------------------------------------------------------///
/// @file Pawn.java                                                       ///
/// @brief Contains the implementation of Pawn chess piece                ///
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
 * The {@code Pawn} contains the implementation of the Pawn chess piece
 *
 * @version 1.0
 */
public class Pawn extends ChessPiece {

    private class PawnBaseData {
        public int startRowId;
        public int dir;

        public  PawnBaseData(ChessColor chessColor) {
            if (chessColor == ChessColor.CC_WHITE) {
                this.startRowId = 1;
                this.dir = 1;
            } else {
                this.startRowId = 6;
                this.dir = -1;
            }
        }

        private PawnBaseData() {}
    }

    public Pawn(ChessColor chessColor) {
        super(chessColor);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, ChessPieceMoveData chessPieceMoveData) {
        boolean retVal = super.canMoveToPosition(chessBoard, chessPieceMoveData);

        if (wantToEatAnotherPiece(chessPieceMoveData)) {
            if ((Math.abs(chessPieceMoveData.from.colId - chessPieceMoveData.to.colId) == 1) &&
                (Math.abs(chessPieceMoveData.from.rowId - chessPieceMoveData.to.rowId) == 1)) {
                ChessPiece chessPieceToBeEaten = chessBoard.getPieceAt(chessPieceMoveData.to);
                if ((null == chessPieceToBeEaten) || (chessPieceToBeEaten.getColor().equals(this.getColor()))) {
                    // There is no piece at this position or a piece with the same color -> not able to move!
                    retVal = false;
                }
            }
        } else {
            PawnBaseData pawnBaseData = new PawnBaseData(this.getColor());
            //check direction first
            if (chessPieceMoveData.from.rowId + pawnBaseData.dir == chessPieceMoveData.to.rowId) {
                retVal = chessBoard.getPieceAt(chessPieceMoveData.to) == null;
            } if ((chessPieceMoveData.from.rowId == pawnBaseData.startRowId) &&
                  (chessPieceMoveData.from.rowId + 2 * pawnBaseData.startRowId == chessPieceMoveData.to.rowId)) {
                retVal = (chessBoard.getPieceAt(chessPieceMoveData.to) == null);
                ChessBoardPosition chessBoardPosition = new ChessBoardPosition(chessPieceMoveData.from.rowId
                   + pawnBaseData.dir, chessPieceMoveData.from.colId);
                retVal &= chessBoard.getPieceAt(chessBoardPosition) == null;
            }

        }
        return retVal;
    }

    private boolean wantToEatAnotherPiece(ChessPieceMoveData chessPieceMoveData) {
        return (chessPieceMoveData.from.colId != chessPieceMoveData.to.colId);
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
