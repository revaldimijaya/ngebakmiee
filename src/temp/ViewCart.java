package temp;

public class ViewCart {
	private String foodId;
	private String foodName;
	private String foodType;
	private int foodPrice;
	private int foodStock;
	private int foodQuantity;
	private int subTotal;

	public ViewCart(String foodId, String foodName, String foodType, int foodPrice, int foodStock, int foodQuantity,
			int subTotal) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodType = foodType;
		this.foodPrice = foodPrice;
		this.foodStock = foodStock;
		this.foodQuantity = foodQuantity;
		this.subTotal = subTotal;
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public int getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}

	public int getFoodStock() {
		return foodStock;
	}

	public void setFoodStock(int foodStock) {
		this.foodStock = foodStock;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

}
