<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Model.*,java.util.ArrayList"%>
  <%--  <%@page errorPage="error.jsp" %> --%>
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
        
          .modal {
            display: none; /* Hidden by default */
            position: fixed;
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.4); /* Black with transparency */
            padding-top: 60px;
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 30%;
            border-radius: 10px;
        }

        /* Close Button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        /* Form Styles */
        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .form-group button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .form-group button:hover {
            background-color: #45a049;
        }


        fieldset {
            border: 2px solid #4CAF50;
            border-radius: 8px;
            padding: 20px;
            margin: 0 auto;
            max-width: 400px;
        }
        legend {
            font-size: 18px;
            font-weight: bold;
            color: #4CAF50;
        }
        label {
            display: block;
            font-weight: bold;
            margin: 10px 0 5px;
        }
        input[type="text"], input[type="email"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: none;
            border-bottom: 1px solid #ccc;
            background-color: transparent;
            font-size: 16px;
        }
        input[type="text"]:focus, input[type="email"]:focus {
            outline: none;
            border-bottom: 1px solid #4CAF50;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        
        
        <!-- mini statement -->
         .container {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        table th, table td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }

        table th {
            background-color: #007bff;
            color: #fff;
        }

        .btn-container {
            text-align: center;
        }

        .btn {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn:hover {
            background-color: #0056b3;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
    </style>
</head>
<body>
		<%
		Customer customer = (Customer)session.getAttribute("customer");
		BankCustomers bankCustomer = (BankCustomers)session.getAttribute("bankcustomer");
	%>
    <header>
        <h1 style="color:white;">Welcome to State Bank Of India</h1>
    </header>

    <nav>
     <a href="bankingapp.jsp" id="ca"><i class="fa-solid fa-house"></i></i>Home</a>
        <a href="accountOverview.jsp" id="ca"><i class="fas fa-user-circle"></i>Account Overview</a>
        <a href="#" id="myBtn"><i class="fas fa-exchange-alt"></i>Transfer Funds</a>
        <a href="miniStatement.jsp" ><i class="fas fa-receipt"></i>Transactions</a>
        <a href="showProfile.jsp" ><i class="fas fa-user"></i>Profile</a>
        <a href="customerLogout"><i class="fas fa-sign-out-alt"></i>Logout</a>
    </nav>

    <div class="container">
        <h3 style="color:green;">Welcome, <%= session.getAttribute("fname") %>!</h3>
        <% 
			String msgG = (String)request.getAttribute("msgG");
			String msgR = (String)request.getAttribute("msgR");
		%>
        	<%if(msgG!=null){ %>
			<h3 id="h3" style="color:green;"><%=msgG %></h3>
			<%} %>
			<%if(msgR!=null){ %>
			<h3 id="h3" style="color:red;"><%=msgR %></h3>
			<%} %>
    	
    		<!-- ------------------------------ Mini Statement Page ----------------------------- -->
    
   
		
        <h2>Transaction Statement</h2>
        <form action="MiniStatement" method="post">
            <div class="form-group">
                <label for="accountNumber">Account Number:</label>
                <input type="text" id="accountNumber" name="accountNumber" value="<%=bankCustomer.getAcno()%>" readonly="readonly">
            </div>
            <div class="btn-container">
                <button type="submit" id="btn" class="btn">Get Statement</button>
            </div>
        </form>
          <% 
			ArrayList<TransctionHistory> list = (ArrayList<TransctionHistory>)request.getAttribute("list");
			
		%>
		<% if(list !=null){ %>

        <h2>Recent Transactions</h2>
       <div  style="overflow-x: auto; max-height: 380px; border: 1px solid #ccc;">
        <table>
            <thead>
                <tr>
                	<th>Transaction_ID</th>
                    <th>Date & Time</th>
                    <th>Amount (&#8377;) </th>
                    <th>Current_Balance</th>
                </tr>
            </thead>
            <tbody>
           	
           		 <% 
                   for (TransctionHistory tr : list) {
                %>
                <tr style=" background: linear-gradient(135deg, rgba(250, 250, 250, 1) 0%, rgba(240, 240, 240, 1) 100%);">
                   <td><%= tr.getTrans_id() %></td>
                    <td><%= tr.getTrans_time() %></td>
                    <td><%= tr.getAmount() %>
						<b>
					<%if(tr.getTrans_type().equals("Debited")){ %>
                    	Rs.  (Debited.)
                    	<%}else{ %>
                    	Rs.  (Credited.)
                    	<%} %>    
                    	</b></td>
                    <td style="color:green"><%= tr.getCurrent_bal() %>
                    <b>Cr.</b>
                    </td>
                    
                </tr>
                <% } %>
            </tbody>
        </table>
       </div>
       
         <%} %>
       
 
   		<!-- ----------------------------------  end ----------------------------- -->
   
     
        <!-- ---------------------------- Transfer Popup Form --------------------->
        
      
    <div id="myModal" class="modal">
       
        <div class="modal-content">
            <span class="close">&times;</span>
            <h3>Money Transfer</h3>

            <!-- Form -->
            <form action="transferAmount" method="post">
            <div class="form-group">
                <label for="sender">Sender's Account No.:</label>
                <input type="text" id="sender" name="myAcc" placeholder="Enter sender's Account No." value="<%=bankCustomer.getAcno()%>" readonly="readonly">
            </div>
            <div class="form-group">
                <label for="receiver">Receiver's Account No.:</label>
                <input type="number" id="receiver" name="anotherAcc" placeholder="Enter receiver's Account No." required="required">
            </div>
            <div class="form-group">
                <label for="amount">Amount:</label>
                <input type="number" id="amount" name="amount" placeholder="Enter amount"  min="1" max="<%=bankCustomer.getBalance()%>" required="required">
            </div>
            <div class="form-group">
                <button type="submit">Transfer</button>
            </div>
            </form>
        </div>
    </div>
        
  
		
</div>
		
		

    <footer>
        <p>&copy; 2024 Banking Management System. All rights reserved.</p>
    </footer>




    <script>
  //================   Account Balance  ==================//
	const balance= document.getElementById("balance");
	setTimeout(()=>{
		balance.textContent="";
	},10000);

	//================ popup form ======================//
	  // Get the modal and the button that opens it
        var modal = document.getElementById("myModal");
        var btn = document.getElementById("myBtn");
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal
        btn.onclick = function() {
            modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
       
        // to hide msg after 10 seconds
                const msg = document.getElementById('h3');
        setTimeout(()=>{
            msg.textContent ="";
        },5000)
	
    
    </script>
    
</body>
</html>