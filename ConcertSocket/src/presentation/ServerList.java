/**
 * esta clase se encarga de .
 */
package presentation;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import logic.Server;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class ServerList extends JFrame{
	
	private ArrayList<Server> servers;
	private JTextArea listServers;
	/**
	 * este es el metodo constructor de la clase ServerList.java
	 */
	public ServerList() {
		super("Lista de Servidores");
		// TODO Auto-generated constructor stub
	}
}
