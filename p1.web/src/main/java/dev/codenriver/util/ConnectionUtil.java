package dev.codenriver.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionUtil {
	
	private static ConnectionUtil connUtil;
	private Properties prop;
	
	private ConnectionUtil(String fileName) {
		prop = new Properties();
		InputStream propFile = ConnectionUtil.class.getClassLoader().getResourceAsStream(fileName);
		try {
			prop.load(propFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static synchronized ConnectionUtil getConnectionUtil(String fileName) {
		
		if (connUtil == null) {
			connUtil = new ConnectionUtil(fileName);
		}
		return connUtil;
	}
	
	public Connection openConnection() {
		
		String url = prop.getProperty("url");
		String user = prop.getProperty("usr");
		String pass = prop.getProperty("pas");
		
		Connection conn = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, pass);
	
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}