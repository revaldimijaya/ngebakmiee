package controller;

import java.util.Vector;

import model.Cart;
import model.DetailTransaction;

public class DetailController {

	public static String insertDetailTransaction(String TransactionId, Vector<Cart> carts) {
		for (Cart cart : carts) {
			DetailTransaction.insertTransactionDetail(new DetailTransaction(TransactionId, cart.getFoodID(), cart.getQuantity()));
			FoodController.removeStock(cart.getFoodID(), cart.getQuantity());
		}
		
		return "";
	}
	
	public static Vector<DetailTransaction> getDetail(String TransactionID){
		return DetailTransaction.getAllTransactionDetail(TransactionID);
	}

}
