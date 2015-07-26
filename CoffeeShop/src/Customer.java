import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.channels.ShutdownChannelGroupException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Checkbox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

import java.sql.*;


public class Customer {
	
	Connection conn=null;
	//ResultSet rs=null;
	PreparedStatement pst=null;

	private JFrame frame;
	private JTextField amtArabica;
	private JTextField amtRobusta;
	private JTextField amtTotal;
	private JTextField qtyA;
	private JTextField qtyR;
	private JLabel label;
	private JTextField textField;
	private JTextField textField_1;
	private static int TotQty = 0;
	int qA,qR,Arabica,Robusta,Total, custno;
	private JTextField txt_customer;
	
	

	/**
	 * Launch the application.
	 */
	public void CustomerScreen() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer window = new Customer();
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
	public Customer() {
		initialize();
		conn=javaconnect.ConnecrDB();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 772, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//conn=javaconnect.ConnecrDB();
		
		JButton btnCheckout = new JButton("CHECKOUT & PRINT RECEIPT");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "Insert into Customer (CustomerName, QtyArabica, QtyRobusta, AmtTotal) values (?,?,?,?)";
					//JOptionPane.showMessageDialog(null,"values captured");
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString (1,txt_customer.getText());
					pst.setString (2,qtyA.getText());
					pst.setString (3,qtyR.getText());
					pst.setString (4,amtTotal.getText());
					//JOptionPane.showMessageDialog(null,"getText captured");
					pst.execute();
					
					qtyA.setText(null);
					qtyR.setText(null);
					amtArabica.setText(null);
					amtRobusta.setText(null);
					amtTotal.setText(null);
					txt_customer.setText(null);
					
					JOptionPane.showMessageDialog(null,"Saved");
					
				} catch (Exception e) {
					
				}
			}
		});
		btnCheckout.setBounds(470, 250, 202, 39);
		frame.getContentPane().add(btnCheckout);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtyA.setText(null);
				qtyR.setText(null);
				amtArabica.setText(null);
				amtRobusta.setText(null);
				amtTotal.setText(null);
				txt_customer.setText(null);
			}
		});
		btnClear.setBounds(262, 250, 162, 39);
		frame.getContentPane().add(btnClear);
		
		/*textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(555, 173, 131, 33);
		frame.getContentPane().add(textField);*/
		
		JLabel lblTotalrm = new JLabel("TOTAL (RM):");
		lblTotalrm.setBounds(462, 173, 96, 33);
		frame.getContentPane().add(lblTotalrm);
		
		JPanel panelRobusta = new JPanel();
		panelRobusta.setLayout(null);
		panelRobusta.setBorder(new TitledBorder(null, "Espresso Robusta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRobusta.setBounds(59, 165, 378, 55);
		frame.getContentPane().add(panelRobusta);
		
		JLabel label_1 = new JLabel("Amount (RM):");
		label_1.setBounds(202, 16, 82, 22);
		panelRobusta.add(label_1);
		
		amtRobusta = new JTextField();
		amtRobusta.setBackground(Color.LIGHT_GRAY);
		amtRobusta.setEditable(false);
		amtRobusta.setBounds(294, 17, 47, 20);
		panelRobusta.add(amtRobusta);
		amtRobusta.setColumns(10);
		
		JLabel lblRobustarm = new JLabel("Robusta@RM5");
		lblRobustarm.setBounds(10, 19, 91, 19);
		panelRobusta.add(lblRobustarm);
		
		JLabel lblQty_1 = new JLabel("Qty:");
		lblQty_1.setBounds(111, 18, 29, 18);
		panelRobusta.add(lblQty_1);
		
		qtyR = new JTextField();
		qtyR.setBounds(139, 17, 23, 20);
		panelRobusta.add(qtyR);
		qtyR.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int qA,qR,Arabica,Robusta,Total,TotQty;
				
				try {
					qA=Integer.parseInt((String) qtyA.getText());
					qR=Integer.parseInt((String) qtyR.getText());
					Arabica=qA*5;
					Robusta=qR*5;
					if (qtyA == null){
							Total=Robusta;
							JOptionPane.showMessageDialog(null,"OK1");}
					if (qtyR== null){
							Total=Arabica;
							JOptionPane.showMessageDialog(null,"OK2");}
					else {
						Total=Arabica + Robusta;
						//JOptionPane.showMessageDialog(null,"OK3");
						}
					
					//JOptionPane.showMessageDialog(null,TotQty);
					/*TotQty=0;
					TotQty=TotQty+qA+qR;
					//JOptionPane.showMessageDialog(null,TotQty);*/
					
					
					amtArabica.setText(Integer.toString(Arabica));
					amtRobusta.setText(Integer.toString(Robusta));
					amtTotal.setText(Integer.toString(Total));
					//JOptionPane.showMessageDialog(null,TotQty);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Please enter a valid number");
				}
				
			}
		});
		btnCalculate.setBounds(454, 90, 232, 39);
		frame.getContentPane().add(btnCalculate);
		
		JPanel panelArabica = new JPanel();
		panelArabica.setLayout(null);
		panelArabica.setBorder(new TitledBorder(null, "Espresso Arabica", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelArabica.setBounds(59, 90, 378, 55);
		frame.getContentPane().add(panelArabica);
		
		JLabel label;
		label = new JLabel("Amount (RM):");
		label.setBounds(200, 16, 84, 20);
		panelArabica.add(label);
		
		amtArabica = new JTextField();
		amtArabica.setBackground(Color.LIGHT_GRAY);
		amtArabica.setEditable(false);
		amtArabica.setBounds(294, 16, 47, 20);
		panelArabica.add(amtArabica);
		amtArabica.setColumns(10);
		
		JLabel lblQty = new JLabel("Qty:");
		lblQty.setBounds(112, 16, 30, 20);
		panelArabica.add(lblQty);
		
		qtyA = new JTextField();
		qtyA.setBounds(138, 16, 24, 20);
		panelArabica.add(qtyA);
		qtyA.setColumns(10);
		
		JLabel label_6 = new JLabel("Arabica@RM5");
		label_6.setBounds(10, 19, 92, 17);
		panelArabica.add(label_6);
		
		JLabel label_7 = new JLabel("CUSTOMER");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Tahoma", Font.BOLD, 20));
		label_7.setBounds(304, 32, 120, 25);
		frame.getContentPane().add(label_7);
		
		amtTotal = new JTextField();
		amtTotal.setEditable(false);
		amtTotal.setColumns(10);
		amtTotal.setBackground(Color.LIGHT_GRAY);
		amtTotal.setBounds(541, 173, 131, 33);
		frame.getContentPane().add(amtTotal);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(66, 250, 145, 39);
		frame.getContentPane().add(btnExit);
		
		JLabel lblCustomer = new JLabel("Customer Name");
		lblCustomer.setBounds(66, 64, 96, 14);
		frame.getContentPane().add(lblCustomer);
		
		txt_customer = new JTextField();
		txt_customer.setBounds(158, 61, 131, 20);
		frame.getContentPane().add(txt_customer);
		txt_customer.setColumns(10);
		
		/*amtTotal = new JTextField();
		amtTotal.setBackground(Color.LIGHT_GRAY);
		amtTotal.setEditable(false);
		amtTotal.setBounds(528, 184, 131, 33);
		//panelCustomer.add(amtTotal);
		amtTotal.setColumns(10);*/
		
	}
	
	/* private void TotQtyMethod()
	    {
		 TotQty=0;
		 TotQty=TotQty+qA+qR; // Assigning a value in Customer class;
		 JOptionPane.showMessageDialog(null,TotQty);
	    }
	 
	 public static int getVariable()
	    {
	        return TotQty;
	    }*/
}
