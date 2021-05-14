import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

// PyTreeCanvas class to implement the usage of Pythagoras
// menu choice usage. 
public class PyTreeCanvas extends Canvas {
   // Variables.
   private Random rand;
   private Point2F point1, point2;  
   private float centerX, centerY; // center(centerX, centerY)
   private float startWidth = 100.0F, startHeight = 100.0F;  
   private float size;
   private boolean listener;
   private long seed;  

   // Constructor usage for muose events.
   public PyTreeCanvas() {
       listener = false;
       seed = System.currentTimeMillis();  
       MouseAdapter mouseAdapterListener = new MouseAdapter() {
           public void mousePressed(MouseEvent e) {
               if(point1 == null && listener) {
                   point1 = floatP(e.getPoint());
               }
               else if(point2 == null && listener) {
                   point2 = floatP(e.getPoint());
               }
               repaint();
           }
       };
       addMouseListener(mouseAdapterListener);
   }

   // Mouse click event on menu function.
   public void menuClickedEvent() {
       listener = true;
       point1 = point2 = null;
       seed = System.currentTimeMillis();
       repaint();
   }
   // Paint function.
   public void paint(Graphics g) {
       initialGraphics();
       g.setColor(Color.black);
       if(point2 != null) {
           drawPyTree(g, point1, point2);
       }
   }

   // drawPyTree function implementation for usage to graph points
   // and fill in the rest of the tree when called for by the user.
   public void drawPyTree(Graphics g, Point2F point2f1, Point2F point2f2) {
       if(point2f1.distance(point2f2) <= size) {
           return;
       }
       Point2F point2f3 = point2f2.subtraction(point2f1);      
       Point2F point2f4 = new Point2F(-point2f3.y, point2f3.x);
       Point2F point2f5 = point2f2.addition(point2f4);
       Point2F point2f6 = point2f1.addition(point2f4);
       Point2F point2f7 = new Point2F((point2f5.x + point2f6.x) / 2, (point2f5.y + point2f6.y) / 2).addition(point2f4.scale(.5f));
       Color randColor1 = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
       Color randColor2 = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
      
       g.setColor(randColor1);
       g.fillPolygon(new int[]{intX(point2f1.x), intX(point2f2.x), intX(point2f5.x), intX(point2f6.x)}, new int[]{intY(point2f1.y), intY(point2f2.y), intY(point2f5.y), intY(point2f6.y)}, 4);

       g.setColor(randColor2);
       g.fillPolygon(new int[]{intX(point2f6.x), intX(point2f5.x), intX(point2f7.x)}, new int[]{intY(point2f6.y), intY(point2f5.y), intY(point2f7.y)}, 3);

       g.setColor(Color.black);
       g.drawLine(intX(point2f1.x), intY(point2f1.y), intX(point2f2.x), intY(point2f2.y));
       g.drawLine(intX(point2f2.x), intY(point2f2.y), intX(point2f5.x), intY(point2f5.y));
       g.drawLine(intX(point2f5.x), intY(point2f5.y), intX(point2f6.x), intY(point2f6.y));
       g.drawLine(intX(point2f6.x), intY(point2f6.y), intX(point2f1.x), intY(point2f1.y));
       g.drawLine(intX(point2f6.x), intY(point2f6.y), intX(point2f5.x), intY(point2f5.y));
       g.drawLine(intX(point2f5.x), intY(point2f5.y), intX(point2f7.x), intY(point2f7.y));
       g.drawLine(intX(point2f7.x), intY(point2f7.y), intX(point2f6.x), intY(point2f6.y));

       drawPyTree(g, point2f6, point2f7);
       drawPyTree(g, point2f7, point2f5);
   }

   // initialGraphics function usage.
   public void initialGraphics() {
       Dimension dim = getSize();
       size = Math.max(startWidth / (dim.width - 1), startHeight / (dim.height - 1));
       centerX = (dim.width - 1) / 2;
       centerY = (dim.height - 1) / 2;
       rand = new Random(seed);
   }
   // The next few functions work as implementation 
   // functions for the overall usage of the program
   // for the user.
   public int intX(float x) {
       return Math.round(centerX + x / size);
   }
   
   public int intY(float y) {
       return Math.round(centerY - y / size);
   }

   public Point intP(Point2F p) {
       return new Point(intX(p.x), intY(p.y));
   }

   public float floatX(int x) {
       return (x - centerX) * size;
   }

   public float floatY(int y) {
       return (centerY - y) * size;
   }

   public Point2F floatP(Point2D p) {
       return new Point2F(floatX((int) p.getX()), floatY((int) p.getY()));
   }
   
   // Point2F class usage for different calls to return
   // points for drawing purposes when Pyhtagoras is
   // called for usage by the user.
   private class Point2F {
       // Variables.
       protected float x, y;
       public Point2F(float x, float y) {
           this.x = x;
           this.y = y;
       }
       // The next few functions implement the usage of
       // addition, subtraction, negative, scale, distance,
       // and toString methods for points.
       public Point2F addition(Point2F point2f) {
           return new Point2F(x + point2f.x, y + point2f.y);
       }

       public Point2F subtraction(Point2F point2f) {
           return addition(point2f.negative());
       }      

       public Point2F negative() {
           return scale(-1);
       }

       public Point2F scale(float k) {
           return new Point2F(x * k, y * k);
       }

       public float distance(Point2F point2f) {
           return (point2f.x - x) * (point2f.x - x) + (point2f.y - y) * (point2f.y - y);
       }

       @Override
       public String toString(){
           return "(" + x + ", " + y + ")";
       }
   }
}