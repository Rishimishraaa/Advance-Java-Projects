<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Bean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert/Update Books</title>
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
        <span class="nav-link fw-bold text-light" >Welcome : <span class="text-success fw-bold"> <a href="editEdiminDetails.jsp"><%=s %></a><sup >🟢</sup></span></span>
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

	
			<% BookDetails book = (BookDetails)request.getAttribute("book");
	String msg = (String)request.getAttribute("msg");
	if(msg !=null){
	%>
	<h3 style="color:green; text-align: center; margin-top: 20px;"><%=msg %></h3>
	<%} %>
	
	<div class="container col-md-5" >
			<div class="card">
					<div class="card-body">
						<%if(book != null){ %>
						<form action="update" method ="post">
						<%}else{ %>
						<form action="insert" method ="post">
						<%} %>
						
						<h2 >
							<%if(book !=null){ %>Edit Book
							<%}else{ %> Add New Book<%} %>
						</h2>
						<br>
						<fieldset class="form-group">
						<label>Book Code :</label>
						<%if(s.equals("RISHI")){ %>
						<input type="text" value="${book.bookCode}"  class="form-contro" name="bcode" required="required">
						<%}else{ %>
						<input type="text" value="${book.bookCode}"  class="form-contro" name="bcode" required="required" readonly="readonly">
						<%} %>
						
						</fieldset>
						
						<fieldset class="form-group">
						<label>Book Name :</label>
						<input type="text" value="${book.bookName}"  class="form-contro" name="bname" required="required">
						</fieldset>
						
						<fieldset class="form-group">
						<label>Book Author :</label>
						<input type="text" value="${book.bookAuthor}"  class="form-contro" name="bauthor" required="required">
						</fieldset>
						
						<fieldset class="form-group">
						<label>Book Price :</label>
						<input type="text" value="${book.bookPrice}"  class="form-contro" name="bprice" required="required">
						</fieldset>
						
						<fieldset class="form-group">
						<label>Book Quantity :</label>
						<input type="text" value="${book.bookQty}"  class="form-contro" name="bqty" required="required">
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