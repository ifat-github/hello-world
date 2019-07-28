import java.sql.*;

public class Remove {
	public static boolean removeProduct(String email, String id) {
		boolean st = false;
		String companyId = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = DBConnection.getDBConnection();
			
			ps = con.prepareStatement("SELECT id FROM Company WHERE email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.first()) {
				companyId = rs.getObject(1).toString();
			}
			
			ps = con.prepareStatement("DELETE FROM Product WHERE id=? and manufactor_id=?");
			ps.setString(1, id);
			ps.setString(2, companyId);
			st = ps.executeUpdate() > 0;

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return st;                 
	}   
}