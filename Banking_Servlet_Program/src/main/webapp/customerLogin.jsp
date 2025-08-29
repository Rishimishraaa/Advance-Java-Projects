<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Banking Management System - Login</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #0044cc, #003399);
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 400px;
            background: white;
            border-radius: 15px;
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.2);
            padding: 30px;
            text-align: center;
        }
        .container h2 {
            color: #0044cc;
            margin-bottom: 20px;
            font-size: 1.8em;
        }
        .container form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        .container input {
            padding: 12px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 8px;
            transition: all 0.3s ease;
        }
        .container input:focus {
            border-color: #0044cc;
            box-shadow: 0 0 5px rgba(0, 68, 204, 0.5);
            outline: none;
        }
        .container button {
            background-color: #0044cc;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 1.1em;
            transition: background-color 0.3s ease;
        }
        .container button:hover {
            background-color: #003399;
        }
        .container a {
            color: #0044cc;
            text-decoration: none;
            font-size: 0.9em;
        }
        .container a:hover {
            text-decoration: underline;
        }
        .icon {
            font-size: 3em;
            color: #0044cc;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<% 
String msgG = (String)request.getAttribute("msgG");
String msgR = (String)request.getAttribute("msgR");
%>

    <div class="container">
    	<%if(msgG!=null){ %>
			<h3 id="h3" style="color:green;"><%=msgG %></h3>
			<%} %>
			<%if(msgR!=null){ %>
			<h3 id="h3" style="color:red;"><%=msgR %></h3>
			<%} %>
    	
        <i class="fas fa-university icon"></i>
        <h2>Login</h2>
        
        <form action="customerLogin" method="post">
            <input type="text" name="uname" placeholder="Username" required>
            <input type="password" name="pass" placeholder="Password" required>
            <button type="submit">Login</button>
            <a href="#">Forgot Password?</a>
            <a href="customerRegister.jsp">Register?</a>
        </form>
    </div>
    
    <script>
        const msg = document.getElementById('h3');
        setTimeout(()=>{
            msg.textContent ="";
        },5000)
    </script>
</body>
</html>
