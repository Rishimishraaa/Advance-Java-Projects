package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Bean.Admin1;
import Bean.User;
import DAO.AdminDAO;
import DAO.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminOperationServlet{
	AdminDAO ad = new AdminDAO();
	UserDAO userDao = new UserDAO();
	
	protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uname= req.getParameter("name");
		String pass = req.getParameter("pass");
		Admin1 admin = ad.login(uname, pass);
		
		if(admin != null) {
			String name = admin.getName();
			String val[] = name.split(" ");
			ServletContext sct = req.getServletContext();
			sct.setAttribute("admin", admin);
//			Cookie ck=new Cookie("username", val[0]);
//			ck.setPath("/");
//			resp.addCookie(ck);
			HttpSession session = req.getSession();
			session.setAttribute("msg1", val[0]);
//			session.setAttribute("msg2", val[1]);
			session.setMaxInactiveInterval(20);
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "Invalid Login Details.....");
			req.getRequestDispatcher("admin.jsp").include(req, resp);
		}
		
	}
	
	protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
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

	   
	    resp.sendRedirect("admin.jsp");
	}
	
	//////////////////////////////////////////////////// Admin ////////////////////////////////////////////////////////////////////////
	
	protected void showAllAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Admin1> list = ad.showAllAdmin();
		
		if(list.isEmpty()) {
			req.setAttribute("msg", "Admin Not Found...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else {
			req.setAttribute("list",list);
			req.getRequestDispatcher("showAdmin.jsp").forward(req, resp);
		}
		
	}
	
	protected void editAdminDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Admin1 admin=new Admin1();
		String uname = req.getParameter("uname");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass= req.getParameter("pass");
		
		admin.setUname(uname);
		admin.setName(name);
		admin.setEmail(email);
		admin.setPass(pass);

		String aName = admin.getName();
		String val[] = aName.split(" ");
		
		
		
		boolean flag = ad.editAdminDetails(admin);
		if(flag==false) {
			req.setAttribute("msg", "Admin Updatation Failed...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("msg1", val[0]);
//			session.setAttribute("msg2", val[1]);
			session.setMaxInactiveInterval(5*60);
			req.setAttribute("admin", admin);
			ServletContext sct = req.getServletContext();
			sct.setAttribute("admin", admin);
			req.setAttribute("msg", "Admin Update Successfully....");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}
	}
	
	protected void registerAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Admin1 admin = new Admin1();
		String uname = req.getParameter("uname");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass= req.getParameter("pass");
		
		admin.setUname(uname);
		admin.setName(name);
		admin.setEmail(email);
		admin.setPass(pass);
		
	//	System.out.println(uname+" "+name+" "+email+" "+pass);
		
		
		boolean flag = ad.insertAdmin(admin);
		if(flag==false) {
			req.setAttribute("msg", "Admin Register Failed...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "Admin Registration Successfully....");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}
	}
	
	protected void showEditAdminForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("id");
		Admin1 admin = ad.showAdminByUsername(username);
		req.setAttribute("admin", admin);
		req.getRequestDispatcher("updateAdministrator.jsp").forward(req, resp);
	}
	
	protected void updateAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Admin1 admin = new Admin1();
		String uname = req.getParameter("uname");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass= req.getParameter("pass");
		
		admin.setUname(uname);
		admin.setName(name);
		admin.setEmail(email);
		admin.setPass(pass);
		
		boolean flag = ad.editAdminDetails(admin);
		if(flag==false) {
			req.setAttribute("msg", "Admin Update Failed...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "Admin Update Successfully....");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}
		
	
	}
	
	protected void deleteAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("id");
		boolean flag = ad.deleteAdmin(username);
		if(flag==false) {
			req.setAttribute("msg", "Admin Deleted Failed...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "Admin Deleted Successfully....");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}
		
		
	}
	
	protected void showAllUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<User> list = userDao.getAllUsers();
		for(User user : list) {
		System.out.println(user.getEmail());
		}
		if(list.isEmpty()) {
			req.setAttribute("msg", "User Not Found...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else {
			req.setAttribute("list",list);
			req.getRequestDispatcher("showAllUsers.jsp").forward(req, resp);
		}
		
		
	}
	
	protected void showUserEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid=req.getParameter("id");
		User user = userDao.getUserById(userid);
		
		if(user!=null) {
			req.setAttribute("user", user);
			req.getRequestDispatcher("updateUserDetailsByAdmin.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "User not found...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}
	}
	
	protected void updateUserDetailsByAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = new User();
		user.setUserid(req.getParameter("uid"));
		user.setUname(req.getParameter("name"));
		user.setEmail(req.getParameter("email"));
		user.setPhno(Long.parseLong(req.getParameter("phno")));
		user.setCity(req.getParameter("city"));
		user.setPass(req.getParameter("pass"));
		
		boolean flag = userDao.updateUserByAdmin(user);
		if(flag==true) {
			req.setAttribute("msg", "Update Successfully....");
			req.getRequestDispatcher("showAllUsers.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "Update user failed...");
			req.getRequestDispatcher("showAllUsers.jsp");
		}
		
	}
	
	protected void deleteUserByAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("id");
		boolean flag = userDao.deleteUserByAdmin(userid);
		if(flag==true) {
			req.setAttribute("msg", "User deleted successfully");
			req.getRequestDispatcher("showAllUsers.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "User deleted failed");
			req.getRequestDispatcher("showAllUsers.jsp").forward(req, resp);
		}
	}
}
