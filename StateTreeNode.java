import java.util.ArrayList;

public class StateTreeNode {
    public Board board;
    private int score;
    public ArrayList<StateTreeNode> children;

    StateTreeNode(Board board) {
        this.board = board;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void generateOpeningMoves(boolean forWhite) {

        this.children = new ArrayList<StateTreeNode>();
        if (forWhite) {
            ArrayList<Board> moves = MoveGeneratorWhite.generateMovesOpening(board);

            for (Board next : moves) {
                this.children.add(new StateTreeNode(next));
            }
        } else {
            ArrayList<Board> moves = MoveGeneratorBlack.generateMovesOpening(board);

            for (Board next : moves) {
                this.children.add(new StateTreeNode(next));
            }

        }
    }

    public void generateMidgameEndgameMoves(boolean forWhite) {


        this.children = new ArrayList<StateTreeNode>();
        if (forWhite) {
            ArrayList<Board> moves = MoveGeneratorWhite.generateMovesMidGameEndgame(board);

            for (Board next : moves) {
                this.children.add(new StateTreeNode(next));
            }
        } else {
            ArrayList<Board> moves = MoveGeneratorBlack.generateMovesMidGameEndgame(board);

            for (Board next : moves) {
                this.children.add(new StateTreeNode(next));
            }

        }

    }
}
