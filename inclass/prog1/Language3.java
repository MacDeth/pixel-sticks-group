/*Drew Richardson, James D'Angelo, Taylor Miller
Programming Assignment 1
Language 3 Class*/

import java.util.ArrayList;

public class Language3{
  private static Tokenizer tknzr;
  public Language3(ArrayList<String> lineList) {
		tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isA2());
      tknzr.resetTokenizer();
		}
	}

  private static boolean isA2(){
    int ccount= 0;
    
    while(getNext() == 'a')
      ccount++;

    if(tknzr.getCurrToken() == 'b')
      ccount++;

    else if(tknzr.getCurrToken() == 'c')
      ccount--;

    while(getNext() == 'b')
      ccount++;

    if(tknzr.getCurrToken() == 'c')
      ccount--;

    while(getNext() == 'c')
      ccount--;

    if(ccount == 0)
      return true;

    return false;
  }

  private static char getNext(){
    char temp = tknzr.getCurrToken();
    tknzr.getNextToken();
    return temp;
  }
}
