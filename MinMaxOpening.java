import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MinMaxOpening {

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

        Board output = minMaxOpening(board, depth, true);
        System.out.println("Board Position: " + output.toString());

    }

    public static Board minMaxOpening(Board board, int depth, boolean isMax) {
        if (depth == 0)
            return chooseOpening(MoveGenerator.generateMovesOpening(board), isMax);

        //ArrayList<Board> moves = MoveGenerator.generateMovesOpening(board);
        //MoveGenerator.generateMovesOpening(board);
        

        // return chooseOpening(moves, !isMax);
        return null;

    }

    public static Board chooseOpening(ArrayList<Board> L, boolean max) {
        if (L.size() == 0)
            return null;
        Board chosen = L.get(0);
        int chosen_heuristic_score = StaticEstimator.Opening(chosen.countWhite(), chosen.countBlack());

        for (int i = 0; i < L.size(); i++) {
            int i_heuristic_score = StaticEstimator.Opening(L.get(i).countWhite(), L.get(i).countBlack());
            if (max & i_heuristic_score > chosen_heuristic_score) {
                chosen = L.get(i);
                chosen_heuristic_score = i_heuristic_score;
            } else if (!max & i_heuristic_score < chosen_heuristic_score) {
                chosen = L.get(i);
                chosen_heuristic_score = i_heuristic_score;
            }
        }

        return chosen;
    }
}
