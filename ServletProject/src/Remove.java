import java.sql.*;

public class Remove {
	public static boolean removeProduct(String id) {
		boolean st = false;
		try {
			Connection con = DBConnection.getDBConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE id=?");
			ps.setString(1, id);
			st = ps.executeUpdate() > 0;

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return st;                 
	}   
}