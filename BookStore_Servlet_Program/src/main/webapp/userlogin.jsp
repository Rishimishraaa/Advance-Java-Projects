<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
body{
background: linear-gradient(135deg, #74ebd5, #9face6);
}
form {
    width: 300px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    background-color: #f9f9f9;
}

form h2 {
    text-align: center;
    margin-bottom: 20px;
    font-family: Arial, sans-serif;
    color: #333;
}

form p {
    margin: 15px 0;
}

form input[type="text"],
form input[type="password"] {
    width: 100%;
    padding: 10px;
    margin: 5px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
    box-sizing: border-box;
}

form input[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

form input[type="submit"]:hover {
    background-color: #0056b3;
}

form a {
    display: block;
    text-align: center;
    margin-top: 10px;
    font-size: 14px;
    color: #007bff;
    text-decoration: none;
    transition: color 0.3s ease;
}

form a:hover {
    color: #0056b3;
}
</style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">BookStore</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="#">Show All Books</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Insert Book</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Log Out</a>
      </li>
    </ul>
  </div>
</nav>

	<%String msg =(String) request.getAttribute("msg"); %>
	<% if(msg!=null){ %>
	<h2 style="color:red"><%=msg %></h2>
	<%} %>
	<form action="userLogin" method="post">
	<h2>User Login</h2>
		<p>
			<input type="text" name="uname" placeholder="Enter Username" required="required">
		</p>
		<p>
			<input type="password" name="pass" placeholder="Enter Password" required="required">
		</p>
		<p>
			<input type="submit"  value="Login">
		</p>
		<a href="userRegister.jsp">User Register?</a>
		<a href="admin.jsp">Admin Login?</a>
	</form>
</body>
</html>