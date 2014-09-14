

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class MainGame extends BasicGame {

	Image player1, court, player2, ground, p1ScoreBoard, p2ScoreBoard, slime, net;
	private float p1PosX, p1PosY, p2PosX, p2PosY, slimePosX, slimePosY, iniSlime1X, iniSlime1Y, iniSlime2X, iniSlime2Y;
	Color[] slimeData, player1Data, player2Data, groundData, netData;
	Rectangle slimeBound, p1Bound, p2Bound, netBound;
	
	private float p1InitX, p1InitY, p2InitX, p2InitY;
	
	boolean gameEnd;
	
	private static final int waitTime = 1500;
	private int elapsedTime;
	
	private int player1Score, player2Score;
	
	public MainGame() {
		super("Slime Volleyball");
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		ground.draw(0, arg0.getHeight() - ground.getHeight());
		net.draw(ground.getWidth()/2 - net.getWidth()/2, arg0.getHeight() - ground.getHeight() - net.getHeight());
		player1.draw(p1PosX, p1PosY);
		player2.draw(p2PosX, p2PosY);
		slime.draw(p1InitX + player1.getWidth()/2, 100);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		//set initial scores to 0
		player1Score = 0;
		player2Score = 0;
		
		// game status
		gameEnd = false;
		
		// load png sprites
		player1 = new Image("red_slime_left.png");
		ground = new Image("ground.png");
		player2 = new Image("blue_slime_right.png");
		p1ScoreBoard = new Image("score_board.png");
		p2ScoreBoard = new Image("score_board.png");
		slime = new Image("slime.png");
		net = new Image("net.png");
		net.setImageColor(0, 0, 0);
		slime.setImageColor(255, 21, 0);
		
		// set bounding boxes for collision detection
		slimeBound = new Rectangle(slimePosX, slimePosY, slime.getWidth(), slime.getHeight());
		p1Bound = new Rectangle(p1PosX, p1PosY, player1.getWidth(), player1.getHeight());
		p2Bound = new Rectangle(p2PosX, p2PosY, player2.getWidth(), player2.getHeight());
		
		p1InitX = container.getWidth()/(float)4 - player1.getWidth()/(float)2;
		p1InitY = container.getHeight() - ground.getHeight() - player1.getHeight();
		p2InitX = container.getWidth()/(float)4 *3 - player2.getWidth()/(float)2;
		p2InitY = container.getHeight() - ground.getHeight() - player2.getHeight();
		
		p1PosX = p1InitX;
		p1PosY = p1InitY;
		p2PosX = p2InitX;
		p2PosY = p2InitY;
		
		ground.setImageColor(0,250,0);
		container.getGraphics().setBackground(Color.white);
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if(container.getInput().isKeyDown(Keyboard.KEY_A) && p1PosX > 10)
			p1PosX -= 10;
		if(container.getInput().isKeyDown(Keyboard.KEY_D) && p1PosX < 300)
			p1PosX += 10;
		if(container.getInput().isKeyDown(Keyboard.KEY_W) && p1PosY > 250)
			p1PosY -= 10;
		if(container.getInput().isKeyDown(Keyboard.KEY_S) && p1PosY < 340)
			p1PosY += 10;
		if(container.getInput().isKeyDown(Keyboard.KEY_LEFT) && p2PosX > container.getWidth()/2 + net.getWidth())
			p2PosX -= 10;
		if(container.getInput().isKeyDown(Keyboard.KEY_RIGHT) && p2PosX < 675)
			p2PosX += 10;
		if(container.getInput().isKeyDown(Keyboard.KEY_UP) && p2PosY > 250)
			p2PosY -= 10;
		if(container.getInput().isKeyDown(Keyboard.KEY_DOWN) && p2PosY < 340)
			p2PosY += 10;
		if(p1PosY < 340 && !(container.getInput().isKeyDown(Keyboard.KEY_W))){
			p1PosY +=4.5;
		}
		if(p2PosY < 340 && !(container.getInput().isKeyDown(Keyboard.KEY_UP))){
			p2PosY +=2.45;
		}
	}

	/**
	 * @param args
	 * @throws SlickException
	 */
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer app = new AppGameContainer(new MainGame());
		app.setMinimumLogicUpdateInterval(20);
		app.setDisplayMode(760, 450, false);
		app.setShowFPS(false);
		app.start();

	}

}
