package com.example.assignment_1.Controller;

import com.example.assignment_1.Model.Account;
import com.example.assignment_1.Model.User;
import com.example.assignment_1.View.ViewManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable{

    @FXML
    private TextField dateTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private TextField currencyTF;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField balanceTF;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button saveNewAccountBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private TextField accountNoTF;
    @FXML
    private TextField useridTF;





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {}


    // save new account to the database table by our save method in user model
    @FXML
    private void saveNewAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
        // get data from all text fields
        int userId = Integer.parseInt(useridTF.getText());
        int accountNum = Integer.parseInt(accountNoTF.getText());
        String username = usernameTF.getText();
        String currency = currencyTF.getText();
        double balance = Double.parseDouble(balanceTF.getText());
        String date = dateTF.getText();
        // make an account object having this data
        Account account = new Account(userId, accountNum, username, currency, balance,date);
        // save the user in database by save method
        account.save();

        //after saving should return to the back scene and show an alert
        ViewManager.adminPage.changeSceneToAccountsManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account inserted");
        alert.setContentText("Account inserted");
        alert.showAndWait();
    }

    @FXML
    private void cancelAccountCreation(ActionEvent event) {
        ViewManager.adminPage.changeSceneToAccountsManagment();
    }


}
