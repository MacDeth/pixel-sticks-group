import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Recognizer {
	static Recognizer recog = new Recognizer();

	public static void main(String[] args) {
		// can make switch here based on input to route to each solution or if
		// else
		ArrayList<String> stringList = recog.getFileContent();
		int choice = 0;
		while (choice != 5) {
			switch(recog.getLangOption()) {
				case 1:
					Language1 langOne = new Language1(stringList);
					break;
				case 2:
					Language2 langTwo = new Language2(stringList);
					break;
				case 3:
					Language3 langThree = new Language3(stringList);
					break;
				case 4:
					Language4 langFour= new Language4(stringList);
					break;
				case 5: 
					recog.getFileContent();
					break;
			}
		}

	}
	//gets the input from user
	private String getInput() {
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
	public int getLangOption() {
		int option = 0;
		System.out.println("What would you like to do?\n");
		System.out.println("1.	Parse lang 1.\n");
		System.out.println("2.	Parse lang 2.\n");
		System.out.println("3.	Parse lang 3.\n");
		System.out.println("4.	Parse lang 4.\n");
		System.out.println("5.	Load new file.\n");

		option = Integer.parseInt(getInput());
		if (option < 1 || option > 5) {
			System.out.println("Please enter a valid language option\n");
			getLangOption();
		}
		return option;

	}
	//gets file input using getInput() method
	public ArrayList<String> getFileContent() {
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
