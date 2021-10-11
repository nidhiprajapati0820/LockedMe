import java.io.*;
import java.util.*;

public class MainMenu {
	static final String folderPath = "C:\\Users\\shyam\\Desktop\\Simplilearn Videos\\Phase1Project\\LockedMe";
	static File folderObject = null;
	static String optionValue = "";
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
		MainMenu.optionValue = sc.next();

		switch (MainMenu.optionValue) {
		case "1":
			rd.showFiles();
			break;
		case "2":
			rd.sortFiles();
			break;
		case "3":
			rd.addNewFile();
			break;
		case "4":
			rd.removeFile();
			break;
		case "5":
			rd.searchFile();
			break;
		case "6":
			System.out.println("***************************");
			System.out.println("GoodBye..");
			System.out.println("***************************");
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
				System.out.println("***************************");
				System.out.println("Created by - Nidhi Prajapati");
				System.out.println("***************************");
				System.out.println("\nChoose an option:");
				System.out.println("***************************");
				count++;
			} else {
				System.out.println("\n\nTry again, choose an option:");
				System.out.println("***************************");
			}
			MainMenu.getMenu();
			MainMenu.chooseOption();
		} while (MainMenu.optionValue.equals("1") || 
				MainMenu.optionValue.equals("2") ||
				MainMenu.optionValue.equals("3") ||
				MainMenu.optionValue.equals("4") ||
				MainMenu.optionValue.equals("5"));

		sc.close();
	}
}