import java.util.ArrayList;

public class Language1 {
	Tokenizer tknzr = new Tokenizer();

	public Language1(ArrayList<String> lineList) {
		this.tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isS());
			tknzr.resetTokenizer();
		}
	}

	public boolean isS() {
		if (tknzr.getCurrToken() == 'a') {
			tknzr.getNextToken();
			isS();
			if(tknzr.getCurrToken() == 'c') {
				tknzr.getNextToken();
				return isB();
			}
			
		} else if (tknzr.getCurrToken() == 'b') {
			tknzr.getNextToken();
			return tknzr.isEndOfString();
		} else 
			return isA();
		return false;
	}

	public boolean isA() {

		if (tknzr.getCurrToken() == 'c') {
			tknzr.getNextToken();
			return isA();
		}

		if (tknzr.getCurrToken() == 'd') {
			tknzr.getNextToken();
			if (tknzr.isEndOfString()) {
				return true;
			}
		}
		return false;
	}

	public boolean isB() {
		if (tknzr.getCurrToken() == 'a') {
			return isA();
		}
		if (tknzr.getCurrToken() == 'd') {
			tknzr.getNextToken();
			if (tknzr.isEndOfString()) {
				return true;
			}
		}

		return false;
	}
}