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
		System.out.println("*************************************");
		System.out.println("Here is the list of available files:");
		System.out.println("*************************************");
		ArrayList<String> fileList = getAllFiles(MainMenu.folderObject);
		Iterator<String> it = fileList.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public void sortFiles() {
		System.out.println("**********************************************");
		System.out.println("Here is the list in reverse alphabetic order:");
		System.out.println("**********************************************");
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
		System.out.println("\nEnter a file name to remove from the given list or press ENTER go to Main menu: ");
		String filename = sc.nextLine();
		if(!filename.equals("")) {
			File file = new File(MainMenu.folderPath + "\\" + filename);
			if (file.delete()) {
				System.out.println("\nFile deleted successfully\n\nHere is the updated list:\n");
				showFiles();
			} else {
				System.out.println("Failed to delete the file");
			}
		}
		
	}

	public void searchFile() {
		ArrayList<String> fileList = getAllFiles(MainMenu.folderObject);
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter a file name to search: ");
		String filename = sc.nextLine();
		
		Object[] arr = fileList.toArray();
		
		int searchResult = binarySearch(arr, filename);
		if(searchResult == -1) {
			System.out.println("File not found");
		} else {
			System.out.println("File found !!!");
		}
	}

	public int binarySearch(Object[] arr, String x) {
		int l = 0, r = arr.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;

			int res = x.toLowerCase().compareTo(((String)arr[m]).toLowerCase());

			// Check if x is present at mid
			if (res == 0)
				return m;

			// If x greater, ignore left half
			if (res > 0)
				l = m + 1;

			// If x is smaller, ignore right half
			else
				r = m - 1;
		}

		return -1;
	}
}
