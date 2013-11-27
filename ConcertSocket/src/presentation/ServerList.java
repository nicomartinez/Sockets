/**
 * esta clase se encarga de .
 */
package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logic.Concert;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class ServerList extends JFrame{
	private JTextArea listServers;
	private JScrollPane pane;
	private JButton btnAccept;
	private ServerSocket serverSocket;
	/**
	 * este es el metodo constructor de la clase ServerList.java
	 */
	public ServerList() {
		super("Lista de Servidores");
		setSize(300, 400);
		setResizable(false);
		setLayout(new FlowLayout());
		
//		ips = new ArrayList<String>();
//		ports = new ArrayList<String>();
		
		listServers = new JTextArea(20,20);
		listServers.setText("___IP_____puerto___\n");
		listServers.setEditable(false);
		fillList();
		
		pane = new JScrollPane();
		pane.setViewportView(listServers);
		
		btnAccept = new JButton("aceptar");
		btnAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getActionCommand().equals("aceptar")){
					dispose();
					
				}
				
			}
		});
		
		
		add(pane);
		add(btnAccept);
		
		setVisible(true);
	}
	
	
	public void fillList(){
		for (int i = 2999; i < 10000; i++) {
			try {
//				if(serverSocket == null)
//					System.out.print("");
//				else
					
				serverSocket = new ServerSocket(i);
			} catch (Exception e) {
					listServers.setText(listServers.getText() + "\n" + serverSocket.getInetAddress().getHostAddress() + "  " +  String.valueOf(i));
				
			}
//			if(serverSocket != null){
				//ips.add(serverSocket.getInetAddress().getHostAddress());
				//ports.add(String.valueOf(i));
//			} 
		}
		
	}
}

