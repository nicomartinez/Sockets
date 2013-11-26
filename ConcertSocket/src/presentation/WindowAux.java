/**
 * esta clase se encarga de .
 */
package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import logic.Concert;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class WindowAux extends JFrame{

	private Concert concertAux;
	private JTextArea txtDetails;
	private JButton btnAccept;
	private String name;
	private int price;
	private int capacity;
	private int port;
	/**
	 * este es el metodo constructor de la clase WindowAux.java
	 */
	public WindowAux(String name, int price, int capacity, int port) {
		super("Manejo Servidor");
		setSize(250, 300);
		setLayout(new FlowLayout());
		
		this.name = name;
		this.price = price;
		this.capacity = capacity;
		this.port = port;
		
		txtDetails = new JTextArea(20,20);
		txtDetails.setEditable(false);
		fillText();
		
		btnAccept = new JButton("aceptar");
		btnAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("aceptar")){
					dispose();
				}
				
			}
		});
		
		
		add(txtDetails);
		add(btnAccept);
		this.setVisible(true);
	}
	
	public void fillText(){
		txtDetails.setText("Nombre: " + name + "\n"
				+ "Precio por segundo: " + price + "\n" 
				+ "Cupo permitido: " + capacity + "\n"
				+ "Puerto: " + port);
	}
}
