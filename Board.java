import java.io.IOException;
import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> positions = new ArrayList<Piece>();

    Board(String line) throws IOException {
        char[] charline = line.substring(0, 21).toCharArray();

        for (int i = 0; i < 21; i++) {
            if (charline[i] == 'w' || charline[i] == 'W')
                positions.add(Piece.WHITE);
            else if (charline[i] == 'b' || charline[i] == 'B')
                positions.add(Piece.BLACK);
            else if (charline[i] == 'x' || charline[i] == 'X')
                positions.add(Piece.EMPTY);
            else
                throw new IOException("Invalid character found in input.");
        }
    }

    public String toString() {
        String stringForm = "";

        for (int i = 0; i < 21; i++) {
            switch (positions.get(0)) {
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
            positions.remove(0);
        }

        return stringForm;
    }

    public Piece get(int i) {
        return positions.get(i);
    }

    public Piece set(int i, Piece piece) {
        return positions.set(i, piece);
    }
}