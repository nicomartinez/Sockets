/**
 * esta clase se encarga de .
 */
package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logic.Concert;


/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class WindowServer extends JFrame implements ActionListener{

	private JLabel lblDescription;
	private JLabel lblNumberPort;
	private JLabel lblPrice;
	private JLabel lblCapacity;
	private JLabel lblNameConcert;
	
	private JTextField txtNumberPort;
	private JTextField txtPrice;
	private JTextField txtCapacity;
	
	private JComboBox<String> comboConcert;
	private String[] concerts = {"Concierto rock", "Concierto pop", "Concierto electronica"};
	
	private JButton btnCreate;
	private JButton btnStadistics;
	
	private Concert server;
//	private ArrayList<Concert> lst;
	/**
	 * este es el metodo constructor de la clase WindowServer.java
	 */
	public WindowServer(/*ArrayList<Concert> lst*/) {
		super("Crear Servidor");
		setLayout(new FlowLayout());
		setSize(405, 240);
		setResizable(false);
//		this.lst = lst;
		
		init();
		addComponents();
		
		this.setVisible(true);
	}
	
	public void init(){
		lblDescription = new JLabel("<html>En esta ventana se creara el servidor <br>que manejara un concierto</html>");
		lblCapacity = new JLabel("cupo del servidor");
		lblNumberPort = new JLabel("puerto que manejara el servidor");
		lblPrice = new JLabel("precio por segundo que manejara el concierto");
		lblNameConcert = new JLabel("concierto:_________");
		
		txtCapacity = new JTextField(10);
		txtNumberPort = new JTextField(10);
		txtPrice = new JTextField(10);
		
		comboConcert = new JComboBox<String>();
		comboConcert.addItem(concerts[0]);
		comboConcert.addItem(concerts[1]);
		comboConcert.addItem(concerts[2]);
		
		btnCreate = new JButton("Crear Servidor");
		btnCreate.addActionListener(this);
		
		btnStadistics = new JButton("Estadisticas");
		btnStadistics.addActionListener(this);
	}
	
	public void addComponents(){
		add(lblDescription);
		add(lblNumberPort);
		add(txtNumberPort);
		add(lblCapacity);
		add(txtCapacity);
		add(lblPrice);
		add(txtPrice);
		add(lblNameConcert);
		add(comboConcert);
		add(btnCreate);
		add(btnStadistics);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Crear Servidor")){
			if(!txtCapacity.getText().isEmpty() && !txtNumberPort.getText().isEmpty() && !txtPrice.getText().isEmpty()){
				try {
					server = new Concert((String) comboConcert.getSelectedItem(), Integer.parseInt(txtPrice.getText()), Integer.parseInt(txtCapacity.getText()), Integer.parseInt(txtNumberPort.getText()));
					server.initServer();
					//this.dispose();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Por favor verifique los datos ingresados");
				}
			//	lst.add(server);
			}else{
				JOptionPane.showMessageDialog(this, "Por favor ingrese todos los datos correspondientes");
			}
		}
		else
		if(e.getActionCommand().equals("Estadisticas")){
			
		}
	}
	
}

