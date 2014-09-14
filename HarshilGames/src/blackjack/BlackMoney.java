package blackjack;

public class BlackMoney {
	
	private int playerMoney;
	private int cMoney;
	private boolean is21 = false;
	private boolean cis21 = false;
	private boolean isDouble = false;
	
	public BlackMoney(){
		playerMoney = 10000;
		cMoney = 50000;
	}
	
	public int getPMoney(){
		return playerMoney;
	}
	
	public int getCMoney(){
		return cMoney;
	}
	
	public String toString(double bet){
		playerMoney += bet;
		return "" + (playerMoney) + "$";
	}
	
	public String cMoneyToString(double bet){
		cMoney+= bet;
		return "" + cMoney + "$";
	}
	
	public boolean noMoney(){
		return playerMoney <= 0;
	}
	
	public boolean getIs21(){
		return is21;
	}
	
	public boolean getCIs21(){
		return cis21;
	}
	
	public boolean getD(){
		return isDouble;
	}
	
	public void setD(boolean b){
		isDouble = b;
	}
	
	public void setIs21(boolean bbb){
		is21 = bbb;
	}
	
	public void setCIs21(boolean bb){
		cis21 = bb;
	}

}
