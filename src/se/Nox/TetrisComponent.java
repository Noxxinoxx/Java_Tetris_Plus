package se.liu.noaan869.LabbTetris;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;


public class TetrisComponent extends JComponent implements BoardListener {

    private final Board board;

    private final TetrisViewer tetrisViewer =  new TetrisViewer();
    private static final int blockSize = 20;

    private HighscoreList highscoreList = new HighscoreList();

    public TetrisComponent(Board board) {
        this.board = board;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void boardChanged(){
        repaint();
    }

    public void drawGUI() {
        this.tetrisViewer.getEnd().addActionListener(event -> {
            if(this.tetrisViewer.shuldQuit()){
                System.exit(0);
            }
        });
        this.tetrisViewer.getPause().addActionListener(event -> {
            this.board.setPausedGame(true);
            boolean value = this.tetrisViewer.pauseGame();
            if(value) {
                this.board.setPausedGame(false);
            }
        });
    }

    public void renderGame() {
        this.tetrisViewer.renderGame();
    }

    public void startTetris() {
        tetrisViewer.getScore().setText("Score: " + this.board.getScore());
        JFrame frame = tetrisViewer.getFrame();
        Input input = new Input(this.board,frame);
        frame.remove(tetrisViewer.getImageIcon());
        frame.add(this, BorderLayout.CENTER);
        frame.setSize(getPreferredSize());
    }


    public void disposeFrame() {
        tetrisViewer.getFrame().dispose();
    }

    public void gameOver() {
        tetrisViewer.gameOver();
    }

    public void addInput() {
        String name = tetrisViewer.nameInput();
        System.out.println(name);

        Highscore highscore = new Highscore(name, this.board.getScore());
        highscoreList.add(highscore);
    }

    public void showScoreBoard() {
        tetrisViewer.showScoreBoard(highscoreList.showList());
    }



    public Dimension getPreferredSize() {
        int dx = ((board.getWidth()-3)*blockSize)-4;
        int dy = ((board.getHeight()-1)*blockSize)+4;
        return new Dimension(dx, dy);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        HashMap<SquareType, Color> colourMap = new HashMap<>();
        colourMap.put(SquareType.EMPTY, Color.WHITE);
        colourMap.put(SquareType.O, Color.YELLOW);
        colourMap.put(SquareType.Z, Color.RED);
        colourMap.put(SquareType.L, Color.ORANGE);
        colourMap.put(SquareType.T, Color.MAGENTA);
        colourMap.put(SquareType.S, Color.GREEN);
        colourMap.put(SquareType.J, Color.BLUE);
        colourMap.put(SquareType.I, Color.CYAN);
        colourMap.put(SquareType.OUTSIDE, Color.BLACK);
        

        final int offset = 2;

        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                SquareType type = board.getSquareAt(j, i);

                g2d.setColor(colourMap.get(type));
                
                g2d.fillRect((j - offset)*blockSize, (i - offset)*blockSize, blockSize, blockSize);

            }

        }

    }



}