<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Model.*"%>
<!DOCTYPE html>
<html>
<head>
    <title>Bank Account Creation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f4f4f9;
        }
        .form-container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            text-align: center;
            color: #007bff;
        }
        .form-section {
            margin-bottom: 20px;
        }
        .form-section h3 {
            margin-bottom: 10px;
            border-bottom: 2px solid #007bff;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .form-group button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
		<% Customer customer = (Customer)session.getAttribute("customer");
			String str[] = customer.getFullName().split(" ");
		%>

    <div class="form-container">
        <h2>Create Bank Account</h2>
        <form action="CreateAccount" method="post">

            <div class="form-section">
                <h3>Personal Details</h3>
                <div class="form-group">
                    <label for="uname">Username:</label>
                    <input type="text" id="uname" name="uname" value="<%=customer.getUsername()%>" readonly="readonly">
                </div>
                <div class="form-group">
                    <label for="fname">First Name:</label>
                    <input type="text" id="fname" name="fname" value="<%=str[0] %>" required>
                </div>
                <div class="form-group">
                    <label for="lname">Last Name:</label>
                    <input type="text" id="lname" name="lname" value="<%=str[1] %>" required>
                </div>
                <div class="form-group">
                    <label for="dob">Date of Birth:</label>
                    <input type="date" id="dob" name="dob" required>
                </div>
                <div class="form-group">
                    <label for="gender">Gender:</label>
                    <select id="gender" name="gender" required>
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="nationality">Nationality:</label>
                    <input type="text" id="nationality" name="nationality" required>
                </div>
            </div>

            <div class="form-section">
                <h3>Contact Information</h3>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="<%=customer.getEmail() %>" required>
                </div>
                <div class="form-group">
                    <label for="phon">Phone Number:</label>
                    <input type="text" id="phon" name="phon" required>
                </div>
                <div class="form-group">
                    <label for="city">City:</label>
                    <input type="text" id="city" name="city" required>
                </div>
                <div class="form-group">
                    <label for="state">State:</label>
                    <input type="text" id="state" name="state" required>
                </div>
                <div class="form-group">
                    <label for="pincode">Pincode:</label>
                    <input type="number" id="pincode" name="pincode" required>
                </div>
            </div>

            <div class="form-section">
                <h3>Account Details</h3>
                <div class="form-group">
                    <label for="accType">Account Type:</label>
                    <select id="accType" name="accType" required>
                        <option value="">Select Account Type</option>
                        <option value="Savings">Savings</option>
                        <option value="Current">Current</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="balance">Initial Balance:</label>
                    <input type="number" id="balance" name="balance" value="500" >
                </div>
            </div>

            <div class="form-group">
                <button type="submit">Create Account</button>
            </div>
        </form>
    </div>
</body>
</html>
