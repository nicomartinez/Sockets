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
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Customer;

/**
 * @author Juan Nicolas Martinez Fagua
 *
 */
public class WindowCustomer extends JFrame implements ActionListener{

	private JLabel lblDescription;
	private JLabel lblIP;
	private JLabel lblPort;
	
	private JTextField txtIP;
	private JTextField txtPort;
	
	private JButton btnConnect;
	private JButton btnShowList;
	
	private JTextArea areaConcert;
	private Customer customer;
	
	/**
	 * este es el metodo constructor de la clase ConcertManagerCustomer.java
	 */
	public WindowCustomer() {
		super("Usuario");
		setSize(400, 400);
		setLayout(new FlowLayout());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		init();
		addComponents();
		this.setVisible(true);
	}
	
	public void init(){
		lblDescription = new JLabel("<html>En esta ventana se puede conectar <br>a un concierto disponible</html>");
		lblIP = new JLabel("escriba la direccion IP del servidor");
		lblPort = new JLabel("escriba el puerto del servidor");
		
		txtIP = new JTextField(10);
		txtPort = new JTextField(13);
		
		btnConnect = new JButton("conectar");
		btnConnect.addActionListener(this);
		btnShowList = new JButton("mostrar servidores");
		btnShowList.addActionListener(this);
		
		areaConcert = new JTextArea(15,30);
		areaConcert.setEditable(false);
		
		//customer = new Customer();
		
		
	}
	
	public void addComponents(){
		add(lblDescription);
		add(areaConcert);
		add(lblIP);
		add(txtIP);
		add(lblPort);
		add(txtPort);
		add(btnConnect);
		add(btnShowList);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("mostrar servidores")){
			ServerList list = new ServerList();
		}
		else
		if(e.getActionCommand().equals("desconectar")){
			customer.closeConnetion();
			btnConnect.setText("conectar");
		}
		else
		if(e.getActionCommand().equals("conectar")&& !txtIP.getText().isEmpty() && !txtPort.getText().isEmpty()){
			try {
				//WindowAux aux = new WindowAux()
				customer = new Customer(Integer.parseInt(txtPort.getText()), txtIP.getText(), this);
				customer.initCommunication(1);
				btnConnect.setText("desconectar");
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "por favor verifique la informacion");
			}
		}else{
			JOptionPane.showMessageDialog(this, "Por Favor llene todos los datos");
		}
		
		
	}

	/**
	 * @return the txtIP
	 */
	public JTextField getTxtIP() {
		return txtIP;
	}

	/**
	 * @param txtIP the txtIP to set
	 */
	public void setTxtIP(JTextField txtIP) {
		this.txtIP = txtIP;
	}

	/**
	 * @return the txtPort
	 */
	public JTextField getTxtPort() {
		return txtPort;
	}

	/**
	 * @param txtPort the txtPort to set
	 */
	public void setTxtPort(JTextField txtPort) {
		this.txtPort = txtPort;
	}

	/**
	 * @return the areaConcert
	 */
	public JTextArea getAreaConcert() {
		return areaConcert;
	}

	/**
	 * @param areaConcert the areaConcert to set
	 */
	public void setAreaConcert(JTextArea areaConcert) {
		this.areaConcert = areaConcert;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
