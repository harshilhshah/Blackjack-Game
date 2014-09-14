package blackjack;

public class Deck {
	
	private Card[] userDeck;
	private Card handed;
	
	public Deck(){
		userDeck = new Card[52];
		createCardArray();
	}
	
	public Card[] createCardArray(){
		for(int i = 0; i < 52; i++)
			userDeck[i] = new Card((i/13) + 1, (i%13) + 1);
		return userDeck;
	}
	
	public void shuffle(){
		Card temp;
		for(int i = 0; i < userDeck.length; i++){
			int hold = (int)(Math.random()*userDeck.length);
			temp = userDeck[hold];
			userDeck[hold] = userDeck[i];
			userDeck[i] = temp;
		}
	}
	
	public Card randomOne(){
		shuffle();
	    int hold = (int)(Math.random()*userDeck.length);
	    handed = userDeck[hold];
	    return handed;
	}
	
	public Card getCard(){
		return handed;
	}
	
	public String toString(){
		return handed.toString();
	}
		
}
