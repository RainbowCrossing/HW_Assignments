/*
 * Tyler Waller
 * Java program to show case drawLine and drawCircle function
 * along with the dGrid function for a grid like showing all
 * going through the call of the putPixel call. Some modifications
 * were made to the program to show case the ability to read
 * a text file using a scanner method. Please change location of file
 * location in the cvSuperPixels class to get the program to read the input file.
 */
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Setting up window to display and dunction/class calls.
public class SuperPixels extends Frame{
	public static void main(String[] args) throws FileNotFoundException{
		new SuperPixels();
	}
	public SuperPixels() throws FileNotFoundException{
		super("SuperPixels.java");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		setSize(800, 700);
		add("Center", new CvSuperPixels());
		setVisible(true);
	}
}

// Class to call for all functions to operate
// File reader, drawLine, drawCircle, grid.
class CvSuperPixels extends Canvas{
	// Variables.
	int xCenter, yCenter;
	int dGrid = 10;
	float pixelSize, startWidth = 10.0F, startHeight = 10.0F,
	rWidth, rHeight;
	float r = -1, hexHeight, hexWidth;
	int[][] fileLine;
	int xCircle, yCircle, radCircle;
	// Using scanner to locate file and read.
	// ****PLEASE FOR TESTING PURPOSES CHANGE FILE LOCATION IN CODE 
	// TO BE ABLE TO READ "input.txt" IN YOUR COMPUTER LOCATION!****
	CvSuperPixels() throws FileNotFoundException{
		Scanner scan = new Scanner(new File("C:\\Users\\tyler\\Documents\\UTD\\Spring 2020\\Computer Graphics\\input.txt"));
		int n = scan.nextInt();
		fileLine = new int[n][4];
		// Reading through file to get the determined values.
		for(int k=0;k<n;k++){
			fileLine[k] = new int[] {
				scan.nextInt(),
				scan.nextInt(),
				scan.nextInt(),
				scan.nextInt()
			};
		}
		// Reading last line in file for center location and radius.
		xCircle = scan.nextInt();
		yCircle = scan.nextInt();
		radCircle = scan.nextInt();
		scan.close();
	}
	// Isotropic mapping.
	protected void initgr(){
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(startWidth / maxX, startHeight / maxY);
		xCenter = maxX / 2;
		yCenter = maxY / 2;
		rWidth = maxX * pixelSize;
		rHeight = maxY * pixelSize;
	}
	
	// Math functions.
	int iX(float x){
		return Math.round(xCenter + x / pixelSize);
	}
	int iY(float y){
		return Math.round(yCenter - y / pixelSize);
	} 
	float fx(int X){
		return (X - xCenter) * pixelSize;
	}
	float fy(int Y){
		return (yCenter - Y) * pixelSize;
	}
	// Paint function to call draw to for grid, line and circle.
	public void paint(Graphics g){
		// Isotropic call.
		initgr();  
		// Grid call.
		drawGrid(g);
		for(int[] line : fileLine){
			// drawLine call.
			drawLine(g, line[0], line[1], line[2], line[3]);
		}
		// drawCircle call.
		drawCircle(g, xCircle, yCircle, radCircle); // g, xC, yC, r
	}
	// Function for drawing grid.
	private void drawGrid(Graphics g){
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		for(int x = dGrid; x < maxX; x += dGrid){
			for(int y = dGrid; y < maxY; y += dGrid){
				g.drawLine(x, y, x, y);
			}
		}
	}
	// putPixel function for drawing purposes.
	private void putPixel(Graphics g, int x, int y){
		g.drawOval(x*dGrid - dGrid/2, y*dGrid - dGrid/2, dGrid, dGrid);
	}
	// Function for drawing line referenced from book.
	void drawLine(Graphics g, int xA, int yA, int xB, int yB){ 
		int x = xA, y = yA, d = 0, x2 = xB - xA, y2 = yB - yA,
		c, m, xInc = 1, yInc = 1;
		if (x2 < 0){
			xInc = -1; x2 = -x2;
			}
		if (y2 < 0){
			yInc = -1; y2 = -y2;
			}
		if (y2 <= x2){
			c = 2 * x2; m = 2 * y2;
			if (xInc < 0) x2++;
			for (;;){ 
				putPixel(g, x, y);
				if (x == xB) 
					break;
				x += xInc;
				d += m;
				if (d >= x2){
					y += yInc; d -= c;
				}
			}
		}
		else{
			c = 2 * y2; m = 2 * x2;
			if (yInc < 0) 
				y2++;
			for (;;){
				putPixel(g, x, y);
				if (y == yB) 
					break;
				y += yInc;
				d += m;
				if (d >= y2){
					x += xInc; d -= c;
					}
			}
		}
	}
	// Function for drawing circle reference from book.
	void drawCircle(Graphics g, int centerValueX, int centerValueY, int valueRadius){
		int x = 0, y = valueRadius, u = 1, v = 2 * valueRadius - 1, e = 0;
		// While loop to call putPixel to get placements of pixels on to 
		// canvas to show results.
		while (x < y){ 
			putPixel(g, centerValueX + x, centerValueY + y);
			putPixel(g, centerValueX + y, centerValueY - x);
			putPixel(g, centerValueX - x, centerValueY - y);
			putPixel(g, centerValueX - y, centerValueY + x);
			x++; 
			e += u; 
			u += 2;
			if (v < 2 * e){
				y--; 
				e -= v; 
				v -= 2;
			}
			if (x > y) 
				break;
			putPixel(g, centerValueX + y, centerValueY + x);
			putPixel(g, centerValueX + x, centerValueY - y);
			putPixel(g, centerValueX - y, centerValueY - x);
			putPixel(g, centerValueX - x, centerValueY + y);
		}
	}

}
