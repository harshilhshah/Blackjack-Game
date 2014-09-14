import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
public class Breakout extends JApplet implements Runnable, KeyListener
{
   ArrayList<Rectangle> blocks;
   int appletWidth, appletHeight;      // dimensions of applet
   int numBlocks = 10;          // number of blocks horizontally
   int numRows = 3;      // number of rows - 1 least difficult, 5 more difficult
   Rectangle paddle;
   int speed = 10;
   Thread thread;
   Point ballPosition;
   int ballSpeed = 5;
   int ballSize = 15;
   int ballDirectionX = 1, ballDirectionY = 1;
   Image buffer;    
  public void init()
  {
      ballPosition = new Point(50, 120);
      blocks = new ArrayList<Rectangle>();
      appletWidth = getWidth();
      appletHeight = getHeight();
      buildBlocks();
      paddle = new Rectangle(50, appletHeight-30, 50, 10);
      buffer = createImage(appletWidth, appletHeight);
      addKeyListener(this);
  }
  public void start()
  {
     if (thread == null)
     	  thread = new Thread(this);
     thread.start();
     setFocusable(true);
  }
  public void buildBlocks()
  {
    int sizeOfBlock = appletWidth / numBlocks;
    int heightOfBlock = 15;
    for (int rows=0; rows<appletWidth; rows += sizeOfBlock)
       for (int cols=0; cols < numRows * heightOfBlock; cols += heightOfBlock)
       {
          Rectangle r = new Rectangle(rows, 80+cols, sizeOfBlock-2, heightOfBlock-2);
          blocks.add(r);
       }
  }
  public void paint(Graphics g)
  {
      Graphics bg = buffer.getGraphics();
      bg.setColor(Color.WHITE);		// clear screen
      bg.fillRect(0, 0, appletWidth, appletHeight); 
      bg.setColor(Color.BLUE); 		// draw ball
      bg.fillOval(ballPosition.x, ballPosition.y, ballSize, ballSize);      
      bg.setColor(Color.RED); 		// draw blocks
      for (int i=0; i<blocks.size(); i++)
      {
          Rectangle r = blocks.get(i);
          bg.fillRect(r.x, r.y, r.width, r.height);
      }
      bg.setColor(Color.BLACK);
      bg.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
      g.drawImage(buffer,0,0,this);
   }
    public void ballMove()
    {					 // move ball based on speed and direction
       ballPosition.x = ballPosition.x + ballSpeed * ballDirectionX;
       ballPosition.y = ballPosition.y + ballSpeed * ballDirectionY;
       if (ballPosition.x < 0)		  // hit left boundary, change direction
           ballDirectionX = 1;
       else if (ballPosition.x > appletWidth) // hit rt boundary, change direction
           ballDirectionX = -1;
       if (ballPosition.y < 0)		  // hit top boundary, change direction
           ballDirectionY = 1;
       else if (ballPosition.y > appletHeight)	// hit bottom boundary, chg directn
           ballDirectionY = -1;
    }
   public void run()
   {
       while(true)
       {
          ballMove();
          checkForCollision();
          repaint();
          try {
              Thread.sleep(15);
          }catch(Exception ex) { }
       }
   }
   public void checkForCollision()
   {
      Rectangle ballR = new Rectangle(ballPosition.x, ballPosition.y, 
 				ballSize, ballSize);
      for(int i=0; i<blocks.size(); i++)	// check each rect for collision w/ ball
      {
         Rectangle r = (Rectangle)blocks.get(i);
         if (r.intersects(ballR))			// collision detected
         {
             blocks.remove(r);
             ballDirectionX = -1 * ballDirectionX;	// change direction of ball 
             ballDirectionY = -1 * ballDirectionY;
         }
      }
      if (ballR.intersects(paddle))      // check for paddle collision
      {
          ballDirectionX = -1 * ballDirectionX;
          ballDirectionY = -1 * ballDirectionY;
      }
   }
   public void keyTyped(KeyEvent ke) {   }
   public void keyReleased(KeyEvent ke) {    }
   public void keyPressed(KeyEvent ke) 
   {
      int code = ke.getKeyCode();
      if(code == KeyEvent.VK_LEFT)
          paddle.x -= speed;
      else if(code == KeyEvent.VK_RIGHT)
          paddle.x += speed;
   }
}
