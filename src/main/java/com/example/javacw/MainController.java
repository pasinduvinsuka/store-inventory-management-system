package com.example.javacw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainController extends DashboardController {


    //Database tools;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;



    //Add data into Inventory Table
    public void addDataIntoInventory() {
        String sql = "INSERT INTO inventory (movie_id,movie_name,category,price,items_in_stock,status)"
                + "VALUES (?,?,?,?,?,?)";

        try {
            database connection = new database();
            connection.connectDB();
            preparedStatement = connection.connectDB().prepareStatement(sql);

            preparedStatement.setString(1, movieIdInput.getText());
            preparedStatement.setString(2, movieNameInput.getText());
            preparedStatement.setString(3, (String) categoryComboBox.getSelectionModel().getSelectedItem());
            preparedStatement.setDouble(4, Double.parseDouble(priceInput.getText()));
            preparedStatement.setString(5, itemsAmountInput.getText());
            preparedStatement.setString(6, (String) statusComboBox.getSelectionModel().getSelectedItem());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        showTableData();

    }

    private String[] listType = {" horror", "action", "romantic"};

    public void addProductCombo() {
        List<String> listT = new ArrayList<>();

        for (String data : listType) {
            listT.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listT);
        categoryComboBox.setItems(listData);
    }

    private String[] listStatus = {"Available", "Not Available"};

    public void inventoryStatus() {
        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        statusComboBox.setItems(listData);
    }


    //Showing Database data in table
    public void showTableData() {

        ObservableList<moviesData> dataToShow = FXCollections.observableArrayList();

        try {
            database connection = new database();
            connection.connectDB();
            String sql = "SELECT movie_id,movie_name,category,price,items_in_stock,status FROM inventory;";
            Statement statement = connection.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                dataToShow.add(new moviesData(
                        resultSet.getInt("movie_id"),
                        resultSet.getString("movie_name"),
                        resultSet.getString("category"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("items_in_stock"),
                        resultSet.getString("status")));
            }
            inventory_col_movieid.setCellValueFactory(new PropertyValueFactory<>("movieId"));
            inventory_col_moviename.setCellValueFactory(new PropertyValueFactory<>("movieName"));
            inventory_col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
            inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            inventory_col_itemsInStock.setCellValueFactory(new PropertyValueFactory<>("itemsAmount"));
            inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

            inventoryTable.setItems(dataToShow);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Showing selected table data in Input field
    public void showTableDataInInputFields() {
        moviesData selectedItem = inventoryTable.getSelectionModel().getSelectedItem();
        int selectedIndex = inventoryTable.getSelectionModel().getSelectedIndex();

        movieIdInput.setText(String.valueOf(selectedItem.getMovieId()));
        movieNameInput.setText(selectedItem.getMovieName());
        priceInput.setText(String.valueOf(selectedItem.getPrice()));
        itemsAmountInput.setText(String.valueOf(selectedItem.getItemsAmount()));

    }

    //Remove Selected Row from Table
    public void removeDataFromTable() {
        try {
            database connection = new database();
            connection.connectDB();
            String sql = "DELETE FROM inventory WHERE movie_id='" + movieIdInput.getText() + "'";
            Statement statement = connection.connectDB().createStatement();
            statement.executeUpdate(sql);

            //table refresh;
            showTableData();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Update Table Data
    public void updateTableData() {

        try {
            database connection = new database();
            connection.connectDB();
            String sql = "UPDATE inventory SET movie_name='" + movieNameInput.getText() + "'," +
                    "category='" + categoryComboBox.getSelectionModel().getSelectedItem() + "'," +
                    "price='" + priceInput.getText() + "'," +
                    "items_in_stock='" + itemsAmountInput.getText() + "'," +
                    " status='" + statusComboBox.getSelectionModel().getSelectedItem() + "' " +
                    "WHERE movie_id='" + movieIdInput.getText() + "'";

            Statement statement = connection.connectDB().createStatement();
            statement.executeUpdate(sql);

            //table refresh;
            showTableData();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Search from InventoryTable
    public void searchFromInventoryTable() throws SQLException {
        String searchKeyword = inventorySearchField.getText();
        ObservableList<moviesData> moviesDataObservableList = FXCollections.observableArrayList();

        try {
            database connection = new database();
            connection.connectDB();
            String sql = "SELECT * FROM inventory WHERE movie_name LIKE '" + searchKeyword + "%'";
            Statement statement = connection.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Integer movieID_data = resultSet.getInt("movie_id");
                String movieName_data = resultSet.getString("movie_name");
                String category_data = resultSet.getString("category");
                Double price_data = resultSet.getDouble("price");
                Integer itemsInStock_data = resultSet.getInt("items_in_stock");
                String status_data = resultSet.getString("status");

                //Populate the observable list
                moviesDataObservableList.add(new moviesData(
                        movieID_data,
                        movieName_data,
                        category_data,
                        price_data,
                        itemsInStock_data,
                        status_data));
            }

            inventory_col_movieid.setCellValueFactory(new PropertyValueFactory<>("movieId"));
            inventory_col_moviename.setCellValueFactory(new PropertyValueFactory<>("movieName"));
            inventory_col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
            inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            inventory_col_itemsInStock.setCellValueFactory(new PropertyValueFactory<>("itemsAmount"));
            inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

            inventoryTable.setItems(moviesDataObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Switch between pages
    public void switchForm(ActionEvent event) {

        if (event.getSource() == homeBtn) {
            homepage.setVisible(true);
            inventoryPage.setVisible(false);
            salesPage.setVisible(false);

        } else if (event.getSource() == inventoryBtn) {
            homepage.setVisible(false);
            inventoryPage.setVisible(true);
            salesPage.setVisible(false);
            addProductCombo();
            inventoryStatus();

        } else if (event.getSource() == salesBtn) {
            homepage.setVisible(false);
            inventoryPage.setVisible(false);
            salesPage.setVisible(true);

        }
    }

    //Sales-Page MovieName Suggestion;
    public void movieNameSearch() {
        ObservableList<salesMovieSearch> salesMovieSearchObservableList = FXCollections.observableArrayList();
        try {
            database connection = new database();
            connection.connectDB();
            String sql = "SELECT * FROM inventory WHERE movie_name LIKE '" + sales_movieNameSearch.getText() + "%'";
            Statement statement = connection.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                salesMovieSearchObservableList.add(new salesMovieSearch(
                        resultSet.getString("movie_name"),
                        resultSet.getDouble("price")

                ));
                Suggestions_column.setCellValueFactory(new PropertyValueFactory<>("movieName"));
                Unit_Price_Column.setCellValueFactory(new PropertyValueFactory<>("price"));

                suggestions_table.setItems(salesMovieSearchObservableList);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //add data to sales table
    public void addSalesTableData() {
        try {
            database connection = new database();
            connection.connectDB();
            String sql = "INSERT INTO sales (sales_date,movie_name,quantity,total_price)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.connectDB().prepareStatement(sql);
            salesMovieSearch selectedItem = suggestions_table.getSelectionModel().getSelectedItem();
            int Total = (int) (Integer.parseInt(quantity_input.getText()) * selectedItem.getPrice());
            LocalDate salesDate = date_input.getValue();

            preparedStatement.setString(1, salesDate.toString());
            preparedStatement.setString(2, selectedItem.getMovieName());
            preparedStatement.setInt(3, Integer.parseInt(quantity_input.getText()));
            preparedStatement.setDouble(4, Total);
            preparedStatement.executeUpdate();

            showSalesTableData();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

//show Total LabelTexts;

    public void updateTotalLabel() {
        salesMovieSearch selectedItem = suggestions_table.getSelectionModel().getSelectedItem();
        int Total = (int) (Integer.parseInt(quantity_input.getText()) * selectedItem.getPrice());
        TotalLabel.setText(String.valueOf(Total) + "$");
    }

    //show Balance LabelTexts;
    public void updateBalanceLabel() {
        salesMovieSearch selectedItem = suggestions_table.getSelectionModel().getSelectedItem();
        int Total = (int) (Integer.parseInt(quantity_input.getText()) * selectedItem.getPrice());
        int Amount = Integer.parseInt(amount_input.getText());
        int Balance = Amount - Total;
        BalanceLabel.setText(String.valueOf(Balance) + "$");
    }
    //show data in sales table

    public void showSalesTableData() {
        ObservableList<showSalesTableData> showSalesTableDataObservableList = FXCollections.observableArrayList();

        try {
            database connection = new database();
            connection.connectDB();
            String sql = "SELECT  sales_id,movie_name,quantity,total_price,sales_date from sales";
            Statement statement = connection.connectDB().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                showSalesTableDataObservableList.add(new showSalesTableData(
                        resultSet.getDate("sales_date"),
                        resultSet.getString("movie_name"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("total_price"),
                        resultSet.getInt("sales_id")


                ));
            }
            sales_date_column.setCellValueFactory(new PropertyValueFactory<>("date_salesTable"));
            sales_movieName_column.setCellValueFactory(new PropertyValueFactory<>("movieName_salesTable"));
            sales_quantity_column.setCellValueFactory(new PropertyValueFactory<>("quantity_salesTable"));
            sales_totalPrice_column.setCellValueFactory(new PropertyValueFactory<>("totalPrice_salesTable"));

            sales_table.setItems(showSalesTableDataObservableList);
            chart();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //delete data in sales table

    public void deleteTableData() {


        try {
            showSalesTableData selectedItem = sales_table.getSelectionModel().getSelectedItem();
            database connection = new database();
            connection.connectDB();
            String sql = "DELETE FROM sales WHERE sales_id='" + selectedItem.getSalesID_salesTable() + "'";
            Statement statement = connection.connectDB().createStatement();
            statement.executeUpdate(sql);

            showSalesTableData();




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /*Showing sales table data on chart*/

    public void chart() throws SQLException {
        database connection = new database();
        connection.connectDB();

        String sql = "SELECT sales_date, SUM(total_price) FROM sales GROUP BY sales_date ORDER BY DATE (sales_date) ASC LIMIT 100";


        XYChart.Series ChartData = new XYChart.Series();
        PreparedStatement statement = connection.connectDB().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            ChartData.getData().add(new XYChart.Data(resultSet.getString(1), resultSet.getInt(2)));
            sales_barChart.getData().add(ChartData);

        }



    }

    /*show label texts in mainDashBoard*/








    public void initialize() throws SQLException {
        chart();
    }

}
