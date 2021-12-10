package internal;

import java.awt.event.ActionListener;
import java.util.Vector;

import controller.CartController;
import controller.FoodController;
import controller.UserController;
import helper.SuccessInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Cart;
import model.Food;
import temp.ViewCart;

public class CartInternal extends BorderPane implements EventHandler<ActionEvent> {

	GridPane gridPane;
	VBox verticalPane;
	HBox horizontalPaneLeft, horizontalPaneRight;
	TableView<ViewCart> table;
	TableColumn foodId, foodName, foodType, foodPrice, foodStock, foodQuantity, foodSubTotal;
	Vector<Food> foods;
	Vector<Cart> carts;
	Button btnRemove, btnClear, btnCheckout;
	ObservableList<ViewCart> obs;

	public void init() {
		gridPane = new GridPane();
		verticalPane = new VBox();
		horizontalPaneLeft = new HBox();
		horizontalPaneLeft.setSpacing(10);
		horizontalPaneLeft.maxWidth(Double.MAX_VALUE);
		horizontalPaneLeft.setAlignment(Pos.CENTER);
		horizontalPaneRight = new HBox();
		horizontalPaneRight.setSpacing(10);
		horizontalPaneRight.maxWidth(Double.MAX_VALUE);
		horizontalPaneRight.setAlignment(Pos.CENTER);

		btnRemove = new Button("Remove Selected Cart");
		btnRemove.setPrefWidth(200);
		btnRemove.setAlignment(Pos.CENTER);
		btnClear = new Button("Clear Cart");
		btnClear.setPrefWidth(200);
		btnClear.setAlignment(Pos.CENTER);
		btnCheckout = new Button("Checkout Cart");
		btnCheckout.setPrefWidth(200);
		btnCheckout.setAlignment(Pos.CENTER);

		setCenter(verticalPane);
	}

	public void setUpNewFood() {

		horizontalPaneRight.getChildren().addAll(btnRemove, btnClear, btnCheckout);
		horizontalPaneRight.setPadding(new Insets(10));
		verticalPane.getChildren().add(horizontalPaneRight);

	}

	public void setUpAction() {
		btnRemove.setOnAction(this);
		btnClear.setOnAction(this);
		btnCheckout.setOnAction(this);

		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				TableSelectionModel<ViewCart> tableSelectionModel = table.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				obs = tableSelectionModel.getSelectedItems();

				if (obs.size() <= 0) {
					return;
				}

			}
		});
	}

	public void setUpTable() {
		this.foods = new Vector<>();
		this.carts = new Vector<>();

		this.carts = CartController.getAllCart(UserController.currUser.getId());

		Vector<ViewCart> tableData = new Vector<>();
		for (Cart c : carts) {
			Food food = FoodController.getOneFood(c.getFoodID());
			tableData.add(new ViewCart(food.getId(), food.getName(), food.getType(), food.getPrice(), food.getStock(),
					c.getQuantity(), c.getQuantity() * food.getPrice()));
		}

		ObservableList<ViewCart> datas = FXCollections.observableArrayList(tableData);
		table = new TableView<>();
		foodId = new TableColumn<>("Food ID");
		foodId.setCellValueFactory(new PropertyValueFactory<>("foodId"));
		foodName = new TableColumn<>("Food Name");
		foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
		foodType = new TableColumn<>("Food Type");
		foodType.setCellValueFactory(new PropertyValueFactory<>("foodType"));
		foodPrice = new TableColumn<>("Food Price");
		foodPrice.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
		foodStock = new TableColumn<>("Food Stock");
		foodStock.setCellValueFactory(new PropertyValueFactory<>("foodStock"));
		foodQuantity = new TableColumn<>("Quantity");
		foodQuantity.setCellValueFactory(new PropertyValueFactory<>("foodQuantity"));
		foodSubTotal = new TableColumn<>("Food Sub Total");
		foodSubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

		table.setItems(datas);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getColumns().addAll(foodId, foodName, foodType, foodPrice, foodStock, foodQuantity, foodSubTotal);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		foodId.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodName.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodType.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodPrice.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodStock.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodQuantity.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodSubTotal.setMaxWidth(1f * Integer.MAX_VALUE * 14);

		verticalPane.getChildren().add(table);
	}

	public CartInternal() {
		init();
		setUpTable();
		setUpNewFood();
		setUpAction();
	}
	
	public void refresh() {
		table.getItems().clear();
		this.carts = CartController.getAllCart(UserController.currUser.getId());

		Vector<ViewCart> tableData = new Vector<>();
		for (Cart c : carts) {
			Food food = FoodController.getOneFood(c.getFoodID());
			tableData.add(new ViewCart(food.getId(), food.getName(), food.getType(), food.getPrice(), food.getStock(),
					c.getQuantity(), c.getQuantity() * food.getPrice()));
		}

		ObservableList<ViewCart> datas = FXCollections.observableArrayList(tableData);
		table.getItems().addAll(datas);
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == btnRemove) {
			if(obs == null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Alert");
				alert.setContentText("Please select cart first!");
				alert.showAndWait();
				return;
			}
			
			String userId = UserController.currUser.getId();
			String foodId = obs.get(0).getFoodId();
			
			String msg = CartController.removeCart(userId, foodId);
			
			if(msg.equals(SuccessInfo.successRemoveCart)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setContentText(msg);
				alert.showAndWait();
				refresh();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setContentText(msg);
				alert.showAndWait();
			}
			
		} else if(e.getSource() == btnClear) {
			String userId = UserController.currUser.getId();
			
			String msg = CartController.clearCart(userId);
			
			if(msg.equals(SuccessInfo.successClearCart)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setContentText(msg);
				alert.showAndWait();
				refresh();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setContentText(msg);
				alert.showAndWait();
			}
			
		} else if(e.getSource() == btnCheckout) {
			String userId = UserController.currUser.getId();
			
			String msg = CartController.checkoutCart(userId);
			if(msg.equals(SuccessInfo.successCheckoutCart)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setContentText(msg);
				alert.showAndWait();
				refresh();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setContentText(msg);
				alert.showAndWait();
			}
		}
	}

}
