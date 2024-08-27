package se.liu.noaan869.LabbTetris;

public class Highscore {
	private final String name;
    private final int score;

    public Highscore(String name, int score){
		this.name = name;
		this.score = score;
    }

    public int getScore(){
    	return score;
    }
    
    public String getName(){
    	return name;
    }

    
}
