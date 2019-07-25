

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {
	static final String tablesCtorUsername = "ifat";
	static final String tablesCtorPassword = "123456";

	static Connection getDBConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/IOT", tablesCtorUsername, tablesCtorPassword);
	}
}