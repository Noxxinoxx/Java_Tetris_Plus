package se.liu.noaan869.LabbTetris;

public class Poly {

	private final SquareType[][] block;
	
	public Poly(SquareType[][] block) {
		this.block = block;
		
	}
	public int getWidthPoly() {
		return block.length;
	}
	public int getHeightPoly() {
		return block[0].length;
	}

	public SquareType[][] getBlock() {
		return block;
	}
	
	public int getLength() {
        return block.length;
    }
	
	public SquareType getType(int y, int x){
		return block[y][x];
	}
	
	public Poly rotateRight() {

        Poly newPoly = new Poly(new SquareType[getLength()][getLength()]);

        for (int r = 0; r < getLength(); r++) {
            for (int c = 0; c < getLength(); c++){
                newPoly.block[c][getLength()-1-r] = this.block[r][c];
            }
        }

        return newPoly;
    }

}