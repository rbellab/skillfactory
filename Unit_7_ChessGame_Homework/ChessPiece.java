///-----------------------------------------------------------------------///
/// @file ChessPiece.java                                                 ///
/// @brief Contains the implementation of ChessPiece                      ///
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
 * The {@code ChessPiece} contains the implementation of the chess piece
 *
 * @version 1.0
 */
public abstract class ChessPiece {
    // The color of the chess piece
    ChessColor chessColor;
    boolean check = true;

    /**
     * @brief The constructor of the chess piece object
     * @param chessColor - the desired color of the chess piece
     */
    public ChessPiece(ChessColor chessColor) {
        this.chessColor = chessColor;
    }

    /**
     * @return the color of the chess piece
     */
    public ChessColor getColor() {
        return this.chessColor;
    }

    /**
     * @return The name of the chess piece
     */
    public String getName() {
        StringBuilder sb = new StringBuilder(this.getSymbol());
        sb.append((this.chessColor == ChessColor.CC_WHITE) ? "w" : "b");
        return sb.toString();
    }

    /**
     * @brief Checks if the chess piece can be moved to the desired position
     * @param chessBoard - the chess board object
     * @param chessPieceMoveData - contains start and target positions
     * @return true, if succeeded, otherwise - false
     */
    public boolean canMoveToPosition(ChessBoard chessBoard, ChessPieceMoveData chessPieceMoveData) {
        // Check if start and target positions are within the chess board
        boolean retVal = chessBoard.isWithinBoard(chessPieceMoveData.from);
        retVal &= chessBoard.isWithinBoard(chessPieceMoveData.to);

        // Check if start and target positions are not the same
        retVal &= (!chessPieceMoveData.from.equals(chessPieceMoveData.to));

        ChessPiece startChessPiece = chessBoard.getPieceAt(chessPieceMoveData.from);
        ChessPiece targetChessPiece = chessBoard.getPieceAt(chessPieceMoveData.to);

        // Do we have a piece at the start position?
        if (null != startChessPiece) {
            // Do we have some piece at the target position?
            if (null != targetChessPiece) {
                // Are the pieces of the same color?
                if (startChessPiece.getColor() == targetChessPiece.getColor()) {
                    // We're not able to move if at the target position is a piece
                    // of the same color
                    retVal = false;
                }
            }
        } else {
            // We're not able to move an "empty" board position
            retVal = false;
        }

        return retVal;
    }

    /**
     * @return The symbol of the chess piece
     */
    public abstract String getSymbol();
}
