package Bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookDetails implements Serializable{
	private String bookCode;
	private String bookName;
	private String bookAuthor;
	private double bookPrice;
	private int bookQty;
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookId) {
		this.bookCode = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public int getBookQty() {
		return bookQty;
	}
	public void setBookQty(int bookQty) {
		this.bookQty = bookQty;
	}
	
	
}
