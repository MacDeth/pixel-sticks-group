import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LexicalAnalyzer {
	private File file;
	private FileInputStream scan;
	private ArrayList<Integer> stream;
	private ArrayList<Token> tokens;
	private int currTokenPosition;
	public boolean invalid;
	
	//constructor for lexanalyzer takes filename and reads file char by char
	public LexicalAnalyzer(String filename){
		invalid = false;
		this.file = new File(filename);
		try {
			scan = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stream = new ArrayList<Integer>();
		tokens = new ArrayList<Token>();
		currTokenPosition = -1;
		
		while(readNextToken() != "");
		currTokenPosition = 0;
		Parser parser = new Parser(this);
	}
	//grabs current token
	public String getCurrToken(){
		if(tokens.size() > currTokenPosition){
			tokens.get(currTokenPosition).tryingToUse = true;
			return tokens.get(currTokenPosition).content;
		}
		return "";
	}
	//Gets next token and increments position
	public String getNextToken() {
		tokens.get(currTokenPosition).tryingToUse = true;
		if(currTokenPosition >= tokens.size()) return null;
		currTokenPosition++;
		return getCurrToken();
	}
	
	//Recursively goes through and builds language from tokens
	public String readNextToken(){
		String currToken;
		if(stream.size() == 1 && stream.get(0) == (int)';'){
			currToken = ";";
			stream.remove(0);
			Token t = new Token();
			t.content = currToken;
			tokens.add(t);
			currTokenPosition++;
			return currToken;
		}
		
		currToken = "";
		boolean isTerminal = false;
		int c;
		
		try{
			do{
				c = scan.read();
				if(c == -1) {
					isTerminal = true;
				}
				if((c <= 32 && c >= 0) || c == 127){
					if(stream.size() > 0){
						isTerminal = true;
					}
				}
				if(!isTerminal){
					if(!(c <= 32 && c >= 0) || c == 127) {
						stream.add(c);
					}
					if(c == (int)';'){
						isTerminal = true;
					}
				}
			} while(!isTerminal);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		while(stream.size() > 0 && stream.get(0) != ';'){
			currToken += (char)(int)stream.get(0);
			stream.remove(0);
		}
		if(currToken.length() > 0){
			Token t = new Token();
			t.content = currToken;
			tokens.add(t);
		}
		currTokenPosition++;
		return currToken;
	}
	//retrieves current character
	public char getCurrCharacter(){
		if(currTokenPosition >= tokens.size()) return (char)-1;
		return tokens.get(currTokenPosition).getCurrChar();
	}
	//retrieves next character
	public char getNextCharacter(){
		if(currTokenPosition >= tokens.size()) return (char)-1;
		return tokens.get(currTokenPosition).getNextChar();
	}
	//checks for end of string
	public boolean isEndOfString(){
		if(currTokenPosition >= tokens.size()) return false;
		return tokens.get(currTokenPosition).isEndOfString();
	}
	//checks current position in token index
	public int getCurrPosition(){
		if(currTokenPosition >= tokens.size()) return (char)-1;
		return tokens.get(currTokenPosition).getCurrPosition();
	}
	//resets position in token index
	public void resetCurrPosition(){
		if(currTokenPosition >= tokens.size()) return;
		tokens.get(currTokenPosition).resetCurrPosition();
	}
	//marks used tokens and prints token(s)
	public void setUsed(){
		System.out.print(">>");
		for(int i = 0; i < tokens.size(); i++){
			if(tokens.get(i).tryingToUse == true){
				System.out.print(" " + tokens.get(i).content);
				tokens.get(i).tryingToUse = false;
				tokens.get(i).hasBeenUsed = true;
			}
		}
		System.out.println();
		
	}
	//moves token index back one and unmarks that token usage
	public void goBackOne(){
		tokens.get(currTokenPosition).tryingToUse = false;
		currTokenPosition--;
	}
	//moves token index to beginning and unmarks all token usage
	public void goBackAll(){
		for(int i = 0; i < tokens.size(); i++){
			if(tokens.get(i).tryingToUse){
				tokens.get(i).tryingToUse = false;
			}
		}
		currTokenPosition = 0;
		
		while(currTokenPosition < tokens.size() && tokens.get(currTokenPosition).hasBeenUsed){
			currTokenPosition++;
		}
	}
	//retrieves current token position
	public int getCurrTokenPosition(){
		return currTokenPosition;
	}
	
	
}
