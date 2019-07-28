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

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignInServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		if (ValidateEmail.checkUser(email)) {
			if (ValidatePassword.checkUser(email, pass)) {
				RequestDispatcher rd = request.getRequestDispatcher("DisplayTableServlet");
				rd.forward(request,response);
			}
			else {
				String message = "Incorrect password";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
				rd.forward(request,response);
			}
		} else {
			String message = "There's no user with that email";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
			rd.forward(request,response);
		}
	}
}
