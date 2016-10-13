
public class Token {
	public String content;
	public int currChar;
	public boolean hasBeenUsed;
	public boolean tryingToUse;
	
	public Token() {
		content = "";
		currChar = 0;
		hasBeenUsed = false;
		tryingToUse = false;
	}
	
	public int getCurrPosition(){
		return currChar;
	}
	
	public void resetCurrPosition(){
		currChar = 0;
	}
	
	public char getCurrChar(){
		if(!isEndOfString())
			return content.charAt(currChar);
		
		return 0;
	}
	
	public char getNextChar(){
		currChar++;
		return getCurrChar();
	}
	
	public boolean isEndOfString(){
		return currChar >= content.length();
	}
}
