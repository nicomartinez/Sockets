/**
 * esta clase se encarga de .
 */
package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import logic.Server;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class MainServer extends JFrame implements ActionListener{

	private JButton btnCreate;
	
	/**
	 * este es el metodo constructor de la clase MainServer.java
	 */
	public MainServer() {
		super("Generadpr de Servidores");
		setSize(200, 200);
		setLayout(null);
		
		btnCreate = new JButton("Crear Servidor");
		btnCreate.setBounds(30, 90, 150, 30);
		btnCreate.addActionListener(this);
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
//		Server server = new Server();
//		server.initServer();
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Crear Servidor")){
			
		}
		
	}
}
