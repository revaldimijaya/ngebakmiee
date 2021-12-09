package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import helper.ErrorInfo;
import helper.SuccessInfo;
import model.Food;
import model.Cart;
import model.User;

public class CartController {
	
	public static Vector<Cart> getAllCart(String UserID){
		return Cart.getAllUserCart(UserID);
	}
	
	public static String insertCart(String UserID, String FoodID, String Quantity) {
		int qty = 0;
		
		if(FoodID.isEmpty() || FoodID == "") {
			return "Please select the food!";
		}
		
		try {
			qty = Integer.parseInt(Quantity);
		} catch (Exception e) {
			return ErrorInfo.cartQuantityNumeric;
		}
		
		if(qty <= 0) {
			return ErrorInfo.cartQuantityZero;
		}
		
		Food food = Food.getOneFood(FoodID);
		if(food.getStock() < qty) {
			return ErrorInfo.invalidQuantity;
		}
		
		Vector<Cart> carts = getAllCart(UserID);
		for (Cart cart : carts) {
			if(cart.getFoodID().equals(FoodID)) {
				if(cart.getQuantity() + qty > food.getStock()) {
					return ErrorInfo.invalidQuantity; 
				}
			}
		}
		
		boolean isSaved = Cart.insertCart(new Cart(UserID, FoodID, qty));
		if(!isSaved) {
			return ErrorInfo.failedSaveDatabase;
		}
		
		return SuccessInfo.successInsertCart;
	}
	
	public static String removeCart(String UserID, String FoodID) {
		boolean isSaved = Cart.removeCart(UserID, FoodID);
		if(!isSaved) {
			return ErrorInfo.failedSaveDatabase;
		}
		
		return SuccessInfo.successRemoveCart;
	}
	
	public static String clearCart(String UserID) {
		boolean isSaved = Cart.clearCart(UserID);
		if(!isSaved) {
			return ErrorInfo.failedSaveDatabase;
		}
		return SuccessInfo.successClearCart;
	}
	
	public static String checkoutCart(String UserID) {
		String transactionId = HeaderController.generateID();
		Vector<Cart> carts = getAllCart(User.currentUser.getId());
		if(carts.isEmpty()) {
			return ErrorInfo.emptyCart;
		}
		HeaderController.insertHeaderTransaction(transactionId, User.currentUser.getId(), LocalDate.now());
		DetailController.insertDetailTransaction(transactionId, carts);
		clearCart(User.currentUser.getId());
		
		return SuccessInfo.successCheckoutCart;
	}
	
	
}
