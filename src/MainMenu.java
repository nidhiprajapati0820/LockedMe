import java.io.*;
import java.util.*;

public class MainMenu {
	static final String folderPath = "C:\\Users\\shyam\\Desktop\\Simplilearn Videos\\Phase1Project\\LockedMe";
	static File folderObject = null;
	static int optionValue = -1;
	static Scanner sc = null;

	static void getMenu() {
		ArrayList<String> menu = new ArrayList<String>();
		menu.add("Show all files");
		menu.add("Sort files");
		menu.add("Add a new file");
		menu.add("Delete new file");
		menu.add("Search a file");
		menu.add("Exit");

		Iterator<String> it = menu.iterator();
		int count = 0;
		while (it.hasNext()) {
			System.out.println((++count) + ". " + it.next());
		}
	}

	static void chooseOption() {

		ReadFilesFromFolder rd = new ReadFilesFromFolder();
		if (sc.hasNext()) {
			MainMenu.optionValue = sc.nextInt();
		}

		switch (MainMenu.optionValue) {
		case 1:
			rd.showFiles();
			break;
		case 2:
			rd.sortFiles();
			break;
		case 3:
			rd.addNewFile();
			break;
		case 4:
			rd.removeFile();
			break;
		case 5:
			rd.searchFile();
			break;
		case 6:
			System.out.println("Bye..");
			break;
		default:
			System.out.println("Invalid option");
		}
	}

	public static void main(String[] args) {

		sc = new Scanner(System.in);

		MainMenu.folderObject = new File(MainMenu.folderPath);
		int count = 0;
		do {
			if (count == 0) {
				System.out.println("Welcome to LockedMe !!");
				System.out.println("\nChoose an option:");
				count++;
			} else {
				System.out.println("\n\nTry again, choose an option: \n");
			}
			MainMenu.getMenu();
			MainMenu.chooseOption();
		} while (MainMenu.optionValue < 6);

		sc.close();
	}
}