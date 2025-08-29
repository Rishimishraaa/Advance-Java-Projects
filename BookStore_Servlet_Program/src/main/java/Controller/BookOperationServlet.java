package Controller;

import java.io.IOException;
import java.util.ArrayList;
import Bean.BookDetails;
import DAO.BookDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BookOperationServlet{
	BookDAO bd = new BookDAO();;

	//////////////////////////////// Book Operation /////////////////////////////////////////
	
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDetails book = new BookDetails();
		book.setBookCode(req.getParameter("bcode"));
		book.setBookName(req.getParameter("bname"));
		book.setBookAuthor(req.getParameter("bauthor"));
		try {
		book.setBookPrice(Double.parseDouble(req.getParameter("bprice")));
		}catch(Exception e) {
			System.out.println("Here is error : +"+e);
		}
		book.setBookQty(Integer.parseInt(req.getParameter("bqty")));
		boolean k = bd.insert(book);
		if(k==true) {
		req.setAttribute("msg", "Book Inserted Successfully....");
		req.getRequestDispatcher("insert.jsp").forward(req, resp);
		}else {
			req.setAttribute("msg", "Book Already Exists....");
			req.getRequestDispatcher("insert.jsp").forward(req, resp);
		}
	}
	
	protected void display(HttpServletRequest req, HttpServletResponse resp) throws  IOException, ServletException {
		ArrayList<BookDetails> list = bd.displayAllBook();
		
			req.setAttribute("list", list);
			req.setAttribute("msg", "Book Not Found....");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);

	}
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookDetails book = new BookDetails();
		book.setBookCode(req.getParameter("bcode"));
		book.setBookName(req.getParameter("bname"));
		book.setBookAuthor(req.getParameter("bauthor"));
		book.setBookPrice(Double.parseDouble(req.getParameter("bprice")));
		book.setBookQty(Integer.parseInt(req.getParameter("bqty")));
		
		boolean flag = bd.update(book);
		if(flag==true) {
			req.setAttribute("msg", "Book Update Successfully....");
			req.getRequestDispatcher("insert.jsp").forward(req, resp);
			}else {
				req.setAttribute("msg", "Book Not Update.....");
				req.getRequestDispatcher("insert.jsp").forward(req, resp);
			}
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		boolean flag = bd.delete(id);
		if(flag==true) {
			req.setAttribute("msg", "Book Deleted Successfully....");
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
			}else {
				req.setAttribute("msg", "Book Not Deleted....");
				req.getRequestDispatcher("profile.jsp").forward(req, resp);
			}
	}
	protected void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		BookDetails book =bd.displayById(id);
		req.setAttribute("book", book);
		req.getRequestDispatcher("insert.jsp").forward(req, resp);;
	}
	
}
