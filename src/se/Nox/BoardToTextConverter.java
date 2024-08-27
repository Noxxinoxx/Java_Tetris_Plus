package se.liu.noaan869.LabbTetris;

public class BoardToTextConverter {
	//converts SquareType board to String board.
	


	
	
	public String convertToText(Board b) {
		
		StringBuilder board = new StringBuilder();
		for(int y = 0; y < b.getHeight(); y++) {
			board.append("\n");
			for(int x = 0; x < b.getWidth(); x++) {
				
				SquareType witchType = b.getSquareType(x, y);
				switch (witchType) {
					case EMPTY -> board.append("-,");
					case I -> board.append("#,");
					case O -> board.append("!,");
					case T -> board.append("?,");
					case S -> board.append("+,");
					case Z -> board.append("�,");
					case J -> board.append("%,");
					case L -> board.append("&,");
					case OUTSIDE -> board.append("t,");
				}
			}
		}
		return board.toString();
	}
}
