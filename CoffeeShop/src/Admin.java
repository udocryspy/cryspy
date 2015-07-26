import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class Admin {

	private JFrame frame;
	private JTextField txt_totOrders;
	private JTextField txt_totSales;
	private JTextField txt_totEmp;
	private JTextField txt_salEmp;
	private JTextField txt_mnthPayout;
	int totOrders, totSales, totEmp, salEmp, mnthPayout;
	
	Connection conn=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	/*{
	    int totOrders = Customer.getVariable();  // Accessing in Admin class
	}*/

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public void AdminScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
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
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		conn=javaconnect.ConnecrDB();
		
		JPanel panelRevenue = new JPanel();
		panelRevenue.setBorder(new TitledBorder(null, "Revenue", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelRevenue.setBounds(44, 48, 353, 102);
		frame.getContentPane().add(panelRevenue);
		panelRevenue.setLayout(null);
		
		JLabel lblTotalOrders = new JLabel("Total Orders");
		lblTotalOrders.setBounds(20, 19, 100, 14);
		panelRevenue.add(lblTotalOrders);
		
		JLabel lblTotalSales = new JLabel("Total Sales (RM)");
		lblTotalSales.setBounds(20, 44, 100, 14);
		panelRevenue.add(lblTotalSales);
		
		txt_totOrders = new JTextField();
		txt_totOrders.setBounds(192, 11, 86, 20);
		panelRevenue.add(txt_totOrders);
		txt_totOrders.setBackground(Color.LIGHT_GRAY);
		txt_totOrders.setEditable(false);
		txt_totOrders.setColumns(10);
		
		txt_totSales = new JTextField();
		txt_totSales.setBounds(192, 41, 86, 20);
		panelRevenue.add(txt_totSales);
		txt_totSales.setBackground(new Color(192, 192, 192));
		txt_totSales.setEditable(false);
		txt_totSales.setColumns(10);
		
		JButton btnCalculateSales = new JButton("Calculate Sales");
		btnCalculateSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sumA,sumR,sumAT;
				sumA=0;
				sumR=0;
				sumAT=0;
				//String sumArabica, sumRobusta, sumAmtTotal;
				//totSales = totOrders*5;
				
				try {
					String sqlArabica = ("SELECT sum(QtyArabica) FROM Customer");
					//JOptionPane.showMessageDialog(null, sqlArabica);
					
					String sqlRobusta = ("SELECT sum(QtyRobusta) FROM Customer");
					//JOptionPane.showMessageDialog(null, sqlRobusta);
					
					String sqlAmtTotal = ("SELECT sum(AmtTotal) FROM Customer");
					//JOptionPane.showMessageDialog(null, sqlAmtTotal);
					
					pst=conn.prepareStatement(sqlArabica);
					rs=pst.executeQuery();
					if(rs.next()){
                        String sumArabica = rs.getString("sum(QtyArabica)");
                        sumA=Integer.parseInt((String) sumArabica);
                        //JOptionPane.showMessageDialog(null, sumA);
					}
					
					pst=conn.prepareStatement(sqlRobusta);
					rs=pst.executeQuery();
					if(rs.next()){
                        String sumRobusta = rs.getString("sum(QtyRobusta)");
                        sumR=Integer.parseInt((String) sumRobusta);
                        //JOptionPane.showMessageDialog(null, sumR);
					}
					
					pst=conn.prepareStatement(sqlAmtTotal);
					rs=pst.executeQuery();
					if(rs.next()){
                        String sumAmtTotal = rs.getString("sum(AmtTotal)");
                        sumAT=Integer.parseInt((String) sumAmtTotal);
                        //JOptionPane.showMessageDialog(null, sumAT);
					}
					
					pst.execute();
					
					//sumIntArabica=Integer.valueOf(sumArabica);
					//sumIntRobusta=Integer.valueOf(sumRobusta);
					//sumIntAmtTotal=Integer.valueOf(sumAmtTotal);
					
					totOrders=sumA + sumR;
					totSales=sumAT;
					//JOptionPane.showMessageDialog(null, "total sales = " + sumAT);
					
					
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Total sales not correct!");
				}
				
				txt_totOrders.setText(Integer.toString(totOrders));
				txt_totSales.setText(Integer.toString(totSales));
				
			}
		});
		btnCalculateSales.setBounds(20, 69, 135, 23);
		panelRevenue.add(btnCalculateSales);
		
		JButton btnPrintSalesReport = new JButton("Print Sales Report");
		btnPrintSalesReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,totSales);
			}
		});
		btnPrintSalesReport.setBounds(165, 69, 166, 23);
		panelRevenue.add(btnPrintSalesReport);
		
		JPanel panelExpense = new JPanel();
		panelExpense.setBorder(new TitledBorder(null, "Expense", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelExpense.setBounds(44, 163, 353, 132);
		frame.getContentPane().add(panelExpense);
		panelExpense.setLayout(null);
		
		JLabel lblTotalEmployee = new JLabel("Total Employee");
		lblTotalEmployee.setBounds(26, 16, 124, 14);
		panelExpense.add(lblTotalEmployee);
		
		txt_totEmp = new JTextField();
		txt_totEmp.setBounds(191, 13, 86, 20);
		panelExpense.add(txt_totEmp);
		txt_totEmp.setColumns(10);
		
		JLabel lblSalaryPerEmployee = new JLabel("Salary Per Employee (RM)");
		lblSalaryPerEmployee.setBounds(26, 41, 155, 14);
		panelExpense.add(lblSalaryPerEmployee);
		
		txt_salEmp = new JTextField();
		txt_salEmp.setBounds(191, 38, 86, 20);
		panelExpense.add(txt_salEmp);
		txt_salEmp.setBackground(Color.LIGHT_GRAY);
		txt_salEmp.setEditable(false);
		txt_salEmp.setColumns(10);
		
		JLabel lblTotalMonthlyPayout = new JLabel("Total Monthly Payout (RM)");
		lblTotalMonthlyPayout.setBounds(26, 66, 155, 14);
		panelExpense.add(lblTotalMonthlyPayout);
		
		txt_mnthPayout = new JTextField();
		txt_mnthPayout.setBounds(191, 63, 86, 20);
		panelExpense.add(txt_mnthPayout);
		txt_mnthPayout.setBackground(Color.LIGHT_GRAY);
		txt_mnthPayout.setEditable(false);
		txt_mnthPayout.setColumns(10);
		
		JButton btnPrintSalaryStatement = new JButton("Print Salary Statement");
		btnPrintSalaryStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					totEmp=Integer.parseInt((String) txt_totEmp.getText());
					salEmp=500;
					mnthPayout=totEmp*salEmp;
					
					txt_salEmp.setText(Integer.toString(salEmp));
					txt_mnthPayout.setText(Integer.toString(mnthPayout));
					JOptionPane.showMessageDialog(null,mnthPayout);
					
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null,"Enter valid number of Employee");
				}
			}
		});
		btnPrintSalaryStatement.setBounds(39, 102, 214, 23);
		panelExpense.add(btnPrintSalaryStatement);
		
		JLabel lblAdmin = new JLabel("ADMIN");
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdmin.setBounds(148, 11, 120, 25);
		frame.getContentPane().add(lblAdmin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(363, 303, 64, 23);
		frame.getContentPane().add(btnExit);
	}
}
