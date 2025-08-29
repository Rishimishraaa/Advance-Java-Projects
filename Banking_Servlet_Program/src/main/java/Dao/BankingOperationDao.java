package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.ArrayList;

import DB_Info.DBConnection;
import Model.TransctionHistory;

public class BankingOperationDao {
	private final static String CHECK_BALANCE = "SELECT BALANCE FROM BANK_CUSTOMERS WHERE ACCNO=?";
	private final static String FINDING_ACC	= "SELECT * FROM BANK_CUSTOMERS WHERE ACCNO=?";

	public double checkBalance(String accno) {
		double amt = 0;
		try(Connection con =DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(CHECK_BALANCE);
			pst.setString(1, accno);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				amt=rs.getDouble(1);
			}
		}catch(Exception e) {
			System.out.println("check balance area +"+e);
		}
		return amt;
	}
	
	public boolean findAccount(String anotherAcc) {
		boolean flag = false;
		
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(FINDING_ACC);
			pst.setString(1, anotherAcc);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				flag=true;
				System.out.println(rs.getString(1));
			}
		}catch(Exception e) {
			System.out.println("This is find account area.."+e);
		}
		
		return flag;
	}
	
	public boolean transaction(String myAcc, String anotherAcc,double amount) {
		boolean flag = false;
		
		try(Connection con = DBConnection.getCon()){
			con.setAutoCommit(flag);
			Savepoint sp= con.setSavepoint();
			PreparedStatement pst = con.prepareStatement("update bank_customers set balance=balance+? where accno=?");
			PreparedStatement log = con.prepareStatement("insert into transaction_log(accno,amount,trans_type,CURRENT_BAL) values(?,?,?,?)");
		
			double mybal = checkBalance(myAcc);
			double anotherbal = checkBalance(anotherAcc);
			
			// Debit and Credite Amount 
			
			pst.setDouble(1, -amount);
			pst.setString(2, myAcc);
			int k =pst.executeUpdate();
			mybal -=amount;
			
			
			pst.setDouble(1, amount);
			pst.setString(2, anotherAcc);
			int l = pst.executeUpdate();
			anotherbal +=amount;
			
			
			// Transaction history
			log.setString(1, myAcc);
			log.setDouble(2, amount);
			log.setString(3, "Debited");
			log.setDouble(4,mybal);
			log.executeUpdate();
	
			log.setString(1, anotherAcc);
			log.setDouble(2, amount);
			log.setString(3, "Credited");
			log.setDouble(4,anotherbal);
			log.executeUpdate();
			
			if(k>0 && l>0) {
				flag=true;
				con.commit();
			
			}else {
				con.rollback(sp);
			}
		}catch(Exception e) {
			System.out.println("This is transaction area..."+e);
		}
		
		return flag;
	}
	
	public ArrayList<TransctionHistory> fetchTransaction(String accno) {
		ArrayList<TransctionHistory> transactionHistory = new ArrayList<TransctionHistory>();
		TransctionHistory th = null;
		try(Connection con=DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement("select*from transaction_log where accno=?");
			pst.setString(1, accno);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				th = new TransctionHistory();
				th.setAccno(accno);
				th.setAmount(rs.getDouble(2));
				th.setTrans_time(rs.getString(3));
				th.setTrans_type(rs.getString(4));
				th.setCurrent_bal(rs.getDouble(5));
				th.setTrans_id(rs.getString(6));
				transactionHistory.add(th);
			}
		}catch(Exception e) {
			System.out.println("This is fetching Transaction History area..."+e);
		}
		
		return transactionHistory;
	}
	
	public boolean deleteBankAccount(String accno) {
		boolean flag = false;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement("delete from bank_customers where accno=?");
			pst.setString(1, accno);
			flag = pst.executeUpdate()>0;
		}catch(Exception e) {
			System.out.println("This is delete account area...");
		}
		
		return flag;
	}

}
