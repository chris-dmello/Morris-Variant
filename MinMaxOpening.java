import java.io.FileNotFoundException;
import java.io.IOException;

public class MinMaxOpening {
    public static void main(String[] args) {
        String in = args[0];
        String out = args[1];
        int depth = Integer.parseInt(args[2]);

        System.out.format("Input file  | %s\nOutput File | %s\nDepth       | %d\n", in, out, depth);
        Piece[] board;
        try {
            board = FileIOManager.parseInput(in);
        } catch (FileNotFoundException fnfe) {
            System.out.print("Specified input file could not be found.");
        } catch (IOException ioe) {
            System.out.println("Input Error: " + ioe.getMessage());
        }
    }
}
