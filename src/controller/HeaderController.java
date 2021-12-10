package controller;

import java.time.LocalDate;
import java.util.Vector;

import helper.ErrorInfo;
import model.Food;
import model.Cart;
import model.HeaderTransaction;
import model.User;

public class HeaderController {
	
	public static String generateID() {
		String transactionId = HeaderTransaction.getLastId();
		if(transactionId == null) {
			return "TR001";
		}
		String newId = "TR";
		int intId = Integer.parseInt(transactionId.substring(2,transactionId.length()));
		intId += 1;
		newId += String.format("%03d", intId);
		return newId;
	}
	
	public static String insertHeaderTransaction(String TransactionID, String UserID, LocalDate TransactionDate) {
		boolean isSaved = HeaderTransaction.insertHeaderTransaction(new HeaderTransaction(TransactionID, UserID, TransactionDate));
		if(!isSaved) {
			return ErrorInfo.failedSaveDatabase;
		}
		return TransactionID;
	}
	
	public static Vector<HeaderTransaction> getHeader(){
		return HeaderTransaction.getAllTransactionHeader(UserController.currUser.getId());
	}

}
