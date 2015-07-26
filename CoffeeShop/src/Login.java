import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class Login{
	Connection conn=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
	
	private JFrame frame;
	private JTextField txt_username;
	private JPasswordField txt_password;

	/**
	 * Launch the application.
	 */
	public void LoginScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
		//initComponents();
		//conn=javaconnect.ConnecrDB();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn=javaconnect.ConnecrDB();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(125, 47, 170, 117);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(6, 19, 62, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 44, 62, 14);
		panel.add(lblPassword);
		
		txt_username = new JTextField();
		txt_username.setBounds(75, 16, 86, 20);
		panel.add(txt_username);
		txt_username.setColumns(10);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(75, 41, 86, 20);
		panel.add(txt_password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "in JButton1");
				//conn=javaconnect.ConnecrDB();
				String sql="select * from Employee_Info where username=? and password=? ";
				//JOptionPane.showMessageDialog(null, sql);
				try {
					//JOptionPane.showMessageDialog(null, "in try block");
					pst=conn.prepareStatement(sql);
					pst.setString (1,txt_username.getText());
					pst.setString (2,txt_password.getText());
					//JOptionPane.showMessageDialog(null, "execute query");
					ResultSet rs=pst.executeQuery();
					
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Username and Password is correct");
						Admin s= new Admin();
						//s.setVisible(true);
						s.AdminScreen();
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Username and Password is not correct");
					}
					
					
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Can't navigate to Admin");
					
				}
			}
		});
		btnLogin.setBounds(6, 87, 76, 23);
		panel.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Exiting Login Screen");
		        System.exit(0);
			}
		});
		btnCancel.setBounds(88, 87, 76, 23);
		panel.add(btnCancel);
	}
}
