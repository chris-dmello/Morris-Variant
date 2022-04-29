import java.io.FileNotFoundException;
import java.io.IOException;

public class RunGame  {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Please provide the correct arguments.");
            return;
        }
        String in = args[0];
        String out = args[1];
        int depth = Integer.parseInt(args[2]);

        System.out.format("Input file  | %s\nOutput File | %s\nDepth       | %d\n", in, out, depth);

        Board board;
        try {
            board = FileIOManager.parseInput(in);
        } catch (FileNotFoundException fnfe) {
            System.out.print("Specified input file could not be found.");
            return;
        } catch (IOException ioe) {
            System.out.println("Input Error: " + ioe.getMessage());
            return;
        }

        ///// TEST HERE /////

        System.out.println(board.toString());

        ///// TEST HERE /////
    }

    ///// TEST FUNCTIONS HERE /////



    ///// TEST FUNCTIONS HERE /////
}