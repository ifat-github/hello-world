


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/SignUpServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AddProductServlet() {
        super();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String id = request.getParameter("id").toString();
        String email = request.getParameter("email");
        String message = null;
        
        if (Insert.registerProduct(id, name, description)) {
        	message = "The product was added successfully";
            //rs.forward(request, response);
        } else {
        	message = "Something went wrong..";
        }
        request.setAttribute("message", message);
        request.getSession().setAttribute("email", email);
        RequestDispatcher view = request.getRequestDispatcher("DisplayTableServlet");
        view.forward(request, response);
	}

}
