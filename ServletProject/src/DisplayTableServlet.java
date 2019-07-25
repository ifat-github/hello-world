import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayTableServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;  
		PreparedStatement namePS = null;
		PreparedStatement idPS = null;
		PreparedStatement ps = null;
		ResultSet nameResultSet = null;
		ResultSet idResultSet = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String name = null;
		String message = null;
		int id = 0;
		
		String email = request.getParameter("email");
		
		try {
			con = DBConnection.getDBConnection();

			request.setAttribute("email", email);
			request.setAttribute("message", message);

			namePS = con.prepareStatement("select name from Company where email=?");
			namePS.setString(1, email);
			nameResultSet = namePS.executeQuery();
			if (nameResultSet.first()) {
				name = nameResultSet.getObject(1).toString();
				request.setAttribute("name", name);
			}

			idPS = con.prepareStatement("select id from Company where name=?");
			idPS.setString(1, name);
			idResultSet = idPS.executeQuery();
			if (idResultSet.first()) {
				id = ((Number)idResultSet.getObject(1)).intValue();
				request.setAttribute("id", id);
			}

			ps = con.prepareStatement("select id, name, description from Product where manufactor_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			request.getSession().setAttribute("columnCount", rsmd.getColumnCount());
			request.getSession().setAttribute("rsmd", rsmd);
			request.getSession().setAttribute("rs", rs);
			request.setAttribute("message", message);
			
			RequestDispatcher view = request.getRequestDispatcher("companyPage.jsp");
	        view.forward(request, response);

		} catch (SQLException e) {
			throw new ServletException("Servlet Could not display records.", e);
		} catch (ClassNotFoundException e) {
			throw new ServletException("JDBC Driver not found.", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (idResultSet != null) {
					idResultSet.close();
					idResultSet = null;
				}
				if (idPS != null) {
					idPS.close();
					idPS = null;
				}
				if (nameResultSet != null) {
					nameResultSet.close();
					nameResultSet = null;
				}
				if (namePS != null) {
					namePS.close();
					namePS = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
