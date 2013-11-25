/**
 * esta clase se encarga de .
 */
package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
		pause = false;
		stop = false;
		//thread = new Thread(this);
		speed = 1000;
		
		port = 3500;
		connections = new ArrayList<Connect>();
		thread = new Thread(this);
		
	}
	
	public Server(int capacity, String name, int price) {
		pause = false;
		stop = false;
		//thread = new Thread(this);
		speed = 1000;
		
		port = 3500;
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
				socketAux = serverSocket.accept();
				connections.add(new Connect(socketAux));
				System.out.println("nueva conexion aceptada");
			} catch (IOException e1) {
				e1.printStackTrace();
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

}
