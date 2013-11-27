/**
 * esta clase se encarga de .
 */
package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
	
	private ServerSocket serverSocket;
	private ArrayList<Connect> connections;
	private Socket socketAux;
	private String ip;
	private int port;
	
	private int capacity;
	private int cup;
	//private Concert concert;
	
	private boolean stop;
	private boolean pause;
	private Thread thread;
	private long speed;
	//private Connect connect;
	/**
	 * este es el metodo constructor de la clase Concert.java
	 */
	public Concert() {
		pause = false;
		stop = false;
		speed = 1000;
		
		cup = 0;
		capacity = 0;
		port = 3500;
		connections = new ArrayList<Connect>();
		
		thread = new Thread(this);
	}
	
	public Concert(String name, int price, int capacity, int port) {
		this.name = name;
		this.price = price;
//		this.priceSong = priceSong;
		pause = false;
		stop = false;
		speed = 1000;
		
		cup = 0;
		this.port = port;
		this.capacity = capacity;
		//concert = new Concert(name, price);
		connections = new ArrayList<Connect>();
		songs = new ArrayList<Song>();
		fillSongs();
		thread = new Thread(this);
	}
	

	public void initServer(){
		if(serverSocket == null){
			try {
				serverSocket = new ServerSocket(port);
				start();
			} catch (IOException e) {
				//e.printStackTrace();
				System.out.println("error");
			}
		}
	}
	
	public void disconncet(int numberConnect){
		connections.get(numberConnect).closeConnetion();
		connections.remove(numberConnect);
		cup -= 1;
		
	}

	public void closeServer(){
		for (Connect connection : connections) {
			connection.closeConnetion();
		}
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
		priceSong = price * song.getLife();
		return priceSong; 
	}
	
	public void pasarCancion(Song song){
		speed = song.getLife() / song.getLetter().size();
		for (String letter : song.getLetter()) {
			for (Connect connection : connections) {
				connection.initCommunication(1, letter);
			}
		} 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!stop) {

			System.out.println("esperando conexiones");
			
			
			try {
				if(cup < capacity){
					socketAux = serverSocket.accept();
					connections.add(new Connect(socketAux ,name, price));
					//connections.get(connections.size()-1).initCommunication(2, text);
					cup += 1;
					System.out.println("nueva conexion aceptada");
					if(songs.get(1) == null){
						JOptionPane.showMessageDialog(null, "el concierto terminara en "  + songs.get(0).getLife() + "segundos");
					}
					pasarCancion(songs.get(0));
					songs.remove(0);
					if(songs.get(0) == null){
						closeServer();
						stop();
					}
				}else{
					JOptionPane.showMessageDialog(null, "el cupo permitido esta completo");
				}
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "no se puede realizar conexion");
			}
			
			
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

//	/**
//	 * @return the connect
//	 */
//	public Connect getConnect() {
//		return connect;
//	}
//
//	/**
//	 * @param connect the connect to set
//	 */
//	public void setConnect(Connect connect) {
//		this.connect = connect;
//	}
	
	/**
	 * @return the connections
	 */
	public ArrayList<Connect> getConnections() {
		return connections;
	}

	/**
	 * @param connections the connections to set
	 */
	public void setConnections(ArrayList<Connect> connections) {
		this.connections = connections;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the cup
	 */
	public int getCup() {
		return cup;
	}

	/**
	 * @param cup the cup to set
	 */
	public void setCup(int cup) {
		this.cup = cup;
	}
	
}
