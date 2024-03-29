import java.util.Arrays;

public class MorrisVariant {
    /**
     * <h2>neighbours(int j)</h2>
     * 
     * @param j a location in the array representing the board
     * @return
     */
    public static int[] neighbours(int j) {
        switch (j) {
            case 0:
                return new int[] { 1, 2, 6 };
            case 1:
                return new int[] { 0, 3, 11 };
            case 2:
                return new int[] { 0, 3, 4, 7 };
            case 3:
                return new int[] { 1, 2, 5, 10 };
            case 4:
                return new int[] { 2, 5, 8 };
            case 5:
                return new int[] { 3, 4, 9 };
            case 6:
                return new int[] { 0, 7, 18 };
            case 7:
                return new int[] { 2, 6, 8, 15 };
            case 8:
                return new int[] { 4, 7, 12 };
            case 9:
                return new int[] { 5, 10, 14 };
            case 10:
                return new int[] { 3, 9, 11, 17 };
            case 11:
                return new int[] { 1, 10, 20 };
            case 12:
                return new int[] { 8, 13, 15 };
            case 13:
                return new int[] { 12, 14, 16 };
            case 14:
                return new int[] { 9, 13, 17 };
            case 15:
                return new int[] { 7, 12, 16, 18 };
            case 16:
                return new int[] { 13, 15, 17, 19 };
            case 17:
                return new int[] { 10, 14, 16, 20 };
            case 18:
                return new int[] { 6, 15, 19 };
            case 19:
                return new int[] { 16, 18, 20 };
            case 20:
                return new int[] { 11, 17, 19 };
            default:
                return new int[] {};
        }
    }

    public static boolean contains(final int[] positions, final int key) {
        return Arrays.stream(positions).anyMatch(i -> i == key);
    }

    /**
     * <h2>neighbours(int j)</h2>
     * Test for the neighbours helper functions.
     * 
     * @return <b>boolean </b> True if no contradictions, false if contradictions
     *         found in positions data.
     */
    public static boolean testNeighbours() {
        for (int i = 0; i < 21; i++) {
            for (int position : neighbours(i)) {
                if (!contains(neighbours(position), i)) {
                    System.out.println("Contradiction at positions " + position + " and " + i);
                    return false;
                }
            }
        }
        for (int i = 0; i < 21; i++) {
            if (contains(neighbours(i), i)) {
                System.out.println("Contradiction, position " + i + " is a neighbour of itself");
                return false;
            }
        }
        return true;
    }

    /**
     * <h2>closeMill(Piece[] board, int j)</h2>
     * 
     * @param board the Piece array representing the board
     * @param j     a location in the array representing the board
     * @return
     */
    public static boolean closeMill(Board board, int j) throws IllegalArgumentException {
        Piece C = board.get(j);
        if (C == Piece.EMPTY)
            throw new IllegalArgumentException("Position cannot be empty.");
        switch (j) {
            case 0:
                if (board.get(2) == C & board.get(4) == C)
                    return true;
                if (board.get(6) == C & board.get(18) == C)
                    return true;
                return false;
            case 1:
                if (board.get(3) == C & board.get(5) == C)
                    return true;
                if (board.get(11) == C & board.get(20) == C)
                    return true;
                return false;
            case 2:
                if (board.get(0) == C & board.get(4) == C)
                    return true;
                if (board.get(7) == C & board.get(15) == C)
                    return true;
                return false;
            case 3:
                if ((board.get(1) == C & board.get(5) == C)
                        || (board.get(10) == C & board.get(17) == C))
                    return true;
                return false;
            case 4:
                if ((board.get(2) == C & board.get(0) == C)
                        || (board.get(8) == C & board.get(12) == C))
                    return true;
                return false;
            case 5:
                if ((board.get(3) == C & board.get(1) == C)
                        || (board.get(9) == C & board.get(14) == C))
                    return true;
                return false;
            case 6:
                if ((board.get(0) == C & board.get(18) == C)
                        || (board.get(7) == C & board.get(8) == C))
                    return true;
                return false;
            case 7:
                if ((board.get(6) == C & board.get(8) == C)
                        || (board.get(2) == C & board.get(15) == C))
                    return true;
                return false;
            case 8:
                if ((board.get(7) == C & board.get(6) == C)
                        || (board.get(4) == C & board.get(12) == C))
                    return true;
                return false;

            case 9:
                if ((board.get(5) == C & board.get(14) == C)
                        || (board.get(10) == C & board.get(11) == C))
                    return true;
                return false;
            case 10:
                if ((board.get(9) == C & board.get(11) == C)
                        || (board.get(3) == C & board.get(17) == C))
                    return true;
                return false;
            case 11:
                if ((board.get(10) == C & board.get(9) == C)
                        || (board.get(1) == C & board.get(20) == C))
                    return true;
                return false;
            case 12:
                if ((board.get(15) == C & board.get(18) == C)
                        || (board.get(13) == C & board.get(14) == C)
                        || (board.get(8) == C & board.get(4) == C))
                    return true;
                return false;
            case 13:
                if ((board.get(12) == C & board.get(14) == C)
                        || (board.get(16) == C & board.get(19) == C))
                    return true;
                return false;
            case 14:
                if ((board.get(9) == C & board.get(5) == C)
                        || (board.get(17) == C & board.get(20) == C)
                        || (board.get(13) == C & board.get(12) == C))
                    return true;
                return false;
            case 15:
                if ((board.get(12) == C & board.get(18) == C)
                        || (board.get(7) == C & board.get(2) == C)
                        || (board.get(16) == C & board.get(17) == C))
                    return true;
                return false;
            case 16:
                if ((board.get(15) == C & board.get(17) == C)
                        || (board.get(13) == C & board.get(19) == C))
                    return true;
                return false;
            case 17:
                if ((board.get(14) == C & board.get(20) == C)
                        || (board.get(10) == C & board.get(3) == C)
                        || (board.get(16) == C & board.get(15) == C))
                    return true;
                return false;
            case 18:
                if ((board.get(6) == C & board.get(0) == C)
                        || (board.get(15) == C & board.get(12) == C)
                        || (board.get(19) == C & board.get(20) == C))
                    return true;
                return false;
            case 19:
                if ((board.get(18) == C & board.get(20) == C)
                        || (board.get(16) == C & board.get(13) == C))
                    return true;
                return false;
            case 20:
                if ((board.get(11) == C & board.get(1) == C)
                        || (board.get(17) == C & board.get(14) == C)
                        || (board.get(19) == C & board.get(18) == C))
                    return true;
                return false;

            default:
                break;
        }
        return false;
    }

    /**
     * <h2>main()</h2>
     * Only to be run to run tests
     */
    public static void main(String[] args) {
        // Call tests
        testNeighbours();
    }
}
