/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 4 Class*/

import java.util.ArrayList;

//Declares the String ArrayList, the index of the Array, the current string, the current position within the current string, the current token, and the character for the end
// of the string. 
public class Tokenizer {
	private ArrayList<String> lineList;
	private int arrayIndex = 0;
	private String currString;
	private int currPos;
	private char currToken;
	private final char endOfString = '\u00B6';
	
	// Constructor for the Tokenizer class.
	public Tokenizer (ArrayList<String> lineList){
		this.lineList = lineList;
		this.currString = lineList.get(arrayIndex);
		currPos = 0;
		currToken = currString.charAt(currPos);
	}
	
	public Tokenizer (){
		
	}
	
	// Returns the current string
	public String getString(){
		return currString;
	}
	
	//Sets the current token to the next token after
	public char getNextToken(){
		if(++currPos < currString.length()){
			currToken = currString.charAt(currPos);
		}
		else{
			currToken = endOfString;
		}
		return getCurrToken();
	}	
	
	//Returns current token
	public char getCurrToken(){
		return currToken;
	}
	
	// Checks to see if the current token is at the end of the string 
	public boolean isEndOfString(){
		if(currToken == endOfString){
			return true;
		}
		else{
			return false;
		}
	}
	
	// Returns the current position
	public int getCurrPosition(){
		return currPos;
	}
	
	//Moves on to the next string, and resets the current string and token to 0, and sets the current string to the string in the current position of the array
	public void resetTokenizer() {
        this.arrayIndex++;
        if (arrayIndex < lineList.size()) {
            this.currString = lineList.get(arrayIndex);
            this.currToken = currString.charAt(0);
            this.currPos = 0;
        }
    }
	
	//Decreases the current position in order to accomodate for an empty string in some languages
	public Tokenizer goBack(){
		if(this.currPos == 0) 
			return this;
		
		this.currPos--;
		this.currToken = currString.charAt(this.currPos);
		return this;
		
	}
}