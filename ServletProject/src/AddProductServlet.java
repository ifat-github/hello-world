
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String id = request.getParameter("id").toString();
        String email = request.getParameter("email");
        String message = null;
        RequestDispatcher view = null;
        
        try {
			if (Insert.registerProduct(id, name, description)) {
				message = "The product was added successfully";
			} else {
				message = "Something went wrong..";
			}
			request.getSession().setAttribute("message", message);
			request.getSession().setAttribute("email", email);
			view = request.getRequestDispatcher("companyPage.jsp");
			view.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
