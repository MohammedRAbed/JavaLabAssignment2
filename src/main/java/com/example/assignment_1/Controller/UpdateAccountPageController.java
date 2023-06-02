package com.example.assignment_1.Controller;

import com.example.assignment_1.Model.Account;
import com.example.assignment_1.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateAccountPageController implements Initializable {

    private Account oldAccount;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private Button updateAccountBtn;

    @FXML
    private TextField currencyTF;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField balanceTF;

    @FXML
    private TextField accountNoTF;

    @FXML
    private TextField useridTF;

    public void initialize(URL url, ResourceBundle rb) {
        //get selected user data from UsersManagmentController updatedUser var
        this.oldAccount = AccountsManagmentController.selectedAccountUpdate;

        //set text field's data the same as updatedUser data
        useridTF.setText(oldAccount.getUserId()+"");
        accountNoTF.setText(oldAccount.getAccountNumber()+"");
        usernameTF.setText(oldAccount.getUsername());
        currencyTF.setText(oldAccount.getCurrency());
        balanceTF.setText(oldAccount.getBalance()+"");
        dateTF.setText(oldAccount.getCreationDate());
    }

    @FXML
    void updateAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
        //get the new data from text field's and store it in new user object
        int userid = Integer.parseInt(useridTF.getText());
        int accountNo = Integer.parseInt(accountNoTF.getText());
        String username = usernameTF.getText();
        String currency = currencyTF.getText();
        double balance = Double.parseDouble(balanceTF.getText());
        String date = dateTF.getText();

        //make an new user object having this data
        Account newAccount = new Account(userid,accountNo,username,currency,balance,date);

        //set the new user id the same as the old user
        newAccount.setId(oldAccount.getId());

        // update the user in database by update method
        newAccount.update();

        //close the update stage using our global stage var in UsersManagmentController and show an alert
        AccountsManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("account updated");
        alert.setContentText("account updated");
        alert.showAndWait();
    }

    @FXML
    void cancelCreation(ActionEvent event) {
        AccountsManagmentController.updateStage.close();
    }
}
