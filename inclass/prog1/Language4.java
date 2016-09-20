
public class Language4 {
	
	public boolean isS(Tokenizer line){
		boolean returnVal = false;
		
		if(isA(line)){
			line.getNextToken();
			
			if(line.getCurrToken() == 'a'){
				line.getNextToken();
				
				if(isB(line)){
					line.getNextToken();
					
					if(line.getCurrToken() == 'b'){
						line.getNextToken();
						
						if(line.isEndOfString()){
							returnVal = true;
						}
					}
				}
			}
		}
		return returnVal;
	}
	
	public boolean isA(Tokenizer line){
		boolean returnVal = false;
		
		if(line.getCurrToken() == 'b'){
			line.getNextToken();
			
			if(isA1(line)){
				line.getNextToken();
				returnVal = true;
			}
		}
		return returnVal;
	}
	
	public boolean isA1(Tokenizer line){
		boolean returnVal = false;
		
		if(line.isEndOfString()){
			line.getNextToken();
			returnVal = true;
		}
		else if(line.getCurrToken() == 'b'){
			line.getNextToken();
			
			if(isA1(line)){
				line.getNextToken();
				returnVal = true;
			}
		}
		
		return returnVal;
	}
	
	public boolean isB(Tokenizer line){
		boolean returnVal = false;
		
		if(line.getCurrToken() == 'a'){
			line.getNextToken();
			
			if(isB(line)){
				line.getNextToken();
				returnVal = true;
			}
		}
	
		if(line.getCurrToken() == 'a'){
			line.getNextToken();
			returnVal = true;
		}
		return returnVal;
	}
}
