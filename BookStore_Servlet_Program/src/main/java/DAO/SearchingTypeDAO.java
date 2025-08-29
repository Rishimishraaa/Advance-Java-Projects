package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.BookDetails;
import DB_Info.DBConnection;

public class SearchingTypeDAO {

	private static String SELECT_BY_ID = "SELECT * FROM bookdetails where BCODE =?";
	private static String SELECT_BY_NAME = "SELECT * FROM bookdetails where BNAME =?";
	private static String SELECT_BY_AUTHOR = "SELECT * FROM bookdetails where AUTHOR =?";
	
	public ArrayList<BookDetails> searchById(String code){
		ArrayList<BookDetails> bookdetails = new ArrayList<>();
		BookDetails book = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(SELECT_BY_ID);
			pst.setString(1, code);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				 book = new BookDetails();
				 book.setBookCode(code);
				 book.setBookName(rs.getString(2));
				 book.setBookAuthor(rs.getString(3));
				 book.setBookPrice(rs.getDouble(4));
				 book.setBookQty(rs.getInt(5));
				 bookdetails.add(book);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookdetails;
	}
	
	public ArrayList<BookDetails> searchByName(String code){
		ArrayList<BookDetails> bookdetails = new ArrayList<>();
		BookDetails book = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(SELECT_BY_NAME);
			pst.setString(1, code);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				book = new BookDetails();
				book.setBookCode(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setBookPrice(rs.getDouble(4));
				book.setBookQty(rs.getInt(5));
				bookdetails.add(book);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookdetails;
	}
	public ArrayList<BookDetails> searchByAthour(String code){
		ArrayList<BookDetails> bookdetails = new ArrayList<>();
		BookDetails book = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(SELECT_BY_AUTHOR);
			pst.setString(1, code);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				book = new BookDetails();
				book.setBookCode(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setBookAuthor(rs.getString(3));
				book.setBookPrice(rs.getDouble(4));
				book.setBookQty(rs.getInt(5));
				bookdetails.add(book);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return bookdetails;
	}
	
}
