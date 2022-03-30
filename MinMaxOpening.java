import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MinMaxOpening {

    private class MinMaxTreeNode {
        public Board board;
        public int score;
        public ArrayList<Board> children;

        MinMaxTreeNode(Board board){
            this.board = board;
        }
    }

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

        Board output = miniMaxOpening(board, depth, true);
        System.out.println("Board Position: " + output.toString());

    }

    public static Board miniMaxOpening(Board board, int depth, boolean isMax) {
        // Find Best Child and Return it
        if (depth == 0)
            return chooseOpening(MoveGenerator.generateMovesOpening(board), isMax);

        // Find all next moves. These will be the children.
        ArrayList<Board> children = MoveGenerator.generateMovesOpening(board);
        ArrayList<Board> grandchildren = new ArrayList<Board>();
        
        // Fill grandchildren list
        for (int i = 0; i < children.size(); i++) {
            grandchildren.set(i, chooseOpening(MoveGenerator.generateMovesOpening(children.get(i)), !isMax));
        }

        // Select temporary "best" child at first 
        Board bestChild = children.get(0); // TODO: handle null case
        
        // Choose the best board using a for loop.
        for (int i = 0; i < children.size(); i++) {
            
        }

        // Find "best" grandchild
        // Return parent of best gradnchild
        return bestChild;
    }

    public static Board chooseOpening(ArrayList<Board> L, boolean isMax) {
        if (L.size() == 0)
            return null;
        Board chosen = L.get(0);
        int chosen_heuristic_score = StaticEstimator.Opening(chosen.countWhite(), chosen.countBlack());

        for (int i = 0; i < L.size(); i++) {
            int i_heuristic_score = StaticEstimator.Opening(L.get(i).countWhite(), L.get(i).countBlack());
            if (isMax & i_heuristic_score > chosen_heuristic_score) {
                chosen = L.get(i);
                chosen_heuristic_score = i_heuristic_score;
            } else if (!isMax & i_heuristic_score < chosen_heuristic_score) {
                chosen = L.get(i);
                chosen_heuristic_score = i_heuristic_score;
            }
        }

        return chosen;
    }

    public static int staticEstimate(Board board) {
        return StaticEstimator.Opening(board.countWhite(), board.countBlack());
    }
}
