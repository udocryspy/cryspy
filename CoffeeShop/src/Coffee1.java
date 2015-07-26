import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class Coffee1 {

	private JFrame frame;
	private JTextField amtArabica;
	private JTextField amtRobusta;
	private JTextField amtTotal;
	private JTextField qtyA;
	private JTextField qtyR;

	/**
	 * Launch the application.
	 */
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Coffee1 window = new Coffee1();
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
	public Coffee1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 741, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("Login");
		mnFile.add(mnNew);
		
		JMenuItem mntmCoffeeBean = new JMenuItem("Admin");
		mnNew.add(mntmCoffeeBean);
		
		JMenuItem mntmCashier = new JMenuItem("Cashier");
		mnNew.add(mntmCashier);
		
		JMenuItem mntmManager = new JMenuItem("Manager");
		mnNew.add(mntmManager);
		
		JSeparator separator = new JSeparator();
		mnNew.add(separator);
		
		JMenuItem mntmUser = new JMenuItem("Customer");
		mnNew.add(mntmUser);
		
		JMenuItem mnExit = new JMenuItem("Exit");
		mnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(JFrame.EXIT_ON_CLOSE);
				//System.exit(0);
			}
		});
		mnFile.add(mnExit);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 705, 395);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelCustomer = new JPanel();
		tabbedPane.addTab("Customer", null, panelCustomer, null);
		panelCustomer.setLayout(null);
		
		JPanel panelArabica = new JPanel();
		panelArabica.setBorder(new TitledBorder(null, "Espresso Arabica", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelArabica.setBounds(32, 101, 378, 55);
		panelCustomer.add(panelArabica);
		panelArabica.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Amount (RM):");
		lblNewLabel.setBounds(200, 16, 84, 20);
		panelArabica.add(lblNewLabel);
		
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
		
		JLabel lblArabicarm = new JLabel("Arabica@RM5");
		lblArabicarm.setBounds(10, 19, 92, 17);
		panelArabica.add(lblArabicarm);
		
		JPanel panelRobusta = new JPanel();
		panelRobusta.setBorder(new TitledBorder(null, "Espresso Robusta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRobusta.setBounds(32, 176, 378, 55);
		panelCustomer.add(panelRobusta);
		panelRobusta.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Amount (RM):");
		lblNewLabel_1.setBounds(202, 16, 82, 22);
		panelRobusta.add(lblNewLabel_1);
		
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
		qtyR.setBounds(136, 17, 23, 20);
		panelRobusta.add(qtyR);
		qtyR.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int qA,qR,Arabica,Robusta,Total;

				try {
					//spinA=Integer.parseInt((String) spinnerArabica.getValue());
					//spinR=Integer.parseInt((String) spinnerRobusta.getValue());
					qA=Integer.parseInt((String) qtyA.getText());
					qR=Integer.parseInt((String) qtyR.getText());
					Arabica=qA*5;
					Robusta=qR*5;
					//spinR=Integer.parseInt((String) spinnerRobusta.getValue());
					if (Arabica==0){
							Total=Robusta;
							JOptionPane.showMessageDialog(null,"OK1");}
					if (Robusta==0){
							Total=Arabica;
							JOptionPane.showMessageDialog(null,"OK2");}
					else {
						Total=Arabica + Robusta;
						//JOptionPane.showMessageDialog(null,"OK3");
						}
					
					//num1=Integer.parseInt(spinnerArabica.getvalue());
					amtArabica.setText(Integer.toString(Arabica));
					amtRobusta.setText(Integer.toString(Robusta));
					amtTotal.setText(Integer.toString(Total));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Please enter a valid number");
				}
			}
		});
		btnCalculate.setBounds(427, 101, 232, 39);
		panelCustomer.add(btnCalculate);
		
		JLabel lblTotalrm = new JLabel("TOTAL (RM):");
		lblTotalrm.setBounds(435, 184, 96, 33);
		panelCustomer.add(lblTotalrm);
		
		amtTotal = new JTextField();
		amtTotal.setBackground(Color.LIGHT_GRAY);
		amtTotal.setEditable(false);
		amtTotal.setBounds(528, 184, 131, 33);
		panelCustomer.add(amtTotal);
		amtTotal.setColumns(10);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qtyA.setText(null);
				qtyR.setText(null);
				amtArabica.setText(null);
				amtRobusta.setText(null);
				amtTotal.setText(null);
			}
		});
		btnClear.setBounds(32, 261, 202, 39);
		panelCustomer.add(btnClear);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBounds(248, 261, 202, 39);
		panelCustomer.add(btnCancel);
		
		JButton btnCheckout = new JButton("CHECKOUT & PRINT RECEIPT");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//FileWriter writer = new FileWriter("C:\\Customer_Receipt\\CustomerReceipt.txt");
					//BufferedWriter bw = new BufferedWriter(writer);
			
				} catch (Exception eprint) {
					
				}
			}
		});
		btnCheckout.setBounds(460, 261, 202, 39);
		panelCustomer.add(btnCheckout);
		
		JLabel lblCustomer = new JLabel("CUSTOMER");
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomer.setBounds(277, 43, 120, 25);
		panelCustomer.add(lblCustomer);
	

	}

}
