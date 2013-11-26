/**
 * esta clase se encarga de .
 */
package logic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import presentation.WindowCustomer;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class Customer implements Runnable{

	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private String ip;
	private int port;

	private boolean stop;
	private boolean pause;
	private Thread thread;
	private long speed;
	
	private int option;
	
	private WindowCustomer customer;

	public long getSpeed() {
		return this.speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	/**
	 * este es el metodo constructor de la clase Customer.java
	 */
	public Customer() {
		pause = false;
		stop = false;
		speed = 1000;
		thread = new Thread(this);
		
		try {
			//socket = new Socket(InetAddress.getByName("localhost"), 4500);
			socket = new Socket("10.0.39.59", 3900);
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			System.out.println("el host no existe");
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("el puerto no disponible");
		}

		try {
			inputStream = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("error creando canales de entrada");
		}
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("error creando el canal de salida");
		}

		start();
	}
	
	public Customer(int port, String ip, WindowCustomer customer) {
		pause = false;
		stop = false;
		speed = 1000;
		thread = new Thread(this);
		
		this.customer = customer;
		
		try {
			//socket = new Socket(InetAddress.getByName("localhost"), 4500);
			socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			//e.printStackTrace();
			System.out.println("el host no existe");
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("el puerto no disponible");
		}

		try {
			inputStream = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("error creando canales de entrada");
		}
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("error creando el canal de salida");
		}

		start();
	}


	public void closeConnetion(){
		try {
			inputStream.close();
		} catch (IOException e) {
			System.out.println("no se pudo cerrar canal de entrada");
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			System.out.println("no se pudo cerrar canal de salida");
		}
		try {
			socket.close();
		} catch (IOException e) {
			System.out.println("no se pudo desconectar del servidor");
		}
	}

	public void initCommunication(int option){
		try {
			outputStream.writeInt(option);

			//outputStream.writeUTF("mensaje");
		} catch (IOException e) {
			//e.printStackTrace();
			
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (!stop) {

			try {
				option = inputStream.readInt();
			} catch (IOException e1) {
				//e1.printStackTrace();
				System.out.println("no se recibio la opcion");
			}
			switch (option) {
			case 1:
				try {
					//System.out.println("se recibio " + inputStream.readUTF());
					customer.getAreaConcert().setText(customer.getAreaConcert().getText() + inputStream.readUTF());
				} catch (IOException e1) {
					//e1.printStackTrace();
					System.out.println("no");
				}
				break;
			case 2:
				initCommunication(3);				
				break;
			case 3:

				break;
			default:
				break;
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
