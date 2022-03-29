import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class FileIOManager {
    public static Board parseInput(String in) throws FileNotFoundException, IOException {

        File inputFile = new File(in);
        Scanner scanner = new Scanner(inputFile);
        String line = scanner.nextLine();

        scanner.close();
        
        Board board = new Board(line);
        return board;
    }
}