//------------------------------------------------------------------------///
/// @file King.java                                                       ///
/// @brief Contains the implementation of King chess piece                ///
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
 * The {@code King} contains the implementation of the Horse chess piece
 *
 * @version 1.0
 */
public class King extends ChessPiece {

    /**
     * @brief The constructor of the King object
     * @param chessColor - Specifies the color of the King
     */
    public King(ChessColor chessColor) {
        super(chessColor);
    }

    /**
     * @brief Checks if the King can be moved to the desired position
     * @param chessBoard - the chess board object
     * @param chessPieceMoveData - contains start and target positions
     * @return true, if succeeded, otherwise - false
     */
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, ChessPieceMoveData chessPieceMoveData) {
        boolean retVal = super.canMoveToPosition(chessBoard, chessPieceMoveData);
        if (Math.abs(chessPieceMoveData.from.rowId - chessPieceMoveData.to.rowId) > 1 ||
                Math.abs(chessPieceMoveData.from.colId - chessPieceMoveData.to.colId) > 1) retVal = false;
        if (isUnderAttack(chessBoard, chessPieceMoveData.to)) retVal = false;
        return retVal;
    }

    /**
     * @brief Specifies if the Kind is under attack
     * @param chessBoard - the chess board object
     * @param chessBoardPosition - the position on the chess board
     * @return
     */
    public boolean isUnderAttack(ChessBoard chessBoard, ChessBoardPosition chessBoardPosition) {
        for (int colId = 0; colId < chessBoard.BOARD_SIZE; colId++) {
            for (int rowId = 0; rowId < chessBoard.BOARD_SIZE; rowId++) {
                ChessPiece piece = chessBoard.getPieceAt(new ChessBoardPosition(rowId, colId));
                if (piece != null) {
                    boolean canMove = piece.canMoveToPosition(chessBoard,
                            new ChessPieceMoveData(colId, rowId, chessBoardPosition.rowId, chessBoardPosition.colId));
                    if ((piece.getColor() != this.getColor()) && canMove) return true;
                }
            }
        }
        return false;
    }

    /**
     * @return The symbol of the King -> "K"
     */
    @Override
    public String getSymbol() {
        return "K";
    }
}
