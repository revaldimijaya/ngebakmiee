package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class Food {
	private String id;
	private String name;
	private String type;
	private int price;
	private int stock;

	public Food(String id, String name, String type, int price, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.stock = stock;
	}
	
	public void print() {
		System.out.println("ID" + getId());
		System.out.println("Name "+getName());
		System.out.println("Type "+getType());
		System.out.println("price "+getPrice());
		System.out.println("stock "+getStock());
	}
	
	public static Vector<Food> getAllFood(){
		ResultSet rs = Connect.getConnection().executeQuery("SELECT * FROM food");
		Vector<Food> foods = new Vector<Food>();
		Food food = null;
		try {
			while(rs.next()) {
				 food = new Food(rs.getString("FoodId"), 
						rs.getString("FoodName"), 
						rs.getString("FoodType"),
						rs.getInt("FoodPrice"), 
						rs.getInt("FoodStock"));
				 foods.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foods;
	}
	
	public static Food getOneFood(String FoodID) {
		ResultSet rs = Connect.getConnection().executeQuery("SELECT * FROM food WHERE FoodID = '"+FoodID+"'");
		Food food = null;
		try {
			while(rs.next()) {
				 food = new Food(rs.getString("FoodId"), 
						rs.getString("FoodName"), 
						rs.getString("FoodType"),
						rs.getInt("FoodPrice"), 
						rs.getInt("FoodStock"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return food;
	}
	
	public static String getLastId() {
		String foodId = null;
		ResultSet rs = Connect.getConnection().executeQuery("SELECT * FROM food ORDER BY FoodID DESC LIMIT 1");
		try {
			while(rs.next()) {
				foodId = rs.getString("FoodID");				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foodId;
	}

	public static boolean insertFood(Food food) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"INSERT INTO food(FoodID, FoodName, FoodType, FoodPrice, FoodStock)"
						+ "VALUES (?,?,?,?,?)");

		try {
			ps.setString(1, food.id);
			ps.setString(2, food.name);
			ps.setString(3, food.type);
			ps.setInt(4, food.price);
			ps.setInt(5, food.stock);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public static boolean updateProduct(Food food) {
		PreparedStatement ps = Connect.getConnection().prepareStatement("UPDATE food SET FoodName = ?,"
				+ "FoodType = ?," + "FoodPrice = ?," + "FoodStock = ?" + " WHERE FoodID = ?");

		try {
			ps.setString(1, food.name);
			ps.setString(2, food.type);
			ps.setInt(3, food.price);
			ps.setInt(4, food.stock);
			ps.setString(5,  food.id);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public static boolean removeFood(String id) {
		PreparedStatement ps = Connect.getConnection().prepareStatement("DELETE FROM food WHERE FoodID = ?");

		try {
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean addStock(String id, int stock) {
		PreparedStatement ps = Connect.getConnection().prepareStatement("UPDATE food SET FoodStock = FoodStock + ? WHERE FoodID = ?");
		
		try {
			ps.setInt(1, stock);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		return true;
		
	}
	
	public static boolean removeStock(String id, int qty) {
		PreparedStatement ps = Connect.getConnection().prepareStatement("UPDATE food SET FoodStock = FoodStock - ? WHERE FoodID = ?");
		
		try {
			ps.setInt(1, qty);
			ps.setString(2, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
