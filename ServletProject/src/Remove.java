
import java.sql.*;

public class Remove {
	public static boolean removeProduct(String email, String id) throws SQLException {
		Connection con = null;
		boolean status = false;
		String companyId = null;
		PreparedStatement psSearch = null;
		PreparedStatement psDeletion = null;
		ResultSet rs = null;

		try {
			con = DBConnection.getDBConnection();

			try {
				psSearch = con.prepareStatement("SELECT id FROM Company WHERE email=?");
				psSearch.setString(1, email);
				rs = psSearch.executeQuery();
				if (rs.first()) {
					companyId = rs.getObject(1).toString();
				}

				psDeletion = con.prepareStatement("DELETE FROM Product WHERE id=? and manufactor_id=?");
				psDeletion.setString(1, id);
				psDeletion.setString(2, companyId);
				status = psDeletion.executeUpdate() > 0;
			} catch (Exception e) {
			} finally {
				if (psSearch != null) {
					psSearch.close();
				}
				
				if (psDeletion != null) {
					psDeletion.close();
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