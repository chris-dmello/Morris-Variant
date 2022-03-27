public class StaticEstimator {
	static int MidgameEndgame(int numWhitePieces, int numBlackPieces, int numBlackMoves) {
		if(numBlackPieces <= 2)
			return 10000;
		else if (numWhitePieces <= 2)
			return -10000;
		else if (numBlackMoves == 0)
			return 10000;
		return (1000 * (numWhitePieces-numBlackPieces)) - numBlackMoves;
	}

	static int Opening(int numWhitePieces, int numBlackPieces) {
		return numWhitePieces-numBlackPieces;
	}
}