package internal;

import java.util.Vector;

import com.sun.jdi.connect.Connector.SelectedArgument;

import controller.CartController;
import controller.DetailController;
import controller.FoodController;
import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Cart;
import model.DetailTransaction;
import model.Food;
import model.HeaderTransaction;
import temp.ViewCart;
import temp.ViewTransactionDetail;

public class HistoryTransactionInternal extends BorderPane implements EventHandler<MouseEvent> {

	GridPane gridPane;
	VBox verticalPane;
	TableView<HeaderTransaction> tableHeaderTransaction;
	TableView<ViewTransactionDetail> tableDetailTransaction;
	TableColumn transactionId, userId, transactionDate, foodId;
	TableColumn transactionId2, foodId2, foodName, foodType, foodPrice, foodStock, foodQuantity, foodSubTotal;
	Vector<Food> foods;
	Vector<HeaderTransaction> header;
	Vector<DetailTransaction> detail;
	TextField txtSelectedId, txtGrandTotal;
	ObservableList<HeaderTransaction> obs;
	HBox hbox1, hbox2;
	Label lblSelectedId, lblGrandTotal;
	
	public void init() {
		verticalPane = new VBox();
		verticalPane.setSpacing(10);
		verticalPane.setAlignment(Pos.CENTER);
		verticalPane.setPadding(new Insets(0,0,10,0));
		gridPane = new GridPane();
		gridPane.setPrefWidth(Double.MAX_VALUE);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setBackground(new Background(new BackgroundFill(Color.AQUA, CornerRadii.EMPTY, Insets.EMPTY)));
		txtSelectedId = new TextField();
		txtSelectedId.setEditable(false);
		txtGrandTotal = new TextField();
		txtGrandTotal.setEditable(false);
		lblSelectedId = new Label("Selected ID");
		lblGrandTotal = new Label("Grand Total");
		hbox1 = new HBox();
		hbox1.getChildren().addAll(lblSelectedId, txtSelectedId);
		hbox1.setAlignment(Pos.BASELINE_LEFT);
		hbox1.setSpacing(10);
		hbox2 = new HBox();
		hbox2.getChildren().addAll(lblGrandTotal, txtGrandTotal);
		hbox2.setAlignment(Pos.BASELINE_RIGHT);
		hbox2.setSpacing(10);
		setCenter(verticalPane);
	}

	public void setUpTableHeaderTransaction() {
		this.header = new Vector<>();

		this.header = HeaderTransaction.getAllTransactionHeader(UserController.currUser.getId());

		ObservableList<HeaderTransaction> datas = FXCollections.observableArrayList(header);
		tableHeaderTransaction = new TableView<>();
		transactionId = new TableColumn<>("Transaction ID");
		transactionId.setCellValueFactory(new PropertyValueFactory<>("TransactionID"));
		userId = new TableColumn<>("User ID");
		userId.setCellValueFactory(new PropertyValueFactory<>("UserID"));
		transactionDate = new TableColumn<>("Transaction Date");
		transactionDate.setCellValueFactory(new PropertyValueFactory<>("TransactionDate"));

		tableHeaderTransaction.setItems(datas);
		tableHeaderTransaction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableHeaderTransaction.getColumns().addAll(transactionId, userId, transactionDate);
		tableHeaderTransaction.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		transactionId.setMaxWidth(1f * Integer.MAX_VALUE * 33);
		userId.setMaxWidth(1f * Integer.MAX_VALUE * 33);
		transactionDate.setMaxWidth(1f * Integer.MAX_VALUE * 33);

		verticalPane.getChildren().add(tableHeaderTransaction);
	}

	public void setUpTableDetailTransaction() {
		this.foods = new Vector<>();
		this.detail = new Vector<>();
		String selectId = (txtSelectedId.getText().isEmpty()) ? "" : txtSelectedId.getText();
		this.detail = DetailController.getDetail(selectId);
		
		Vector<ViewTransactionDetail> tableData = new Vector<>();
		
		int grandTotal = 0;
		
		for (DetailTransaction d : detail) {
			Food food = FoodController.getOneFood(d.getFoodID());
			tableData.add(new ViewTransactionDetail(d.getTransactionID(), food.getId(), food.getName(), food.getType(),
					food.getPrice(), food.getStock(), d.getQuantity(), food.getPrice() * d.getQuantity()));
			grandTotal += (d.getQuantity() * food.getPrice());
		}
		
		txtGrandTotal.setText("Rp. "+grandTotal + ",-");

		ObservableList<ViewTransactionDetail> datas = FXCollections.observableArrayList(tableData);
		tableDetailTransaction = new TableView<>();
		transactionId2 = new TableColumn<>("Transaction ID");
		transactionId2.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		foodId2 = new TableColumn<>("Food ID");
		foodId2.setCellValueFactory(new PropertyValueFactory<>("foodId"));
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

		tableDetailTransaction.setItems(datas);
		tableDetailTransaction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableDetailTransaction.getColumns().addAll(transactionId2, foodId2, foodName, foodType, foodPrice, foodQuantity, foodSubTotal);
		tableDetailTransaction.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		transactionId2.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodId2.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodName.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodType.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodPrice.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodQuantity.setMaxWidth(1f * Integer.MAX_VALUE * 14);
		foodSubTotal.setMaxWidth(1f * Integer.MAX_VALUE * 14);

		verticalPane.getChildren().add(tableDetailTransaction);
	}

	public void setUpAction() {
		tableHeaderTransaction.setOnMouseClicked(this);
	}

	public HistoryTransactionInternal() {
		init();
		setUpTableHeaderTransaction();
		
		verticalPane.getChildren().add(hbox1);
		setUpTableDetailTransaction();
		verticalPane.getChildren().add(hbox2);
		setUpAction();
	}

	public void refresh() {
		tableDetailTransaction.getItems().clear();
		String selectId = (txtSelectedId.getText().isEmpty()) ? "" : txtSelectedId.getText();
		this.detail = DetailController.getDetail(selectId);
		Vector<ViewTransactionDetail> tableData = new Vector<>();
		
		int grandTotal = 0;
		
		for (DetailTransaction d : detail) {
			Food food = FoodController.getOneFood(d.getFoodID());
			tableData.add(new ViewTransactionDetail(d.getTransactionID(), food.getId(), food.getName(), food.getType(),
					food.getPrice(), food.getStock(), d.getQuantity(), food.getPrice() * d.getQuantity()));
			grandTotal += (d.getQuantity() * food.getPrice());
		}
		
		txtGrandTotal.setText("Rp. "+grandTotal + ",-");

		ObservableList<ViewTransactionDetail> datas = FXCollections.observableArrayList(tableData);
		tableDetailTransaction.getItems().addAll(datas);
	}
	
	@Override
	public void handle(MouseEvent e) {
		if (e.getSource() == tableHeaderTransaction) {
			TableSelectionModel<HeaderTransaction> tableSelectionModel = tableHeaderTransaction.getSelectionModel();
			tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
			obs = tableSelectionModel.getSelectedItems();

			if (obs.size() <= 0) {
				return;
			}
			
			txtSelectedId.setText(obs.get(0).getTransactionID());
			refresh();
		}
	}

}
