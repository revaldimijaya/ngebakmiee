package internal;

import java.util.Optional;
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
import javafx.scene.control.ButtonType;
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
import model.Food;
import model.User;

public class BuyFoodInternal extends BorderPane implements EventHandler<ActionEvent> {

	GridPane gridPane;
	VBox verticalPane;
	HBox horizontalPaneLeft, horizontalPaneRight;
	TableView<Food> table;
	TableColumn foodId, foodName, foodType, foodPrice, foodStock;
	Vector<Food> foods;
	Label lblTitle;
	Label lblFoodId, lblFoodName, lblFoodType, lblFoodPrice, lblFoodStock, lblAddStock;
	TextField txtFoodId, txtFoodName, txtFoodType, txtFoodPrice, txtFoodStock;
	ComboBox<String> cbType;
	Spinner<Integer> spnFoodStock;
	Button btnAddToCart, btnShowCart;

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

		lblTitle = new Label("Manage Food");
		lblFoodId = new Label("Food ID");
		lblFoodId.setPrefWidth(130);
		lblFoodName = new Label("Food Name");
		lblFoodPrice = new Label("Food Price");
		lblFoodStock = new Label("Food Qty");
		lblFoodType = new Label("Food Type");

		txtFoodId = new TextField();
		txtFoodId.setPrefWidth(130);
		txtFoodId.setEditable(false);
		txtFoodName = new TextField();
		txtFoodName.setEditable(false);
		txtFoodPrice = new TextField();
		txtFoodPrice.setEditable(false);
		txtFoodType = new TextField();
		txtFoodType.setEditable(false);
		spnFoodStock = new Spinner<>(1, 999, 1);
		spnFoodStock.setEditable(true);

		String types[] = { "Mi Lebar", "Mi Keriting", "Mi Lurus" };
		cbType = new ComboBox<>(FXCollections.observableArrayList(types));
		cbType.setMaxWidth(Double.MAX_VALUE);
		cbType.setEditable(false);

		btnAddToCart = new Button("Add to Cart");
		btnAddToCart.setPrefWidth(150);
		btnAddToCart.setAlignment(Pos.CENTER);
		btnShowCart = new Button("Show Cart");
		btnShowCart.setPrefWidth(150);
		btnShowCart.setAlignment(Pos.CENTER);

		setCenter(verticalPane);
		setAlignment(lblTitle, Pos.TOP_CENTER);
	}

	public void setUpNewFood() {

		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(5));

		gridPane.add(lblFoodId, 2, 0);
		gridPane.add(txtFoodId, 3, 0);
		gridPane.add(lblFoodName, 2, 1);
		gridPane.add(txtFoodName, 3, 1);
		gridPane.add(lblFoodPrice, 2, 2);
		gridPane.add(txtFoodPrice, 3, 2);
		gridPane.add(lblFoodStock, 2, 3);
		gridPane.add(spnFoodStock, 3, 3);
		gridPane.add(lblFoodType, 2, 4);
		gridPane.add(txtFoodType, 3, 4);
		horizontalPaneRight.getChildren().addAll(btnAddToCart);
		gridPane.add(horizontalPaneRight, 2, 5, 2, 1);

		verticalPane.getChildren().add(gridPane);
	}

	public void setUpAction() {
		btnAddToCart.setOnAction(this);
		btnShowCart.setOnAction(this);

		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				TableSelectionModel<Food> tableSelectionModel = table.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				ObservableList<Food> obs = tableSelectionModel.getSelectedItems();

				if (obs.size() <= 0) {
					return;
				}

				if (obs.get(0).getStock() <= 0) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Alert");
					alert.setContentText("Out of stock!");
					alert.showAndWait();

					return;
				}

				txtFoodId.setText(obs.get(0).getId());
				txtFoodName.setText(obs.get(0).getName());
				txtFoodPrice.setText(Integer.toString(obs.get(0).getPrice()));
				txtFoodType.setText(obs.get(0).getType());

			}
		});
	}

	public void setUpTable() {
		this.foods = new Vector<>();

		this.foods = FoodController.getAllFood();

		ObservableList<Food> foods = FXCollections.observableArrayList(this.foods);
		table = new TableView<>();
		foodId = new TableColumn<>("Food ID");
		foodId.setCellValueFactory(new PropertyValueFactory<>("id"));
		foodName = new TableColumn<>("Food Name");
		foodName.setCellValueFactory(new PropertyValueFactory<>("name"));
		foodType = new TableColumn<>("Food Type");
		foodType.setCellValueFactory(new PropertyValueFactory<>("type"));
		foodPrice = new TableColumn<>("Food Price");
		foodPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		foodStock = new TableColumn<>("Food Stock");
		foodStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

		table.setItems(foods);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		table.getColumns().addAll(foodId, foodName, foodType, foodPrice, foodStock);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		foodId.setMaxWidth(1f * Integer.MAX_VALUE * 20);
		foodName.setMaxWidth(1f * Integer.MAX_VALUE * 20);
		foodType.setMaxWidth(1f * Integer.MAX_VALUE * 20);
		foodPrice.setMaxWidth(1f * Integer.MAX_VALUE * 20);
		foodStock.setMaxWidth(1f * Integer.MAX_VALUE * 20);

		verticalPane.getChildren().add(table);
	}

	public BuyFoodInternal() {
		init();
		setUpTable();
		setUpNewFood();
		setUpAction();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == btnShowCart) {

		} else if (e.getSource() == btnAddToCart) {
			String id = txtFoodId.getText();
			String quantity = spnFoodStock.getValue().toString();

			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setContentText("Are you sure want to add to cart ?");

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				String inserted = CartController.insertCart(UserController.currUser.getId(), id, quantity);
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Information");
				alert2.setContentText(inserted);
				alert2.showAndWait();
			}

		}
	}

}
