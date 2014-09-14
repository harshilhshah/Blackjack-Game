import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe extends JApplet implements ActionListener
{
  Image tmpImg;					// Image object for reading in images
  ImageIcon x_icon, o_icon, blank_icon;	// ImageIcons for X and O and blank
  JButton[] pos;					// array of buttons for each position 
  JButton startOver;				// button to start the game over
  JLabel title;					// title displayed at the
  JPanel center, board;			// panels for displaying components
  boolean gameOver = false;	// Once the game is over, players cannot 
 									//      click on any more buttons
  boolean isXTurn = true;		// who's turn is it - X or O ?
  public void init()
  {
	tmpImg = getImage(getCodeBase(), "x.png");	// load image for X
	x_icon = new ImageIcon(tmpImg);
	tmpImg = getImage(getCodeBase(), "o.png");	// load image for O
	o_icon = new ImageIcon(tmpImg);
	tmpImg = getImage(getCodeBase(), "blank.png");	// load blank spot img
	blank_icon = new ImageIcon(tmpImg);
	setLayout(new BorderLayout());	    // set layout for overall applet
	setupTitle();			
	setupButtons();				// set up all buttons
	setupBottom();				// display the start over button at bottom
  }
  public void setupTitle()
  {
	title = new JLabel("Tic Tac Toe!", JLabel.CENTER);	// create title
	title.setFont(new Font("Serif", Font.BOLD, 26));
	title.setForeground(Color.RED);
	add(title, BorderLayout.NORTH);			// add to top of applet
  }
  public void setupBottom()
  {
  	startOver = new JButton("Start Over");
	startOver.addActionListener(this);   // add a listener for this button
	JPanel bottom = new JPanel();	 // add panel so button not stretched
	bottom.add(startOver);
	add(bottom, BorderLayout.SOUTH);
  }
  public void setupButtons()
  {
  	board = new JPanel();			// board panel holds all the buttons
	board.setLayout(new GridLayout(3, 3));	// display in grid
	pos = new JButton[9];
	for (int i=0; i<pos.length; i++)
	{
		pos[i] = new JButton(blank_icon);
		pos[i].setMargin(new Insets(0,0,0,0));	// remove margins around button
		pos[i].addActionListener(this);		// add event listener
		board.add(pos[i]);			// add the button to the board panel
	}
	center = new JPanel(new FlowLayout());	// panel so buttons not stretch
	center.add(board);	
	add(center, BorderLayout.CENTER);
  }
  public void selectSpot(JButton position)  // user clicks on open spot
  {
  	position.setEnabled(false);	 // disable button so user can�t click again
	if(isXTurn) 		// change the disabled icon to either the X or O icon
		position.setDisabledIcon(x_icon);
	else
		position.setDisabledIcon(o_icon);
  	isXTurn = ! isXTurn; 	    // toggle value of isXTurn to track who�s next
  }
  public void actionPerformed(ActionEvent ae)
  { 
	Object src = ae.getSource();
	if(src == startOver)				// restart the game 
	{
		for (int i=0; i<pos.length; i++)
		{
			pos[i].setEnabled(true);		// enable the button
			pos[i].setDisabledIcon(null);	// remove disabled icon
		}
		gameOver = false;
	}
	if(!gameOver)
	{
		for (int i=0; i<pos.length; i++)  // find button selected, put X or O
		{
			if (src == pos[i])
			{
			  	pos[i].setEnabled(false);   	// disable the button
				boolean isWinner = false;
			  	if(isXTurn)      // change disabled icon to either X or O icon
				{
			  		pos[i].setDisabledIcon(x_icon);      // X just played
					isWinner = checkForWinner(x_icon);	  //   so only check X
					if (isWinner)
						setWinner("X");
				}
				else
				{
					pos[i].setDisabledIcon(o_icon); 	  // O just played
					isWinner = checkForWinner(o_icon);	  //    so only check O
					if (isWinner)
						setWinner("O");
				}
			  	isXTurn = ! isXTurn;	// toggle the value of isXTurn	
			}
		}
	}
  }
  public boolean checkForWinner(ImageIcon icon)
  {	 									 // check for horizontal winner
	  if (pos[0].getDisabledIcon() == icon && pos[1].getDisabledIcon()
 		 == icon && pos[2].getDisabledIcon() == icon) 
		  return true;
	  else if (pos[3].getDisabledIcon() == icon && pos[4].getDisabledIcon()
 		 == icon && pos[5].getDisabledIcon() == icon) 
		  return true;
	  else if(pos[6].getDisabledIcon() == icon && pos[7].getDisabledIcon() 
 		 == icon && pos[8].getDisabledIcon() == icon) 
		return true;
						// check for vertical winner
	else if(pos[0].getDisabledIcon() == icon && pos[3].getDisabledIcon() 
 		 == icon && pos[6].getDisabledIcon() == icon) 
		return true;
	else if(pos[1].getDisabledIcon() == icon && pos[4].getDisabledIcon()
 		 == icon && pos[7].getDisabledIcon() == icon) 
		return true;
	else if(pos[2].getDisabledIcon() == icon && pos[5].getDisabledIcon()
 		 == icon && pos[8].getDisabledIcon() == icon) 
		return true;
						// check for diagnol winner
	else if(pos[0].getDisabledIcon() == icon && pos[4].getDisabledIcon()
 		 == icon && pos[8].getDisabledIcon() == icon ) 
		return true;
	else if(pos[2].getDisabledIcon() == icon && pos[4].getDisabledIcon()
 		 == icon && pos[6].getDisabledIcon() == icon ) 
		return true;
	return false;
  }
  public void setWinner(String winner)
  {
	  JFrame fr = new JFrame("WINNER!");  	// create JFrame with Winner!
	  // add the text specifying that the WINNER is Player X or O
	  JLabel win = new JLabel(
 		"<HTML><CENTER>WINNER<BR>is<BR>Player " + winner, JLabel.CENTER);
	  win.setFont(new Font("Serif", Font.BOLD, 36));
	  fr.add(win);
	  fr.setSize(300, 300);  // set the size of the frame to 300 by 300
	  fr.setLocation(100, 200); // set the location of the frame to 100, 200
	  fr.setVisible(true);
	  gameOver = true; // set the gameOver variable to true
	  repaint();
  }
}
