import java.sql.*;

public class ValidatePassword
{
	public static boolean checkUser(String email, String pass) {
		boolean st = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection con = DBConnection.getDBConnection();
			
				ps = con.prepareStatement("select * from Company where email=? and password=?");
				ps.setString(1, email);
				ps.setString(2, pass);
				rs = ps.executeQuery();
				st = rs.next();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return st;                 
	}   
}