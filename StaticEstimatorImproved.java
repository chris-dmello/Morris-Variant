public class StaticEstimatorImproved {
    static int MidgameEndgame(Board board) {

        int numBlackMoves = MoveGeneratorBlack.generateMovesMidgameEndgame(board).size();
        int numWhitePieces = board.countWhite();
        int numBlackPieces = board.countBlack();

        if (numBlackPieces <= 2)
            return 10000;
        else if (numWhitePieces <= 2)
            return -10000;
        else if (numBlackMoves == 0)
            return 10000;
        return (1000 * (numWhitePieces - numBlackPieces)) - numBlackMoves;
    }

    static int Opening(Board board) {
        int numWhitePieces = board.countWhite();
        int neighbours[] = MorrisVariant.neighbours(numWhitePieces);
        return 10 * (board.countWhite() - board.countBlack()) + neighbours.length;
    }
}