
import java.sql.*;

public class Insert {
	public static boolean registerUser(String name, String email, String pass) {
		boolean st = false;
		try {
			Connection con = DBConnection.getDBConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO Company (name, email, password) values(?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, pass);
			st = ps.executeUpdate() > 0;

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return st;                 
	}   
	
	public static boolean registerProduct(String id, String name, String description) {
		boolean st = false;
		try {
			Connection con = DBConnection.getDBConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO Product (manufactor_id, name, description) values(?,?,?)");
			int idInt = Integer.valueOf(id);
			ps.setInt(1, idInt);
			ps.setString(2, name);
			ps.setString(3, description);
			st = ps.executeUpdate() > 0;

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return st;                 
	}   
}