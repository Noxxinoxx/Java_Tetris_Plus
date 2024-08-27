package se.liu.noaan869.LabbTetris;

import javax.swing.*;
import java.awt.*;

public class TetrisViewer {
	
	private  JFrame frame;
	private  GUI imageIcon;
    private  JLabel score;
    private  JButton pause;
    private  JButton end;
    private  JMenuBar bar;

     public void renderGame() {
         frame = new JFrame();
         imageIcon = new GUI();
         bar = new JMenuBar();
         end = new JButton("Avsluta");
         pause = new JButton("Pause");
         score = new JLabel();

        //cat picture
        frame.setLayout(new GridLayout(1,1));
        frame.add(imageIcon);
        frame.setSize(700, 700);
        frame.setVisible(true);


        bar.add(end);
        bar.add(pause);
        bar.add(score);

        frame.setJMenuBar(bar);
        frame.setVisible(true);


    }
    public GUI getImageIcon() {
        return this.imageIcon;
     }
    public JLabel getScore() {
        return this.score;
    }
    public JButton getPause() {
        return this.pause;
    }

    public JButton getEnd() {
        return this.end;
    }
	 
    public JFrame getFrame() {
	    return frame;
    }

    public boolean pauseGame() {return askUserPasued();}

    public boolean showErrorMessage(String error) {
    return askUser(error + " Try again?");
}

    public void showScoreBoard(String score) {
	    JOptionPane.showMessageDialog(null, score);
    }

    public void gameOver() {
	    JOptionPane.showMessageDialog(null, "GameOver!");
    }

    public String nameInput(){
	    return JOptionPane.showInputDialog("What your name?");
    }
     //ask user from old project / lab.
    public boolean shuldQuit(){
    return askUser("Quit?") && askUser("Relly?");
    }
    private boolean askUser(String question){
	    return JOptionPane.showConfirmDialog(null, question,
		    "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
    private boolean askUserPasued(){
        return JOptionPane.showConfirmDialog(null, "Game is Paused. UnPause?",
	    "", JOptionPane.DEFAULT_OPTION) == JOptionPane.YES_OPTION;
    }
}