import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Recognizer {
	static Recognizer recog = new Recognizer();

	public static void main(String[] args) {
		// can make switch here based on input to route to each solution or if
		// else
		recog.getLangOption();
		recog.getFileContent();

	}

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

	private int getLangOption() {
		int option = 0;
		System.out.println("What would you like to do?\n");
		System.out.println("1.	Parse lang 1.\n");
		System.out.println("2.	Parse lang 2.\n");
		System.out.println("3.	Parse lang 3.\n");
		System.out.println("4.	Parse lang 4.\n");

		option = Integer.parseInt(getInput());
		if (option < 1 || option > 4) {
			System.out.println("Please enter a valid language option\n");
			getLangOption();
		}
		return option;

	}

	private ArrayList<String> getFileContent() {
		ArrayList<String> langFile = new ArrayList<String>();
		boolean valid = false;
		Scanner fileInput = null;
		System.out.println("Please provide a valid file path.");
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

		try {
			while (fileInput.hasNextLine()) {
				langFile.add(fileInput.nextLine());
			}
		} catch (Exception e) {
			System.out.println("Could not parse file properly, please run the program again and provide a valid file.");
			e.printStackTrace();
			System.exit(0);
		}
		return langFile;
	}

}
