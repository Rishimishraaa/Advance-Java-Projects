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
User user = (User)application.getAttribute("user");
    String s = (String) session.getAttribute("msg"); 
    if (s != null) { 
%>
   
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="userProfile.jsp">BookStore</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link text-light" href="userShowBook">Show All Books</a>
      </li>
      <li class="nav-item" >
        <span class="nav-link fw-bold text-light"><span class="text-success fw-bold "><a class="text-white" href="mybooks?id=<%=user.getUserid()%>">YourBooks</a></span></span>
      </li>
       <li class="nav-item " >
        <a class="nav-link text-light" href="ulogout">Log Out</a>
      </li>
      <li class="nav-item" style="margin-left:700px;">
        <span class="nav-link fw-bold text-light"  style="color:white;">Welcome : <span class="text-success fw-bold">  <a href="editUserDetails.jsp"><%=s %></a><sup >🟢</sup></span></span>
      </li>
     
    </ul>
  </div>
</nav>
<br>

<%
	ArrayList<BookDetails> list = (ArrayList<BookDetails>) request.getAttribute("list");
	String msg = (String) request.getAttribute("msg");
	if (list != null) { 
%>
	<br>
	<div>
		
		<form action="userSearchingBook" method="post" class="search-box">
		<select name="operation" class="select">
			
			<option value="Book_ID" selected="selected">Book_CODE</option>
			<option value="Book_Name" >Book_Name</option>
			<option value="Book_Author" >Book_Author</option>
		</select>
		<input type="text" name="inp" class="input">
		<input class="search" type="submit" value="Search">
		</form>

		
	</div>
	
	<div id="tb" class="main">
	<table class="table table-bordered" >
		<thead>
			<tr class="head">
				<th>Book_Code</th>
				<th>Book_Name</th>
				<th>Book_Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Operation</th>
			</tr>
		</thead>
		<tbody>
		<% for (BookDetails book : list) { %>
			<tr class="data">
				<td><%= book.getBookCode() %></td>
				<td><%= book.getBookName() %></td>
				<td><%= book.getBookAuthor() %></td>
				<td><%= book.getBookPrice() %></td>
				<td><%= book.getBookQty()%></td>
				<td>
					<a class="op" href="removeBook?id=<%= book.getBookCode() %>">Remove</a>
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
