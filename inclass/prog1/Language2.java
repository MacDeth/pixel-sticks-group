/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 2 Class*/

import java.util.ArrayList;

public class Language2{
	private static Tokenizer tknzr;

	/*
		This constructor is called once the main Recognizer class/method is
		provided with a language to process the input. This constructor will
		print out the results of the check by itself, rather than the main method.
	*/
	public Language2(ArrayList<String> lineList) {
		tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isAssign());
      tknzr.resetTokenizer(); // After validation, go to the next input line
		}
	}

	/*
		Starting node.
		Also the rule method for the <ASSGN> non-terminal
	*/
  private static boolean isAssign(){
		/*
			Logic to make sure tokens follow the <ID> non-terminal rule
			followed by an equals sign, followed by the <EXPR> non-terminal rule.
			Returns true if all logic equates to true, false otherwise
		*/
    if(isID() && getNext() == '=' && isExpression())
      return true;
    return false;
  }

	/* Rule method for the <EXPR> non-terminal */
  private static boolean isExpression(){
		/*
			All Expressions start with a digit, then have the option of either
			ending there, or continuing on with a plus, minus, or other Expression.
			This returns true if there it reaches the end of the string, or if
			the plus or minus is followed by another valid Expression.
		*/
    if(isDigit()){
      if(tknzr.isEndOfString() || (checkChars(new char[]{'+', '-'}) && isExpression()))
        return true;
    }
    return false;
  }

	/* Rule method for the <DIGIT> non-terminal */
  private static boolean isDigit(){
		/*
			Uses below checkChars method to see if the next token is a digit.
			Returns true if it is, and false otherwise.
		*/
    return checkChars(
      new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}
    );
  }

	/* Rule method for the <ID> non-terminal */
  private static boolean isID(){
		/*
			Uses below checkChars method to see if the next character token is a valid
			identifier, either 'a' or 'b'. Returns true if it is, and false otherwise.
		*/
    if(checkChars(new char[]{'a', 'b'}))
      return true;
    return false;
  }

	/*
		Compares the next token in the tokenizer with an array of characters,
		returns true if it matches any character in the array.
	*/
  private static boolean checkChars(char[] query){
    char token = getNext(); // Save the token
    for(int i = 0; i < query.length; i++){ // Run through the list of characters
      if(token == query[i]){ // If character matches to the provided list...
        return true; // return true.
      }
    }
		/*
			Otherwise, if the search didn't succeed, return false.
		*/
    return false;
  }

	/* Scanner-like shim for the tokenizer class */
  private static char getNext(){
    char temp = tknzr.getCurrToken(); // Save the current token
    tknzr.getNextToken(); // Advance the tokenizer position
    return temp; // Return the old current token.
  }
}
