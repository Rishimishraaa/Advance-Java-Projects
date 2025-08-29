<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Bean.BookDetails,Bean.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Purchase Form</title>
 <style>
 body{
background: linear-gradient(135deg, #74ebd5, #9face6);
}
        button {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #45a049;
        }
        /* Popup form overlay 
        .popup-form-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.6);
            z-index: 999;
        }
        /* Popup form container */
        .popup-form {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #ffffff;
            padding: 30px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            border-radius: 15px;
            z-index: 1000;
            text-align: center;
        }
        .popup-form h2 {
            margin-bottom: 20px;
            font-weight: 600;
            color: #333;
        }
        .popup-form form input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        .popup-form form button {
            width: 100%;
            background-color: #007BFF;
            color: white;
            padding: 12px 0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .popup-form form button:hover {
            background-color: #0056b3;
        }
        .close-button {
            position: absolute;
            top: 15px;
            right: 15px;
            background-color: #ff4d4d;
            color: white;
            border: none;
            font-size: 16px;
            padding: 5px 10px;
            border-radius: 50%;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .close-button:hover {
            background-color: #cc0000;
        }
    
    </style>
</head>
<body>


<% BookDetails book = (BookDetails) request.getAttribute("book"); 
	User user =(User)application.getAttribute("user");
%>

 <div id="popupForm" class="popup-form">
       <a href="userShowBook"><button class="close-button" id="closeButton">&times;</button></a> 
        <h2>Purchase Book</h2>
        <form action="purchase" method="post">
        	<input type="hidden" name="uid" value="<%= user.getUserid()%>">
           <input type="text" id="quantity" name="bcode" value="<%= book.getBookCode()%>" readonly="readonly" required>
        
            <input type="text" id="productName" name="bname" value="<%= book.getBookName()%>"  readonly="readonly" required>
            <input type="number" id="price" name="price" value="<%= book.getBookPrice() %>" readonly="readonly"  required>
            <input type="number" id="quantity" name="quantity" placeholder="Quantity" min="1" max="<%=book.getBookQty()%>" required>
           
            <button type="submit">Submit</button>
        </form>
    </div>
    
      <script>
      window.onload = () => {
          const popupForm = document.getElementById('popupForm');
          const popupOverlay = document.getElementById('popupOverlay');
          const closeButton = document.getElementById('closeButton');

          // Show the popup form and overlay
          popupForm.style.display = 'block';
          popupOverlay.style.display = 'block';

          // Close the popup form
          closeButton.addEventListener('click', () => {
              popupForm.style.display = 'none';
              popupOverlay.style.display = 'none';
          });

          popupOverlay.addEventListener('click', () => {
              popupForm.style.display = 'none';
              popupOverlay.style.display = 'none';
          });
      };
    </script>
</body>
</html>