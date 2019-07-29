<%@ page import="java.sql.*,javax.servlet.*,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	Connection con = null;
	PreparedStatement namePS = null;
	PreparedStatement idPS = null;
	PreparedStatement tablePS = null;
	ResultSet nameRS = null;
	ResultSet idRS = null;
	ResultSet tableRS = null;
	ResultSetMetaData rsmd = null;
	String name = null;
	String email = null;
	String message = null;
	int id = 0;
	int columnCount = 0;
	RequestDispatcher view = null;

	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("email")) {
				email = cookie.getValue();
			}
		}
	}

	if (email == null) {
		RequestDispatcher rd = request.getRequestDispatcher("signIn.jsp");
		rd.forward(request, response);
	}

	message = (String) request.getAttribute("message");

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IOT", "ifat", "123456");

		namePS = con.prepareStatement("select name from Company where email=?");
		namePS.setString(1, email);
		nameRS = namePS.executeQuery();
		if (nameRS.first()) {
			name = nameRS.getObject(1).toString();
		}

		idPS = con.prepareStatement("select id from Company where name=?");
		idPS.setString(1, name);
		idRS = idPS.executeQuery();
		if (idRS.first()) {
			id = ((Number) idRS.getObject(1)).intValue();
		}

		tablePS = con.prepareStatement("select id, name, description from Product where manufactor_id=?");
		tablePS.setInt(1, id);
		tableRS = tablePS.executeQuery();
		rsmd = tableRS.getMetaData();
	} catch (SQLException e) {
		throw new ServletException("Servlet Could not display records.", e);
	} catch (ClassNotFoundException e) {
		throw new ServletException("JDBC Driver not found.", e);
	}

	columnCount = rsmd.getColumnCount();

	out.println("<title>Company Page - " + name + "</title>");
%>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
	<div class="topnav">
		<a href="/ServletProject/signUp.jsp">Sign Up</a> <a
			href="/ServletProject/signIn.jsp">Sign In</a><a
			class="active" href="/ServletProject/companyPage.jsp">Company Page</a>
	</div>
	<%
		out.println("<h1>" + name + " Products</h1>");

		message = (String) request.getAttribute("message");
		if (message != null) {
			out.println("<h5> " + message + " </h5>");
		}
	%>
	<div class="form-style-6">
		<form action="SignOutServlet" method="post">
			<input type="submit" value="Logout">
		</form>
	</div>
	<div class="form-style-6">
		<table>
			<tr>
				<%
					for (int i = 0; i < columnCount; i++) {
						out.println("<th>" + rsmd.getColumnLabel(i + 1) + "</th>");
					}
				%>
			</tr>
			<%
				while (tableRS.next()) {
					out.println("<tr>");
					for (int i = 0; i < columnCount; i++) {
						out.println("<td>" + tableRS.getString(i + 1) + "</td>");
					}
					out.println("</tr>");
				}
			%>
		</table>
	</div>
	<div class="form-style-6">
		<h3>Remove a Product</h3>
		<form name="removeProduct" action="RemoveProductServlet" method="POST">
			<input type="text" name="productId" placeholder="Product ID" />
			<%
				out.println("<input type='hidden' name='email' value=" + email + " />");
			%>
			<input type="submit" value="Remove" />
		</form>
	</div>
	<div class="form-style-6">
		<h3>Add a Product</h3>
		<form name="addProduct" action="AddProductServlet" method="POST">
			<input type="text" name="name" placeholder="Product Name" /> <input
				type="text" name="description" placeholder="Product Description" />
			<%
				out.println("<input type='hidden' name='id' value=" + id + " />");
				out.println("<input type='hidden' name='email' value=" + email + " />");
			%>
			<input type="submit" value="Add" />
		</form>
	</div>
</body>
</html>