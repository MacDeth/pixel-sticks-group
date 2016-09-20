public class Language3{
  private static Tokenizer tokenizer;

  public static boolean check(Tokenizer line){
    tokenizer = line;
    return isA2();
  }

  private static boolean isA2(){
    if(true)
      return true;
    return false;
  }

  private static boolean isB2(){
    if(true)
      return true;
    return false;
  }
}
