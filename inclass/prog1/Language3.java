/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 3 Class*/

import java.util.ArrayList;

public class Language3 {
	private static Tokenizer tknzr;

	public Language3(ArrayList<String> lineList) {
		tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isA2());
			tknzr.resetTokenizer();
		}
	}

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
