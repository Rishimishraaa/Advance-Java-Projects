package Controller;
import java.io.IOException;
import Dao.BankCustomerDao;
import Dao.CustomerDao;
import Model.BankCustomers;
import Model.Customer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CustomerOperationServlet {
	
	private CustomerDao customerDao=new CustomerDao();
	private BankCustomerDao bankCustomerDao = new BankCustomerDao();
	
	//////////////////////////////// Customer Login //////////////////////////////////////
	
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("uname");
		String password = req.getParameter("pass");
		
		Customer customer = customerDao.customerLogin(username, password);
		BankCustomers bankCustomer = bankCustomerDao.validate(username);
		
		if(customer !=null && bankCustomer !=null){
			HttpSession session = req.getSession();
			session.setAttribute("bankcustomer", bankCustomer);
			session.setAttribute("customer", customer);
			String fname = bankCustomer.getFname();
			session.setAttribute("fname",fname);
			req.getRequestDispatcher("bankingapp.jsp").forward(req, resp);
			
		}else {
			
			if(customer !=null) {
				HttpSession session = req.getSession();
				session.setAttribute("customer", customer);
				String fname[] = customer.getFullName().split(" ");
				session.setAttribute("fname",fname[0]);
				req.getRequestDispatcher("home.jsp").forward(req, resp);
				
			}else {
				
				req.setAttribute("msgR", "Invalid Credential...");
				req.getRequestDispatcher("customerLogin.jsp").forward(req, resp);
			}
			
		}
		
	}
	
	//////////////////////////////// Customer Register ///////////////////////////////////
	protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		
		if(password.equals(confirmPassword)) {
			Customer customer = new Customer();
			customer.setFullName(fullname);
			customer.setEmail(email);
			customer.setUsername(username);
			customer.setPassword(password);
			
			boolean flag = customerDao.customerRegister(customer, req, resp);
			
			if(flag==true) {
				req.setAttribute("msgG","Registration Successfully...");
				req.getRequestDispatcher("customerLogin.jsp").forward(req, resp);
			}else {
				req.setAttribute("msgR", "Registration Failed....");
				req.getRequestDispatcher("customerRegister.jsp").forward(req, resp);
			}
		}else {
			req.setAttribute("msgR", "Password Missmatch");
			req.getRequestDispatcher("customerRegister.jsp").forward(req, resp);
		}
	}
	
	/////////////////////////////////// Customer Logout //////////////////////////////////////////////

	protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession(false);
			session.invalidate();
			req.setAttribute("msgG", "Logout Successfully..");
			req.getRequestDispatcher("customerLogin.jsp").forward(req, resp);
	} 
	
	
	protected void accountCreationForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Customer cust =(Customer)session.getAttribute("customer");
		if(cust==null) {
			req.setAttribute("msgR", "Session Expired..");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}else {
			
		req.setAttribute("customer", cust);
		req.getRequestDispatcher("createAccount.jsp").forward(req, resp);
}
	}
	protected void accountCreation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname = req.getParameter("uname");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String dob = req.getParameter("dob");
		String gender = req.getParameter("gender");
		String nationality = req.getParameter("nationality");
		String email = req.getParameter("email");
		long phon = Long.parseLong(req.getParameter("phon"));
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		int pincode = Integer.parseInt(req.getParameter("pincode"));
		String acctype =req.getParameter("accType");
		double initial =Double.parseDouble(req.getParameter("balance"));
		
		HttpSession session = req.getSession(false);
		Customer cust =(Customer)session.getAttribute("customer");
		if(cust==null) {
			req.setAttribute("msgR", "Session Expired..");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}else {
		BankCustomers bankCustomers = new BankCustomers();
		bankCustomers.setUname(uname);
		bankCustomers.setFname(fname);
		bankCustomers.setLname(lname);
		bankCustomers.setDob(dob);
		bankCustomers.setGender(gender);
		bankCustomers.setNationality(nationality);
		bankCustomers.setEmail(email);
		bankCustomers.setPhon(phon);
		bankCustomers.setCity(city);
		bankCustomers.setState(state);
		bankCustomers.setPincode(pincode);
		bankCustomers.setAccType(acctype);
		bankCustomers.setBalance(initial);
		
		boolean flag = bankCustomerDao.createCustomerAccount(bankCustomers);
		
		if(flag == true) {
			req.setAttribute("msgG", "Account Created Successfully Login Again..");
			req.getRequestDispatcher("customerLogin.jsp").forward(req, resp);
		}else {
			req.setAttribute("msgR", "Account creation failed..");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}
		}
		
	}
	protected void updateProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("userid");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		Customer customer =customerDao.updateProfile(uid, email, pass);
		if(customer!=null) {
			req.setAttribute("msgG", "Your Profile Updated....");
			HttpSession session = req.getSession(false);
			session.setAttribute("customer", customer);
			req.getRequestDispatcher("bankingapp.jsp").forward(req, resp);
		}else {
			req.setAttribute("msgR", "Profile Not Updated...");
			req.getRequestDispatcher("bankingapp.jsp").forward(req, resp);
		}
	}
	

}
