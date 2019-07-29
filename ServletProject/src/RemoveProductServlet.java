
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("productId");
		String email = request.getParameter("email");
		String message = null;
		RequestDispatcher view = null;
        
        try {
			if (Remove.removeProduct(email, id)) {
				message = "Product was removed successfully";
				
			} else {
				message = "Something went wrong..";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        request.setAttribute("message", message);
        request.setAttribute("email", email);
        view = request.getRequestDispatcher("companyPage.jsp");
        view.forward(request, response);
	}
}
