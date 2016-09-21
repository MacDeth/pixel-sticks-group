public class Language2{
  private static Tokenizer tokenizer;

  public static boolean check(Tokenizer line){
    tokenizer = line;
    return isAssign();
  }

  private static boolean isAssign(){
    if(isID() && tokenizer.getNextToken() == '=' && isExpression())
      return true;
    return false;
  }

  private static boolean isExpression(){
    if(isDigit()){
      if(tokenizer.isEndOfString())
        return true;
      else if(checkChars(new char[]{'+', '-'}) && isExpression())
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
    char token = tokenizer.getNextToken();
    for(int i = 0; i < query.length; i++){
      if(token == query[i]){
        return true;
      }
    }
    return false;
  }
}
