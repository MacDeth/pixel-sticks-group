/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 4 Class*/

import java.util.ArrayList;

public class Language4 {
	Tokenizer tknzr = new Tokenizer();
	
	public Language4(ArrayList<String> lineList) {
		this.tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isS());
			tknzr.resetTokenizer();
			
		}
	}
	
	// Starting point of the language
	public boolean isS(){
		boolean returnVal = false;;
		
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
	
	// Checks to see if the current character is part of <A>; if the current character isn't a 'b', then the position reverses to accommodate the possible empty string
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
	
	// checks to see if the current character is part of <B>; it must end with a b. 
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