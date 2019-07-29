


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String message = null;
        RequestDispatcher rd = null;
        
        try {
			if (Insert.registerUser(name, email, pass)) {
			    message = "You were registered successfully!";
			    request.setAttribute("message", message);
			    rd = request.getRequestDispatcher("signIn.jsp");
			} else {
				message = "Something went wrong..";
				request.setAttribute("message", message);
				rd = request.getRequestDispatcher("signUp.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        rd.forward(request,response);
	}

}
