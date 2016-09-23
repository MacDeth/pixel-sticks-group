/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 3 Class*/

import java.util.ArrayList;

//Declares the tokenizer that will be used to receive the array list, separate each string, and get the characters
public class Language3 {
	private static Tokenizer tknzr;

	//Loops through the String array list beginning with the starting point and prints out the results
	public Language3(ArrayList<String> lineList) {
		tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isA2());
			tknzr.resetTokenizer();
		}
	}

	//The starting point of the language. Checks to see if each character is a part of the language, step by step. 
	private static boolean isA2() {
		if (tknzr.getCurrToken() == 'a') {
			tknzr.getNextToken();
			isA2();
			if (tknzr.getCurrToken() == 'c') {
				tknzr.getNextToken();
				return tknzr.isEndOfString();
			}
		} else
			return isB2();
		
		return false;
	}

	// Redirects from <A2> to see if the current token is a part of <B2>.
	private static boolean isB2() {
		if (tknzr.getCurrToken() == 'b') {
			tknzr.getNextToken();
			isB2();
			if (tknzr.getCurrToken() == 'c') {
				tknzr.getNextToken();
				return tknzr.isEndOfString();
			}
		}
		return false;
	}

}
