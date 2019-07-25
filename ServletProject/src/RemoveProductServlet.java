

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("productId");
		String email = request.getParameter("email");
		String message = null;
        
        if (Remove.removeProduct(id)) {
        	message = "Product was removed successfully";
        	
        } else {
        	message = "Something went wrong..";
        }
        request.getSession().setAttribute("message", message);
        request.getSession().setAttribute("email", email);
        RequestDispatcher view = request.getRequestDispatcher("DisplayTableServlet");
        view.forward(request, response);
	}
}
