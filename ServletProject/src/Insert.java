

import java.sql.*;

public class Insert {
	public static boolean registerUser(String name, String email, String pass) throws SQLException {
		boolean status = false;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getDBConnection();
			try {
				ps = con.prepareStatement("INSERT INTO Company (name, email, password) values(?,?,?)");
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, pass);
				status = ps.executeUpdate() > 0;
			} catch (Exception e) {
			} finally {
				if (ps != null) {
					ps.close();
				}
			}
		} catch (Exception e) {
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return status;                 
	}   

	public static boolean registerProduct(String id, String name, String description) throws ClassNotFoundException, SQLException {
		boolean status = false;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DBConnection.getDBConnection();
			try {
				ps = con.prepareStatement("INSERT INTO Product (manufactor_id, name, description) values(?,?,?)");
				int idInt = Integer.valueOf(id);
				ps.setInt(1, idInt);
				ps.setString(2, name);
				ps.setString(3, description);
				status = ps.executeUpdate() > 0;
			} finally {
				if (ps != null) {
					ps.close();
				}
			}
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return status;                 
	}   
}