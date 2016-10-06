public class LexAnalyzer{
  private int position = 0;
  private char character;
  private String[] input;

  public void setInput(String input){
    String[] temp = input.split("\\b *"); // Input preprocessing for boundaries
    for(int i = 0; i < temp.length; i++){
      temp[i].trim();
    }
  }

  public Token lex(){

    return Token.END_OF_INPUT; // Placeholder
  }

  public void advance(){

  }

  public char curChar(){
    return character;
  }

  public int pos(){
    return position;
  }

  public char getNext(){
    char temp = character;
    advance();
    return temp;
  }

}
