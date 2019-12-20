/**
 * 
 */
package com.raju.filemanagement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author raju
 *
 */
public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	private static Scanner scanner = null;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// display();

		// copyFiles();

		// copyFilesDummy();

		/*
		 * try { listAllFiles(); } catch (IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		//deleteFile();
		
		deleteFolder();

		// Delete folder
		// Delete files
		
		// Edit Files
		// Create files--- extensions
		// File Permissions
		// Zip files
		// Move Folders/Files

		System.out.println("\nProgram terminating...");
	}

	private static void copyFilesDummy() {
		String sourcePath = null;
		String destinationPath = null;

		try {
			LOGGER.info("Please enter source folder:");
			scanner = new Scanner(System.in);
			sourcePath = scanner.nextLine();

			LOGGER.info("Please enter destination folder:");
			scanner = new Scanner(System.in);
			destinationPath = scanner.nextLine();
			copyFiles(sourcePath, destinationPath);
		} catch (Exception e) {
			LOGGER.info("Something went wrong. Please validate file path.");
		}
	}

	private static void display() {
		LOGGER.info("Please enter:");
		scanner = new Scanner(System.in);
		String filePath = scanner.nextLine();
		if (filePath != null && filePath == "exit") {
			existFromSystem();
		} else if (filePath == null || filePath.isEmpty()) {
			System.out.println("Pease enter file/folder path:");
		} else {
			browseResource(filePath);
		}
	}

	private static void existFromSystem() {
		System.exit(1);
	}

	private static void browseResource(String filePath) {
		try {
			File files = new File(filePath);
			if (files.isDirectory()) {
				System.out.print("\nFolder :" + files.getName() + "\n");
				String[] fileArray = files.list();
				List<String> filesList = new ArrayList<>();
				List<String> dirList = new ArrayList<>();
				for (String fileName : fileArray) {
					File resource = new File(files, fileName);
					if (resource.isDirectory()) {
						dirList.add(resource.getName());
					} else if (resource.isFile()) {
						filesList.add(resource.getName());
					} else {
					}
				}

				System.out.println("\n**********************\n Directories:");
				Stream<String> streamDir = dirList.stream();
				streamDir.forEach(e -> System.out.print(e + "\t"));

				System.out.println("\n**********************\n Files:");
				Stream<String> streamFiles = filesList.stream();
				streamFiles.forEach(e -> System.out.print(e + "\t"));
			}
		} catch (Exception e) {
			System.out.println("something wrong." + e.getMessage());
		}

	}

	private static boolean isDirectory(String filePath) {
		return Files.isDirectory(Paths.get(filePath), LinkOption.NOFOLLOW_LINKS);
	}

	private static void copyFiles() {

		// C:\Users\raju\eclipse-workspace\FileManagementProject\src\com\raju\filemanagement

		Path sourcePath = Paths.get(
				"C:\\Users\\raju\\eclipse-workspace\\FileManagementProject\\src\\com\\raju\\filemanagement\\file1.txt");
		Path destPath = Paths.get(
				"C:\\Users\\raju\\eclipse-workspace\\FileManagementProject\\src\\com\\raju\\filemanagement\\file2.txt");
		try {
			Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void copyFiles(String sourcePath, String destPath) {
		try {
			Files.copy(Paths.get(sourcePath), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("copied successfully");
		} catch (IOException e) {
			LOGGER.info("Something went wrong." + e.getMessage());
		}

	}

	@SuppressWarnings("unused")
	private static void listAllFiles() throws IOException {
		Path start = Paths.get("C:\\Users\\raju\\Desktop\\");
		try (Stream<Path> stream = Files.walk(start, 1)) {
			List<String> collect = stream.map(String::valueOf).sorted().collect(Collectors.toList());
			collect.forEach(System.out::println);
		}
	}

	private static void listAllFiles1() throws IOException {
		Path start = Paths.get("C:\\Users\\\\raju\\\\Desktop\\\\");
		try (Stream<Path> stream = Files.walk(start, 2)) {
			List<String> collect = stream.map(String::valueOf).sorted().collect(Collectors.toList());
			collect.forEach(System.out::println);
		}
	}

	private static void deleteFile() {
		System.out.print("Enter your file to delete : ");
		Scanner scanner = new Scanner(System.in);
		File inputFile = new File(scanner.nextLine());
		if (inputFile.delete())
			System.out.println("Your file is deleted......");
		else
			System.out.println("Your entered file is doesn't exist......");
	}
	
	private static void deleteFolder() {
		System.out.print("Enter your folder to delete : ");
		Scanner scanner=new Scanner(System.in);
		File inputFile=new File(scanner.nextLine());
		if(inputFile.delete())
			System.out.println("Folder deleted......");
		else
			System.out.println("Your entered Folder is doesn't exist......");
	}

}




















