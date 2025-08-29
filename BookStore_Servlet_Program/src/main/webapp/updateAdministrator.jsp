<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Register/Update</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<style type="text/css">
	body{
background: linear-gradient(135deg, #74ebd5, #9face6);
}
	.btn{
		text-decoration: none;
		border:1px solid black;
		border-radius:5px;
		padding:5px;
		font-size:large;
		background-color: cyan;
		color: black;
		margin:5px;
		box-shadow: 3px 5px 3px;
	}
	.table{
		
		background-color: lightblue;
		width:80%;
		text-align: center;
		margin-left:30px;
	}
	.data{
		background-color: navy;
		color:white;
		font-family: cursive;
	}
	.op{
		color:red;
	}
		 .underline {
    text-decoration: underline;
    color:green; font-size: x-large; 
  }
  .outerspan{
  font-size: x-large;
  font-weight: 500;
  }
  form{
  text-align:center;
  }
  .card{
   border-radius: 20px;
  }
  .card-body{
    box-shadow: 10px 15px 10px gray;
    border-radius: 20px;
  }
  .container{
  margin-top: 50px;
 
  }
  h2{
  	text-decoration: underline;
  }
</style>
</head>
<body>
		<%
	String s = (String)session.getAttribute("msg1"); 
		String s2 = (String)session.getAttribute("msg2");
 				if(s != null || s2 !=null){
 				   %>
 				

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="profile.jsp">BookStore</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link text-light" href="dis">Show All Books</a>
      </li>
      <li class="nav-item">
        <a class="nav-link text-light" href="insert.jsp">Insert Book</a>
      </li>
 
      
      <li class="nav-item">
        <a class="nav-link text-light" href="logout">Log Out</a>
      </li>
       <li class="nav-item" style="margin-left:700px;">
        <span class="nav-link fw-bold" >Welcome:<span class="text-success fw-bold"> <a href="editEdiminDetails.jsp"><%=s %></a><sup >🟢</sup></span></span>
      </li>
    </ul>
  </div>
</nav>
	  		<% Admin1 admin = (Admin1)application.getAttribute("admin");
	String msg = (String)request.getAttribute("msg");
	if(msg !=null){
	%>
	<h3 style="color:green; text-align: center; margin-top: 20px;"><%=msg %></h3>
	<%} %>
	
	<div class="container col-md-5" >
			<div class="card">
					<div class="card-body">
						<%if(admin != null){ %>
						<form action="updateAdmin" method ="post">
						<%}else{ %>
						<form action="#" method ="post">
						<%} %>
						
						<h2 >
							<%if(admin !=null){ %>Edit Admin Details
							<%}else{ %> Add New Admin<%} %>
						</h2>
						<br>
						<fieldset class="form-group">
						<label>UserName :</label>
							<%if(s.equals("RISHI")){%>
						<input type="text" value="${admin.uname}"  class="form-contro" name="uname" required="required">
						
						<%}else{ %>
						<input type="text" value="${admin.uname}"  class="form-contro" name="uname" required="required" readonly="readonly">
						<%} %>
						</fieldset>
						
						<fieldset class="form-group">
						<label>Admin Name :</label>
						<input type="text" value="${admin.name}"  class="form-contro" name="name" required="required">
						</fieldset>
						
						<fieldset class="form-group">
						<label>Admin Email :</label>
						<input type="text" value="${admin.email}"  class="form-contro" name="email" required="required">
						</fieldset>
						
						<fieldset class="form-group">
						<label>Admin Password :</label>
						<input type="text" value="${admin.pass}"  class="form-contro" name="pass" required="required">
						</fieldset>
						
						
							<button type="submit" style="background-color: green; color:white; width:200px" class="btn btn-success">Save</button>
						</form>
					</div>
			</div>
	</div>
	<%}else{%>
 						<span style="color:red; font-size: x-large; margin-left:100px;">Session Time Out....</span><br>
 						<button><a style="border:1px black;" href="admin.jsp">Goto Login Page</a></button>
 				<%} %>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa7EsFjjNfIWzjPEU1iBrj7OE" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>