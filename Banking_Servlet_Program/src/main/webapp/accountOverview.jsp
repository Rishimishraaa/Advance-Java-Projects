<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Model.*"%>
    <%@page errorPage="error.jsp" %>
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
        
     
        
.details-container {
    background: #fff;
    padding: 25px;
    border-radius: 8px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 1200px; /* Increased max-width to fit all details */
    animation: fadeIn 1s ease-in-out;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

h1 {
    text-align: center;
    margin-bottom: 30px;
    font-size: 28px;
    color: #333;
    width: 100%;
}

.details-section {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;  /* Space between items */
    width: 30%;  /* Adjust width for each section */
}

.detail-item {
    display: flex;
    flex-direction: column;
    width: 100%;
}

label {
    font-weight: bold;
    color: #333;
    margin-bottom: 5px;
}

span {
    display: block;
    font-size: 16px;
    color: #555;
    padding: 8px;
    background-color: #e9ecef;
    border-radius: 5px;
    text-align: center; /* Center text in the span */
}

@keyframes fadeIn {
    0% {
        opacity: 0;
        transform: translateY(-20px);
    }
    100% {
        opacity: 1;
        transform: translateY(0);
    }
}

@media screen and (max-width: 768px) {
    .details-section {
        width: 45%;  /* Adjust width for medium screens */
    }
}

@media screen and (max-width: 480px) {
    .details-section {
        width: 100%;  /* Full width for small screens */
    }
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
        <a href="bankingapp.jsp" id="ca"><i class="fa-solid fa-house"></i>Home</a>
        <a href="accountOverview.jsp" id="ca"><i class="fas fa-user-circle"></i>Account Overview</a>
        <a href="bankingapp.jsp"><i class="fas fa-exchange-alt"></i>Transfer Funds</a>
        <a href="miniStatement.jsp" ><i class="fas fa-receipt"></i>Transactions</a>
        <a href="showProfile.jsp" ><i class="fas fa-user"></i>Profile</a>
        <a href="customerLogout"><i class="fas fa-sign-out-alt"></i>Logout</a>
    </nav>

    <div class="container">
        <h2>Dashboard</h2>
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
    	
   
   
        <!-- ------------------------------ Transfer amount -------------------------- -->
     <div id="form" class="modal">
       
        <div class="modal-content">
            <span class="close" id="close">&times;</span>
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
        
    <!-- ---------------------------- Account Overview ---------------------- -->
    
		  <div class="details-container" style=" background: linear-gradient(135deg, rgba(250, 250, 250, 1) 0%, rgba(240, 240, 240, 1) 100%);">
        <h1 style="color:navy;">Customer Details</h1>
        
        <!-- Personal Details Section -->
        <div class="details-section">
            <div class="detail-item">
                <label for="fname">First Name:</label>
                <span id="fname" ><%=bankCustomer.getFname() %></span>
            </div>
        
           
            <div class="detail-item">
                <label for="gender">Gender:</label>
                <span id="gender"><%=bankCustomer.getGender() %></span>
            </div>
             <div class="detail-item">
                <label for="zipcode">Zip Code:</label>
                <span id="zipcode"><%=bankCustomer.getPincode() %></span>
            </div>
            <div class="detail-item">
                <label for="accountno">Account Number:</label>
                <span id="accountno"><%=bankCustomer.getAcno()%></span>
            </div>
            <div class="detail-item">
                <label for="branchcode">Bran Code:</label>
                <span id="branchcode"><%=bankCustomer.getBranchcode()%></span>
            </div>
        </div>

        <!-- Address Section -->
        <div class="details-section">
            <div class="detail-item">
                <label for="lname">Last Name:</label>
                <span id="lname" ><%=bankCustomer.getLname() %></span>
            </div>
            <div class="detail-item">
                <label for="street">City:</label>
                <span id="street"><%=bankCustomer.getCity() %></span>
            </div>
           
           
             <div class="detail-item">
                <label for="phone">Phone:</label>
                <span id="phone">+91 <%=bankCustomer.getPhon() %></span>
            </div>
            <div class="detail-item">
                <label for="acopening">Account Opening Date:</label>
                <span id="acopening"><%=bankCustomer.getOpeningDate() %></span>
            </div>
            <div class="detail-item">
                <label for="Nationality">Nationality:</label>
                <span id="Nationality"><%=bankCustomer.getNationality() %></span>
            </div>
        </div>

        <!-- Contact Info Section -->
        <div class="details-section">
            <div class="detail-item">
                <label for="dob">Date of Birth:</label>
                <span id="dob"><%=bankCustomer.getDob() %></span>
            </div>
             <div class="detail-item">
                <label for="city">State:</label>
                <span id="city"><%=bankCustomer.getState() %></span>
            </div>
             <div class="detail-item">
                <label for="email">Email:</label>
                <span id="email"><%=bankCustomer.getEmail() %></span>
            </div>
            
            <div class="detail-item">
                <label for="acctype">Account Type:</label>
                <span id="acctype"><%=bankCustomer.getAccType() %></span>
            </div>
            <div class="detail-item">
                <label for="delete">Delete Account:</label>
                <button id="openModalBtn" class="open-btn">Delete Account</button>
            </div>
        </div>
    </div>    
  
      
        <!-- ------------------------------ end --------------------------------- -->
  <style>
  .modal {
    display: none; /* Hidden by default */
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* Black background with transparency */
}

.modal-content {
    background-color: #fff;
    margin: 15% auto;
    padding: 20px;
    border-radius: 8px;
    width: 50%;
    text-align: center;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
}

.close {
    color: #aaa;
    font-size: 28px;
    font-weight: bold;
    position: absolute;
    top: 10px;
    right: 20px;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

h2 {
    font-size: 24px;
    color: #333;
}

.modal-buttons {
    margin-top: 20px;
}

button {
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border: none;
    border-radius: 5px;
    margin: 5px;
}

.open-btn {
    padding: 15px 30px;
    background-color: #f44336;
    color: white;
    font-size: 18px;
    cursor: pointer;
    border-radius: 5px;
}

.open-btn:hover {
    background-color: #e53935;
}

.confirm-btn {
    background-color: #4CAF50;
    color: white;
}

.confirm-btn:hover {
    background-color: #45a049;
}

.cancel-btn {
    background-color: #9e9e9e;
    color: white;
}

.cancel-btn:hover {
    background-color: #757575;
}
  </style>
  
  <!-- Confirmation Popup -->
    <div id="confirmationModal" class="modal">
        <div class="modal-content">
            <span id="closeModalBtn" class="close">&times;</span>
            <h2>Are you sure?</h2>
            <p>Do you really want to delete your account? This action cannot be undone.</p>
            <div class="modal-buttons">
             <a  href="deleteAccount?id=<%=customer.getUsername()%>&accno=<%=bankCustomer.getAcno()%>"><button  class="confirm-btn" id="confirmBtn">Delete Account</button> </a>
                <button id="cancelBtn" class="cancel-btn">Cancel</button>
            </div>
        </div>
    </div>
 
 </div>

    <footer>
        <p>&copy; 2024 Banking Management System. All rights reserved.</p>
    </footer>


  <script>

   // show profile 
var modal = document.getElementById("confirmationModal");
var openModalBtn = document.getElementById("openModalBtn");
var closeModalBtn = document.getElementById("closeModalBtn");
var confirmBtn = document.getElementById("confirmBtn");
var cancelBtn = document.getElementById("cancelBtn");

// Open modal when button is clicked
openModalBtn.onclick = function() {
    modal.style.display = "block";
}

// Close modal when 'x' or cancel button is clicked
closeModalBtn.onclick = function() {
    modal.style.display = "none";
}

cancelBtn.onclick = function() {
    modal.style.display = "none";
}


// Close modal when clicked outside of the modal content
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

    </script>

    <script>
	//================ popup form ======================//
	  // Get the modal and the button that opens it
      const form = document.getElementById("form");
      const btn = document.getElementById("myBtn");
      const btn2 = document.getElementById("myBtn2")
      const close2 = document.getElementById("close")
      // When the user clicks the button, open the modal
      btn.onclick = function() {
          form.style.display = "block";
      }
 
      
      close2.onclick = function() {
          form.style.display = "none";
      }

      // When the user clicks anywhere outside the modal, close it
      window.onclick = function(event) {
          if (event.target == form) {
             form.style.display = "none";
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