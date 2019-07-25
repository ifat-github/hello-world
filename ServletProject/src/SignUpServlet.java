

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        
		String name = request.getParameter("name");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String message = null;
        RequestDispatcher rd = null;
        
        if (Insert.registerUser(name, email, pass)) {
            message = "You were registered successfully!";
            request.setAttribute("message", message);
            rd = request.getRequestDispatcher("signIn.jsp");
        } else {
        	message = "Something went wrong..";
        	request.setAttribute("message", message);
        	rd = request.getRequestDispatcher("signUp.jsp");
        }
        rd.forward(request,response);
	}

}
