/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Tokenizer Class*/

import java.util.ArrayList;

public class Tokenizer {
	private ArrayList<String> lineList;
	private int arrayIndex = 0;
	private String currString;
	private int currPos;
	private char currToken;
	private final char endOfString = '\u00B6';
	//Constructor for tokenizer. Starts everything from 0
	public Tokenizer (ArrayList<String> lineList){
		this.lineList = lineList;
		this.currString = lineList.get(arrayIndex);
		currPos = 0;
		currToken = currString.charAt(currPos);
	}
	//Empty/default constructor
	public Tokenizer() {

	}
	//returns the current string thats being tokenized
	public String getString(){
		return currString;
	}
	//returns next char or EOS
	public char getNextToken(){
		if(++currPos < currString.length()){
			currToken = currString.charAt(currPos);
		}
		else{
			currToken = endOfString;
		}
		return getCurrToken();
	}
	//returns current token
	public char getCurrToken(){
		return currToken;
	}
	//checks for EOS using EOS character
	public boolean isEndOfString(){
		if(currToken == endOfString){
			return true;
		}
		else{
			return false;
		}
	}
	//returns current position in the tokenizer
	public int getCurrPosition(){
		return currPos;
	}
	//resets tokenizer to go to next line
	public void resetTokenizer() {
        this.arrayIndex++;
        if (arrayIndex < lineList.size()) {
            this.currString = lineList.get(arrayIndex);
            this.currToken = currString.charAt(0);
            this.currPos = 0;
        }
    }
	//goes back one position in the index. for lang4
	public Tokenizer goBack(){
		if(this.currPos == 0)
			return this;

		this.currPos--;
		this.currToken = currString.charAt(this.currPos);
		return this;

	}
}
