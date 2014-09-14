import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class Hangman2 extends JApplet implements KeyListener
{
	int wrongGuess = 0;
   String currentWord = "APPLET";
	int numCorrect = 0;
	Random random;
	public void init()
	{
		random = new Random();
		int index = random.nextInt(6);
		addKeyListener(this);
		setFocusable(true);
	}
	public void paint(Graphics g)
	{
		g.drawLine(50, 20, 100, 20);	 	// base
		g.drawLine(100, 20, 100, 120);
		g.drawLine(30, 120, 100, 120);
		for (int i=0; i<currentWord.length(); i++) // underlines for each letter
			g.drawLine(i*30+10, 150, i*30+20, 150);
	}
	public void keyPressed(KeyEvent ke)
	{
		boolean found = false;
		int code = ke.getKeyCode();
		char letter = (char)code;
		Graphics g = getGraphics();
		for (int i=0; i<currentWord.length(); i++)
			if (letter == currentWord.charAt(i))	// it's a match!
			{
				int x = i*30+12;
				g.drawString(String.valueOf(letter), x, 150);
				numCorrect++;
				found = true;
			}
		if (!found)
		{
			wrongGuess++;
			switch (wrongGuess)
			{
				case 1:	g.drawOval(40, 30, 20, 20); 	break;
				case 2: 	g.drawLine(50, 50, 50, 80);	break;
				case 3:	g.drawLine(30, 65, 70, 65);	break;
				case 4:	g.drawLine(50, 80, 40, 100);	break;
				case 5:	g.drawLine(50, 80, 60, 100);
 							g.drawString("YOU LOSE", 50, 180);
							removeKeyListener(this);
			}
		}
	}
	public void keyTyped(KeyEvent ke)  { }
	public void keyReleased(KeyEvent ke) { }
}
