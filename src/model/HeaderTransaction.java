package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import connect.Connect;

public class HeaderTransaction {
	private String TransactionID;
	private String UserID;
	private LocalDate TransactionDate;

	public HeaderTransaction(String transactionID, String userID, LocalDate transactionDate) {
		super();
		TransactionID = transactionID;
		UserID = userID;
		TransactionDate = transactionDate;
	}

	public static Vector<HeaderTransaction> getAllTransactionHeader(String UserID) {
		ResultSet rs = Connect.getConnection()
				.executeQuery("SELECT * FROM HeaderTransaction WHERE UserID ='" + UserID + "'");
		Vector<HeaderTransaction> headers = new Vector<>();
		HeaderTransaction header = null;
		try {
			while (rs.next()) {
				header = new HeaderTransaction(rs.getString("TransactionID"), rs.getString("UserID"),
						LocalDate.parse(rs.getString("TransactionDate")));
				headers.add(header);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return headers;
	}

	public static String getLastId() {
		String TransactionID = null;
		ResultSet rs = Connect.getConnection().executeQuery("SELECT * FROM HeaderTransaction ORDER BY TransactionID DESC LIMIT 1");
		try {
			while (rs.next()) {
				TransactionID = rs.getString("TransactionID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TransactionID;
	}

	public static boolean insertHeaderTransaction(HeaderTransaction header) {
		PreparedStatement ps = Connect.getConnection().prepareStatement(
				"INSERT INTO HeaderTransaction(TransactionID, UserID, TransactionDate)"
						+ "VALUES (?,?,?)");

		try {
			ps.setString(1, header.TransactionID);
			ps.setString(2, header.UserID);
			ps.setString(3, header.TransactionDate.toString());
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

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public LocalDate getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		TransactionDate = transactionDate;
	}
	
}
