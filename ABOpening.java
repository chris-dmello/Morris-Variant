import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ABOpening {

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

        System.out.format("Input file  | %s\nOutput File | %s\nDepth       | %d\n\n", in, out, depth);

        // Parse input to Board.
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

        // Call Minimax Function
        Board output = alphaBetaOpening(board, depth);

        // Output board position and stats
        System.out.println("Board Position: " + output.toString());
        System.out.println("Positions evaluated by static estimation: " + ABOpening.count);
        System.out.println("MINIMAX Estimate: " + ABOpening.estimate);
        try {
            FileIOManager.writeOutput(out, output.toString());
        } catch (IOException ioe) {
            System.out.println("Output Error: " + ioe.getMessage());
        }

    }

    public static Board alphaBetaOpening(Board board, int depth) {
        // Handle null cases
        if (board.isEmpty())
            return null;
        if (depth < 1) {
            ABOpening.estimate = staticEstimateOpening(board);
            return board;
        }

        // Generate moves for white
        ArrayList<Board> children = MoveGeneratorWhite.generateMovesOpening(board);

        // If the search depth is 1, just return the "best" child
        if (depth == 1)
            return chooseOpening(children, true);

        // If depth is more than 1, we need to choose the moves whos following moves give us a high score.

        // Select temporary "best" child at first
        Board bestMove = children.get(0);
        int bestMoveScore = findNextMoveAlphaBetaOpening(children.get(0), depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);

        // Find the actual "best" child
        for (int i = 1; i < children.size(); i++) {
            int iScore = findNextMoveAlphaBetaOpening(children.get(i), depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            if (iScore > bestMoveScore) {
                bestMove = children.get(i);
                bestMoveScore = iScore;
            }
        }

        // Save Estimate
        ABOpening.estimate = bestMoveScore;

        // Return "best" child
        return bestMove;
    }

    // Return the highest heuristic score for the given depth.
    public static int findNextMoveAlphaBetaOpening(Board board, int depth, int alpha, int beta, boolean isMax) {
        // Find possible next moves and store as children. (depending on isMax)
        ArrayList<Board> children = isMax ? MoveGeneratorWhite.generateMovesOpening(board) : MoveGeneratorBlack.generateMovesOpening(board);

        // Depending on isMax, either choose an initial heuristic score of "infinity" or "negative infinity"
        int heuristic_score = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // Base case: Return the highest/lowest score generated by any child.
        if (depth <= 1) {
            for (int i = 0; i < children.size(); i++) {
                int i_heuristic_score = staticEstimateOpening(children.get(i));

                // Replace heuristic score if a higher/lower one if found depending on ply.
                if (isMax) {
                    if (heuristic_score >= beta)
                        break;
                    heuristic_score = heuristic_score > i_heuristic_score ? heuristic_score : i_heuristic_score;
                    alpha = alpha > heuristic_score ? alpha : heuristic_score;
                } else {
                    if (heuristic_score <= alpha)
                        break;
                    heuristic_score = heuristic_score < i_heuristic_score ? heuristic_score : i_heuristic_score;
                    beta = beta < heuristic_score ? beta : heuristic_score;
                }
            }

            return heuristic_score;
        }

        // Recursive Case: Recurse over input, reduce depth and flip isMax
        for (int i = 0; i < children.size(); i++) {
            int i_heuristic_score = findNextMoveAlphaBetaOpening(children.get(i), depth - 1, alpha, beta, !isMax);

            if (isMax) {
                if (heuristic_score >= beta)
                    break;
                heuristic_score = heuristic_score > i_heuristic_score ? heuristic_score : i_heuristic_score;
                alpha = alpha > heuristic_score ? alpha : heuristic_score;
            } else {
                if (heuristic_score <= alpha)
                    break;
                heuristic_score = heuristic_score < i_heuristic_score ? heuristic_score : i_heuristic_score;
                beta = beta < heuristic_score ? beta : heuristic_score;
            }
        }

        return heuristic_score;
    }

    public static Board chooseOpening(ArrayList<Board> L, boolean isMax) {
        // Handle null case
        if (L.size() == 0)
            return null;

        // Select temporary "best" move at first
        Board chosen = L.get(0);
        int chosen_heuristic_score = staticEstimateOpening(chosen);

        // Find the actual "best" move
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
        // Increment position evaluation count
        ABOpening.count++;
        return StaticEstimator.Opening(board.countWhite(), board.countBlack());
    }
}
