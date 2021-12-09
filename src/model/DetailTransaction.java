package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

import connect.Connect;

public class DetailTransaction {
	private String TransactionID;
	private String FoodID;
	private int Quantity;

	public DetailTransaction(String transactionID, String foodID, int quantity) {
		super();
		TransactionID = transactionID;
		FoodID = foodID;
		Quantity = quantity;
	}
	
	public static Vector<DetailTransaction> getAllTransactionDetail(String TransactionID) {
		ResultSet rs = Connect.getConnection()
				.executeQuery("SELECT * FROM DetailTransactions WHERE TransactionID ='" + TransactionID + "'");
		Vector<DetailTransaction> details = new Vector<>();
		DetailTransaction detail = null;
		try {
			while (rs.next()) {
				detail = new DetailTransaction(
						rs.getString("TransactionID"),
						rs.getString("FoodID"),
						rs.getInt("Quantity"));
				details.add(detail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return details;
	}

	public static boolean insertTransactionDetail(DetailTransaction detail) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"INSERT INTO DetailTransactions(TransactionID, FoodID, Quantity)"
						+ "VALUES (?,?,?)");

		try {
			ps.setString(1, detail.TransactionID);
			ps.setString(2, detail.FoodID);
			ps.setInt(3, detail.Quantity);
			ps.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public String getFoodID() {
		return FoodID;
	}

	public void setFoodID(String foodID) {
		FoodID = foodID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

}
