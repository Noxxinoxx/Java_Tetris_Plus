package se.liu.noaan869.LabbTetris;

public interface BoardListener {
	void boardChanged();

	void drawGUI();

	void renderGame();

	void startTetris();

	void disposeFrame();

	void gameOver();

	void addInput();

	void showScoreBoard();

}