package rockPaperScissor;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RPS extends Applet{
	
	Button rock = new Button("ROCK");
	Button paper = new Button("PAPER");
	Button sci = new Button("SCISSORS");
	Button quit = new Button("QUIT");
	Button winner = new Button("......");
	Label player = new Label("Player picked: ");
	Label computer = new Label("Computer picked: ");
	Font f = new Font("Algerian", Font.BOLD, 40);
	Image rockImage;
	Image paperImage;
	Image sciImage;
	MediaTracker mt;
	
	public void init(){
		setSize(600,500);
		setBackground(Color.YELLOW);
		mt = new MediaTracker(this);
		rockImage = getImage(getDocumentBase(), "rock.jpg");
		paperImage = getImage(getDocumentBase(), "paper.jpg");
		sciImage = getImage(getDocumentBase(), "scissor.jpg");
		mt.addImage(rockImage,1);
		mt.addImage(paperImage,2);
		mt.addImage(sciImage,3);
		try { 
			mt.waitForAll(); 
	    } 
	    catch (InterruptedException  e) {}
	}
	
	public void stop(){}
	
	public void paint(Graphics g){
		g.setFont(f);
		g.drawString("Rock, paper, Scissor",50, 50 );
		winner.setFont(f);
		listenBtn(winner, 200, 325, 200, 40);
		add(player);
		player.setFont(new Font("MonotypeCursiva", Font.PLAIN, 20));
		player.setBounds(75, 75, 125, 25);
		listenBtn(rock, 25, 400, 150, 50);
		listenBtn(paper, 200, 400, 150, 50);
		listenBtn(sci, 375, 400, 150, 50);
		rock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawImg(rockImage, rock);
			}
		});
		paper.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawImg(paperImage, paper);
			}
		});
		sci.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				drawImg(sciImage, sci);
			}
		});
	}
	
	public void listenBtn(Button bt, int a, int b, int c, int d){
		add(bt);
		bt.setBounds(a, b, c, d);
	}
	
	public void drawImg(Image i, Button b){
		rock.enable(false);
		paper.enable(false);
		sci.enable(false);
		this.getGraphics().drawImage(i, 50, 100, 200, 200, b);
		int rand = (int)(Math.random()*3)+1;
		try {
			this.wait(5500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rand == 1)
			this.getGraphics().drawImage(rockImage, 350, 100, 200, 200, rock);
		else if(rand == 2)
			this.getGraphics().drawImage(paperImage, 350, 100, 200, 200, paper);
		else
			this.getGraphics().drawImage(sciImage, 350, 100, 200, 200, sci);
		decide(rand, b);
	}
	
	public void decide(int x, Button b){
		if(x == 1){
			if(b.equals(rock))
				winner.setLabel("TIED");
			else if(b.equals(paper))
				winner.setLabel("WINNER!!");
			else
				winner.setLabel("LOSER!");
		}
		else if(x == 2){
			if(b.equals(rock))
				winner.setLabel("LOSER!");
			else if(b.equals(paper))
				winner.setLabel("TIED!");
			else
				winner.setLabel("WINNER!!");
		}
		else{
			if(b.equals(rock))
				winner.setLabel("WINNER!!");
			else if(b.equals(paper))
				winner.setLabel("LOSER!");
			else
				winner.setLabel("TIED");
		}
		rock.enable();
		sci.enable();
		paper.enable();
	}

}
