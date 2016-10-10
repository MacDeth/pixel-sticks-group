import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Recognizer {
	static Recognizer recog = new Recognizer();

	public static void main(String[] args) {
		// can make switch here based on input to route to each solution or if
		// else
		String fileName= recog.getFileName();

	}
	//gets the input from user
	public String getInput() {
		Scanner menuInput = null;
		boolean valid = false;
		String userIn = "";
		while (valid != true) {
			try {
				menuInput = new Scanner(System.in);
				userIn = menuInput.nextLine();
				valid = true;
			} catch (Exception e) {
				System.out.println("Invalid menu option");
				valid = false;
			}
		}
		return userIn;
	}
	//gets language option using getInput() method
	
	//gets file input using getInput() method
	public String getFileName() {
		System.out.println("Please provide a valid file path.");
		LexAnalyzer lex = new LexAnalyzer(getInput());
		while (valid != true) {
			try {
				File file = new File(recog.getInput());
				fileInput = new Scanner(file);
				valid = true;
			} catch (FileNotFoundException e) {
				System.out.println("Please provide a valid file and check the path");
				valid = false;
			}
		}
		return langFile;
	}

}
