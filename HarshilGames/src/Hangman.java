import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Hangman extends JApplet implements KeyListener
{
  public void init()
  {
     addKeyListener(this); 
     setFocusable(true); 
  }
  public void paint(Graphics g)
  {
		g.drawLine(50, 20, 100, 20);	// base
		g.drawLine(100, 20, 100, 120);
		g.drawLine(30, 120, 100, 120);
		                 // underlines for each letter
		g.drawLine(10, 150, 20, 150);  	
		g.drawLine(40, 150, 50, 150);
		g.drawLine(70, 150, 80, 150);
		g.drawLine(100, 150, 110, 150);
  }  
  public void keyReleased(KeyEvent ke) { }
  public void keyTyped(KeyEvent ke)    { }
  public void keyPressed(KeyEvent ke)
  {
  }		
}
