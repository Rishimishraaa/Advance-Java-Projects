package Controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/")
public class ServletController extends HttpServlet{
	BookOperationServlet bop = null;
	AdminOperationServlet ado =null;
	UserOperationServlet uo = null;
	BookOperationByUserServlet userBook = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		ado = new AdminOperationServlet();
		bop = new BookOperationServlet();
		uo = new UserOperationServlet();
		userBook=new BookOperationByUserServlet();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String path = req.getServletPath();
		System.out.println(path);
		
		switch(path) {

		case "/alogin" ->{
			ado.login(req, resp);
		}
		
		case "/editAdmin"->{
			ado.editAdminDetails(req, resp);
		}
	
		case "/showAllAdmin"->{
			ado.showAllAdmin(req, resp);
		}
		case "/showAllUsers"->{
			ado.showAllUsers(req, resp);
			}
		case "/registerAdmin"->{
			ado.registerAdmin(req, resp);
		}
		case "/updateAdmin"->{
			ado.updateAdmin(req, resp);
		}
		case "/showEditAdminForm"->{
			ado.showEditAdminForm(req, resp);
		}
		case "/showEditUserForm"->{
			System.out.println("usereditform");
			ado.showUserEditForm(req, resp);
		}
		case "/editUserByAdmin"->{
			ado.updateUserDetailsByAdmin(req, resp);
		}
		case "/deleteAdmin"->{
			ado.deleteAdmin(req, resp);
		}		
		case "/deleteUser"->{
			ado.deleteUserByAdmin(req, resp);
		}
		case "/logout"->{
			ado.logout(req, resp);
		} 
		}
		
		
		//////////////////////////// User Operation //////////////////////////////////
		
		switch(path) {
		case "/userLogin"->{
			uo.userLogin(req, resp);
		}
		case "/userRegister" ->{
			try {
				uo.userRegister(req, resp);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		case "/userSearchingBook"->{
			req.getRequestDispatcher("userSearchingB").forward(req, resp);
		}
		
		case "/userShowBook"->{
			userBook.userBookDisplay(req, resp);
		}
		
		case "/editUserProfile"->{
			uo.editUserProfile(req, resp);
		}
		
		case "/purchaseform"->{
			uo.purchaseForm(req,resp);
		}
		
		case "/purchase"->{
			uo.purchaseBook(req,resp);
		}
		case "/ulogout"->{
			uo.userLogout(req, resp);
		}
		
		case "/mybooks"->{
			uo.getMyBooks(req, resp);
		}
		case "/removeBook"->{
			uo.removeMyBook(req, resp);
		}
		}
		
		
		
		//////////////////////////// Book Operations /////////////////////////////////
		
		switch (path) {
	
		case "/insert" ->{
			bop.insert(req, resp);
		}
		case "/dis"->{
			bop.display(req, resp);
		}
		case "/edit"->{
			bop.showEditForm(req, resp);
		}
		case "/update"->{
			bop.update(req, resp);
		}
		case "/delete"->{
			bop.delete(req, resp);
		}
		case "/search"->{
			req.getRequestDispatcher("searchingBook").forward(req, resp);
		}
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}	
	
}
