import java.util.ArrayList;

public class Language1 {
	Tokenizer tknzr = new Tokenizer();

	public Language1(ArrayList<String> lineList) {
		this.tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.printf("%-20s%s%n","Line " + i + " was " + isS(), " | "+ "\tLine data: " + tknzr.getString());
			tknzr.resetTokenizer();
		}
	}
	//Starting point. 
	public boolean isS() {
		//If 'a' we want to kick off recursion on <S> since that should be the only legal possibility
		if (tknzr.getCurrToken() == 'a') {
			tknzr.getNextToken();
			//next character after a is s
			isS();
			//if we've moved past <S> we'll check for c and then go for <B> since thats our only option within <S>
			if(tknzr.getCurrToken() == 'c') {
				tknzr.getNextToken();
				return isB();
			}
		//Check for plain old 'b' since we could just have it alone
		} else if (tknzr.getCurrToken() == 'b') {
			tknzr.getNextToken();
			return tknzr.isEndOfString();
		} else 
			//only other option is the grammar starting with <A>
			return isA();
		return false;
	}
	//Goes down the <A> path. Options are c and terminal d
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
	//Goes down <B> path. Options are <A> and terminal d.
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