package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.ArrayList;

import Bean.BookDetails;
import DB_Info.DBConnection;

public class BookDAO {
	private static String DISPLAY_ALL_BOOK = "SELECT * FROM bookdetails" ;
	private static String DISPLAY_BY_ID = "SELECT * FROM bookdetails WHERE BCODE = ?";
	private static String INSERT_SQL =	"INSERT INTO bookdetails VALUES(?,?,?,?,?)";
	private static String UPDATE_SQL = "UPDATE bookdetails SET BNAME = ?, AUTHOR = ?, PRICE=?, QTY =? WHERE BCODE=?";
	private static String DELETE_SQL = "DELETE FROM bookdetails WHERE BCODE=?";
	private static String PURCHASE_BOOK_SQL = "UPDATE bookdetails SET QTY=QTY+? WHERE BCODE=?";
	private static String ADDING_BOOK_SQL = "INSERT INTO USER_BOOKS VALUES(?,?,?)";
	private static String DISPLAY_USER_BOOK = "select * from bookdetails b join user_books u on b.bcode=u.bcode where userid=?";
	private static String REMOVE_MY_BOOK = "DELETE FROM USER_BOOKS WHERE BCODE =?";
	public ArrayList<BookDetails> displayAllBook(){
		ArrayList<BookDetails> list = new ArrayList<>();
		
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst  = con.prepareStatement(DISPLAY_ALL_BOOK);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				
				BookDetails bd = new BookDetails();
				bd.setBookCode(rs.getString(1));
				bd.setBookName(rs.getString(2));
				bd.setBookAuthor(rs.getString(3));
				bd.setBookPrice(rs.getDouble(4));
				bd.setBookQty(rs.getInt(5));
				list.add(bd);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		return list;
	}
	
	public BookDetails displayById(String id) {
		BookDetails book = null;
		
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(DISPLAY_BY_ID);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				book = new BookDetails();
				book.setBookCode(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setBookPrice(rs.getDouble(4));
				book.setBookQty(rs.getInt(5));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	/////////////////////////////// Book Delete Method ////////////////////////////////////////////
	public boolean delete(String id) {
		boolean flag = false;
		
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(DELETE_SQL);
			pst.setString(1, id);
			flag = pst.executeUpdate()>0;
		}catch (Exception e) {
			System.out.println("this is delete area "+e);
		}
		return flag;
	}
	
	
	public boolean update(BookDetails book) {
		boolean flag = false;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(UPDATE_SQL);
			pst.setString(1, book.getBookName());
			pst.setString(2, book.getBookAuthor());
			pst.setDouble(3, book.getBookPrice());
			pst.setInt(4, book.getBookQty());
			pst.setString(5, book.getBookCode());
			flag = pst.executeUpdate()>0;
		}catch (Exception e) {
			System.out.println("This is updation are :"+e);
		}
		
		return flag;
	}
	
	public boolean insert(BookDetails book) {
		boolean k = false;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(INSERT_SQL);
			pst.setString(1, book.getBookCode());
			pst.setString(2, book.getBookName());
			pst.setString(3, book.getBookAuthor());
			pst.setDouble(4, book.getBookPrice());
			pst.setInt(5, book.getBookQty());
			k = pst.executeUpdate()>0;
		}catch (Exception e) {
			System.out.println("this is insert area...."+e);
			e.printStackTrace();
		}
		return k;
	}
	
	public boolean saleBook(String bcode, int qty,String uid) {
		boolean flag = false;
		BookDetails book = displayById(bcode);
		if(book.getBookQty()>qty) {
			try(Connection con = DBConnection.getCon()){
				PreparedStatement pst = con.prepareStatement(PURCHASE_BOOK_SQL);
				PreparedStatement pst1 = con.prepareStatement(ADDING_BOOK_SQL);
				con.setAutoCommit(false);
				Savepoint sp =con.setSavepoint();
				pst.setInt(1, -qty);
				pst.setString(2, bcode);
				int k =pst.executeUpdate();
				pst1.setString(1, uid);
				pst1.setString(2, bcode);
				pst1.setInt(3, qty);
				int k1 = pst1.executeUpdate();
				
				if(k>0&&k1>0) {
					flag = true;
				}else {
					con.rollback(sp);
				}
			}catch (Exception e) {
				System.out.println("This is saling area..."+e);
				
			}
		}
			
		return flag;
	}
	
	public ArrayList<BookDetails> displayUserBook(String uid){
		ArrayList<BookDetails> list = new ArrayList<BookDetails>();
		BookDetails book = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(DISPLAY_USER_BOOK);
			pst.setString(1, uid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				book = new BookDetails();
				book.setBookCode(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setBookPrice(rs.getDouble(4));
				book.setBookQty(rs.getInt(8));
				list.add(book);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean removeMyBook(String bcode) {
		boolean flag = false;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(REMOVE_MY_BOOK);
			pst.setString(1, bcode);
			flag = pst.executeUpdate()>0;
		}catch(Exception e) {
			System.out.println("This is remove my book area..."+e);
		}
		return flag;
	}
	
}
