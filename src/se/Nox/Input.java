package se.liu.noaan869.LabbTetris;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class Input {
    private final Board board;
    private final JFrame frame;
    public Input(Board board, JFrame frame) {
        this.board = board;
        this.frame = frame;
        input();
    }

    public class ButtonInput extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {


            if(e.getActionCommand().contains("a")) {
                board.moveLeft();
            }else if(e.getActionCommand().contains("d")) {
                board.moveRight();
            }else if(e.getActionCommand().contains("s")) {
                board.moveDown();
            }else if(e.getActionCommand().contains("w")) {
                board.rotate();
            }
        }
    }
    //keyboard input listens for keyboard strokes and if a key is pressed the action above is called.
    public void input(){
        JComponent pane = frame.getRootPane();

        final InputMap in = pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        final ActionMap act = pane.getActionMap();

        //keyboard listeners.
        in.put(KeyStroke.getKeyStroke("A"), "Left");
        act.put("Left",  new ButtonInput());

        in.put(KeyStroke.getKeyStroke("S"), "Down");
        act.put("Down",  new ButtonInput());

        in.put(KeyStroke.getKeyStroke("D"), "Right");
        act.put("Right",  new ButtonInput());

        in.put(KeyStroke.getKeyStroke("W"), "Rotate");
        act.put("Rotate",  new ButtonInput());


    }


}
