///-----------------------------------------------------------------------///
/// @file ChessBoard.java                                                 ///
/// @brief Contains the implementation of chess board                     ///
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
 * The {@code ChessBoard} contains the implementation of the chess board
 *
 * @version 1.0
 */
public class ChessBoard {
    // Size of the chess board
    public final int BOARD_SIZE = 8;
    // The color of the current player
    private ChessColor nowPlayer;
    // The array of the chess pieces
    private ChessPiece[][] board;

    /**
     * @brief The constructor of the ChessBoard object
     * @param chessColor - the color of the first player
     */
    public ChessBoard(ChessColor chessColor) {
        this.nowPlayer = chessColor;
        this.board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * @brief Returns the chess piece at the given position
     * @param chessBoardPosition - the desired position on the chess board
     * @return Returns the chess piece at the given position if there is
     *         any piece, otherwise null
     */
    public ChessPiece getPieceAt(ChessBoardPosition chessBoardPosition) {
        return this.board[chessBoardPosition.rowId][chessBoardPosition.colId];
    }

    /**
     * @brief Sets the chess piece to the given position
     * @param chessBoardPosition - the desired position on the chess board
     * @param chessPiece - the chess piece which has to be set to the given position
     */
    public void setPieceAt(ChessBoardPosition chessBoardPosition, ChessPiece chessPiece) {
        this.setPieceAt(chessBoardPosition.rowId, chessBoardPosition.colId, chessPiece);
    }

    /**
     * @brief Sets the chess piece to the given position
     * @param row - the row number
     * @param col - the coulumn number
     * @param chessPiece - the chess piece which has to be set to the given position
     */
    public void setPieceAt(int row, int col, ChessPiece chessPiece) {
        this.board[row][col] = chessPiece;
    }

    /**
     * @brief prints the chess board to the console
     */
    public void printBoard() {
        System.out.println("Turn " + ((nowPlayer == ChessColor.CC_WHITE)? "white" : "black"));
        System.out.println();
        System.out.println("Player 2 (Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        for (int row = BOARD_SIZE - 1; row >= 0; row--) {
            System.out.print(row + "\t");
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[row][col].getName() + "\t");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Player 1 (White)");
    }

    /**
     * @brief Specifies if the given position is inside the chess board
     * @param chessBoardPosition - the desired position
     * @return true, if the given position is inside the chess board, otherwise false.
     */
    public boolean isWithinBoard(ChessBoardPosition chessBoardPosition) {
        boolean retVal =  (chessBoardPosition.rowId >= 0) && (chessBoardPosition.rowId < BOARD_SIZE);
        retVal &= (chessBoardPosition.colId >= 0) && (chessBoardPosition.colId < BOARD_SIZE);
        return retVal;
    }

    /**
     * @breif Moves the chess piece from start to target position on the chess board
     * @param chessPieceMoveData
     * @return true, if succeeded, otherwise false.
     */
    public boolean moveToPosition(ChessPieceMoveData chessPieceMoveData) {
        if (false == isWithinBoard(chessPieceMoveData.from)) {
            System.err.println("The start position [" + chessPieceMoveData.from.rowId + ", " + chessPieceMoveData.from.colId + "] is outside of the board!");
            return false;
        }

        if (false == isWithinBoard(chessPieceMoveData.to)) {
            System.err.println("The target position [" + chessPieceMoveData.to.rowId + ", " + chessPieceMoveData.to.colId + "] is outside of the board!");
            return false;
        }

        if (!nowPlayer.equals(getPieceAt(chessPieceMoveData.from).getColor())) {
            System.err.println("You can't move " + getPieceAt(chessPieceMoveData.from).getName() +
                    " because current player is " + ((nowPlayer == ChessColor.CC_WHITE) ? "white!" : "black!"));
            return false;
        }

        ChessPiece chessPiece = this.getPieceAt(chessPieceMoveData.from);

        if (chessPiece.canMoveToPosition(this, chessPieceMoveData)) {
            // check position for castling
            if ("KR".contains(chessPiece.getSymbol())) chessPiece.check = false;
            // move the piece, we moved a piece
            this.setPieceAt(chessPieceMoveData.to, this.getPieceAt(chessPieceMoveData.from));
            // set null to previous cell
            this.setPieceAt(chessPieceMoveData.from, null);
            // switch the current player
            this.nowPlayer = this.nowPlayer.equals(ChessColor.CC_WHITE) ? ChessColor.CC_BLACK : ChessColor.CC_WHITE;
            return true;
        }
        return false;
    }

    /**
     * @brief Performs castling
     * @param castlingId - the ID of the castling operation
     * @return true, if succeeded, otherwise false
     */
    public boolean castling(int castlingId) {
        // ToDo: Implement this method!
        System.err.println("The method castling() is NOT implemented yet!");
        return false;
    }

}
