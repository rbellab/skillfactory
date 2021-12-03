///-----------------------------------------------------------------------///
/// @file ChessGame.java                                                  ///
/// @brief Contains the implementation of chess game                      ///
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

import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The {@code ChessGame} contains the implementation of the chess game
 *
 * @version 1.0
 */
public class ChessGame {
    private final int ROW_PART_ID    = 1;
    private final int COL_PART_ID    = 2;
    private final int TO_ROW_PART_ID = 3;
    private final int TO_COL_PART_ID = 4;

    private boolean isRunning;
    private ChessBoard chessBoard;

    /**
     * @brief Program entry point
     * @param args - Currently not used; */
    public static void main(String[] args) {
        // Get instance of the chess game object
        ChessGame game = ChessGame.getInstance();
        try {
            // Run the game
            game.run();
        } catch (Exception e) {
            // Print error, if something went wrong
            System.err.println(e);
        }
    }

    /**
     * @brief Runs the chess game
     */
    public void run() throws InterruptedException {
        // Specifies if the game is still running
        this.isRunning = true;
        chessBoard.printBoard();
        while (this.isRunning) {
            // Get raw user input
            String rawUserInput = getUserInput();
            // Decode command from the raw user input
            String userCommand = rawUserInput.contains("MOVE") ? "MOVE" : rawUserInput;
            // Handle user commands
            switch (userCommand) {
                case "EXIT"      : { handleExit();              break; }
                case "REPLAY"    : { handleReplay();            break; }
                case "MOVE"      : { handleMove(rawUserInput);  break; }
                case "CASTLING0" : { handleCastling(0); break; }
                case "CASTLING7" : { handleCastling(7); break; }
                default:           { handleDefault();           break; }
            }
            TimeUnit.SECONDS.sleep(1); // Give the stderr a chance to print the error message if any
         }
    }

    /**
     * @brief The "castling" command handler
     * @param catlingId - the id (0 - for cadtling0 and 1 for castling7)
     */
    private void handleCastling(int catlingId) {
        if (chessBoard.castling(catlingId)) {
            System.out.println("Castling succeeded");
            chessBoard.printBoard();
        } else {
            System.err.println("Castling failed");
        }
    }

    /**
     * @brief The "move" command handler
     * @param rawUserInput - the raw user input, containing the "move" command
     */
    private void handleMove(String rawUserInput) {
        // Split the raw user input
        String[] inputParts = rawUserInput.split(" ");
        try {
            // Create move data object
            ChessPieceMoveData moveData = new ChessPieceMoveData(
                Integer.parseInt(inputParts[ROW_PART_ID]),
                Integer.parseInt(inputParts[COL_PART_ID]),
                Integer.parseInt(inputParts[TO_ROW_PART_ID]),
                Integer.parseInt(inputParts[TO_COL_PART_ID])
            );
            ChessPiece chessPiece = chessBoard.getPieceAt(moveData.from);
            // Try to move the chess piece
            if (chessBoard.moveToPosition(moveData)) {
                if (null != chessPiece) {
                    // Success. Print the chess board again.
                    System.out.println(getSuccessInfoString(chessPiece, moveData));
                    chessBoard.printBoard();
                }
            } else {
                // Failure. Print error.
                System.err.println(getFailureInfoString(chessPiece, moveData));
            }
        } catch (Exception e) {
            System.err.println("Uups! Something went wrong! Please try again!\r\nERROR: " + e);
        }
    }

    /**
     * @brief Builds the info message for case "succeeded".
     * @param chessPiece - the chess piece, which has to be moved
     * @param moveData - the move data object
     * @return The info message string
     */
    private String getSuccessInfoString(ChessPiece chessPiece, ChessPieceMoveData moveData) {
        StringBuilder sb = new StringBuilder(chessPiece.getName() + " successfully moved from [");
        sb.append(moveData.from.rowId);
        sb.append(", ");
        sb.append(moveData.from.colId);
        sb.append("] to [");
        sb.append(moveData.to.rowId);
        sb.append(", ");
        sb.append(moveData.to.colId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @brief Builds the info message for case "fails".
     * @param chessPiece - the chess piece, which has to be moved
     * @param moveData - the move data object
     * @return The error message string
     */
    private String getFailureInfoString(ChessPiece chessPiece, ChessPieceMoveData moveData) {
        StringBuilder sb = new StringBuilder("Failed to move " + chessPiece.getName() + " from [");
        sb.append(moveData.from.rowId); sb.append(", "); sb.append(moveData.from.colId);
        sb.append("] to [");
        sb.append(moveData.to.rowId); sb.append(", "); sb.append(moveData.to.colId);
        sb.append("]");
        return sb.toString();
    }

    /**
     * @breif The "replay" command handler
     */
    private void handleReplay() {
        System.out.println("OK, let's play again!");
        chessBoard = createChessBoard();
        chessBoard.printBoard();
    }

    /**
     * @breif The handler for unexpected user input
     */
    private void handleDefault() {
        System.err.println("Unexpected user input! Please try again!");
    }

    /**
     * @brief The handler for "exit" command
     */
    private void handleExit() {
        System.out.println("Are you already going away? It's pity.");
        this.isRunning = false;
    }

    /**
     * @return The raw user input
     */
    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("=> ");
        return scanner.nextLine().toUpperCase(Locale.ROOT);
    }

    /**
     * @brief Private constructor of the ChessGame class.
     *        Don't let anyone from outside to instantiate this class */
    private ChessGame() {
        this.isRunning = false;
        chessBoard = createChessBoard();
    }

    /**
     * @brief Create the chess board and fill it with all the chess pieces
     * @return The chess board with all the chess pieces
     */
    private ChessBoard createChessBoard() {
        // create the board
        chessBoard = new ChessBoard(ChessColor.CC_WHITE);
        // set the pieces for player 1 (white)
        fillChessPieceRows(ChessColor.CC_WHITE);
        // set the pieces for player 2 (black)
        fillChessPieceRows(ChessColor.CC_BLACK);
        return chessBoard;
    }


    /**
     * @brief Fills the board with the chess pieces
     * @param chessColor - desired color of the chess pieces
     */
    private void fillChessPieceRows(ChessColor chessColor) {
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 0 : 7,0, new Rook(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 0 : 7,1, new Horse(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 0 : 7,2, new Bishop(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 0 : 7,3, new Queen(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 0 : 7,4, new King(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 0 : 7,5, new Bishop(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 0 : 7,6, new Horse(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 0 : 7,7, new Rook(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 1 : 6,0, new Pawn(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 1 : 6,1, new Pawn(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 1 : 6,2, new Pawn(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 1 : 6,3, new Pawn(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 1 : 6,4, new Pawn(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 1 : 6,5, new Pawn(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 1 : 6,6, new Pawn(chessColor));
        chessBoard.setPieceAt((chessColor == ChessColor.CC_WHITE) ? 1 : 6,7, new Pawn(chessColor));
    }

    /**
     * @brief The instance holder class for the game object
     */
    public static class ChessInstanceHolder {
        public static final ChessGame CHESS_GAME_INSTANCE = new ChessGame();
    }

    /**
     * @return The instance of the game object
     */
    public static ChessGame getInstance() {
        return ChessInstanceHolder.CHESS_GAME_INSTANCE;
    }
}
