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
    if(true)
      return true;
    return false;
  }

  private static boolean isDigit(){
    if(true)
      return true;
    return false;
  }

  private static boolean isID(){
    if(true)
      return true;
    return false;
  }

}
