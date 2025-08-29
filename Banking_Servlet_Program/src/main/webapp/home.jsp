<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Model.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Banking Management System</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f3f4f6;
            color: #333;
        }
        header {
            background: linear-gradient(90deg, #0044cc, #003399);
            color: white;
            padding: 20px;
            text-align: center;
        }
        header h1 {
            margin: 0;
            font-size: 2.5em;
        }
        nav {
            display: flex;
            justify-content: center;
            background-color: #002266;
            padding: 10px 0;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
        }
        nav a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
            font-size: 1.1em;
            font-weight: bold;
            display: flex;
            align-items: center;
        }
        nav a i {
            margin-right: 8px;
        }
        nav a:hover {
            text-decoration: underline;
        }
        .container {
            padding: 20px;
            max-width: 1200px;
            margin: auto;
            text-align: center;
        }
        .card {
            background: white;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            margin: 20px;
            padding: 20px;
            display: inline-block;
            width: 30%;
            vertical-align: top;
        }
        .card h3 {
            margin-bottom: 15px;
            color: #0044cc;
        }
        .card p {
            margin-bottom: 20px;
        }
        .card a {
            background-color: #0044cc;
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
        }
        .card a:hover {
            background-color: #003399;
        }
        footer {
            background-color: #002266;
            color: white;
            text-align: center;
            padding: 15px 0;
            margin-top: 30px;
        }
        footer p {
            margin: 0;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to State Bank Of India</h1>
    </header>

    <nav>
        <a href="createAccountForm" id="ca"><i class="fas fa-user-circle"></i>Create Account</a>
        <a href="#" onclick="msg()"><i class="fas fa-exchange-alt"></i>Transfer Funds</a>
        <a href="#" onclick="msg()"><i class="fas fa-receipt"></i>Transactions</a>
        <a href="#" onclick="msg()"><i class="fas fa-user"></i>Profile</a>
        <a href="customerLogout"><i class="fas fa-sign-out-alt"></i>Logout</a>
    </nav>

    <div class="container">
        <h2>Dashboard</h2>
        <h3 style="color:green;">Welcome, <%= session.getAttribute("fname") %>!</h3>
        <div class="card">
            <h3>Check Your Balance</h3>
            <p>Quickly view your account balance and recent activity.</p>
            <a href="#" onclick="msg()">View Details</a>
        </div>

        <div class="card">
            <h3>Transfer Money</h3>
            <p>Easily send money to friends and family with just a few clicks.</p>
            <a href="#" onclick="msg()">Transfer Now</a>
        </div>

        <div class="card">
            <h3>Transaction History</h3>
            <p>Review your transaction history and keep track of your finances.</p>
            <a href="#" onclick="msg()">View Transactions</a>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Banking Management System. All rights reserved.</p>
    </footer>
    
    <script type="text/javascript">
    	const createAccount = document.getElementById("ca");
    	function msg(){
    		alert("First Create Account....");
    		createAccount.style.color="red";
    		setTimeout(()=>{
        		createAccount.style.color="white";
        	},5000);
    	}
    	
    </script>
</body>
</html>