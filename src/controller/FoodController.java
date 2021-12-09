package controller;

import java.io.Console;
import java.util.Vector;

import helper.ErrorInfo;
import helper.SuccessInfo;
import model.Food;
import model.User;

public class FoodController {

	public static Vector<Food> getAllFood(){
		return Food.getAllFood();
	}

	public static String generateID() {
		String foodId = Food.getLastId();
		if(foodId == null) {
			return "FD001";
		}
		String newId = "FD";
		int intId = Integer.parseInt(foodId.substring(2,foodId.length()));
		intId += 1;
		newId += String.format("%03d", intId);
		return newId;
	}
	
	public static Food getOneFood(String FoodID) {
		return Food.getOneFood(FoodID);
	}
	
	public static String insertFood(String foodId, String foodName, String foodType, String foodPrice, String foodStock ) {
		
		if(foodName.length() < 5 || foodName.length() > 30) {
			return ErrorInfo.foodName;
		}
		
		int price = 0;
		try {
			price = Integer.parseInt(foodPrice);
		} catch (Exception e) {
			return ErrorInfo.foodPriceNumeric;
		}
		
		if(price <= 0) {
			return ErrorInfo.foodPriceZero;
		}
		
		int stock = 0;
		
		try {
			stock = Integer.parseInt(foodStock);
		} catch (Exception e) {
			return ErrorInfo.foodStockNumeric;
		}
		
		if(stock <= 0) {
			return ErrorInfo.foodStockZero;
		}
		
		Food food = new Food(foodId, foodName, foodType, price, stock);
		boolean isSuccess = Food.insertFood(food);
		if(!isSuccess) {
			return ErrorInfo.failedSaveDatabase;
		}
		return SuccessInfo.successInsertFood;
	}
	
	public static String updateFood(String foodId, String foodName, String foodType, String foodPrice, String foodStock ) {
		if(foodId.isEmpty()) {
			return ErrorInfo.foodId;
		}
		
		if(foodName.length() < 5 && foodName.length() > 30) {
			return ErrorInfo.foodName;
		}
		
		int price = 0;
		try {
			price = Integer.parseInt(foodPrice);
		} catch (Exception e) {
			return ErrorInfo.foodPriceNumeric;
		}
		
		if(price <= 0) {
			return ErrorInfo.foodPriceZero;
		}
		
		int stock = 0;
		
		try {
			stock = Integer.parseInt(foodStock);
		} catch (Exception e) {
			return ErrorInfo.foodStockNumeric;
		}
		
		if(stock <= 0) {
			return ErrorInfo.foodStockZero;
		}
		
		Food food = new Food(foodId, foodName, foodType, price, stock);
		boolean isSuccess = Food.updateProduct(food);
		if(!isSuccess) {
			return ErrorInfo.failedSaveDatabase;
		}
		return SuccessInfo.successUpdateFood;
	}
	
	public static String removeFood(String foodId) {
		
		if(foodId.isEmpty()) {
			return ErrorInfo.foodId;
		}
		
		boolean isSuccess = Food.removeFood(foodId);
		if(!isSuccess) {
			return ErrorInfo.failedSaveDatabase;
		}
		return SuccessInfo.successRemoveFood;
	}
	
	public static String addStock(String foodId, String foodStock) {
		int stock = 0;
		try {
			stock = Integer.parseInt(foodStock);			
		} catch (Exception e) {
			return ErrorInfo.foodStockNumeric;
		}
		
		if(stock <= 0) {
			return ErrorInfo.foodStockZero;
		}
		
		boolean isSuccess = Food.addStock(foodId, stock);
		if(!isSuccess) {
			return ErrorInfo.failedSaveDatabase;
		}
		
		return SuccessInfo.successAddStockFood;
	}
	
	public static String removeStock(String foodId, int quantity) {
		boolean isSuccess = Food.removeStock(foodId, quantity);
		if(!isSuccess) {
			return ErrorInfo.failedSaveDatabase;
		}
		
		return SuccessInfo.successAddStockFood;
	}
	
}
