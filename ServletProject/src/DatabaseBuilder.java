

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

	public static void buildDatabase() throws SQLException, ClassNotFoundException {
		String DBCtorUsername = "ifat";
		String DBCtorPassword = "123456";
		Connection dbcon = DBConnection.getDBConnection();
		try {
			Statement stcon = dbcon.createStatement();
			try {
				String IOTDBCreation = "CREATE DATABASE IOT";
				stcon.executeUpdate(IOTDBCreation);
				System.out.println("Created database...");
			} finally {
				if (stcon != null) {
					stcon.close();
				}
			}
		} finally {
			if (dbcon != null) {
				dbcon.close();
			}
		}

		Connection con = DBConnection.getDBConnection();
		try {
			Statement st = con.createStatement();
			try {
				String CompanyTableCreation = "CREATE TABLE Company ( id int AUTO_INCREMENT PRIMARY KEY, name varchar (50), email varchar (50), password varchar (50)); ";
				st.executeUpdate(CompanyTableCreation);

				String ProductTableCreation = "CREATE TABLE Product ( id int AUTO_INCREMENT PRIMARY KEY, manufactor_id int,"
						+ "name varchar (50), description varchar (50), FOREIGN KEY(manufactor_id) REFERENCES Company(id) ); ";
				st.executeUpdate(ProductTableCreation);

				String UserCreation = "INSERT INTO Company (name, email, password) values('Tadiran','tad@tad.com','Td');";
				st.executeUpdate(UserCreation);
				
				String ProductCreation = "INSERT INTO Product (manufactor_id, name, description) values(1,'i20','air-conditioner');";
				st.executeUpdate(ProductCreation);
				
				System.out.println("Created tables in given database...");
			} finally {
				if (st != null) {
					st.close();
				}
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}