import java.io.FileNotFoundException;
import java.util.Scanner;

// Declares a new Recognizer
public class Recognizer {
	static Recognizer recog = new Recognizer();

	public static void main(String[] args) {
		// can make switch here based on input to route to each solution or if
		// else
		System.out.println("Please provide a valid file path or type 'quit' to exit");
		new LexicalAnalyzer(recog.getInput());

		System.out.println("\nProgram complete - terminated.");
	}

	// Creates a Scanner that can be used to receive user input
	private String getInput() {
		Scanner menuInput = null;
		boolean valid = false;
		String userIn = "";
		while (valid != true) {
			menuInput = new Scanner(System.in);
			userIn = menuInput.nextLine();
			valid = true;
		}
		if (userIn.equals("quit")) {
			System.out.println("Program Terminated");
			System.exit(0);
		}
		return userIn;
	}
}