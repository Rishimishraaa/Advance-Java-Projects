package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Bean.BookDetails;
import Bean.User;
import DB_Info.DBConnection;

public class UserDAO {
	public static final String USER_INSERT_SQL = "INSERT INTO user_credential VALUES(?,?,?,?,?,?)";
	public static final String USER_LOGIN_SQL = "SELECT * FROM user_credential WHERE USERID =? AND PASS=?";
	public static final String UPDATE_USER_SQL = "UPDATE user_credential SET UNAME=?,EMAIL=?, PHNO=?,CITY=?,PASS=? WHERE USERID=?";
	public static final String GET_BOOK_SQL ="SELECT B.BCODE,B.BNAME,B.AUTHOR,B.PRICE,B.QTY FROM BOOKDETAILS B JOIN USER_CREDENTIAL U ON B.USERID = U.USERID WHERE U.USERID=?";
	public static final String GET_ALL_USERS = "SELECT * FROM user_credential";
	public static final String GET_USER_BYID =	"SELECT * FROM user_credential where USERID=?";
	public static final String UPDATE_USER_BY_ADMIN = "update user_credential set uname=?, email=?, phno=?, city=?, pass=? where userid=?";
	public static final String DELETE_USER_BY_ADMIN ="delete from user_credential where userid=?";
	
	public boolean userRegister(User user) throws SQLException {
		boolean flag = false;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(USER_INSERT_SQL);
			pst.setString(1, user.getUserid());
			pst.setString(2, user.getUname());
			pst.setString(3, user.getEmail());
			pst.setLong(4, user.getPhno());
			pst.setString(5, user.getCity());
			pst.setString(6, user.getPass());
			
			
			flag = pst.executeUpdate()>0;
		}catch(Exception e) {
			System.out.println("This is user register dao..."+e);
		}
		return flag;
	}
	
	public User login(String uname, String pass) {
		User user = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(USER_LOGIN_SQL);
			pst.setString(1, uname);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserid(rs.getString(1));
				user.setUname(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhno(rs.getLong(4));
				user.setCity(rs.getString(5));
				user.setPass(rs.getString(6));
			}
		}catch(Exception e) {
			System.out.println("This is user login area..."+e);
		}
		
		return user;
	}
	
	public boolean editUserDetails(User user) {
		boolean flag = false;
		
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(UPDATE_USER_SQL);
			pst.setString(1,user.getUname());
			pst.setString(2, user.getEmail());
			pst.setLong(3, user.getPhno());
			pst.setString(4, user.getCity());
			pst.setString(5, user.getPass());
			pst.setString(6, user.getUserid());
			
			flag =pst.executeUpdate()>0;
		}catch (Exception e) {
			System.out.println("This is admin edit form are.. "+e);
		}
		
		
		return flag;
	}
	
	public ArrayList<BookDetails> getbooks(String userid){
		ArrayList<BookDetails> list = new ArrayList<BookDetails>();
		
		return list;
	}
	
	public ArrayList<User> getAllUsers(){
		ArrayList<User> list= new ArrayList<User>();
		User user = null;
			try(Connection con = DBConnection.getCon()){
				PreparedStatement pst = con.prepareStatement(GET_ALL_USERS);
				ResultSet rs = pst.executeQuery();
				while(rs.next()) {
					user=new User();
					user.setUserid(rs.getString(1));
					user.setUname(rs.getString(2));
					user.setEmail(rs.getString(3));		
					user.setPhno(rs.getLong(4));
					user.setCity(rs.getString(5));
					user.setPass(rs.getString(6));
					list.add(user);
				}
			}catch(Exception e) {
				System.out.println("Show all users area..."+e);
			}
		return list;
	}
	
	public User getUserById(String id) {
		User user = null;
		try(Connection con =DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(GET_USER_BYID);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserid(rs.getString(1));
				user.setUname(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPhno(rs.getLong(4));
				user.setCity(rs.getString(5));
				user.setPass(rs.getString(6));
			}
		}catch (Exception e) {
			System.out.println("Get user by ID area...."+e);
			
		}
		return user;
	}
	
	public boolean updateUserByAdmin(User user) {
		boolean flag = false;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(UPDATE_USER_BY_ADMIN);
		
			pst.setString(1, user.getUname());
			pst.setString(2, user.getEmail());
			pst.setLong(3, user.getPhno());
			pst.setString(4, user.getCity());
			pst.setString(5, user.getPass());
			pst.setString(6, user.getUserid());
			flag=pst.executeUpdate()>0;
		}catch (Exception e) {
			System.out.println("This is user Update by admin area...."+e);
		}
		return flag;
	}
	public boolean deleteUserByAdmin(String id) {
		boolean flag=false;
			try(Connection con = DBConnection.getCon()){
				PreparedStatement pst = con.prepareStatement(DELETE_USER_BY_ADMIN);
				pst.setString(1, id);
				flag = pst.executeUpdate()>0;
			}catch(Exception e) {
				System.out.println("This is delete user by admin area..."+e);
			}
		return flag;
	}
}
