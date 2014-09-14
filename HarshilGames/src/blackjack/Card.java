package blackjack;

public class Card {
	
	private int val;
	private int face;
	private final int SPADES = 1;
	private final int CLUBS = 4;
	private final int HEARTS = 2;
	private final int DIAMONDS = 3;
	private final int ACE = 1;
	private final int KING = 13;
	private final int JACK = 11;
	private final int QUEEN = 12;
	private String str;
	
	public Card(int suit, int value){
		val = value;
		face= suit;
	}
	
	public int getFace(){
		return face;
	}
	
	public int getValue(){
		if(val>=10)
			return 10;
		return val;
	}
	
	public boolean equals(Card a){
		if(a.getValue() == this.getValue())
			return true;
		return false;
	}
	
	public String toString(){
		if(val == 11)
			str = "J";
		else if(val == 12)
			str = "Q";
		else if(val == 13)
			str = "K";
		else if(val == 1)
			str = "A";
		else if(val > 0 && val < 14)
			str = "" + val;
		str += " ";
		if(face == 2)
			str += "♥";
		else if(face == 4)
			str += "♣";
		else if(face == 1)
			str+= "♠";
		else if(face == 3)
			str += "♦";
		return  str;
	}
}
		
