/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assignment_1.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.assignment_1.HelloApplication;
import com.example.assignment_1.Model.Account;
import com.example.assignment_1.Model.User;
import com.example.assignment_1.View.ViewManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {
    public static Account selectedAccountUpdate;
    public static Stage updateStage;
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
    @FXML
    private TableView<Account> accountsTableView;
    @FXML
    private TableColumn<Account,Integer> idCol;
    @FXML
    private TableColumn<Account,Integer> acNumCol;
    @FXML
    private TableColumn<Account,String> usernameCol;
    @FXML
    private TableColumn<Account,String> currencyCol;
    @FXML
    private TableColumn<Account,Double> balanceCol;
    @FXML
    private TableColumn<Account,String> dateCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///////////////////////////////////////////////////////////////////////////
        // make maping between TableView column's and user object attributes .   //
        // TableView { idCol,usernameCol,passwordCol,emailCol,genderCol,roleCol }//
        //              |       |           |           |       |        |       //
        // UserObject{ id   ,username   ,password   ,email   ,gender   ,role    }//
        ///////////////////////////////////////////////////////////////////////////
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //acNumCol.setCellValueFactory(new PropertyValueFactory<>("account_number"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        currencyCol.setCellValueFactory(new PropertyValueFactory<>("currency"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));
        //dateCol.setCellValueFactory(new PropertyValueFactory<>("creation_date"));
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
         ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {}

    @FXML
    private void showOperationsPage(ActionEvent event) {}

    @FXML
    private void showAccountCreationPage(ActionEvent event) {ViewManager.adminPage.changeSceneToCreateAccount();}

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Account> accountsList =
                FXCollections.observableArrayList(Account.getAllAccounts());

        accountsTableView.setItems(accountsList);
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
        //check if there is an user selected from the TableView
        if(accountsTableView.getSelectionModel().getSelectedItem() != null){
            //store the selected user from the TableView in our global var user selectedUserToUpdate
            selectedAccountUpdate = accountsTableView.getSelectionModel().getSelectedItem();
            //load update page fxml [My own code]
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("UpdateAccountPage.fxml"));
        /*FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateUserPage.fxml"));
        Parent rootUpdate = loaderUpdate.load();*/
            Scene updateAccountScene = new Scene(fxmlLoader.load());
            //store loaded fxml in our global stage updateStage and show it
            updateStage = new Stage();
            updateStage.setScene(updateAccountScene);
            updateStage.setTitle("Update user " + selectedAccountUpdate.getUsername() );
            updateStage.show();
        }
    }

    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
        //check if there is an user selected from the TableView
        if(accountsTableView.getSelectionModel().getSelectedItem() != null){
            //store the selected user from the TableView in new user object
            Account selectedAccount = accountsTableView.getSelectionModel().getSelectedItem();

            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    try {
                        //delete the selected user from database table using delete method in our User model
                        selectedAccount.delete();
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("Account deleted");
                    deletedSuccessAlert.setContentText("Account deleted");
                    deletedSuccessAlert.show();
                }
            });

        }else{
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an account");
            warnAlert.setContentText("Please select an account from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void searchForAnAccount(ActionEvent event) {}
    
}
