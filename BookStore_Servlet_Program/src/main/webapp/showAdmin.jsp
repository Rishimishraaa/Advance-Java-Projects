<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,Bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style type="text/css">
	body{
background: linear-gradient(135deg, #74ebd5, #9face6);
}
	
	.table {
		background-color: lightblue;
		width: 95%;
		text-align: center;
		
	}
	.data {
		background-color: navy;
		color: white;
		font-family: cursive;
	}
	.op {
		color: red;
	}
	
 
  #btn{
  	margin-left:25px;
  	border-radius: 10px;
  	background-color: navy;
  	color:white;
  	
  }
  .search{
  	border:1px solid black;
  	padding:3.5px;
  	text-decoration: none;
  		border-radius: 5px;
  		margin-left:10px;
  		background-color: navy;
  	color:white;
  }
  .select{
  	padding:2px;
  	margin-left:10px;
  	border-radius: 5px;
  }
  .input{
  	border-radius: 5px;
  }
  .main{
  	margin-left: 60px;
  }
  .search-box{
  	margin-left:50px;
  }
  .select{
  	background-color: navy;
  	color:white;
  }
  .reg-btn{

  	padding: 8px 10px;
  	margin-left:10px;
  	text-decoration:none;
  	 display: inline-block; 
  	background-color: navy;
  	color:white;
  	font-style: none;
  	border-radius: 5px;
  	border: none;
  }
  .reg-btn:hover{
  	 background-color: #007bff;
  	 color: white;
  }
  .ad{
  margin-left: 52px;
  }

</style>
</head>
<body>
<%
    String s = (String) session.getAttribute("msg1"); 
	String s2 = (String)session.getAttribute("msg2");
    if (s != null || s2 !=null) { 
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
      
      <li class="nav-item " >
        <a class="nav-link text-light" href="logout">Log Out</a>
      </li>
      <li class="nav-item" style="margin-left:700px;">
        <span class="nav-link fw-bold text-light"  style="color:white;">Welcome : <span class="text-success fw-bold">  <a href="editEdiminDetails.jsp"><%=s %></a><sup >🟢</sup></span></span>
      </li>
    </ul>
  </div>
</nav>
<br>
<div class="ad">
<%if(s.equals("RISHI")){ %>
<h1 style="margin-left: 380px; color:green;">This is Administrator Login⚙️</h1>
		<a class="reg-btn" href="adminRegister.jsp">Register Admin</a>
		
		<a class="reg-btn" href="showAllAdmin">Show All Admin</a>
		<a class="reg-btn" href="showAllUsers">Show All Users</a>
		
	<%} %>
</div>
<%
	ArrayList<Admin1> list = (ArrayList<Admin1>) request.getAttribute("list");
	String msg = (String) request.getAttribute("msg");
	if (list != null) { 
%>
	<br>
	<br>
	
	<div id="tb" class="main">
	<table class="table table-bordered" >
		<thead>
			<tr class="head">
				<th>Admin Name</th>
				<th>Username</th>
				<th>Email</th>
				<th>Password</th>
				<th>Operation</th>
			</tr>
		</thead>
		<tbody>
		<% for (Admin1 admin : list) {%>
			<tr class="data">
				<td><%= admin.getName() %></td>
				<td><%= admin.getUname() %></td>
				<td><%=	admin.getEmail() %></td>
				<td><%= admin.getPass() %></td>
				
				<td>
					
					
					<% if(!admin.getName().equals("RISHI MISHRA")){%>
					<a class="op" href="showEditAdminForm?id=<%= admin.getUname() %>">Edit</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="op" href="deleteAdmin?id=<%=admin.getUname()%>">Delete</a>
					<%} else{%>
					<h5 style="color:red;">Administrator</h5>
					<%} %>
				</td>
			</tr>
		<% } %>
		</tbody>
	
	</table>
		
		</div>
		<input type="submit" id="btn" value="👆">
		
<% }else{if(msg !=null){ %>
	<h3><%=msg %></h3>
<%}} %>

<% } else { %>
    <span style="color:red; font-size: x-large; margin-left:100px;">Session Time Out....</span><br>
 						<button><a style="border:1px black;" href="admin.jsp">Goto Login Page</a></button>
<% } %>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa7EsFjjNfIWzjPEU1iBrj7OE" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
$('#btn').click(() => {
    $('#tb').slideToggle(2000);
});
setTimeout(() => {
    $('#btn').click();
}, 60000);

</script>
</body>
</html>
