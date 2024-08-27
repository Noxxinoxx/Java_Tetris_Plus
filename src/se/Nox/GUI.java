package se.liu.noaan869.LabbTetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class GUI extends JComponent{

    //change me to the path to the cat.jpg file.
    public final ImageIcon icon = new ImageIcon("/home/nox/Desktop/tddd78-labbar-2023-u1-g10-07-main/resources/images/cat.jpg");

    public void paintComponent(final Graphics g) {
       final Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(	RenderingHints.KEY_ANTIALIASING,
			 RenderingHints.VALUE_ANTIALIAS_ON);

        icon.paintIcon(this, g, 50, 50);
    }
	
}