<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
String name = (String) request.getAttribute("name"); 
String email = (String) request.getAttribute("email");	
Integer id = (Integer) request.getAttribute("id");

ResultSet rs = (ResultSet) session.getAttribute("rs");
ResultSetMetaData rsmd = (ResultSetMetaData) session.getAttribute("rsmd");
int columnCount = (Integer) session.getAttribute("columnCount");
out.println("<title>Company Page - " + name + "</title>");
%>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
	<div class="topnav">
		<a href="/ServletProject/signUp.jsp">Sign Up</a> <a
			href="/ServletProject/signIn.jsp">Sign In</a>
	</div>
<%
//out.println("<h1>email = " + email + "</h1>");
out.println("<h1>" + name + " Products</h1>");
//out.println("<h1>id = " + id + "</h1>");


String message = (String) request.getAttribute("message");
if (message != null) {
	out.println("<h5> " + message + " </h5>");
}

%>
<div class="form-style-6">
<table>
<tr>
<%

// table header
for (int i = 0; i < columnCount; i++) {
	out.println("<th>" + rsmd.getColumnLabel(i + 1) + "</th>");
}
%>
</tr>

<%
while (rs.next()) {
	out.println("<tr>");
	for (int i = 0; i < columnCount; i++) {
		out.println("<td>" + rs.getString(i + 1) + "</td>");
}
out.println("</tr>");
}
%>
</table>
	<div class="form-style-6">
		<h3>Remove a Product</h3>
		<form name="removeProduct" action="RemoveProductServlet" method="POST">
			<input type="text" name="productId" placeholder="Product ID" />
			<%
			out.println("<input type='hidden' name='email' value=" + email + " />");
			%>
			 <input
				type="submit" value="Remove" />
		</form>
	</div>
	<div class="form-style-6">
		<h3>Add a Product</h3>
		<form name="addProduct" action="AddProductServlet" method="POST">
			<input type="text" name="name" placeholder="Product Name" />
			<input type="text" name="description" placeholder="Product Description" />
			<%
			out.println("<input type='hidden' name='id' value=" + id + " />");
			out.println("<input type='hidden' name='email' value=" + email + " />");
			%>
			 <input
				type="submit" value="Add" />
		</form>
	</div>
</body>
</html>