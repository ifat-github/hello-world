<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Company Sign Up</title>
<link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
	<div class="topnav">
		<a class="active" href="/ServletProject/signUp.jsp">Sign Up</a> <a
			href="/ServletProject/signIn.jsp">Sign In</a> 
	</div>
	<div class="form-style-6">
	<%
		String message = (String) request.getAttribute("message");
		if (message != null) {
			out.println("<h5>" + message + "</h5>");
		}
%>
		<h1>Sign Up</h1>
		<form name="myForm" action="SignUpServlet" method="POST"
			onsubmit="return validateForm()">
			<input type="text" name="name" placeholder="Company Name" /> <input
				type="text" name="email" placeholder="Email Address" /> <input
				type="password" name="password" placeholder="Password"> <input
				type="submit" value="Sign Up" />
		</form>
	</div>
	<script>
		function validateForm() {
			var name = document.forms["myForm"]["name"].value;
			var email = document.forms["myForm"]["email"].value;
			var password = document.forms["myForm"]["password"].value;
			if (name == "") {
				alert("Name must be filled out");
				return false;
			}
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