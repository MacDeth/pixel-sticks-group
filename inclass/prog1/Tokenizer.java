<<<<<<< HEAD
/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 4 Class*/

import java.util.ArrayList;

public class Tokenizer {
	private ArrayList<String> lineList;
	private int arrayIndex = 0;
	private String currString;
	private int currPos;
	private char currToken;
	private final char endOfString = '\u00B6';
	
	public Tokenizer (ArrayList<String> lineList){
		this.lineList = lineList;
		this.currString = lineList.get(arrayIndex);
		currPos = 0;
		currToken = currString.charAt(currPos);
	}
	
	public Tokenizer() {
		
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
	
	public int getCurrPosition(){
		return currPos;
	}
	
	public void resetTokenizer() {
        this.arrayIndex++;
        if (arrayIndex < lineList.size()) {
            this.currString = lineList.get(arrayIndex);
            this.currToken = currString.charAt(0);
            this.currPos = 0;
        }
    }
	
	public Tokenizer goBack(){
		if(this.currPos == 0) 
			return this;
		
		this.currPos--;
		this.currToken = currString.charAt(this.currPos);
		return this;
		
	}
=======
/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 4 Class*/

import java.util.ArrayList;

public class Tokenizer {
	private ArrayList<String> lineList;
	private int arrayIndex = 0;
	private String currString;
	private int currPos;
	private char currToken;
	private final char endOfString = '\u00B6';
	
	public Tokenizer (ArrayList<String> lineList){
		this.lineList = lineList;
		this.currString = lineList.get(arrayIndex);
		currPos = 0;
		currToken = currString.charAt(currPos);
	}
	
	public Tokenizer() {
		
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
	
	public int getCurrPosition(){
		return currPos;
	}
	
	public void resetTokenizer() {
        this.arrayIndex++;
        if (arrayIndex < lineList.size()) {
            this.currString = lineList.get(arrayIndex);
            this.currToken = currString.charAt(0);
            this.currPos = 0;
        }
    }
	
	public Tokenizer goBack(){
		if(this.currPos == 0) 
			return this;
		
		this.currPos--;
		this.currToken = currString.charAt(this.currPos);
		return this;
		
	}
>>>>>>> origin/master
}