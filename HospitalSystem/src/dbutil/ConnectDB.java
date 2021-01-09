package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
public static Connection getConnections() {
	try {
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/hospital","root","");
	
	return conn;
	
}
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}
}
