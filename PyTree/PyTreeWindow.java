import java.awt.*;
import java.awt.event.*;

// PyTreeWindow class file to be called and create the 
// window for the graphics to be displayed for the user.
public class PyTreeWindow extends Frame {
   // Variables.
   private PyTreeCanvas canvas;
   private MenuBar menuBar;
   private Menu menu;
   private MenuItem menuItemPythagoras, menuItemQuit;  
   private ScrollPane scrollPane;
  
   // Function to create window with the addition of
   // the functionality of the menu for "Quit" and
   // "Pythagoras".
   public PyTreeWindow() {
       super("Tree of Pythagoras");
       setSize(500, 500);
      
       canvas = new PyTreeCanvas();
       canvas.setSize(4000, 3000);
      
       menuBar = new MenuBar();
       menu = new Menu("Menu");
      
       menuItemPythagoras = new MenuItem("Pythagoras");
       menuItemQuit = new MenuItem("Quit");
      
       menuItemPythagoras.setActionCommand("pythagoras");      
       menuItemQuit.setActionCommand("quit");
      
       menu.add(menuItemPythagoras);
       menu.add(menuItemQuit);
       menuBar.add(menu);      
      
       scrollPane = new ScrollPane();
       scrollPane.setSize(500, 500);      
       scrollPane.add("Center", canvas);
      
       setMenuBar(menuBar);
       add("Center", scrollPane);      
                  
       menu.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if(e.getActionCommand().equals("pythagoras")) {
                   canvas.menuClickedEvent();
               }
               if(e.getActionCommand().equals("quit")) {
                   System.exit(0);
               }                  
           }
       });
       addWindowListener(new WindowAdapter()
       {
           public void windowClosing(WindowEvent e)
           {
               System.exit(0);
           }
       });
       setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));      
       setVisible(true);
   }
}
