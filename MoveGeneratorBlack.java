import java.util.ArrayList;

public class MoveGeneratorBlack {

    public static ArrayList<Board> generateMovesOpening(Board board) {
        return generateAdd(board);
    }

    public static ArrayList<Board> generateAdd(Board board) {
        ArrayList<Board> L = new ArrayList<Board>();
        for (int i = 0; i < 21; i++) {
            if (board.get(i) == Piece.EMPTY) {
                Board b = new Board(board.toString()); // Copy board
                b.set(i, Piece.BLACK);
                if (MorrisVariant.closeMill(b, i))
                    generateRemove(b, L);
                else
                    L.add(b);
            }
        }
        return L;
    }

    public static void generateRemove(Board board, ArrayList<Board> L) {
        boolean added = false;
        for (int i = 0; i < 21; i++) {
            if (board.get(i) == Piece.WHITE) {
                if (!MorrisVariant.closeMill(board, i)) {
                    Board b = new Board(board.toString()); // Copy board
                    b.set(i, Piece.EMPTY);
                    L.add(b);
                    added = true;
                }
            }
        }
        if (!added) L.add(new Board(board.toString()));

    }

    public static ArrayList<Board> generateMovesMidgameEndgame(Board board) {
        // Find number of Black pieces on the board
        int numBlack = 0;
        for (int i = 0; i < 21; i++) {
            if (board.get(i) == Piece.BLACK)
                numBlack++;
        }

        // Begin hopping if the board has exactly three black pieces left
        if (numBlack == 3)
            return generateHopping(board);

        // Continue with regular moves if the board does not have exaclty three black pieces left
        return generateMove(board);
    }

    public static ArrayList<Board> generateHopping(Board board) {
        ArrayList<Board> L = new ArrayList<Board>();
        for (int alpha = 0; alpha < 21; alpha++) {
            if (board.get(alpha) == Piece.BLACK) {
                for (int beta = 0; beta < 21; beta++) {
                    if (board.get(beta) == Piece.EMPTY) {
                        Board b = new Board(board.toString()); // Copy board
                        b.set(alpha, Piece.EMPTY);
                        b.set(beta, Piece.BLACK);
                        if (MorrisVariant.closeMill(b, beta))
                            generateRemove(b, L);
                        else
                            L.add(b);
                    }
                }
            }
        }
        return L;
    }

    public static ArrayList<Board> generateMove(Board board) {
        ArrayList<Board> L = new ArrayList<Board>();

        for (int i = 0; i < 21; i++) {
            if (board.get(i) == Piece.BLACK) {
                int[] neighbours = MorrisVariant.neighbours(i);
                for (int j : neighbours) {
                    if (board.get(j) == Piece.EMPTY) {
                        Board b = new Board(board.toString()); // Copy board
                        b.set(i, Piece.EMPTY);
                        b.set(j, Piece.BLACK);
                        if (MorrisVariant.closeMill(b, j)) {
                            generateRemove(b, L);
                        } else {
                            L.add(b);
                        }
                    }
                }
            }
        }

        return L;
    }
}
