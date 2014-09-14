package aimaze;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Program extends BasicGame {
	
	Image mazeImage;
	Image player;
	int xpos = 10;
	int ypos = 10;

	public Program(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		mazeImage.draw();
		player.draw(xpos, ypos);
		player.setImageColor(0,0,0);
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		mazeImage = new Image("aimaze/simple maze.png");
		player = new Image("slime.png");
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		if(container.getInput().isKeyDown(Keyboard.KEY_A) && xpos > 5)
			xpos -= 5;
		if(container.getInput().isKeyDown(Keyboard.KEY_D) && xpos < 415){
			xpos += 5;
			System.out.println(mazeImage.getColor(xpos, ypos));
		}
		if(container.getInput().isKeyDown(Keyboard.KEY_W) && ypos > 5)
			ypos -= 5;
		if(container.getInput().isKeyDown(Keyboard.KEY_S) && ypos < 415)
			ypos += 5;
		if(mazeImage.getColor(xpos, ypos).equals(Color.BLACK)){
			JOptionPane.showMessageDialog(null, "Game Over");
			System.exit(0);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SlickException{
		// TODO Auto-generated method stub
		AppGameContainer app = new AppGameContainer(new Program("AI-Maze"));
		app.setMinimumLogicUpdateInterval(20);
		app.setDisplayMode(441, 441, false);
		app.setShowFPS(false);
		app.start();
	}

}
