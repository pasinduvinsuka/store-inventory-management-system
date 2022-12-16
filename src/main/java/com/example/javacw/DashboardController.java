package com.example.javacw;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class DashboardController implements DashboardControllerInitializer {

    @Override
    public void initialize(URL url, ResourceBundle rb) throws SQLException {

       MainController fromMainController=new MainController();
        fromMainController.chart();
    }

    @FXML
    public Button homeBtn;

    @FXML
    public URL location;
    @FXML
    public ResourceBundle resources;
    @FXML
    public Button inventoryBtn;

    @FXML
    public AnchorPane inventoryPage;

    @FXML
    public AnchorPane salesPage;

    @FXML
    public Pane homepage;

    @FXML
    public Button salesBtn;

    @FXML
    public TextField movieIdInput;

    @FXML
    public TextField movieNameInput;

    @FXML
    public TextField itemsAmountInput;

    @FXML
    public TextField priceInput;

    @FXML
    public ComboBox<?> categoryComboBox;

    @FXML
    public ComboBox<?> statusComboBox;

    @FXML
    public TextField inventorySearchField;

    @FXML
    public TableView<moviesData> inventoryTable;

    @FXML
    public TableColumn<moviesData, String> inventory_col_category;

    @FXML
    public TableColumn<moviesData, Integer> inventory_col_itemsInStock;

    @FXML
    public TableColumn<moviesData, Integer> inventory_col_movieid;

    @FXML
    public TableColumn<moviesData, String> inventory_col_moviename;

    @FXML
    public TableColumn<moviesData, Integer> inventory_col_price;

    @FXML
    public TableColumn<moviesData, String> inventory_col_status;

    @FXML
    public Button addBtn;

    @FXML
    public TextField sales_movieNameSearch;

    @FXML
    public TableColumn<showSalesTableData, Date> sales_date_column;

    @FXML
    public TableColumn<showSalesTableData, Integer> sales_movieID_column;

    @FXML
    public TableColumn<showSalesTableData, String> sales_movieName_column;

    @FXML
    public TableColumn<showSalesTableData, Integer> sales_quantity_column;

    @FXML
    public TableColumn<showSalesTableData, Integer> sales_totalPrice_column;
    @FXML
    public TableView<showSalesTableData> sales_table;

    @FXML
    public TextField amount_input;

    @FXML
    public TextField quantity_input;

    @FXML
    public DatePicker date_input;

    @FXML
    public Label BalanceLabel;

    @FXML
    public Label TotalLabel;

    @FXML
    public TableView<salesMovieSearch> suggestions_table;

    @FXML
    public TableColumn<salesMovieSearch, String> Suggestions_column;
    @FXML
    public TableColumn<salesMovieSearch, Double> Unit_Price_Column;

    @FXML
    public Label AverageSaleLabel;

    @FXML
    public Label CountOfSaleLabel;

    @FXML
    public Label TotalSalesLabel;

    @FXML
    public Label TotalItemsLabel;


    @FXML
    public AreaChart<?, ?> sales_barChart;


}




