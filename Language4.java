/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 4 Class*/

import java.util.ArrayList;

//Declares the tokenizer that will be used to receive the array list, separate each string, and get the characters
public class Language4 {
	Tokenizer tknzr = new Tokenizer();
	
	//Loops through the String array list beginning with the starting point and prints out the results
	public Language4(ArrayList<String> lineList) {
		this.tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isS());
			tknzr.resetTokenizer();
			
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
						else {
							return false;
						}
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		return returnVal;
	}
	
	// Redirects from <S> to see if the current token is a part of <A>.; if the current character isn't a 'b', then the position reverses to accommodate the possible empty string
	// as well as the 'a' in <S>
	public boolean isA(){		
		if(tknzr.getCurrToken() == 'b'){
			tknzr.getNextToken();
			return isA();
		}
		else if(tknzr.isEndOfString()){
			return true;
		}
		else{
			tknzr.goBack();
			return true;
		}
	}
	
	// Redirects from <S> to see if the current token is a part of <B>. 
	public boolean isB(){
		if(tknzr.getCurrToken() == 'a'){
			tknzr.getNextToken();
			return isB();
		}
		else if(tknzr.isEndOfString()){
			return false;
		}
		else{
			tknzr.goBack();
			return true;
		}
	}
}