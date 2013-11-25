/**
 * esta clase se encarga de .
 */
package logic;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class Concert implements Runnable{

	private String name;
	private ArrayList<Song> songs;
	private int priceSong;
	private int price;
	
	private boolean stop;
	private boolean pause;
	private Thread thread;
	private long speed;
	private Connect connect;
	/**
	 * este es el metodo constructor de la clase Concert.java
	 */
	public Concert() {
		pause = false;
		stop = false;
		speed = 1000;
		thread = new Thread(this);
	}
	
	public Concert(String name, int price) {
		this.name = name;
		this.price = price;
		this.priceSong = priceSong;
		pause = false;
		stop = false;
		speed = 1000;
		thread = new Thread(this);
	}
	
	public void fillSongs(){
		if(name.equals("Concierto pop")){
			songs.add(new Song("g", 1000));
			songs.add(new Song("h", 2000));
			songs.add(new Song("i", 1500));
			songs.add(new Song("j", 3000));
			songs.add(new Song("k", 500));		
		}
		else
		if(name.equals("Concierto rock")){
			songs.add(new Song("a", 1000));
			songs.add(new Song("b", 2000));
			songs.add(new Song("c", 1500));
			songs.add(new Song("d", 3000));
			songs.add(new Song("e", 500));
		}
		else 
		if(name.equals("Concierto electronica")){
			songs.add(new Song("l", 1000));
			songs.add(new Song("m", 2000));
			songs.add(new Song("n", 1500));
			songs.add(new Song("o", 3000));
			songs.add(new Song("p", 500));
		}
	}
	
	public int calculateCost(int duration, Song song){
		return price * song.getLife();
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
	
	public long getSpeed() {
		return this.speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!stop) {

			
			
			
			try {
				Thread.sleep(speed);
			} catch (Exception e) {
				e.printStackTrace();
			}

			synchronized (this) {
				while (pause)

					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				if (stop)
					break;
			}
		}

	}
	
	public void start() {
		thread.start();
	}

	synchronized void stop() {
		stop = true;
		pause = false;
		notify();
	}
	synchronized void suspend() {
		pause = true;

	}

	synchronized void resume() {
		pause = false;
		notify();
	}

	/**
	 * @return the connect
	 */
	public Connect getConnect() {
		return connect;
	}

	/**
	 * @param connect the connect to set
	 */
	public void setConnect(Connect connect) {
		this.connect = connect;
	}
	
}
