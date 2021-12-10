package controller;

import java.util.Vector;
import java.util.regex.Pattern;

import helper.ErrorInfo;
import helper.SuccessInfo;
import model.User;

public class UserController {
	
	public static User currUser;
	
	public static String generateID() {
		String userId = User.getLastId();
		if(userId == null) {
			return "US001";
		}
		String newId = "US";
		int intId = Integer.parseInt(userId.substring(2,userId.length()));
		intId += 1;
		newId += String.format("%03d", intId);
		return newId;
	}
	
	public static User authUser(String email, String password) {
		Vector<User> users = User.getAllUser();
		for (User user : users) {
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
				UserController.currUser = user;
				return user;
			}
		}
		return null;
	}
	
	public static String changePassword(String oldPass, String newPass, String confirmPass) {
		if(!oldPass.equals(UserController.currUser.getPassword())) {
			return ErrorInfo.wrongPass;
		}
		
		boolean isAlpha = false;
		boolean isNumeric = false;
		
		for(int i = 0 ; i < newPass.length() ; i++) {
			if(newPass.charAt(i) >= 'A' && newPass.charAt(i) <= 'Z' ) {
				isAlpha = true;
			}
			if(newPass.charAt(i) >= 'a' && newPass.charAt(i) <= 'z' ) {
				isAlpha = true;
			}
			if(newPass.charAt(i) >= '0' && newPass.charAt(i) <= '9' ) {
				isNumeric = true;
			}
		}
		
		if(!isAlpha || !isNumeric ) {
			return ErrorInfo.userPass;
		}
		
		if(!confirmPass.equals(newPass)) {
			return ErrorInfo.wrongPass;
		}
		
		boolean isSaved = User.changePassword(newPass, UserController.currUser.getId());
		
		if(!isSaved) {
			return ErrorInfo.failedSaveDatabase;
		}
		
		UserController.currUser.setPassword(newPass);
		
		return SuccessInfo.successChangePassword;
	}
	
	public static String updateUser(String userId, String userName, String userEmail, String userGender, String userAddress, String userPhone) {
		if(userName.length() < 5 || userName.length() > 30) {
			return ErrorInfo.userName;
		}
		
		if(userEmail.isEmpty()) {
			return ErrorInfo.userEmail;
		}
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
                  
		Pattern pat = Pattern.compile(emailRegex);
		boolean isValidEmail = pat.matcher(userEmail).matches();
		
		if(!isValidEmail) {
			return ErrorInfo.userEmail;
		}
		
		try {
			Double.parseDouble(userPhone);			
		} catch (Exception e) {
			return ErrorInfo.userPhoneNumber;
		}
		
		if(userPhone.length() < 12) {
			return ErrorInfo.userPhone;
		}
		
		if(!userAddress.endsWith(" Street")) {
			return ErrorInfo.userAddress;
		}
		
		User user = UserController.currUser;
		user.setUsername(userName);
		user.setEmail(userEmail);
		user.setAddress(userAddress);
		user.setGender(userGender);
		user.setPhone(userPhone);
		
		boolean isSaved = User.updateUser(user);
		if(!isSaved) {
			return ErrorInfo.failedSaveDatabase;
		}
		
		return SuccessInfo.successUpdateUser;
	}
	
	public static String insertUser(String userId, String userName, String userEmail, String userPassword, String userGender, String userAddress, String userPhone, String userRole) {
		if(userName.length() < 5 || userName.length() > 30) {
			return ErrorInfo.userName;
		}

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
                  
		Pattern pat = Pattern.compile(emailRegex);
		boolean isValidEmail = pat.matcher(userEmail).matches();
		
		if(!isValidEmail) {
			return ErrorInfo.userEmail;
		}
		
		try {
			Double.parseDouble(userPhone);			
		} catch (Exception e) {
			return ErrorInfo.userPhoneNumber;
		}
		
		if(userPhone.length() < 12) {
			return ErrorInfo.userPhone;
		}
		
		if(!userAddress.endsWith(" Street") || userAddress.length() < 10) {
			return ErrorInfo.userAddress;
		}
		
		if(userPassword.length() < 5 || userPassword.length() > 30) {
			return ErrorInfo.userPass;
		}
		
		boolean isAlpha = false;
		boolean isNumeric = false;
		
		for(int i = 0 ; i < userPassword.length() ; i++) {
			if(userPassword.charAt(i) >= 'A' && userPassword.charAt(i) <= 'Z' ) {
				isAlpha = true;
			}
			if(userPassword.charAt(i) >= 'a' && userPassword.charAt(i) <= 'z' ) {
				isAlpha = true;
			}
			if(userPassword.charAt(i) >= '0' && userPassword.charAt(i) <= '9' ) {
				isNumeric = true;
			}
		}
		
		if(!isAlpha || !isNumeric ) {
			return ErrorInfo.userPass;
		}
		
		if(userEmail.isEmpty()) {
			return ErrorInfo.userEmail;
		}
		
		User user = new User(userId, userName, userEmail, userPhone, userAddress, userPassword, userGender, userRole);
		User.insertUser(user);
		
		return SuccessInfo.successRegister;
	}

}
