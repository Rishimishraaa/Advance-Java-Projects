package Controller;

import java.io.IOException;
import java.util.ArrayList;

import Bean.BookDetails;
import DAO.SearchingTypeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/searchingBook")
public class SearchingBookServlet extends HttpServlet{
	private SearchingTypeDAO search=null;
	@Override
	public void init() {
		search = new SearchingTypeDAO();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String action = req.getParameter("operation");
			System.out.println(action);
			
			switch(action) {
			case "Book_ID" ->{
				searchById(req, resp);
			}
			case "Book_Name"->{
				searchByName(req, resp);
			}
			case "Book_Author"->{
				searchByAuthor(req, resp);
			}
			default->{
				resp.sendRedirect("profile.jsp");
			}
			}
			
			
	}
	
	private void searchById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bcode = req.getParameter("inp");
		ArrayList<BookDetails> book = search.searchById(bcode);
		
		if(book.isEmpty()) {
			req.setAttribute("msg", "Book Nof Found...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else{
			req.setAttribute("list",book);
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}
	}
	private void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bcode = req.getParameter("inp");
		ArrayList<BookDetails> book = search.searchByName(bcode);
		
		if(book.isEmpty()) {
			req.setAttribute("msg", "Book Nof Found...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else{
			req.setAttribute("list",book);
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}
	}
	private void searchByAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bcode = req.getParameter("inp");
		ArrayList<BookDetails> book = search.searchByAthour(bcode);
		
		if(book.isEmpty()) {
			req.setAttribute("msg", "Book Not Found...");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else{
			req.setAttribute("list",book);
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}
	}
	
}
