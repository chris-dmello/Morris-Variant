import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> positions = new ArrayList<Piece>();

    Board(String line) {
        char[] charline = line.substring(0, 21).toCharArray();

        for (int i = 0; i < 21; i++) {
            if (charline[i] == 'w' || charline[i] == 'W')
                positions.add(Piece.WHITE);
            else if (charline[i] == 'b' || charline[i] == 'B')
                positions.add(Piece.BLACK);
            else
                positions.add(Piece.EMPTY);
        }
    }

    public String toString() {
        String stringForm = "";

        for (int i = 0; i < 21; i++) {
            switch (positions.get(i)) {
                case WHITE:
                    stringForm += "W";
                    break;
                case BLACK:
                    stringForm += "B";
                    break;
                case EMPTY:
                    stringForm += "x";
                    break;
                default:
                    break;
            }
        }

        return stringForm;
    }

    public Piece get(int i) {
        return positions.get(i);
    }

    public Piece set(int position, Piece piece) {
        return positions.set(position, piece);
    }

    public int countWhite() {
        int count = 0;
        for (int i = 0; i < 21; i++) {
            if (positions.get(i) == Piece.WHITE)
                count++;
        }
        return count;
    }

    public int countBlack() {
        int count = 0;
        for (int i = 0; i < 21; i++) {
            if (positions.get(i) == Piece.BLACK)
                count++;
        }
        return count;
    }

    public boolean isEmpty() {
        if (positions.size() == 0)
            return true;
        return false;
    }
}