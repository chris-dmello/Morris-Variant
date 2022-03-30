import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MiniMaxOpening {

    private static int count = 0;
    private static int estimate = 0;

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

        Board output = miniMaxOpening(board, depth);
        System.out.println("Board Position: " + output.toString());
        System.out.println("Positions evaluatied by static estimation: " + MiniMaxOpening.count);
        System.out.println("MINIMAX Estimate: " + MiniMaxOpening.estimate);

    }

    public static Board miniMaxOpening(Board board, int depth) {
        if (board.isEmpty())
            return null;

        ArrayList<Board> children = MoveGeneratorWhite.generateMovesOpening(board);
        if (depth <= 1)
            return chooseOpening(children, true);

        // If depth is more than 1, we need to choose the moves whos following moves
        // give us a high score.

        // Select temporary "best" child at first
        Board bestMove = children.get(0);
        int bestMoveScore = findNextMoveMiniMaxOpening(children.get(0), depth - 1, false);

        for (int i = 1; i < children.size(); i++) {
            int iScore = findNextMoveMiniMaxOpening(children.get(i), depth - 1, false);
            if (iScore < bestMoveScore) {
                bestMove = children.get(i);
                bestMoveScore = iScore;
            }
        }

        MiniMaxOpening.estimate = bestMoveScore; // Save Estimate

        // Return "best" child
        return bestMove;
    }

    // Return the highest heuristic score for the given depth.
    public static int findNextMoveMiniMaxOpening(Board board, int depth, boolean isMax) {
        // Find possible next moves and store as children.
        ArrayList<Board> children = isMax ? MoveGeneratorWhite.generateMovesOpening(board)
                : MoveGeneratorBlack.generateMovesOpening(board);

        int heuristic_score = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // Base case: Return the highest/lowest score generated by any child.
        if (depth <= 1) {
            for (int i = 0; i < children.size(); i++) {
                int i_heuristic_score = staticEstimateOpening(children.get(i));

                // Replace heuristic score if a higher/lower one if found depending on ply.
                if (isMax) {
                    heuristic_score = heuristic_score > i_heuristic_score ? heuristic_score : i_heuristic_score;
                } else
                    heuristic_score = heuristic_score < i_heuristic_score ? heuristic_score : i_heuristic_score;
            }

            return heuristic_score;
        }

        // Recursive Case: Recurse
        for (int i = 0; i < children.size(); i++) {
            int i_heuristic_score = findNextMoveMiniMaxOpening(children.get(i), depth - 1, !isMax);

            if (isMax) {
                heuristic_score = heuristic_score > i_heuristic_score ? heuristic_score : i_heuristic_score;
            } else
                heuristic_score = heuristic_score < i_heuristic_score ? heuristic_score : i_heuristic_score;
        }

        System.out.println(heuristic_score + " at depth " + depth); // TODO: remove
        return heuristic_score;
    }

    public static Board chooseOpening(ArrayList<Board> L, boolean isMax) {
        if (L.size() == 0)
            return null;
        Board chosen = L.get(0);
        int chosen_heuristic_score = staticEstimateOpening(chosen);

        for (int i = 0; i < L.size(); i++) {
            int i_heuristic_score = staticEstimateOpening(L.get(i));
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

    public static int staticEstimateOpening(Board board) {
        MiniMaxOpening.count++;
        return StaticEstimator.Opening(board.countWhite(), board.countBlack());
    }
}
