
public class Tokenizer {
	private String[] line;
	private String currString;
	private int currPos;
	private char currToken;
	private final char endOfString = '\u00B6';
	
	public Tokenizer (){
		currPos = 0;
		currToken = endOfString;
	}
	
	public String getString(){
		return currString;
	}
	
	public char getNextToken(){
		if(++currPos < currString.length()){
			currToken = currString.charAt(currPos);
		}
		else{
			currToken = endOfString;
		}
		return getCurrToken();
	}	
	
	public char getCurrToken(){
		return currToken;
	}
	
	public boolean isEndOfString(){
		if(currToken == endOfString){
			return true;
		}
		else{
			return false;
		}
	}
	public void setString(String input){
		currString = input;
		currPos = 0;
		if(currString.length() > 0){
			currToken = input.charAt(0);
		}
		else{
			currToken = endOfString;
		}
	}
	
	public int getCurrPosition(){
		return currPos;
	}
}
