package blackjack;

public class Blackjack{

	private Hand player;
	private Hand player2;
	private Hand computer;
	
	public Blackjack(){
		player = new Hand();
		computer = new Hand();
	}
	
	public void stand(){
		computer.addCard();
	}
	
	public void hit(){
		player.addCard();
	}
	
	public void hit2(){
		player2.addCard();
	}
	
	public Hand getPHand(){
		return player;
	}
	
	public Hand getP2Hand(){
		return player2;
	}
	
	public Hand getCHand(){
		return computer;
	}
	
	public int getPSum(){
		return player.getSum();
	}
	
	public int getCSum(){
		return computer.getSum();
	}
	
	public int getP2Sum(){
		return player2.getSum();
	}
	
	public void newHand(){
		player2 = new Hand();
	}
	
}
