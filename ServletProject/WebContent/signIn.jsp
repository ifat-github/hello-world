<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Company Sign In</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
	<div class="topnav">
		<a href="/ServletProject/signUp.jsp">Sign Up</a> <a class="active"
			href="/ServletProject/signIn.jsp">Sign In</a>
	</div>
	<div class="form-style-6">
<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
			out.println("<h5>" + message + "</h5>");
		}
%>
		<h1>Sign In</h1>
		<form name="myForm" action="SignInServlet" method="POST"
			onsubmit="return validateForm()">
			<input type="text" name="email" placeholder="Email Address" /> <input
				type="password" name="password" placeholder="Password"> <input
				type="submit" value="Login" />
		</form>
	</div>
	<script>
		function validateForm() {
			var email = document.forms["myForm"]["email"].value;
			var password = document.forms["myForm"]["password"].value;
			if (email == "") {
				alert("email must be filled out");
				return false;
			}
			if (password == "") {
				alert("password must be filled out");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>