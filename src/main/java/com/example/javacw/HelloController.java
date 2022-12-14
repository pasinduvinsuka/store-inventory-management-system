package com.example.javacw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {



    @FXML
    private Pane homepage;
    @FXML
    private Button loginBtn;

    @FXML
    private Label loginScreenMessageLabel;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private AnchorPane dashboardPane;





    public void loginValidate() {
        try {

            database connection=new database();
            connection.connectDB();

            String sql="select * from logindetails where username=? and password=? ;";
            PreparedStatement preparedStatement=connection.connectDB().prepareStatement(sql);
            preparedStatement.setString(1,username.getText());
            preparedStatement.setString(2,password.getText());
            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()){
                loginScreenMessageLabel.setText("Login Succeed");

                loginBtn.getScene().getWindow().hide();

                Parent root= FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                Stage stage =new Stage();
                Scene scene =new Scene(root);

                stage.setScene(scene);
                stage.show();






            }else{
                if(username.getText().isEmpty() || password.getText().isEmpty()){
                    loginScreenMessageLabel.setText("Please Fill All the Blanks");
                }else {
                    loginScreenMessageLabel.setText("Wrong Username or Password");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }









}
