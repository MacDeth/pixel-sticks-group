/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 3 Class*/

import java.util.ArrayList;

//Declares the tokenizer that will be used to receive the array list, separate each string, and get the characters
public class Language1 {
	Tokenizer tknzr = new Tokenizer();
	
	//Loops through the String array list beginning with the starting point and prints out the results
	public Language1(ArrayList<String> lineList) {
		this.tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isS());
		}
	}
	
	//The starting point of the language. Checks to see if each character is a part of the language, step by step. 
	public boolean isS(){
		boolean returnVal = false;
		
		if(isA()){
			tknzr.getNextToken();
			
			if(tknzr.getCurrToken() == 'a'){
				tknzr.getNextToken();
				
				if(isB()){
					tknzr.getNextToken();
					
					if(tknzr.getCurrToken() == 'b'){
						tknzr.getNextToken();
						
						if(tknzr.isEndOfString()){
							returnVal = true;
						}
					}
				}
			}
		}
		return returnVal;
	}
	
	// Redirects from <S> to see if the current token is a part of <A>.
	public boolean isA(){
		boolean returnVal = false;
		
		if(tknzr.getCurrToken() == 'b'){
			tknzr.getNextToken();
			
			if(isA1()){
				tknzr.getNextToken();
				returnVal = true;
			}
		}
		return returnVal;
	}
	
	// Redirects from <A> to see if the current token is a part of <A1>.
	public boolean isA1(){
		boolean returnVal = false;
		
		if(tknzr.isEndOfString()){
			tknzr.getNextToken();
			returnVal = true;
		}
		else if(tknzr.getCurrToken() == 'b'){
			tknzr.getNextToken();
			
			if(isA1()){
				tknzr.getNextToken();
				returnVal = true;
			}
		}
		
		return returnVal;
	}
	
	// Redirects from <S> to see if the current token is a part of <B>.
	public boolean isB(){
		boolean returnVal = false;
		
		if(tknzr.getCurrToken() == 'a'){
			tknzr.getNextToken();
			
			if(isB()){
				tknzr.getNextToken();
				returnVal = true;
			}
		}
	
		if(tknzr.getCurrToken() == 'a'){
			tknzr.getNextToken();
			returnVal = true;
		}
		return returnVal;
	}
}