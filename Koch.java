//	Tyler Mark Waller
//	This is a modification of the Koch.java program
//	which was supplied in the book. The modifcation
//	is small, but used to create the Koch Snowflake.
import java.awt.*; 
import java.awt.event.*;

public class Koch extends Frame { 
	public static void main(String[] args) {new Koch();}
	
	Koch() { 
		super("Koch. Click the mouse button to increase the level"); 
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent e) {System.exit(0);} 
		}); 
		setSize(600, 500); 
		add("Center", new CvKoch()); 
		setVisible(true); 
		}
}

class CvKoch extends Canvas { 
	public float x, y; 
	double dir; 
	int midX, midY, level = 1;

	int iX(float x) {return Math.round(midX + x);} 
	int iY(float y) {return Math.round(midY - y);}
	
	CvKoch() { 
		addMouseListener(new MouseAdapter() { 
			public void mousePressed(MouseEvent evt) {
				level++; // Each mouse click increases the level by 1. 
				repaint();
			} 
		});
	}
	public void paint(Graphics g) { 
		Dimension d = getSize(); 
		int maxX = d.width - 1, maxY = d.height - 1, 
				length = 2 * maxX / 4; 
		midX = maxX / 2; midY = maxY / 2; 
		x = (float) (-length / 2); // Start point 
		y = 70; 
		// The modification of the Koch program
		// was implemented here to use 3 uses 
		// of the curve to create a Koch snowflake.
		dir = 0; 
		drawKoch(g, length, level); 
		dir -= 120; 
		drawKoch(g, length, level);
		dir -= 120; 
		drawKoch(g, length, level);
	}

	public void drawKoch(Graphics g, double len, int n) { 
		if (n == 0) { 
			double dirRad, xInc, yInc; 
			dirRad = dir * Math.PI / 180; 
			xInc = len * Math.cos(dirRad); // x increment 
			yInc = len * Math.sin(dirRad); // y increment 
			float x1 = x + (float) xInc, y1 = y + (float) yInc; 
			g.drawLine(iX(x), iY(y), iX(x1), iY(y1)); 
			x = x1; y = y1; 
		} else { 
			drawKoch(g, len /= 3, --n); 
			dir += 60; 
			drawKoch(g, len, n); 
			dir -= 120; 
			drawKoch(g, len, n); 
			dir += 60; 
			drawKoch(g, len, n); 
			

		}
	}
}
