package Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import DB_Info.DBConnection;
import Model.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oracle.jdbc.proxy.annotation.Pre;

public class CustomerDao {
	private final static String CUSTOMER_REGISTRATION = "INSERT INTO USER_LOGIN VALUES(?,?,?,?)";
	private final static String CUSTOMER_LOGIN = "SELECT * FROM USER_LOGIN WHERE USERNAME=? AND PASSWORD=?";
	private final static String CUSTOMER_UPDATE_PROFILE = "UPDATE USER_LOGIN SET EMAIL=?, PASSWORD=? WHERE USERNAME=?";
	
	public boolean customerRegister(Customer customer, HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		boolean flag = false;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(CUSTOMER_REGISTRATION);
			pst.setString(1, customer.getFullName());
			pst.setString(2, customer.getEmail());
			pst.setString(3, customer.getUsername());
			pst.setString(4, customer.getPassword());
			
			flag =pst.executeUpdate()>0;
			
		}catch(SQLIntegrityConstraintViolationException e) {
			req.setAttribute("msgR", "Customer alerady existed..");
			req.getRequestDispatcher("customerRegister.jsp").forward(req,resp);
		}catch(Exception e) {
			System.out.println("This Is Customer Register Area... "+e);
		}
		
		
		return flag;
	}
	
	
	public Customer customerLogin(String uname, String pass) {
		Customer customer = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(CUSTOMER_LOGIN);
			pst.setString(1, uname);
			pst.setString(2, pass);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				customer=new Customer();
				customer.setFullName(rs.getString(1));
				customer.setEmail(rs.getString(2));
				customer.setUsername(rs.getString(3));
				customer.setPassword(rs.getString(4));
			}
		}catch(Exception e) {
			System.out.println("Customer Login Area..."+e);
		}
		
		
		return customer;
	}
	
	public Customer updateProfile(String uid,String email, String pass) {
		Customer cust = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(CUSTOMER_UPDATE_PROFILE);
			pst.setString(1, email);
			pst.setString(2, pass);
			pst.setString(3, uid);
			
			int k = pst.executeUpdate();
			if(k>0) {
				cust = customerLogin(uid, pass);
			}
		}catch(Exception e) {
			System.out.println("This is user profile update area..."+e);
		}
		
		return cust;
		
		
	}
}
