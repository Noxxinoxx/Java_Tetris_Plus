package se.liu.noaan869.LabbTetris;

import java.util.ArrayList;
import java.util.List;

public class Heavy implements CollisionHandler {

    @Override public boolean hasCollision(Board board) {

        boolean canCollide = false;
        List<Integer> collapseFrom = new ArrayList<>();
        List<Integer> collapsibleColumns = new ArrayList<>();
        int fallingLength = board.getFalling().getLength();

        for(int x = 0; x < fallingLength; x++) {
            for(int y = 0; y < fallingLength; y++) {
                SquareType type = board.getFalling().getType(y, x);
                int fallingX = board.getFallingX();
                int fallingY = board.getFallingY();
                SquareType squareType = board.getSquareAt(fallingX + y, fallingY + x + 2);
                if(type != SquareType.EMPTY && squareType == SquareType.OUTSIDE) {
                    return true;
                }
            }
        }

        for (int x = 0; x < fallingLength; x++) {
            for (int y = 0; y < fallingLength; y++) {
                SquareType type = board.getFalling().getType(y, x);
                int fallingX = board.getFallingX();
                int fallingY = board.getFallingY();
                SquareType squareType = board.getSquareAt(fallingX + y, fallingY + x);
                if (type != SquareType.EMPTY && squareType != SquareType.EMPTY) {
                    int toty = fallingY + x + 2;
                    int totx = fallingX + y;
                    if (board.collapsable(totx, toty)) {
                        collapseFrom.add(toty);
                        collapsibleColumns.add(totx);
                    } else {
                        canCollide = true;
                    }
                }
            }
        }

        if (canCollide) {
            return true;
        }

        for (int i = 0; i < collapsibleColumns.size(); i++) {
            int x = collapsibleColumns.get(i);
            int y = collapseFrom.get(i);
            board.collapseRow(x, y);
        }

        return false;
    }

    public String getDescription() {
        return "heavy";
    }

}