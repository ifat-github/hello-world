
import java.sql.*;

public class Validate
{
	public static boolean checkUser(String email, String pass) throws SQLException {
		Connection con = null;
		boolean status = false;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getDBConnection();
			try {
				ps = con.prepareStatement("select * from Company where email=? and password=?");
				ps.setString(1, email);
				ps.setString(2, pass);
				rs = ps.executeQuery();
				status = rs.next();
			} catch (Exception e) {
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return status;                 
	}   
}