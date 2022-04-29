public class StaticEstimatorTournament {
    static int MidgameEndgame(Board board) {

        Board b = new Board(board.toString());
        int numBlackMoves = MoveGeneratorBlack.generateMovesMidgameEndgame(b).size();
        int numWhitePieces = b.countWhite();
        int numBlackPieces = b.countBlack();
        int netBlocked = countBlocked(b, Piece.BLACK) - countBlocked(b, Piece.WHITE);

        int closedWhiteMills = countClosedMills(b, Piece.WHITE);
        int closedBlackMills = countClosedMills(b, Piece.BLACK);

        int netTopAndTopCenter = topAndTopCenterRows(board, Piece.WHITE) - topAndTopCenterRows(board, Piece.BLACK);
        int bottomRows = bottomRows(board, Piece.WHITE) - bottomRows(board, Piece.BLACK);

        if (numBlackPieces <= 2)
            return 10000;
        else if (numWhitePieces <= 2)
            return -10000;
        else if (numBlackMoves == 0)
            return 10000;
        return (75 * (numWhitePieces - numBlackPieces))
                + 40 * netTopAndTopCenter
                - 20 * bottomRows
                + (20 * (closedWhiteMills - closedBlackMills))
                + (20 * netBlocked)
                - 10 *numBlackMoves;
    }

    static int Opening(Board board) {
        Board b = new Board(board.toString());
        int closedWhiteMills = countClosedMills(b, Piece.WHITE);
        int closedBlackMills = countClosedMills(b, Piece.BLACK);

        int netBlocked = countBlocked(b, Piece.BLACK) - countBlocked(b, Piece.WHITE);
        return 100 * (b.countWhite() - b.countBlack()) + 50 * netBlocked + 10 * (closedWhiteMills - closedBlackMills); // + neighbours.length;
    }

    static int countBlocked(Board board, Piece whiteOrBlack) {
        Piece opponent = whiteOrBlack == Piece.WHITE ? Piece.BLACK : Piece.WHITE;
        int blocked = 0;
        for (int i = 0; i < 21; i++) {
            if (board.get(i) == whiteOrBlack) {
                boolean openingFound = false;
                for (int n : MorrisVariant.neighbours(i)) {
                    if (board.get(n) != opponent)
                        openingFound = true;
                }
                if (!openingFound)
                    blocked++;
            }
        }
        return blocked;
    }

    public static int countClosedMills(Board board, Piece whiteOrBlack) {
        int closed = 0;
        for (int i = 0; i < 21; i++) {
            boolean closedMill = false;
            try {
                closedMill = MorrisVariant.closeMill(board, i);
            } catch (IllegalArgumentException iae) {
                // Ignore
            }
            if (board.get(i) == whiteOrBlack & closedMill) {
                closed++;
            }
        }
        return closed;
    }

    public static int topAndTopCenterRows(Board board, Piece whiteOrBlack) {
        int topAndTopCenter = 0;
        for (int i = 12; i <= 14; i++) {
            if (board.get(i) == whiteOrBlack)
                topAndTopCenter++;
        }
        for (int i = 18; i <= 20; i++) {
            if (board.get(i) == whiteOrBlack)
                topAndTopCenter++;
        }
        return topAndTopCenter;
    }

    public static int bottomRows(Board board, Piece whiteOrBlack) {
        int bottomRows = 0;
        
        for (int i = 0; i <= 3; i++) {
            if (board.get(i) == whiteOrBlack)
            bottomRows++;
        }
        return bottomRows;
    }
}