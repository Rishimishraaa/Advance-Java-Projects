package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Bean.BookDetails;
import DAO.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookOperationByUserServlet {
	BookDAO bd = new BookDAO();;

	//////////////////////////////// Book Operation /////////////////////////////////////////
	

	
	protected void userBookDisplay(HttpServletRequest req, HttpServletResponse resp) throws  IOException, ServletException {
		ArrayList<BookDetails> list = bd.displayAllBook();
		
			req.setAttribute("list", list);
			req.setAttribute("msg", "Book Not Found....");
			req.getRequestDispatcher("userProfile.jsp").forward(req, resp);

	}
	
}
