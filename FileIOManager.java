import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class FileIOManager {
    public static Piece[] parseInput(String in) throws FileNotFoundException, IOException {

        File inputFile = new File(in);
        Scanner scanner = new Scanner(inputFile);
        String line = scanner.nextLine();

        scanner.close();
        Piece[] board = new Piece[21];
        char[] charline = line.substring(0, 21).toCharArray();
        for (int i = 0; i < 21; i++) {
            if (charline[i] == 'w' || charline[i] == 'W')
                board[i] = Piece.WHITE;
            else if (charline[i] == 'b' || charline[i] == 'B')
                board[i] = Piece.BLACK;
            else if (charline[i] == 'x' || charline[i] == 'X')
                board[i] = Piece.EMPTY;
            else throw new IOException("Invalid character found in input.");
        }
        return board;
    }
}