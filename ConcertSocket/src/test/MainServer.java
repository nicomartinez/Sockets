/**
 * esta clase se encarga de .
 */
package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import logic.Concert;

import presentation.WindowServer;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class MainServer extends JFrame implements ActionListener{

	private JButton btnCreate;
	private ArrayList<Concert> lst;
	
	/**
	 * este es el metodo constructor de la clase MainServer.java
	 */
	public MainServer() {
		super("Generadpr de Servidores");
		setSize(300, 100);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		btnCreate = new JButton("Crear Servidor");
		btnCreate.setBounds(50, 20, 150, 30);
		btnCreate.addActionListener(this);
		
		setLst(new ArrayList<Concert>());
		
		add(btnCreate);
		
		setVisible(true);
	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Crear Servidor")){
			WindowServer windowServer = new WindowServer(lst);
		}
		
	}
	
	public static void main(String[] args) {
//		Server server = new Server();
//		server.initServer();
		MainServer mainServer = new MainServer();
	}


	/**
	 * @return the lst
	 */
	public ArrayList<Concert> getLst() {
		return lst;
	}


	/**
	 * @param lst the lst to set
	 */
	public void setLst(ArrayList<Concert> lst) {
		this.lst = lst;
	}
}
