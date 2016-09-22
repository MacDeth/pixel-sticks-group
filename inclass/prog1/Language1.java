import java.util.ArrayList;

public class Language1 {
	Tokenizer tknzr = new Tokenizer();
	public Language1(ArrayList<String> lineList) {
		this.tknzr = new Tokenizer(lineList);
		for (int i = 0; i < lineList.size(); i++) {
			System.out.println("Line " + i + " was " + isS());
		}
	}
	
	public boolean isS(){
		boolean returnVal = false;
		
		if(tknzr.getCurrToken() == 'a')
		if(isA()){
			tknzr.getNextToken();
			
			if(tknzr.getCurrToken() == 'a'){
				tknzr.getNextToken();
				
				if(isB()){
					tknzr.getNextToken();
					
					if(tknzr.getCurrToken() == 'b'){
						tknzr.getNextToken();
						
						if(tknzr.isEndOfString()){
							returnVal = true;
						}
					}
				}
			}
		}
		return returnVal;
	}
	
	public boolean isA(){
		boolean returnVal = false;
		
		if(tknzr.getCurrToken() == 'b'){
			tknzr.getNextToken();
			
			if(isA1()){
				tknzr.getNextToken();
				returnVal = true;
			}
		}
		return returnVal;
	}
	
	public boolean isA1(){
		boolean returnVal = false;
		
		if(tknzr.isEndOfString()){
			tknzr.getNextToken();
			returnVal = true;
		}
		else if(tknzr.getCurrToken() == 'b'){
			tknzr.getNextToken();
			
			if(isA1()){
				tknzr.getNextToken();
				returnVal = true;
			}
		}
		
		return returnVal;
	}
	
	public boolean isB(){
		boolean returnVal = false;
		
		if(tknzr.getCurrToken() == 'a'){
			tknzr.getNextToken();
			
			if(isB()){
				tknzr.getNextToken();
				returnVal = true;
			}
		}
	
		if(tknzr.getCurrToken() == 'a'){
			tknzr.getNextToken();
			returnVal = true;
		}
		return returnVal;
	}
}