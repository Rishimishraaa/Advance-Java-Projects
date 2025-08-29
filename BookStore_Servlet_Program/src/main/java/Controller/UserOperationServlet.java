package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Bean.BookDetails;
import Bean.User;
import DAO.BookDAO;
import DAO.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserOperationServlet {
	UserDAO udo = new UserDAO();
	BookDAO bdo = new BookDAO();
	
	
	protected void userRegister(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
		User user = new User();
		user.setUserid(req.getParameter("uid"));
		user.setUname(req.getParameter("uname"));
		user.setEmail(req.getParameter("email"));
		user.setCity(req.getParameter("city"));
		user.setPhno(Long.parseLong(req.getParameter("phno")));
		user.setPass(req.getParameter("pass"));
		
		boolean flag = udo.userRegister(user);
		
		if(flag == true) {
			req.setAttribute("msg", "User registred successfully...<br>");
			req.getRequestDispatcher("userlogin.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "User registration failed...<br>");
			req.getRequestDispatcher("userRegister.jsp").forward(req, resp);
		}
	}
	
	protected void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("uname");
		String pass = req.getParameter("pass");
		User user = udo.login(username, pass);
		if(user !=null) {
			ServletContext sct = req.getServletContext();
			sct.setAttribute("user", user);
			HttpSession session = req.getSession();
			String fname[] = user.getUname().split(" ");
			session.setAttribute("msg", fname[0]);
			session.setMaxInactiveInterval(60*5);
			
			req.getRequestDispatcher("userProfile.jsp").forward(req, resp);
		}else{
			req.setAttribute("msg", "User login failed...");
			req.getRequestDispatcher("userlogin.jsp").forward(req, resp);
		}
	}
	
	protected void userLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		 // Invalidate session
	    HttpSession session = req.getSession(false); // Do not create a new session
	    if (session != null) {
	        session.removeAttribute("msg");
	        session.invalidate();
	    }

//	    // Remove cookies
//	    Cookie[] cookies = req.getCookies();
//	    if (cookies != null) {
//	        for (Cookie cookie : cookies) {
//	            if (cookie.getName().equals("username")) {
//	                cookie.setMaxAge(0); 
//	                cookie.setPath("/"); 
//	                resp.addCookie(cookie);
//	            }
//	        }
//	    }

	
	    resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    resp.setHeader("Pragma", "no-cache");
	    resp.setDateHeader("Expires", 0);

	   
	    resp.sendRedirect("userlogin.jsp");
	}
	
	protected void editUserProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		String uid = req.getParameter("uid");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		long phno =Long.parseLong(req.getParameter("phno"));
		String city = req.getParameter("city");
		String pass = req.getParameter("pass");
		
		user.setUserid(uid);
		user.setUname(name);
		user.setEmail(email);
		user.setPhno(phno);
		user.setCity(city);
		user.setPass(pass);

		String aName = user.getUname();
		String val[] = aName.split(" ");
		
		
		
		boolean flag = udo.editUserDetails(user);
		if(flag==false) {
			req.setAttribute("msg", "User Updatation Failed...");
			req.getRequestDispatcher("userProfile.jsp").forward(req, resp);
		}else {
			HttpSession session = req.getSession(false);
			session.setAttribute("msg", val[0]);
//			session.setAttribute("msg2", val[1]);
			session.setMaxInactiveInterval(5*60);
			req.setAttribute("user", user);
			ServletContext sct = req.getServletContext();
			sct.setAttribute("user", user);
			req.setAttribute("msg", "User Update Successfully....");
			req.getRequestDispatcher("userProfile.jsp").forward(req, resp);
		}
	}
	protected void purchaseForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookId = req.getParameter("id");
		System.out.println("purchaseForm method "+bookId);
		BookDetails book= bdo.displayById(bookId);
		req.setAttribute("book", book);
		
		req.getRequestDispatcher("purchaseForm.jsp").forward(req, resp);
	}
	protected void purchaseBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bcode = req.getParameter("bcode");
		int qty =Integer.parseInt(req.getParameter("quantity"));
		String uid = req.getParameter("uid");
		boolean flag = bdo.saleBook(bcode, qty,uid);

		if(flag == true) {
			req.setAttribute("msg", "Book Purchased..");
			req.getRequestDispatcher("userProfile.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "Book not purchased...");
			req.getRequestDispatcher("userProfile.jsp");
		}
	}
	
	protected void getMyBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("id");
		ArrayList<BookDetails> book =	bdo.displayUserBook(userid);
		if(book !=null) {
			req.setAttribute("list", book);
			System.out.println(book);
			req.getRequestDispatcher("mybook.jsp").forward(req, resp);;
		}else {
			req.setAttribute("msg", "Book not found...");
			req.getRequestDispatcher("userProfile.jsp").forward(req, resp);;
		}
	}
	protected void removeMyBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bcode = req.getParameter("id");
		boolean flag = bdo.removeMyBook(bcode);
		if(flag==true) {
			req.setAttribute("msg", "Book Removed Successfully..");
			req.getRequestDispatcher("mybook.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "Book not Removed..");
			req.getRequestDispatcher("mybook.jsp").forward(req, resp);
		}
	}
}
