package se.liu.noaan869.LabbTetris;

public class DefaultCollision implements CollisionHandler {

    @Override public boolean hasCollision(Board board) {
        //see if there is an active falling tetromino.

        final int offset = 2;
        if (board.getFalling() != null) {
            //loop over the poly that is falling
            for (int i = 0; i < board.getFalling().getLength(); i++) {
                for (int j = 0; j < board.getFalling().getLength(); j++) {
                    //see if the poly is over not over an empty place.
                    if (board.getFalling().getType(j, i) != SquareType.EMPTY && board.getSquareType(j + board.getFallingX(), i + board.getFallingY() + offset) != SquareType.EMPTY) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "default";
    }

}
