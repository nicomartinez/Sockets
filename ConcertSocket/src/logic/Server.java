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
public class Server implements Runnable{

	private ServerSocket serverSocket;
	private ArrayList<Connect> connections;
	private Socket socketAux;
	private String ip;
	private int port;

	private boolean stop;
	private boolean pause;
	private Thread thread;
	private long speed;
	
	private int capacity;
	private int cup;
	private Concert concert;
	

	public long getSpeed() {
		return this.speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	

	/**
	 * este es el metodo constructor de la clase Server.java
	 */
	public Server() {
		cup = 0;
		pause = false;
		stop = false;
		speed = 1000;
		
		port = 3500;
		connections = new ArrayList<Connect>();
		thread = new Thread(this);
		
	}
	
	public Server(int capacity, String name, int price, int port) {
		cup = 0;
		pause = false;
		stop = false;
		speed = 1000;
		
		this.port = port;
		concert = new Concert(name, price);
		connections = new ArrayList<Connect>();
		thread = new Thread(this);
		
	}


	public void initServer(){
		if(serverSocket == null){
			try {
				serverSocket = new ServerSocket(port);
				start();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println();
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
					connections.add(new Connect(socketAux));
					cup += 1;
					System.out.println("nueva conexion aceptada");
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

	/**
	 * @return the concert
	 */
	public Concert getConcert() {
		return concert;
	}

	/**
	 * @param concert the concert to set
	 */
	public void setConcert(Concert concert) {
		this.concert = concert;
	}

	
}
