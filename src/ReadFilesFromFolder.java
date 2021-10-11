import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class ReadFilesFromFolder {
	public ArrayList<String> getAllFiles(File folder) {
		String temp = "";

		ArrayList<String> fileList = new ArrayList<String>();

		File[] fileObjects = folder.listFiles();

		for (File fileEntry : fileObjects) {
			if (fileEntry.isDirectory()) {
				getAllFiles(fileEntry);
			} else {
				if (fileEntry.isFile()) {
					temp = fileEntry.getName();
					fileList.add(temp);
				}
			}
		}
		return fileList;
	}

	public void showFiles() {
		ArrayList<String> fileList = getAllFiles(MainMenu.folderObject);
		Iterator<String> it = fileList.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void sortFiles() {
		ArrayList<String> fileList = getAllFiles(MainMenu.folderObject);
		Collections.reverse(fileList);
		Iterator<String> it = fileList.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void addNewFile() {
		boolean success = false;

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter file name to be created: ");
		String filename = sc.nextLine();

		File myFile = new File(MainMenu.folderPath, filename);

		if (myFile.exists()) {
			System.out.println("File already exists");
		} else {
			try {
				success = myFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (success) {
				try {
					FileWriter myWriter = new FileWriter(MainMenu.folderPath + "\\" + filename);
					System.out.println("Enter the content of the file: ");
					String content = sc.nextLine();
					myWriter.write(content);
					myWriter.close();

					System.out.printf("Successfully created new file: " + filename);
				} catch (Exception e) {
					System.out.println("File i/o exception");
				}

			} else {
				System.out.printf("Failed to create new file: %s%n", myFile);
			}
		}
	}

	public void removeFile() {
		showFiles();
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter a file name to remove from the given list: ");
		String filename = sc.nextLine();
		
		File file = new File(MainMenu.folderPath + "\\" + filename);
		
		if (file.delete()) {
			System.out.println("\nFile deleted successfully\n\nHere is the updated list:\n");
			showFiles();
		} else {
			System.out.println("Failed to delete the file");
		}
	}
	
	public void searchFile() {
		ArrayList<String> fileList = getAllFiles(MainMenu.folderObject);
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter a file name to search: ");
		String filename = sc.nextLine();
		Iterator<String> it = fileList.iterator();
		boolean fileFound = false;
		while (it.hasNext()) {
			if(filename.equals(it.next())) {
				fileFound = true;
				System.out.println("File found !!!");
				break;
			}
		}
		if(!fileFound) {
			System.out.println("File not found");
		}
	}
}
