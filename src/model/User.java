package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import connect.Connect;

public class User {

	private String id;
	private String username;
	private String email;
	private String phone;
	private String address;
	private String password;
	private String gender;
	private String role;
	

	public User(String id, String username, String email, String phone, String address, String password, String gender,
			String role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
		this.gender = gender;
		this.role = role;
	}
	
	public static void insertUser(User user) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"INSERT INTO user(UserID, UserName, UserEmail, UserPassword, UserGender, UserAddress, UserPhone, UserRole)"
				+ "VALUES (?,?,?,?,?,?,?,?)"
				);
		
		try {
			ps.setString(1, user.id);
			ps.setString(2, user.username);
			ps.setString(3, user.email);
			ps.setString(4, user.password);
			ps.setString(5, user.gender);
			ps.setString(6, user.address);
			ps.setString(7, user.phone);
			ps.setString(8, user.role);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Vector<User> getAllUser(){
		ResultSet rs = Connect.getConnection().executeQuery("SELECT * FROM user");
		Vector<User> users = new Vector<User>();
		User user = null;
		try {
			while(rs.next()) {
				 user = new User(rs.getString("UserID"), 
						rs.getString("UserName"), 
						rs.getString("UserEmail"),
						rs.getString("UserPhone"), 
						rs.getString("UserAddress"),
						rs.getString("UserPassword"), 
						rs.getString("UserGender"), 
						rs.getString("UserRole"));
				 users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static User getOneUser(String email, String password) {
		
		ResultSet rs = Connect.getConnection().executeQuery("SELECT * FROM user WHERE UserEmail='"+email+"' AND "+"UserPassword='"+password+"'");
		User user = null;
		try {
			while(rs.next()) {
				 user = new User(rs.getString("UserID"), 
						rs.getString("UserName"), 
						rs.getString("UserEmail"),
						rs.getString("UserPhone"), 
						rs.getString("UserAddress"),
						rs.getString("UserPassword"), 
						rs.getString("UserGender"), 
						rs.getString("UserRole"));			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static String getLastId() {
		String userId = null;
		ResultSet rs = Connect.getConnection().executeQuery("SELECT * FROM user ORDER BY UserID DESC LIMIT 1");
		try {
			while(rs.next()) {
				userId = rs.getString("UserID");				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return userId;
	}
	
	public static boolean updateUser(User user) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"UPDATE user SET username = ?, useremail = ?, usergender = ?, useraddress = ?, userphone = ? WHERE userid = ?"
						);

		try {
			ps.setString(1, user.username);
			ps.setString(2, user.email);
			ps.setString(3, user.gender);
			ps.setString(4, user.address);
			ps.setString(5, user.phone);
			ps.setString(6, user.id);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean changePassword(String pass, String userId) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"UPDATE user SET userpassword = ? WHERE userid = ?"
						);

		try {
			ps.setString(1, pass);
			ps.setString(2, userId);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
