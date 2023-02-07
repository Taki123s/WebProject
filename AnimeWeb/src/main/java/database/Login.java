package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Account;


public class Login {
	public Login() {

	}

	public ArrayList<Account> getConnection() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		ArrayList<Account> res = new ArrayList<>();
		conn = DataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("select * from account");
		ResultSet rs = ps.executeQuery();
		Account ac;
		while (rs.next()) {
			ac = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5));
			res.add(ac);
		}
		;
		return res;

	}


	public void changeProfile(String userName, String passWord, String email, String img) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		conn = DataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("update account set PassW='"+passWord+"',Email='"+email+"',avatar='"+img+"' "+"where UserName='" + userName + "'");
		ps.executeUpdate();

		
		
	}
	public boolean settingUser(String userName,String passWord,String email) throws ClassNotFoundException, SQLException {
			Connection conn = null;
			conn = DataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("update account set PassW=?,Email=? where UserName=?");
			ps.setString(1, passWord);
			ps.setString(2, email);
			ps.setString(3, userName);
			int check = ps.executeUpdate();
			
		boolean result=	check==1?true:false;
		return result;
	}
	public boolean deleteUser(String userName) throws ClassNotFoundException, SQLException{
		Connection conn = null;
		conn = DataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("delete account where UserName=?");
		ps.setString(1, userName);
		int check = ps.executeUpdate();
		boolean rs = check==1?true:false;
		return rs;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	}
}
