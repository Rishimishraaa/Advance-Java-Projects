package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DB_Info.DBConnection;
import Model.BankCustomers;

public class BankCustomerDao {
	private final static String BANK_CUSTOMER_VALIDATION = "SELECT * FROM BANK_CUSTOMERS WHERE USERNAME=?";
	private final static String CREATE_ACC = "INSERT INTO BANK_CUSTOMERS(FIRST_NAME,LAST_NAME,DOB,USERNAME,EMAIL,PHONE_NO,CITY,STATE,PIN_CODE,BALANCE,NATIONALITY,ACC_TYPE,GENDER,BRANCH_CODE) VALUES(?,?,TO_DATE(?, 'YYYY-MM-DD'),?,?,?,?,?,?,?,?,?,?,?)";
	
	public BankCustomers validate(String uname) {
		BankCustomers bankCustomer = null;
		
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(BANK_CUSTOMER_VALIDATION);
			pst.setString(1, uname);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				bankCustomer = new BankCustomers();
				bankCustomer.setAcno(rs.getString(1));
				bankCustomer.setFname(rs.getString(2));
				bankCustomer.setLname(rs.getString(3));
				bankCustomer.setDob(rs.getString(4));
				bankCustomer.setUname(rs.getString(5));
				bankCustomer.setEmail(rs.getString(6));
				bankCustomer.setPhon(rs.getLong(7));
				bankCustomer.setCity(rs.getString(8));
				bankCustomer.setState(rs.getString(9));
				bankCustomer.setPincode(rs.getInt(10));
				bankCustomer.setBalance(rs.getDouble(11));
				bankCustomer.setOpeningDate(rs.getString(12));
				bankCustomer.setNationality(rs.getString(13));
				bankCustomer.setAccType(rs.getString(14));
				bankCustomer.setGender(rs.getString(15));
				bankCustomer.setBranchcode(rs.getString(16));
			}
		}catch(Exception e) {
			System.out.println("This is bankCustomer validate area..."+e);
		}
		return bankCustomer;
	}
	
	public boolean createCustomerAccount(BankCustomers customer) {
		boolean flag = false;
		//FIRST_NAME,LAST_NAME,DOB,USERNAME,EMAIL,PHONE_NO,CITY,STATE,PIN_CODE,BALANCE,NATIONALITY,ACC_TYPE,GENDER,BRANCH_CODE
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(CREATE_ACC);
			pst.setString(1, customer.getFname());
			pst.setString(2, customer.getLname());
			pst.setString(3, customer.getDob());
			pst.setString(4, customer.getUname());
			pst.setString(5, customer.getEmail());
			pst.setLong(6, customer.getPhon());
			pst.setString(7, customer.getCity());
			pst.setString(8, customer.getState());
			pst.setInt(9, customer.getPincode());
			pst.setDouble(10, customer.getBalance());
			pst.setString(11, customer.getNationality());
			pst.setString(12,customer.getAccType());
			pst.setString(13, customer.getGender());
			pst.setString(14, "SBI0586");
			flag = pst.executeUpdate()>0;
		}catch(Exception e) {
			System.out.println("Customer account creation area.."+e);
		}
		
		return flag;
	}
	

}
