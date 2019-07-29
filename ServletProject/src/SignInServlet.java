
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String message = null;

		try {
			if (Validate.checkUser(email, pass)) {
				request.setAttribute("email", email);
				HttpSession oldSession = request.getSession(false);
			    if (oldSession != null) {
			        oldSession.invalidate();
			    }
			    //generate a new session
			    HttpSession newSession = request.getSession(true);

			    //setting session to expiry in 5 mins
			    newSession.setMaxInactiveInterval(5 * 60);
			    
				Cookie cookie = new Cookie("email", email);
				cookie.setMaxAge(60 * 1); 
				response.addCookie(cookie);
				response.sendRedirect("companyPage.jsp");
				//RequestDispatcher rd = request.getRequestDispatcher("companyPage.jsp");
				//rd.include(request, response);
			} else {
				message = "Incorrect email or password.";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
				rd.include(request,response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
