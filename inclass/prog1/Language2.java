/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 2 Class*/

import java.util.ArrayList;

public class Language2{
	static Tokenizer tknzr;
	public Language2(ArrayList<String> lineList) {
		tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isAssign());
      tknzr.resetTokenizer();
		}
	}

  private static boolean isAssign(){
    if(isID() && getNext() == '=' && isExpression())
      return true;
    return false;
  }

  private static boolean isExpression(){
    if(isDigit()){
      if(tknzr.isEndOfString() || (checkChars(new char[]{'+', '-'}) && isExpression()))
        return true;
    }
    return false;
  }

  private static boolean isDigit(){
    return checkChars(
      new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}
    );
  }

  private static boolean isID(){
    if(checkChars(new char[]{'a', 'b'}))
      return true;
    return false;
  }

  private static boolean checkChars(char[] query){
    char token = getNext();
    for(int i = 0; i < query.length; i++){
      if(token == query[i]){
        return true;
      }
    }
    return false;
  }

  private static char getNext(){
    char temp = tknzr.getCurrToken();
    tknzr.getNextToken();
    return temp;
  }
}
