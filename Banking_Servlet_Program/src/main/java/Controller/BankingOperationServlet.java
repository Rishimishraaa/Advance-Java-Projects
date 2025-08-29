package Controller;
import java.io.IOException;
import Dao.BankingOperationDao;
import Model.BankCustomers;
import Model.TransctionHistory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

public class BankingOperationServlet {
	BankingOperationDao bankOperationDao = new BankingOperationDao();
	
	protected void checkBalance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		BankCustomers bank =(BankCustomers)session.getAttribute("bankcustomer");
		if(bank==null) {
			req.setAttribute("msgR", "Session Expired..");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}else {
			String accno = req.getParameter("id");
			double balance= bankOperationDao.checkBalance(accno);
			req.setAttribute("balance", balance);
			req.getRequestDispatcher("bankingapp.jsp").forward(req, resp);	
		}

	}
	
	protected void transferBalance(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String myAccount = req.getParameter("myAcc");
		String anotherAcc = req.getParameter("anotherAcc");
		double amount =Double.parseDouble(req.getParameter("amount"));
		HttpSession session = req.getSession(false);
		BankCustomers bank =(BankCustomers)session.getAttribute("bankcustomer");
		if(bank==null) {
			req.setAttribute("msgR", "Session Expired..");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}else {
		boolean isAvalable = bankOperationDao.findAccount(anotherAcc);
		if(isAvalable==true) {
			boolean flag =bankOperationDao.transaction(myAccount, anotherAcc, amount);
			if(flag==true) {
				req.setAttribute("msgG", "Transfering Amount Successfully...");
				req.getRequestDispatcher("bankingapp.jsp").forward(req, resp);
			}else {
				req.setAttribute("msgR", "Transfering Amount Failed...");
				req.getRequestDispatcher("bankingapp.jsp").forward(req, resp);
			}
		}else {
			req.setAttribute("msgR", "Invalid Account Number....");
			req.getRequestDispatcher("bankingapp.jsp").forward(req, resp);
		}
	}
	}
	
	protected void transaction_log(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accno = req.getParameter("accountNumber");	
		ArrayList<TransctionHistory> al = bankOperationDao.fetchTransaction(accno);
		HttpSession session = req.getSession(false);
		BankCustomers bank =(BankCustomers)session.getAttribute("bankcustomer");
		if(bank==null) {
			req.setAttribute("msgR", "Session Expired..");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}else {
		
		if(al.isEmpty()) {
			req.setAttribute("msgR", "No Transaction Found...");
			req.getRequestDispatcher("miniStatement.jsp").forward(req, resp);
		}else {
			req.setAttribute("list", al);
			req.getRequestDispatcher("miniStatement.jsp").forward(req, resp);
		}
	}
	}
	
	protected void deleteAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String accno = req.getParameter("accno");
		HttpSession session = req.getSession(false);
		BankCustomers bank =(BankCustomers)session.getAttribute("bankcustomer");
		if(bank==null) {
			req.setAttribute("msgR", "Session Expired..");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}else {
		boolean flag = bankOperationDao.deleteBankAccount(accno);
		if(flag==true) {
			req.setAttribute("msgG", "Account Deleted...");
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}else {
			req.setAttribute("msgR", "Account Deletion Failed...");
			req.getRequestDispatcher("bankingapp.jsp").forward(req, resp);
		}
	}
		
	}
}
