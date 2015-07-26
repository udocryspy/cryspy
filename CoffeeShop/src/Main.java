import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {

	private JFrame frame;
	public int cust;
	public static int custno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Welcome to Coffee Kiosk ");
				//Coffee1 nw = new Coffee1();
				//nw.NewScreen();
				cust=cust+1;
				Customer cs = new Customer();
				cs.CustomerScreen();
			}
		});
		btnCustomer.setBounds(83, 66, 117, 86);
		frame.getContentPane().add(btnCustomer);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome Admin ");
				Login ls = new Login();
				ls.LoginScreen();
			}
		});
		
		btnAdmin.setBounds(229, 66, 117, 86);
		frame.getContentPane().add(btnAdmin);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Thank You for using Coffee Kiosk");
		          System.exit(0);
			}
			
		});
		btnExit.setBounds(170, 178, 89, 23);
		frame.getContentPane().add(btnExit);
	}
	
	 /*private void custMethod()
	    {
		 custno=cust; // Assigning a value in Main class;
		 JOptionPane.showMessageDialog(null,cust);
	    }
	 
	 public static int getVariable()
	    {
	        return custno;
	    }*/
}
