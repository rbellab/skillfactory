public class Queen extends ChessPiece {
    public Queen(ChessColor chessColor) {
        super(chessColor);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, ChessPieceMoveData chessPieceMoveData) {
        boolean retVal = super.canMoveToPosition(chessBoard, chessPieceMoveData);

        if ((chessPieceMoveData.from.rowId == chessPieceMoveData.to.rowId) ||
                (chessPieceMoveData.from.colId == chessPieceMoveData.to.colId)) {
            // only rowId or colId are changed -> move like a rook!
            RookMoveChecker rookMoveChecker = new RookMoveChecker(chessBoard);
            retVal &= rookMoveChecker.canMoveToPosition(chessPieceMoveData);
        } else {
            // colId and rowId are changed -> move like a bishop!
            BishopMoveChecker bishopMoveChecker = new BishopMoveChecker(chessBoard);
            retVal &= bishopMoveChecker.canMoveToPosition(chessPieceMoveData);
        }
        return retVal;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
