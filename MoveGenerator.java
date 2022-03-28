import java.util.ArrayList;

public class MoveGenerator {

    public static void generateMovesOpening(Board board) {

    }

    public static ArrayList<Board> generateAdd(Board board) {
        ArrayList<Board> L = new ArrayList<Board>();
        for (int i = 0; i < 21; i++) {
            if (board.get(i) == Piece.EMPTY) {
                Board b = new Board(board.toString()); // copy board
                b.set(i, Piece.WHITE);
                if(MorrisVariant.closeMill(b, i))
                    //TODO: generateRemove()
                    System.out.println("TODO: generateRemove()");
                else
                    L.add(b);
            }
        }
        return null; // placeholder
    }

}
