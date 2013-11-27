/**
 * esta clase se encarga de .
 */
package logic;

import java.util.ArrayList;

import persistence.FileManager;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class Song {

	private FileManager fileManager;
	private ArrayList<String> letter;
	private String name;
	private int life;
	/**
	 * este es el metodo constructor de la clase Song.java
	 */
	public Song(String name, int life) {
		this.name = name;
		this.life = life;
		letter = new ArrayList<String>();
		fileManager = new FileManager(name);
		updateSong();
	}
	
	public void updateSong(){
		letter = fileManager.getLetter();
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

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}
	
	
}
