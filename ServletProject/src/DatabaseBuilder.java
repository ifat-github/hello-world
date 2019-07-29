


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

	public static void buildDatabase() throws SQLException, ClassNotFoundException {
		Connection con = DBConnection.getDBConnection();
		Statement st = null;
		String IOTDBCreation = null;
		String CompanyTableCreation = null;
		String ProductTableCreation = null;
		String UserCreation = null;
		String ProductCreation = null;
		
		try {
			st = con.createStatement();
			try {
				IOTDBCreation = "CREATE DATABASE IOT";
				st.executeUpdate(IOTDBCreation);
				
				System.out.println("Created database...");
			
				CompanyTableCreation = "CREATE TABLE Company (id int AUTO_INCREMENT PRIMARY KEY, name varchar (50), email varchar (50), password varchar (50));";
				st.executeUpdate(CompanyTableCreation);

				ProductTableCreation = "CREATE TABLE Product (id int AUTO_INCREMENT PRIMARY KEY, manufactor_id int,"
						+ "name varchar (50), description varchar (50), FOREIGN KEY(manufactor_id) REFERENCES Company(id));";
				st.executeUpdate(ProductTableCreation);

				UserCreation = "INSERT INTO Company (name, email, password) values('Tadiran','tad@tad.com','Td');";
				st.executeUpdate(UserCreation);
				
				ProductCreation = "INSERT INTO Product (manufactor_id, name, description) values(1,'i20','air-conditioner');";
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