


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {
	static final String CtorUsername = "ifat";
	static final String CtorPassword = "123456";

	static Connection getDBConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/IOT", CtorUsername, CtorPassword);
	}
}