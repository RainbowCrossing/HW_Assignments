/*
 * CS 4361.001
 * TMW160530
 * Exercise 1.6 from Textbook
 * Using Java, created the scene of Tetris with a little bit
 * of functionality in the case where the pause will appear
 * whenever the mouse within the boundaries of the main area.
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;

// Main class to create the window to display the scene
// on a set canvas size. Unfortunately was unable to
// properly get the Isotropic mode working and had to
// leave out.
public class Tetris extends Frame {
	public static void main(String[] args) {new Tetris();}
		Tetris(){
			super("Tetris");
			addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {System.exit(0);}
			});	
			setSize(500, 500);
			add("Center", new CvTetris());
			setVisible(true);
		}
}

// Class to create the basic scene of Tetris
class CvTetris extends Canvas {
	Graphics _screen;	// Set Graphics variable.
	boolean paused = false;	// Boolean for pause screen.
	CvTetris(){
	// Mouse adapter to monitor mouse location and movement
	// for use of pause screen.
	MouseAdapter ma = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		// Scene is hard coded for it's location.
		public void mouseMoved(MouseEvent e) {
			//System.out.println("Moved: " + e.getX() + ", " + e.getY());
			if(e.getX() >= 125 && e.getX() <= 275 && e.getY() >= 75 && e.getY() <= 375) {
				if(!paused) {
					repaint();
				}
	    		paused = true;
	    	 }
			else {
				if(paused) {
					repaint();
				}
				paused = false;
			}
		}
		// Mouse event in terms of click event when clicking
		// on the Exit buttton to exit the program.
	    public void mousePressed(MouseEvent e) {
	    	 System.out.println("Clicked: " + e.getX() + ", " + e.getY());
	    	 if(e.getX() >= 300 && e.getX() <= 350 && e.getY() >= 355 && e.getY() <= 375) {
	    		 System.exit(0);
	    	 }
	    }
	 };
	addMouseListener(ma);
	addMouseMotionListener(ma);
	}
	// Paint function to paint on the Tetris pieces,
	// board, outlines, and texts.
	// Colors are filled with fillRect and outlines
	// are placed over the pieces to showcase themselves.
	public void paint(Graphics g) {
		_screen = g;
		// Tetris board.
		// To abide the 10x20 blocks I based the pieces off
		// of a 15x15 pixel block and matched the board to that
		// size.
		g.setColor(Color.black);
		g.drawRect(125, 75, 150, 300);
		
		// Next Piece
		g.setColor(Color.black);
		g.drawRect(300, 80, 100, 50);
		
		// Next Piece Block
		g.setColor(Color.red);
		g.fillRect(325, 105, 15, 15);
		g.setColor(Color.red);
		g.fillRect(340, 105, 15, 15);
		g.setColor(Color.red);
		g.fillRect(355, 105, 15, 15);
		g.setColor(Color.red);
		g.fillRect(355, 90, 15, 15);
		g.setColor(Color.black);
		g.drawRect(325, 105, 15, 15);
		g.setColor(Color.black);
		g.drawRect(340, 105, 15, 15);
		g.setColor(Color.black);
		g.drawRect(355, 105, 15, 15);
		g.setColor(Color.black);
		g.drawRect(355, 90, 15, 15);
		
		// Quit Button
		g.setColor(Color.black);
		g.drawRect(300, 355, 50, 20);
		Font f = new Font ("Arial", Font.BOLD, 12);
		g.setFont (f);
		g.drawString("QUIT", 312, 370);
		
		
		// Pause Box
		// Called forth to show when conditions
		// are met for the mouse to be in the area.
		if(paused) {
			g.setFont (f);
			g.setColor(Color.blue);
			g.drawRect(165, 200, 80, 30);
			f = new Font ("Arial", Font.BOLD, 18);
			g.setFont (f);
			g.drawString("PAUSE", 175, 222);
		}
		
		// Other Texts
		f = new Font ("Arial", Font.BOLD, 14);
		g.setFont (f);
		g.setColor(Color.black);
		g.drawString("Level:", 300, 185);
		g.drawString("Lines:", 300, 215);
		g.drawString("Score:", 300, 245);
		g.drawString("1", 350, 185);
		g.drawString("0", 350, 215);
		g.drawString("0", 350, 245);
		
		// Green Piece
		g.setColor(Color.green);
		g.fillRect(200, 125, 15, 15);
		g.setColor(Color.black);
		g.drawRect(200, 125, 15, 15);
		g.setColor(Color.green);
		g.fillRect(215, 125, 15, 15);
		g.setColor(Color.green);
		g.fillRect(200, 140, 15, 15);
		g.setColor(Color.green);
		g.fillRect(215, 140, 15, 15);
		g.setColor(Color.black);
		g.drawRect(215, 125, 15, 15);
		g.setColor(Color.black);
		g.drawRect(200, 140, 15, 15);
		g.setColor(Color.black);
		g.drawRect(215, 140, 15, 15);
		
		// Blue Piece
		g.setColor(Color.blue);
		g.fillRect(260, 360, 15, 15);
		g.setColor(Color.blue);
		g.fillRect(260, 345, 15, 15);
		g.setColor(Color.blue);
		g.fillRect(260, 330, 15, 15);
		g.setColor(Color.blue);
		g.fillRect(245, 360, 15, 15);
		g.setColor(Color.black);
		g.drawRect(260, 360, 15, 15);
		g.setColor(Color.black);
		g.drawRect(260, 345, 15, 15);
		g.setColor(Color.black);
		g.drawRect(260, 330, 15, 15);
		g.setColor(Color.black);
		g.drawRect(245, 360, 15, 15);
		
		// Yellow Piece
		g.setColor(Color.yellow);
		g.fillRect(245, 345, 15, 15);
		g.setColor(Color.yellow);
		g.fillRect(230, 345, 15, 15);
		g.setColor(Color.yellow);
		g.fillRect(230, 360, 15, 15);
		g.setColor(Color.yellow);
		g.fillRect(215, 360, 15, 15);
		g.setColor(Color.black);
		g.drawRect(245, 345, 15, 15);
		g.setColor(Color.black);
		g.drawRect(230, 345, 15, 15);
		g.setColor(Color.black);
		g.drawRect(230, 360, 15, 15);
		g.setColor(Color.black);
		g.drawRect(215, 360, 15, 15);
	}
}