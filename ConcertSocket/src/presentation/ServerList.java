/**
 * esta clase se encarga de .
 */
package presentation;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import logic.Concert;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class ServerList extends JFrame{
	
	private ArrayList<Concert> servers;
	private JTextArea listServers;
	
	/**
	 * este es el metodo constructor de la clase ServerList.java
	 */
	public ServerList() {
		super("Lista de Servidores");
		
	}
}
