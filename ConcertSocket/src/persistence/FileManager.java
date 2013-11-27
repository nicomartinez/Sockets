/**
 * esta clase se encarga de .
 */
package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class FileManager {

	private BufferedReader bufferedReader;
	private File file;
	private FileReader fileReader;
	private ArrayList<String> letter;
	private String name;
	
	/**
	 * este es el metodo constructor de la clase File.java
	 */
	public FileManager(String name) {
		file = new File("");
		this.name = file.getAbsolutePath() + "\\src\\Songs\\" + name + ".music";
		System.out.println("nombre"+ this.name);
		letter = new ArrayList<String>();
		openFile();
		readFile();
	}
	
	public void openFile(){
		try {
			fileReader = new FileReader(name);
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			System.out.println("");
		}
		
	}
	
	public void readFile(){
		String line = "";
		//openFile();
		try {
			while ((line = bufferedReader.readLine()) != null ) {
				System.out.println("esta" + line);
				letter.add(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @return the letter
	 */
	public ArrayList<String> getLetter() {
		return letter;
	}

	/**
	 * @param letter the letter to set
	 */
	public void setLetter(ArrayList<String> letter) {
		this.letter = letter;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
}
