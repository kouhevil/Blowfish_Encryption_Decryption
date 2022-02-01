/**
 * @author Lothare KOUHEVI and DARA Morgan
 * Class represents file class
 */
package fr.esigelec.blowfish.model;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JFrame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyFile {
	
	//Get the logger for severals logs
	private static final Logger log = LogManager.getLogger("fr.esigelec.blowfish");

	
	/**
	 * Convert a file to list of string (by reading) from a file target
	 * @param fileLocation : file target location
	 * @return ArrayList<String> if that alright
	 * 		   null else 					
	 */
	public static ArrayList<String> readFile(String fileLocation){
		ArrayList<String> linesFile = new ArrayList<String>();

		InputStream ins = null;
		Scanner obj = null;
		try {
			log.info("Opening file...");			
			ins = new FileInputStream(fileLocation);
			InputStreamReader inp = new InputStreamReader(ins, "UTF-8");
			obj = new Scanner(inp);

			while (obj.hasNextLine())
				linesFile.add(obj.nextLine());

			return linesFile;
		} 

		catch (FileNotFoundException e) {
			log.info("File not found !");
			log.error(e.getMessage() + " :" + e.getCause());
			JOptionPane.showMessageDialog(new JFrame(), "File not found !", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} 

		catch (UnsupportedEncodingException e) {
			log.info("File not found !");
			log.error(e.getMessage() + " :" + e.getCause());
			JOptionPane.showMessageDialog(new JFrame(), "Unsupported encoding exception !", "Error", JOptionPane.ERROR_MESSAGE);			
			return null;
		}

		finally {
			if(!(obj == null)) {
				try {
					ins.close();
					obj.close();
					log.info("Closing file...");
				} catch (IOException e) {					
					log.info("File not found !");
					log.error(e.getMessage() + " :" + e.getCause());
					JOptionPane.showMessageDialog(new JFrame(), "Error occurs during closing file !", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}	
		}
	}
	
	
	/**
	 * Verify the validity of the file target after a reading
	 * @param locationFile
	 * @return true if file is validate
	 * 		   false if the file contains any line
	 * 				 or file is not found, don't exist...
	 */
	public static boolean validateFileChosen(String locationFile) {
		boolean result = false;
		ArrayList<String> fileLines = readFile(locationFile);
		if(fileLines != null) {
			if(!fileLines.isEmpty()) 
				result = true;
			else
				JOptionPane.showMessageDialog(new JFrame(), "File chosen is empty!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(new JFrame(), "The file chosen is null !", "Error", JOptionPane.ERROR_MESSAGE);

		return result;		
	}
	
	
	/**
	 * Write in a file a list of string 
	 * @param linesFile
	 * @param nameFile
	 * @param location
	 * @return true writing is successfully 
	 * 		   false else or an error occurs during the writing
	 */
	public static boolean writeInFile(ArrayList<String> linesFile, String nameFile, String location) {
		if(linesFile == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Writing in file failed !", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {

			try {		
				Path file = Paths.get(location+"/"+nameFile);
				Files.write(file, linesFile, Charset.forName("UTF-8"));
				return true;
			} catch (IOException e) {
				log.info("Error occurs during writing a file !");
				log.error(e.getMessage() + " :" + e.getCause());
				JOptionPane.showMessageDialog(new JFrame(), "Errors occur during writing in the file !", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}

	}


	public static void main(String[] args) {

		String locationFile = "D:/Me/Windows Key.txt";

		ArrayList<String> linesFile = readFile(locationFile);

		writeInFile(linesFile, "test.txt", "D:/Me/");
	}

}
