

import java.sql.*;
import javax.swing.*;


public class javaconnect {
	
	Connection conn=null;
	ResultSet rs=null;
	public static Connection ConnecrDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			//Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\workspace\\database\\CoffeeShop.sqlite");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:‪C:\\Users\\user\\workspace\\CoffeeShop.sqlite");
			//Connection conn=DriverManager.getConnection("jdbc:sqlite:C:/Program Files/workspace/database/CoffeeShop.sqlite");
			JOptionPane.showMessageDialog(null, "Connection Established");
			return conn;
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Connection error!");
			return null;
		}
	}

}
