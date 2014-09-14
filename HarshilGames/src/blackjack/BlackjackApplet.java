package blackjack;

import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BlackjackApplet extends Applet implements KeyListener , ActionListener {
	
	Button btnplay1 = new Button("Let's Play");
	Button dealbtn = new Button("DEAL");
	Button btn2 = new Button("STAND");
	Button btn3 = new Button("HIT");
	Button yh = new Button("Player1 :");
	Button dh = new Button("Dealer :");
	Button pTotSum = new Button("Your sum: ");
	Button lost = new Button("You Lost!");
	Button hurray = new Button("You Won!");
	Button tie = new Button("It's a tie");
	Button fiveHund = new Button("500$");
	Button hundred = new Button("100$");
	Button tho = new Button("1000$");
	Button all = new Button("All in");
	Button value = new Button("You have: 10000$");
	Button cValue = new Button("Comp has: 50000$");
	Button doubleDown = new Button("Double");
	Button sur = new Button("Surrender");
	Button help = new Button("Help?");
	Button split1 = new Button("");
	Button split2 = new Button("");
	Button restart = new Button("Restart");
	Blackjack bl;
	BlackMoney bm = new BlackMoney();
	private int hitCount = 0;
	private int bet = 0;
	private int sder = 1;
	private int split = 10;
	private Font an;
	private Font bfont = new Font("TimesNewRoman",Font.PLAIN,12);
	private Font cfont = new Font("Algerian", Font.BOLD, 40);
	private AudioClip sound1, sound2, currentSound;
    private JButton playJButton = new JButton( "Play" );
    private JButton loopJButton, stopJButton;
    private JComboBox soundJComboBox;
	
	public void init(){	
		an = new Font("TimesNewRoman",Font.BOLD,16);
		setSize(500, 350);
		setFont(an);
		setBackground(Color.GREEN);
		addKeyListener(this);
		
		String choices[] = { "Welcome", "Hi" };
        soundJComboBox = new JComboBox( choices );
        soundJComboBox.addItemListener(
           new ItemListener(){
              public void itemStateChanged( ItemEvent e ) {
                 currentSound.stop();
                 } 
              } 
           );
  
           add( soundJComboBox ); // add JComboBox to applet
           add( playJButton );
           playJButton.setBounds(10, 10, 100, 10);
           playJButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentSound.play();
			}
           });
           loopJButton = new JButton( "Loop" );
           loopJButton.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentSound.loop();
			}
           } );
           add( loopJButton );
           stopJButton = new JButton( "Stop" );
           stopJButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentSound.stop();
			}
		});
           add( stopJButton );
  
            // load sounds and set currentSound
           sound2 = getAudioClip( getDocumentBase(), "Cheering2.wav" );
           sound1 = getAudioClip( getDocumentBase(), "08.au" );
           currentSound = sound1;
	}
	
	public void stop(){currentSound.stop(); }
	
	public void paint(Graphics g){	
		g.setFont(cfont);
		g.setColor(Color.PINK);
		g.drawString("BLACKJACK", 100, 40);
		add(btnplay1);
		btnplay1.setBounds(50, 50, 250, 250);
		btnplay1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Component c = (Component)e.getSource();
		    	c.setVisible(false);
		    	next();
			}
		});
	}

	public void next(){
		addAndSetButton(help, 375, 5, 50, 25, true);
		help.setBackground(Color.RED);
		addAndSetButton(sur, 270, 275, 60, 25, false);
		addAndSetButton(doubleDown, 275, 250, 50, 25, false);
		addAndSetButton(tho, 390, 200, 45, 25, true);
		tho.enable();
		addAndSetButton(all, 390,125, 45, 25, true);
		all.enable();
		addAndSetButton(hundred, 390, 150, 45, 25, true);
		hundred.enable();
		addAndSetButton(fiveHund, 390, 175, 45, 25, true);
		fiveHund.enable();
		addAndSetButton(value, 350, 250, 125, 25, true);
		addAndSetButton(cValue, 350, 275, 125, 25, true);
		addAndSetButton(dealbtn, 25, 250, 75, 50, true);
		addAndSetButton(btn2, 100, 250, 75, 50, true);
		btn2.enable(false);
		addAndSetButton(btn3, 175, 250, 75, 50, true);
		btn3.enable(false);
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(split == 11){
					split++;
					userTurn2();
				}
				else if(split == 12){
					split = 8;
					dealerTurn();
				}
				else
					dealerTurn();
			}
		});
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(split == 10){
					hitCount++;
					userTurn();
				}
				else
					userTurn2();
			}
		});
		dealbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				btn2.enable();
				btn3.enable();
				dealbtn.enable(false);
				dealPressed();
			}
		});
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null,"Place your bets by " +
						"choosing the chip denomination and then clicking on the " +
						"table.\nSelect \"Hit\" or \"Stay\" after your cards are " +
						"dealt.\nOn counts of 17 or higher the dealer must stay; " +
						"16 or lower the dealer must draw.\nIf you and the dealer " +
						"tie, it's a push and no one wins.\nIf the dealer is closer " +
						"to 21 without going \"bust,\" the dealer wins. " );
			}
		});
		fiveHund.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(bm.getPMoney() > 499 && bm.getCMoney() > 499){
					bet += 500;
					value.setLabel("You have: " + bm.toString(-500));
					cValue.setLabel("Comp has: " + bm.cMoneyToString(-500));
				}
			}
		});
		hundred.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(bm.getPMoney() > 99 && bm.getCMoney() > 99){
					bet += 100;
					value.setLabel("You have: " + bm.toString(-100));
					cValue.setLabel("Comp has: " + bm.cMoneyToString(-100));
				}
			}
		});
		tho.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(bm.getPMoney() > 999 && bm.getCMoney() > 999){
					bet +=1000;
					value.setLabel("You have: " + bm.toString(-1000));
					cValue.setLabel("Comp has: " + bm.cMoneyToString(-1000));
					sound1.play();
				}
				
			}
		});
		all.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(bm.getPMoney() > 0 && bm.getCMoney() > bm.getPMoney()){
					bet = bm.getPMoney();
					value.setLabel("You have: " + bm.toString(-bet));
					cValue.setLabel("Comp has: " + bm.cMoneyToString(-bet));
				}
			}
		});
	}
	
	public void dealPressed(){
		fiveHund.enable(false);
		hundred.enable(false);
		tho.enable(false);
		all.enable(false);
		bl = new Blackjack();
		addAndSetButton(yh, 25, 110, 85, 25, true);
		addAndSetButton(dh, 25, 75, 85, 25, true);
		yh.setBackground(Color.CYAN);
		bl.stand();
		bl.hit();
		bl.stand();
		bl.hit();
		addAndSetButton(pTotSum, 25, 200, 100, 20, true);
		pTotSum.setBackground(Color.GREEN);
		pTotSum.setLabel("Your sum: " + bl.getPSum());
		if(bl.getPHand().getCardFromHand(0).equals(bl.getPHand().getCardFromHand(1))){
			yh.setLabel("Split?");
			yh.enable();
			yh.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					doubleDown.enable(false);
					yh.enable(false);
					bl.newHand();
					split++;
					addAndSetButton(split1, 115, 115, 300, 20, true);
					addAndSetButton(split2, 115, 140, 300, 20, true);
					split1.setLabel(bl.getPHand().getCardFromHand(0).toString());
					bl.getP2Hand().addCard(bl.getPHand().getCardFromHand(1));
					bl.getPHand().removeCard(bl.getPHand().getCardFromHand(1));
					bl.hit2();
					split2.setLabel(bl.getP2Hand().splitTOString());
					pTotSum.setLabel("Your sum: " + bl.getPHand().getCardFromHand(0).getValue() + "," + bl.getP2Sum());
				}
			});
		}
		if(bl.getPHand().getSum() == 21 && bl.getCHand().getSum() != 21){
			bm.setIs21(true);
			won();
		}
		else if(bl.getCHand().getSum() == 21 && bl.getPHand().getSum() != 21){
			bm.setCIs21(true);
			lost();
		}
		else if(bl.getCHand().getSum() == 21 && bl.getPHand().getSum() == 21)
			tie();
		this.getGraphics().drawString(bl.getPHand().getCardFromHand(0).toString(), 115, 128);
		this.getGraphics().drawString(bl.getPHand().getCardFromHand(1).toString(), 165, 128); 
		this.getGraphics().drawString(bl.getCHand().getCardFromHand(0).toString(), 115, 90);
		if(bet > 0 && !(bl.getCHand().getSum() == 21 || bl.getPHand().getSum() == 21)){
			sur.setVisible(true);
			sur.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					sur.setVisible(false);
					sder --;
					lost();
				}
			});
		doubleDown.setVisible(true);
		doubleDown.enable();
		doubleDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				doubleDown.enable(false);
				if(bm.getPMoney() > bet && bm.getCMoney() > bet){
					bm.setD(true);
					btn2.enable(false);
					btn3.enable(false);
					if(bl.getPHand().size() != hitCount+3)
						doubled();
				}
			}
		});
		}
	}
	
	public void addAndSetButton(Button bt, int a, int b, int c, int d, boolean bck){
		add(bt);
		bt.setBounds(a, b, c, d);
		bt.setVisible(bck);
		bt.setFont(bfont);
	}
	
	public void dealerTurn(){
		while(bl.getCSum() < 17 && !bm.getD())
			bl.stand();
		if(bl.getCSum() > 21)
			won();
		else if(split == 9){
			split = 10;
			if(bl.getP2Sum() < bl.getCSum())
				lost();
			else
				tie();
		}
		else if(split == 8){
			split = 10;
			if(bl.getP2Sum() > bl.getCSum() && bl.getPSum() > bl.getCSum() && bl.getP2Sum() < 21)
				won();
			else if(bl.getP2Sum() < bl.getCSum() && bl.getPSum() < bl.getCSum())
				lost();
			else
				tie();
		}
		else{
				if(bl.getCSum() > bl.getPSum())
					lost();
				else if(bl.getCSum() == bl.getPSum())
					tie();
				else
					won();
		}
	}
	
	public void won(){
		draw(hurray);
		if(bm.getD()){
			value.setLabel("You have: " + bm.toString(bet*3));
			cValue.setLabel("Comp has: " + bm.cMoneyToString(bet*-1));
		}
		else if(bm.getIs21()){
			value.setLabel("You have: " + bm.toString(bet*2.5));
			cValue.setLabel("Comp has: " + bm.cMoneyToString(-0.5*bet));
		}
		else
			value.setLabel("You have: " + bm.toString(bet*2));
		bm.setIs21(false);
		hurray.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				hurray.setVisible(false);
				restart();
			}
		});
	}
	
	public void userTurn(){
		bl.hit();
		pTotSum.setLabel("Your sum: " + bl.getPSum());
		doubleDown.enable(false);
		this.getGraphics().drawString(bl.getPHand().getCardFromHand(hitCount + 1).toString(),
			165 + (hitCount*50), 125);	
		if(bl.getPSum() > 21)
			lost();
	}
	
	public void userTurn2(){
		if(split == 11){
			bl.hit2();
			split2.setLabel(bl.getP2Hand().splitTOString());
			if(bl.getP2Sum() > 21){
				split++;
				split2.setLabel(bl.getP2Hand().splitTOString() + "<--- Lost");
			}
		}
		else if(split == 12){
			bl.hit();
			split1.setLabel(bl.getPHand().splitTOString());
			if(bl.getPSum() > 21){
				split1.setLabel(bl.getPHand().splitTOString() + "<--- Lost");
				if(bl.getP2Sum() > 21)
					lost();
				else {
					split = 9;
					dealerTurn();
				}
			}
		}
		pTotSum.setLabel("Your sum: " + bl.getPSum() + ", " + bl.getP2Sum());
		doubleDown.enable(false);
	}
	
	public void doubled(){
		bl.hit();
		this.getGraphics().drawString(bl.getPHand().getCardFromHand(2).toString(), 215, 125);
		if(bl.getPSum() > 21)
			lost();
		else
			dealerTurn();
	}

	public void lost(){
		draw(lost);
		if(sder>=0)
			cValue.setLabel("Comp has: " + bm.cMoneyToString(bet*2));
		if(bm.getD()){
			value.setLabel("You have: " + bm.toString(-1*bet));
			cValue.setLabel("Comp has: " + bm.cMoneyToString(bet));
		}
		else if(sder == 0){
			value.setLabel("You have: " + bm.toString(bet*0.5));
			cValue.setLabel("Comp has: " + bm.cMoneyToString(bet*-0.5));
		}
		else if(bm.getCIs21()){
			cValue.setLabel("Comp has: " + bm.cMoneyToString(bet));
			value.setLabel("You have: " + bm.toString(-1*bet));
		}
		lost.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lost.setVisible(false);
				restart();
			}
		});
	}
	
	public void tie(){
		draw(tie);	
		value.setLabel("You have: " + bm.toString(bet));
		cValue.setLabel("Comp has: " + bm.cMoneyToString(bet));
		tie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tie.setVisible(false);
				restart();
			}
		});
	}
	
	public void draw(Button b){		
		if(bl.getPHand().size() > hitCount+3)
			bl.getPHand().removeCard(bl.getPHand().getCardFromHand(hitCount+3));
		else
			pTotSum.setLabel("Your sum: " + bl.getPSum());
		this.getGraphics().drawString("Comp Sum : " + bl.getCSum(), 25, 180);
		this.getGraphics().drawString(bl.getCHand().toString(), 165, 90);
		add(b);
		b.setVisible(true);
		btn2.enable(false);
		btn3.enable(false);
		b.setBounds(0, 300, 500, 40);
		doubleDown.enable(false);
		sur.setVisible(false);
		yh.enable(false);
	}
	
	public void restart(){
		bet =0;
		sder = 1;
		yh.setVisible(false);
		dh.setVisible(false);
		pTotSum.setVisible(false);
		dealbtn.enable();
		fiveHund.enable();
		hundred.enable();
		tho.enable();
		all.enable();
		hitCount = 0;
		bm.setD(false);
		doubleDown.enable(false);
		yh.setLabel("Player 1:");
		split2.setVisible(false);
		split1.setVisible(false);
		if(bm.getPMoney() <= 0 || bm.getCMoney() <= 0){
			sound2.play();
			addAndSetButton(restart, 0, 0, 500, 350, true);
			if(bm.getPMoney() <= 0)
				restart.setLabel("Click this button before I kick you out");
			else
				restart.setLabel("Nice game");
			restart.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.exit(0);
				}
			});
		}
	}
		
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}