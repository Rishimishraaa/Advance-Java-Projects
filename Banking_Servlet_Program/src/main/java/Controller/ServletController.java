package Controller;

import java.io.IOException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/")
public class ServletController extends HttpServlet{
	
	CustomerOperationServlet customerOperation = null;
	BankingOperationServlet bankingOperation = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		customerOperation = new CustomerOperationServlet();
		bankingOperation = new BankingOperationServlet();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		System.out.println("hello Rishi");
		// Customer Operation Area
		
		switch(action) {
		
		case "/customerRegister"->{
			customerOperation.register(req, resp);
		}
		
		case "/customerLogin"->{
			customerOperation.login(req, resp);
		}
		
		case "/customerLogout"->{
			customerOperation.logout(req, resp);
		}
		
		case "/createAccountForm"->{
			customerOperation.accountCreationForm(req, resp);
		}
		
		case "/CreateAccount"->{
			customerOperation.accountCreation(req, resp);
		}
		
		case "/updateProfile"->{
			customerOperation.updateProfile(req,resp);
		}
		
		}
		
		// This is Banking Operation Area.....
		
		switch (action) {
		case "/checkBalance"->{
			bankingOperation.checkBalance(req, resp);
		}
		
		case "/transferAmount"->{
			bankingOperation.transferBalance(req, resp);
		}
		
		case "/MiniStatement"->{
			bankingOperation.transaction_log(req, resp);
		}
		
		case "/deleteAccount"->{
			bankingOperation.deleteAccount(req, resp);
		}
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}



}

