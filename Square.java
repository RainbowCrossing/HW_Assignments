/*
 * Tyler Waller
 * TMW160530@utdallas.edu
 * This program is meant to create a square in counterclockwise
 * rotation one points A and B. A starting out on the right and
 * B on the left to create the counterclockwise movement.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

// Main class to set up window and call CvSquare class.
public class Square extends Frame{
	public static void main(String[] args) {
		new Square();
	}
	
	Square(){
		super("Define a Square");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setSize(700, 500);
		add("Center", new CvSquare());
		setCursor(Cursor.getPredefinedCursor(CROSSHAIR_CURSOR));
		show();
	}
}

// Class that draws the square one points A and B.
class CvSquare extends Canvas{
	// Variables.
	int A, B, C, D;
	boolean start;
	Point one, two;
	
	// Mouse Listener added for mouse clicks for points.
	// Declaring where point A is and then point B.
	CvSquare(){
		start = true;
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent click) {
				if(start) {
					one = click.getPoint();
					start = false;
				} else {
					two = click.getPoint();
					start = true;
					repaint();
				}
			}
		});
	}
	// Function to gather points plots on the canvas
	// and paint them in to create a square.
	public void paint(Graphics g) {
		A = one.x;
		B = one.y;
		C = two.x;
		D = two.y;
		int x1 = (C + (B - D));
		int y1 = (D + (C - A));
		int x2 = (A + (B - D));
		int y2 = (B + (C - A));
		// Drawing lines from A to B
		// then B to C then C to D
		// and last D to A.
		g.drawLine(A,  B,  C,  D);
		g.drawLine(C,  D,  x1,  y1);;
		g.drawLine(x1,  y1, x2, y2);
		g.drawLine(x2,  y2,  A,  B);
	}
}