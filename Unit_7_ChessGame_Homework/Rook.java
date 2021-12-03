public class Rook extends ChessPiece {
    public Rook(ChessColor chessColor) {
        super(chessColor);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, ChessPieceMoveData chessPieceMoveData) {
        boolean retVal = super.canMoveToPosition(chessBoard, chessPieceMoveData);
        RookMoveChecker rookMoveChecker = new RookMoveChecker(chessBoard);
        retVal &= rookMoveChecker.canMoveToPosition(chessPieceMoveData);
        return retVal;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
