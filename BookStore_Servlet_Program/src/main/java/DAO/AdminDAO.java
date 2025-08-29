package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.Admin1;
import DB_Info.DBConnection;
import oracle.jdbc.proxy.annotation.Pre;

public class AdminDAO {
	private static String ADMIN_VALIDATION_SQL = "SELECT * FROM admin_credential WHERE USERNAME =? AND PASSWORD =?";
	private static String ADMIN_EDIT_SQL = "UPDATE admin_credential SET EMAIL=?, PASSWORD=?, NAME=? WHERE USERNAME=?";
	private static String ADMIN_INSERT_SQL = "INSERT INTO admin_credential VALUES(?,?,?,?)";
	private static String SHOW_ADMIN_SQL = "SELECT*FROM admin_credential";
	private static String SHOW_ADMIN_BY_ID = "SELECT * FROM admin_credential WHERE USERNAME =?";
	private static String DELETE_ADMIN_SQL = "DELETE FROM admin_credential WHERE USERNAME = ?";
	
	//
	private static String UPDATE_ADMIN_SQL = "UPDATE admin_credential set email =?, password = ? name =? where username=?";
	// dmin login validation

	public Admin1 login(String uname, String pass) {
		
		Admin1 admin = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(ADMIN_VALIDATION_SQL);
			pst.setString(1, uname);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
				
			
			if(rs.next()) {
				admin = new Admin1();
				admin.setEmail(rs.getString(1));
				admin.setUname(rs.getString(2));
				admin.setPass(rs.getString(3));
				admin.setName(rs.getString(4));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return admin;
	}
	
	public boolean editAdminDetails(Admin1 admin) {
		boolean flag = false;
		
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(ADMIN_EDIT_SQL);
			pst.setString(1, admin.getEmail());
			pst.setString(2, admin.getPass());
			pst.setString(3, admin.getName());
			pst.setString(4, admin.getUname());
			
			flag =pst.executeUpdate()>0;
		}catch (Exception e) {
			System.out.println("This is admin edit form are.. "+e);
		}
		
		
		return flag;
	}
	
	public boolean insertAdmin(Admin1 admin) {
		boolean flag = false;
		
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(ADMIN_INSERT_SQL);
			pst.setString(1, admin.getEmail());
			pst.setString(2, admin.getUname());
			pst.setString(3, admin.getPass());
			pst.setString(4, admin.getName());
			flag = pst.executeUpdate()>0;
		}catch (Exception e) {
			System.out.println("Admin Register Area..."+e);
		}
		return flag;
	}
	
	public ArrayList<Admin1> showAllAdmin(){
		ArrayList<Admin1> list = new ArrayList<Admin1>();
		Admin1 admin=null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst = con.prepareStatement(SHOW_ADMIN_SQL);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				admin=new Admin1();
				admin.setEmail(rs.getString(1));
				admin.setUname(rs.getString(2));
				admin.setPass(rs.getString(3));
				admin.setName(rs.getString(4));
				list.add(admin);
			}
		}catch (Exception e) {
			System.out.println("THis is show all admin area..."+e);
		}
		
		return list;
	}
	
	public Admin1 showAdminByUsername(String username) {
		Admin1 admin = null;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst  = con.prepareStatement(SHOW_ADMIN_BY_ID);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				admin =new Admin1();
				admin.setEmail(rs.getString(1));
				admin.setUname(rs.getString(2));
				admin.setPass(rs.getString(3));
				admin.setName(rs.getString(4));
			}
		}catch(Exception e) {
			System.out.println("this is Display admin by username "+e);
		}
		
		return admin;
	}
	
	public boolean deleteAdmin(String username) {
		boolean flag = false;
		try(Connection con = DBConnection.getCon()){
			PreparedStatement pst  = con.prepareStatement(DELETE_ADMIN_SQL);
			pst.setString(1, username);
			
			flag = pst.executeUpdate()>0;
		
		}catch(Exception e) {
			System.out.println("this is Display admin by username "+e);
		}
		
		
		return flag;
		
	}
	

	
	
	
}
