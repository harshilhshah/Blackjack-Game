package blackjack;

import java.util.ArrayList;

public class Hand{

	private ArrayList<Card> store;
	private Deck d1;
	private int sum;
	
	public Hand(){
		d1 = new Deck();
		store = new ArrayList<Card>();
	}
	
	public void addCard(){
		Card c = d1.randomOne();
		store.add(c);
		updateSum();
	}
	
	public void addCard(Card b){
		store.add(b);
		updateSum();
	}
	
	public int size(){
		return store.size();
	}
	
	public Card getCardFromHand(int b){
		return store.get(b);
	}
	
	public String toString(){
		String l = "";
		for(int i = 1; i < store.size(); i++)
			l = l + store.get(i).toString() + "         ";
		return l;
	}
	
	public String splitTOString(){
		String l = "";
		for(int i = 0; i < store.size(); i++)
			l = l + store.get(i).toString() + "         ";
		return l;
	}
	
	public void updateSum(){
		sum = 0;
	       boolean ace = false;  
	       for ( int i = 0;  i < store.size();  i++ ) {   
	         int cardVal;  
	         cardVal = store.get(i).getValue();
	         if (cardVal > 10) {
	             cardVal = 10; 
	         }
	         if (cardVal == 1) {
	             ace = true;  
	         }
	         sum += cardVal;
	       }
	       if ( ace == true  &&  sum + 10 <= 21 )
	          sum += 10;
	}
	
	public void removeCard(Card c) {
       store.remove(c); 
	}
	
	public int getSum() {
       return sum;
	} 
	
}
