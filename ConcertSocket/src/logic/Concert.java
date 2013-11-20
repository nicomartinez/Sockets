/**
 * esta clase se encarga de .
 */
package logic;

import java.util.ArrayList;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class Concert {

	private String name;
	private ArrayList<Song> songs;
	private int priceSong;
	private int price;
	
	
	/**
	 * este es el metodo constructor de la clase Concert.java
	 */
	public Concert() {
		
	}
	
	public void fillSongs(){
		songs.add(new Song("q"));
		songs.add(new Song("e"));
		songs.add(new Song("r"));
		songs.add(new Song("f"));
		songs.add(new Song("g"));
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
	 * @return the songs
	 */
	public ArrayList<Song> getSongs() {
		return songs;
	}

	/**
	 * @param songs the songs to set
	 */
	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}

	/**
	 * @return the priceSong
	 */
	public int getPriceSong() {
		return priceSong;
	}

	/**
	 * @param priceSong the priceSong to set
	 */
	public void setPriceSong(int priceSong) {
		this.priceSong = priceSong;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
