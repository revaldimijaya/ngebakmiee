package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class Cart {
	private String UserID;
	private String FoodID;
	private int Quantity;

	public Cart(String userID, String beverageID, int quantity) {
		super();
		UserID = userID;
		FoodID = beverageID;
		Quantity = quantity;
	}
	
	public static Vector<Cart> getAllUserCart(String UserID){
		ResultSet rs = Connect.getConnection().executeQuery("SELECT * FROM cart WHERE UserID = '"+UserID+"'");
		Vector<Cart> carts = new Vector<Cart>();
		Cart cart = null;
		try {
			while(rs.next()) {
				 cart = new Cart(rs.getString("UserID"), 
						rs.getString("FoodID"), 
						rs.getInt("Quantity"));
				 carts.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return carts;
	}
	
	public static boolean insertCart(Cart cart) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"INSERT INTO cart(UserID, FoodID, Quantity)"
						+ "VALUES (?,?,?)");

		try {
			ps.setString(1, cart.UserID);
			ps.setString(2, cart.FoodID);
			ps.setInt(3, cart.Quantity);
			ps.execute();
		} catch (SQLException e) {
			return updateCart(cart);
		}
		
		return true;
	}
	
	public static boolean updateCart(Cart cart) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"UPDATE cart SET Quantity = Quantity + ? WHERE UserID = ? AND FoodID IN (SELECT FoodID FROM food WHERE Quantity + ? <= FoodStock AND FoodID = ? )"
						);

		try {
			ps.setInt(1, cart.Quantity);
			ps.setString(2, cart.UserID);
			ps.setInt(3, cart.Quantity);
			ps.setString(4, cart.FoodID);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean clearCart(String UserID) {
		PreparedStatement ps = Connect.getConnection().prepareStatement("DELETE FROM cart WHERE UserID = ?");

		try {
			ps.setString(1, UserID);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean removeCart(String UserID, String FoodID) {
		PreparedStatement ps = Connect.getConnection().prepareStatement("DELETE FROM cart WHERE UserID = ? AND FoodID = ?");

		try {
			ps.setString(1, UserID);
			ps.setString(2, FoodID);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	} 
	
	public static boolean checkout(String UserID) {
		Vector<Cart> carts = getAllUserCart(UserID);
		return true;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getFoodID() {
		return FoodID;
	}

	public void setFoodID(String beverageID) {
		FoodID = beverageID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

}
