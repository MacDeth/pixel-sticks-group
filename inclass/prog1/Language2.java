import java.util.ArrayList;

public class Language2{
	static Tokenizer tknzr = new Tokenizer();
	public Language2(ArrayList<String> lineList) {
		this.tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + check());
		}
	}

  public static boolean check(){
    return isAssign();
  }

  private static boolean isAssign(){
    if(isID() && tknzr.getNextToken() == '=' && isExpression())
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
    char token = tknzr.getNextToken();
    for(int i = 0; i < query.length; i++){
      if(token == query[i]){
        return true;
      }
    }
    return false;
  }
}
