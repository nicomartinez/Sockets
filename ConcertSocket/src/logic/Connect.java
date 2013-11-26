/**
 * esta clase se encarga de .
 */
package logic;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import presentation.WindowCustomer;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class Connect implements Runnable{

	private Socket socketConnect;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	private boolean stop;
	private boolean pause;
	private Thread thread;
	private long speed;

	private int option;
	
	
	
	
	private BufferedImage bufferedImage;;
	
	/**
	 * este es el metodo constructor de la clase Connect.java
	 */
	public Connect(Socket socket) {
		pause = false;
		stop = false;
		speed = 1000;
		this.socketConnect = socket;
		
		try {
			inputStream = new DataInputStream(socketConnect.getInputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("error creando canales de entrada");
		}
		try {
			outputStream = new DataOutputStream(socketConnect.getOutputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("error creando el canal de salida");
		}
		
		thread = new Thread(this);
		start();
		
		initCommunication("");
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
			socketConnect.close();
		} catch (IOException e) {
			System.out.println("no se pudo desconectar del servidor");
		}
	}

	public void initCommunication(String text){
		try {
			outputStream.writeInt(1);
			outputStream.writeUTF(text);
		} catch (IOException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se puede enviar la cancion");
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
					System.out.println("recibido " + inputStream.readUTF());
				} catch (IOException e1) {
					//e1.printStackTrace();
					System.out.println("no");
				}
				break;
			case 2:
				try {
					System.out.println("asdasasdas" + inputStream.readUTF());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

	public long getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(long speed) {
		this.speed = speed;
	}

	/**
	 * @return the inputStream
	 */
	public DataInputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(DataInputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the outputStream
	 */
	public DataOutputStream getOutputStream() {
		return outputStream;
	}

	/**
	 * @param outputStream the outputStream to set
	 */
	public void setOutputStream(DataOutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	
}
