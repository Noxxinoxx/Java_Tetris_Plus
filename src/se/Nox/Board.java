package se.liu.noaan869.LabbTetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;



public class Board {
    private final SquareType[][] squares;
	private final int width;
	private final int height;
	private boolean gameOver = false;
	private boolean pausedGame = false;
	private Poly falling = null;
	private int fallingX;
	private int fallingY;

    private int speed = 500;
    private final List<BoardListener> listeners = new ArrayList<>();
	
	private int score = 0;



    private final TetrisComponent comp;

    private static final int fallingSpeed = 1;

    private  CollisionHandler collisionHandeler;
    private final FallThrough fallThrough;
    private final Heavy heavy;

    private final DefaultCollision defaultCollision;
	
	public Board(int width, int height) {
	    int widthOffset = 4;
	    this.width = width + widthOffset;
	    int heightOffset = 4;
	    this.height = height + heightOffset;
		    this.squares = new SquareType[this.height][this.width];
	    this.comp = new TetrisComponent(this);
	    this.fallThrough = new FallThrough();
	    this.heavy = new Heavy();
	    this.defaultCollision = new DefaultCollision();
	    this.collisionHandeler = this.defaultCollision;
	    this.addBoardListener(this.comp);
	    startGame();

	}
	private void emptyGamePlan() {
        final int yOffset = 2;
        final int xOffset = 2;
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth() ; x++) {
                if (y < yOffset || (y >= (getHeight() - yOffset) && y <= getHeight()) || x < xOffset ||(x >= (getWidth() - xOffset) && x <= getWidth())) {
                    this.squares[y][x] = SquareType.OUTSIDE;
                } else this.squares[y][x] = SquareType.EMPTY;
            }
        }
    }
	private void removeRow(){
        int rowsDel = 0;
        final int heightDelete = 2;
        final int widthDelete = 2;
		for(int y = 2; y < getHeight() - heightDelete; y++) {
    	   boolean full = true;
    	   for(int x = 2; x < getWidth() - widthDelete; x++) {
    		   if(getSquareAt(x,y) == SquareType.EMPTY) {
    			   full = false;
    			   break;
    		   }
    	   }
    	   if (full){
               for (int j = 2; j < getWidth() - widthDelete; j++) {
            	   moveType(j,y, SquareType.EMPTY);
            	   
               }
               
              moveDownRows(y);
               rowsDel +=  1;
           }
    	   
    	   
       }
        updateScore(rowsDel);
    }
    public boolean collapsable(int col, int collapseFrom) {
        for(int y = collapseFrom; y < height; y++) {
            if(getSquareAt(col, y) == SquareType.EMPTY) {
                return true;
            }
        }
        return false;
    }

    public void collapseRow(int col, int collapseFrom) {

        int firstEmptyPos = 0;

        for(int row = collapseFrom; row < height; row++) {
            if(getSquareAt(col, row) == SquareType.EMPTY) {
                firstEmptyPos = row;
                break;
            }
        }

        for(int row = firstEmptyPos; collapseFrom - 1 < row; row--) {
            setSquaresAt(col, row, getSquareType(col, row - 1));
        }

        //Remove the square which we've collapsed down
        setSquaresAt(col, collapseFrom - 1, SquareType.EMPTY);
    }
	private void updateScore(int rows) {
        final int minRows = 1;
        final int maxRows = 4;
	    if (rows >= minRows && rows <= maxRows) {
		final int[] rowScores = new int[] { 100, 300, 500, 800 };
		score += rowScores[rows - 1];
        }
	}
	
	private void moveDownRows(int rowYPos) {
        final int delYRow = 1;
        final int rowXPos = 2;
        final int moveDown = 2;
		for(int y = rowYPos; y > moveDown ; y--) {
     	   for(int x = 2; x < getWidth() - rowXPos; x++) {
     		   
     		  moveType(x,y, getSquareAt(x, (y - delYRow)));
     		
     	   }
        }
	}
	
    private void addFallingToBoard(){
        for (int i = 0; i < falling.getLength(); i++) {
            for (int j = 0; j < falling.getLength(); j++) {
                if(falling.getType(j,i) != SquareType.EMPTY){
                	setTypeAtPosOffset(j+getFallingX(), i+getFallingY(), falling.getType(j,i));
                }
            }

        }
       
    }
    public void rotate(){
        falling = falling.rotateRight();
    }

    private void setTypeAtPosOffset(int x, int y, SquareType type){
        int yOffset = 1;
        this.squares[y + yOffset][x] = type;
    }

    private void moveType(int x, int y, SquareType type){
        this.squares[y][x] = type;
    }

    public void moveLeft() {
        setFallingX(getFallingX()-fallingSpeed);
        if(hasCollision()) {
            setFallingX(getFallingX() + fallingSpeed);
        }
    }

    public void moveRight() {
        setFallingX(getFallingX()+fallingSpeed);
        if(hasCollision()) {
            setFallingX(getFallingX() - fallingSpeed);
        }
    }

    public void moveDown(){
        if (!hasCollision()){
            setFallingY(getFallingY() + fallingSpeed);
        }
    }

    private void makeFalling() {
    	final int screenDivider = 2;
        removeRow();

        //var tredje är heavy, fall, deafult.
        if(Objects.equals(collisionHandeler, defaultCollision)) {
            collisionHandeler = this.fallThrough;
        }else if(Objects.equals(collisionHandeler, fallThrough)){
            System.out.println(heavy.getDescription());
            collisionHandeler = this.heavy;
        }else if(Objects.equals(collisionHandeler, heavy)) {
            System.out.println(defaultCollision.getDescription());
            collisionHandeler = this.defaultCollision;
        }

    	fallingY = 4;
    	falling = new TeroMaker().getRandomPoly();
        fallingX = getWidth() / screenDivider;
    }
    
    
    
    public void startGame() {

        init();


    }
    
    public void setPausedGame(boolean value) {
    	pausedGame = value;
    }

    public Dimension getPreferredSize(){
        final int baseSize = 20;
        final int offset = 4;
        final int widthOffset = 3;
        final int heightOffset = 1;
        int dX = ((this.getWidth()-widthOffset)* baseSize)-offset;
        int dY = ((this.getHeight()-heightOffset)* baseSize)+offset;
        return new Dimension(dX ,dY);
    }

    public void init() {
        emptyGamePlan();
	notifyRendergame();
	notifyGuiAction();
        //game tick
        final Action doOneStep = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

		notifyStartTetris();
                tick();

            }
        };

        final int delay = 5000;
        final javax.swing.Timer clockTimer = new javax.swing.Timer(speed, doOneStep);
        clockTimer.setCoalesce(true);
        clockTimer.setInitialDelay(delay);
        clockTimer.start();
        //_____________________________________________________//

        //Speed upgrade
        final Action speedAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                final float speedDivider = 1.2f;
                final int speedMax = 50;
                speed /= speedDivider;
                if(speed < speedMax) {
                    speed = speedMax;
                }
                clockTimer.setDelay(speed);
            }
        };

        final int delayTimer = 60000;
        final javax.swing.Timer speedTimer = new javax.swing.Timer(delayTimer, speedAction);

        speedTimer.setCoalesce(true);
        speedTimer.setInitialDelay(delay);
        speedTimer.start();


    }


	public void tick() {


        if(!gameOver) {
        	if(!pausedGame) {
                if (getFalling() == null) {
                    makeFalling();
                    if (hasCollision()) {
                        gameOver = true;
                        //change to game over screen.

			notifyGameOver();

                        
                        //append name and score to hsList also shows the input name panel.
			notifyAddInput();
                    	
                    	
                    	//show the current highscore list
			notifyShowScoreBoard();

                		
                        
                        //dispose frame
			notifyDisposeFrame();
                        
                        //make a task that 5 sec later restarts the game.
                        TimerTask restart = new TimerTask() {
                            public void run() {
                        		//remove cat image after timer init.
                            	new Board(15, 20);
                        		
                            }
                        };

                        java.util.Timer timer = new java.util.Timer("Timer");
                        final long delay = 5000L;
                        timer.schedule(restart, delay);
                        

                    }
                } else if (!hasCollision()) {
                    setFallingY(getFallingY() + fallingSpeed);

                } else {
                    
                    addFallingToBoard();

                    falling = null;

                }

        	}

            notifyListeners();
        }
    }




    public boolean hasCollision() {
	if(getFalling() == null) {
	    return false;
	}

	return collisionHandeler.hasCollision(this);


    }
    public SquareType getSquareAt(int x, int y) {

        if (falling != null && fallingX <= x && x < fallingX + falling.getLength() && y < fallingY + falling.getLength() && fallingY <= y) {
            notifyListeners();
            SquareType type =  falling.getType(x - fallingX,y - fallingY);
            if (type != SquareType.EMPTY){
                return type;
            }
        }
        notifyListeners();
        return getSquareType(x,y);

    }
	
    public String getScore() {
	return Integer.toString(score);
    }


    public List<BoardListener> getBoardListeners() {
	        return listeners;
    }

    public void addBoardListener(BoardListener bl) {
        listeners.add(bl);

    }

    public void removeBoardListener(BoardListener bl) {
        listeners.remove(bl);

    }


    private void notifyAddInput() {listeners.forEach(BoardListener::addInput);}
    private void notifyGameOver() {listeners.forEach(BoardListener::gameOver);}
    private void notifyShowScoreBoard() {listeners.forEach(BoardListener::showScoreBoard);}
    private void notifyDisposeFrame() {listeners.forEach(BoardListener::disposeFrame);}
    private void notifyStartTetris() {listeners.forEach(BoardListener::startTetris);}
    private void notifyListeners() {
        listeners.forEach(BoardListener::boardChanged);
    }

    private void notifyGuiAction() {listeners.forEach(BoardListener::drawGUI);}

    private void notifyRendergame() {listeners.forEach(BoardListener::renderGame);}
    public SquareType getSquareType(int x, int y) {
		return squares[y][x];
	}

    public void setSquaresAt(int x, int y, SquareType type) {
        squares[y][x] = type;
    }
    public void deleteSquaresAt(int x, int y) {
        squares[y][x] = SquareType.EMPTY;
    }

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Poly getFalling() {
		return falling;
	}

	public int getFallingX() {
		return fallingX;
	}


	public void setFallingX(int fallingX) {
		this.fallingX = fallingX;
	}


	public int getFallingY() {
		return fallingY;
	}


	public void setFallingY(int fallingY) {
		this.fallingY = fallingY;
	}
	
	
	
}