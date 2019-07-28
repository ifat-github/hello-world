import java.sql.*;

public class ValidateEmail
{
	public static boolean checkUser(String email) {
		boolean st = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = DBConnection.getDBConnection();
			
			ps = con.prepareStatement("select * from Company where email=?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			st = rs.next();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return st;                 
	}   
}