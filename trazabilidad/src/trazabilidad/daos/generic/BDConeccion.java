package trazabilidad.daos.generic;

import java.sql.Connection;
import java.sql.DriverManager;

public class BDConeccion {
	
	public static Connection getConnection () {
		
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "trazabilidad";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "root";
		
		try {
		
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			System.out.println("Conectado a la base de datos");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
}
