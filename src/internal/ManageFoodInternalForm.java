package internal;

import java.util.Optional;
import java.util.Vector;

import controller.FoodController;
import helper.ErrorInfo;
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

public class ManageFoodInternalForm extends BorderPane implements EventHandler<ActionEvent>{

	GridPane gridPane;
	VBox verticalPane;
	HBox horizontalPaneLeft, horizontalPaneRight;
	TableView<Food> table;
	TableColumn foodId, foodName, foodType, foodPrice, foodStock;
	Vector<Food> foods;
	Label lblTitle, lblNewFoodId, lblNewFoodName, lblNewFoodType, lblNewFoodPrice, lblNewFoodStock;
	Label lblFoodId, lblFoodName, lblFoodType, lblFoodPrice, lblFoodStock, lblAddStock;
	TextField txtNewFoodId, txtNewFoodName, txtNewFoodPrice;
	TextField txtFoodId, txtFoodName, txtFoodType, txtFoodPrice, txtFoodStock;
	ComboBox<String> cbNewType, cbType;
	Spinner<Integer> spnNewFoodStock, spnFoodStock;
	Button btnInsertFood, btnReset, btnUpdateFood, btnDeleteFood, btnAddStock;
	
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
		lblNewFoodId = new Label("New Food ID");
		lblNewFoodId.setPrefWidth(130);
		lblNewFoodName = new Label("New Food Name");
		lblNewFoodPrice = new Label("New Food Price");
		lblNewFoodStock = new Label("New Food Stock");
		lblNewFoodType = new Label("New Food Type");
		lblFoodId = new Label("Food ID");
		lblFoodId.setPrefWidth(130);
		lblFoodName = new Label("Food Name");
		lblFoodPrice = new Label("Food Price");
		lblFoodStock = new Label("Food Stock");
		lblFoodType = new Label("Food Type");
		
		txtNewFoodId = new TextField();
		txtNewFoodId.setPrefWidth(130);
		txtNewFoodId.setText(FoodController.generateID());
		txtNewFoodId.setEditable(false);
		txtNewFoodName = new TextField();
		txtNewFoodPrice = new TextField();
		spnNewFoodStock = new Spinner<>(1, 999, 1);
		spnNewFoodStock.setEditable(true);
		txtFoodId = new TextField();
		txtFoodId.setPrefWidth(130);
		txtFoodName = new TextField();
		txtFoodPrice = new TextField();
		spnFoodStock = new Spinner<>(1, 999, 1);
		spnFoodStock.setEditable(true);
		
		String types[] = {"Mi Lebar", "Mi Keriting", "Mi Lurus"};
		cbType = new ComboBox<>(FXCollections.observableArrayList(types));
		cbType.setMaxWidth(Double.MAX_VALUE);
		cbNewType = new ComboBox<>(FXCollections.observableArrayList(types));
		cbNewType.setMaxWidth(Double.MAX_VALUE);
		cbNewType.setValue(types[0]);
		
		
		btnInsertFood = new Button("Insert Food");
		btnInsertFood.setPrefWidth(150);
		btnInsertFood.setAlignment(Pos.CENTER);
		btnReset = new Button("Reset");
		btnReset.setPrefWidth(150);
		btnReset.setAlignment(Pos.CENTER);
		btnUpdateFood = new Button("Update Food");
		btnUpdateFood.setPrefWidth(150);
		btnUpdateFood.setAlignment(Pos.CENTER);
		btnDeleteFood = new Button("Delete Food");
		btnDeleteFood.setPrefWidth(150);
		btnDeleteFood.setAlignment(Pos.CENTER);
		
		setCenter(verticalPane);
		setAlignment(lblTitle, Pos.TOP_CENTER);
	}
	
	public void setUpNewFood() {
		
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(5));
		
		gridPane.add(lblNewFoodId, 0, 0);
		gridPane.add(txtNewFoodId, 1, 0);
		gridPane.add(lblNewFoodName, 0, 1);
		gridPane.add(txtNewFoodName, 1, 1);
		gridPane.add(lblNewFoodPrice, 0, 2);
		gridPane.add(txtNewFoodPrice, 1, 2);
		gridPane.add(lblNewFoodStock, 0, 3);
		gridPane.add(spnNewFoodStock, 1, 3);
		gridPane.add(lblNewFoodType, 0, 4);
		gridPane.add(cbNewType, 1, 4);
		horizontalPaneLeft.getChildren().addAll(btnInsertFood, btnReset);
		gridPane.add(horizontalPaneLeft, 0, 5, 2, 1);
		
		gridPane.add(lblFoodId, 2, 0);
		gridPane.add(txtFoodId, 3, 0);
		gridPane.add(lblFoodName, 2, 1);
		gridPane.add(txtFoodName, 3, 1);
		gridPane.add(lblFoodPrice, 2, 2);
		gridPane.add(txtFoodPrice, 3, 2);
		gridPane.add(lblFoodStock, 2, 3);
		gridPane.add(spnFoodStock, 3, 3);
		gridPane.add(lblFoodType, 2, 4);
		gridPane.add(cbType, 3, 4);
		horizontalPaneRight.getChildren().addAll(btnUpdateFood, btnDeleteFood);
		gridPane.add(horizontalPaneRight, 2, 5, 2, 1);
		
		
		verticalPane.getChildren().add(gridPane);
	}
	
	public void setUpAction() {
		btnInsertFood.setOnAction(this);
		btnUpdateFood.setOnAction(this);
		btnDeleteFood.setOnAction(this);
		btnReset.setOnAction(this);
		
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				TableSelectionModel<Food> tableSelectionModel = table.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				ObservableList<Food> obs = tableSelectionModel.getSelectedItems();
				
				if(obs.size() <= 0) {
					return;
				}
				
				txtFoodId.setText(obs.get(0).getId());
				txtFoodName.setText(obs.get(0).getName());
				txtFoodPrice.setText(Integer.toString(obs.get(0).getPrice()));
				spnFoodStock.getValueFactory().setValue(obs.get(0).getStock());
				cbType.setValue(obs.get(0).getType());
				
			}
		});
	}
	
	public void setUpTable() {
		this.foods = new Vector<>();
		
		this.foods = FoodController.getAllFood();
		
		ObservableList<Food> foods = FXCollections.observableArrayList(
				this.foods
		);
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
		foodId.setMaxWidth(1f * Integer.MAX_VALUE * 20 );
		foodName.setMaxWidth(1f * Integer.MAX_VALUE * 20 );
		foodType.setMaxWidth(1f * Integer.MAX_VALUE * 20 );
		foodPrice.setMaxWidth(1f * Integer.MAX_VALUE * 20 );
		foodStock.setMaxWidth(1f * Integer.MAX_VALUE * 20 );
		
		verticalPane.getChildren().add(table);
	}
	
	
	public ManageFoodInternalForm() {
		init();
		setUpTable();
		setUpNewFood();
		setUpAction();
	}
	
	public void refresh() {
		table.getItems().clear();
		this.foods = FoodController.getAllFood();
		ObservableList<Food> foods = FXCollections.observableArrayList(
				this.foods
		);
		table.getItems().addAll(foods);
	}

	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() == btnInsertFood) {
			String foodId = txtNewFoodId.getText();
			String foodName = txtNewFoodName.getText();
			String foodType = cbNewType.getValue().toString();
			String foodPrice = txtNewFoodPrice.getText();
			String foodStock = spnNewFoodStock.getValue().toString();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setContentText("Are you sure want to insert new food ?");
			
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.OK) {
				String message = FoodController.insertFood(foodId, foodName, foodType, foodPrice, foodStock);
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Information");
				alert2.setContentText(message);
				alert2.showAndWait();
				
				if(message.equals(SuccessInfo.successInsertFood)) {
					refresh();
				}
			}
		} else if(e.getSource() == btnUpdateFood) {
			String foodId = txtFoodId.getText();
			String foodName = txtFoodName.getText();
			String foodType = (cbType.getValue() != null) ? cbType.getValue().toString() : "";
			String foodPrice = txtFoodPrice.getText();
			String foodStock = spnFoodStock.getValue().toString();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setContentText("Are you sure want to update food ?");
			
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.OK) {
				String message = FoodController.updateFood(foodId, foodName, foodType, foodPrice, foodStock);
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Information");
				alert2.setContentText(message);
				alert2.showAndWait();
				
				if(message.equals(SuccessInfo.successUpdateFood)) {
					refresh();
				}
			}
		} else if(e.getSource() == btnDeleteFood) {
			String foodId = txtFoodId.getText();
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setContentText("Are you sure want to delete food ?");
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.OK) {
				String message = FoodController.removeFood(foodId);
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Information");
				alert2.setContentText(message);
				alert2.showAndWait();
				
				if(message.equals(SuccessInfo.successRemoveFood)) {
					refresh();
				}
			}
		} else if(e.getSource() == btnReset) {
			
			txtNewFoodName.setText("");
			txtNewFoodPrice.setText("");
			spnNewFoodStock.getValueFactory().setValue(1);
		}
	}

}

