/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.assignment_1.View;

import java.io.IOException;

import com.example.assignment_1.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yahya
 */
public class AdminDashboard extends Stage{
    
    // All Scenes that AdminDashboard page have
    private final Scene usersManagmentScene;
    private final Scene createUserScene;
    private final Scene accountsManagementScene;
    private final Scene createAccountScene;

    //private Scene operationsScene;
    public AdminDashboard() throws IOException {
        
        //load UserManagment FXML File in UsersManagment Scene
        FXMLLoader usersManagmentLoader = new FXMLLoader(HelloApplication.class.getResource("UsersManagment.fxml"));
        Parent usersManagmentRoot = usersManagmentLoader.load();     
        usersManagmentScene = new Scene(usersManagmentRoot);
        
        
        //load CreateUser FXML File in CreateUser Scene
        FXMLLoader createUserLoader = new FXMLLoader(HelloApplication.class.getResource("CreateUser.fxml"));
        Parent createUserRoot = createUserLoader.load();     
        createUserScene = new Scene(createUserRoot);
        
        //load AccountsManagment FXML File in AccountsManagment Scene
        FXMLLoader accountsLoader = new FXMLLoader(HelloApplication.class.getResource("AccountsManagment.fxml"));
        Parent accountsRoot = accountsLoader.load();     
        accountsManagementScene = new Scene(accountsRoot);

        //load CreateAccount FXML File in CreateUser Scene
        FXMLLoader createAccountLoader = new FXMLLoader(HelloApplication.class.getResource("CreateAccount.fxml"));
        Parent createAccountRoot = createAccountLoader.load();
        createAccountScene = new Scene(createAccountRoot);
        
        // Set Main Scene in Admin Dasboard ( UsersManagment Scene )
        this.setScene(usersManagmentScene);
        this.setTitle("Admin Dashboard");
        this.show();
        
    }
    public void changeSceneToCreateUser(){
        this.setScene(createUserScene);
    }
    public void changeSceneToCreateAccount() {
        this.setScene(createAccountScene);
    }
    public void changeSceneToUsersManagment(){
        this.setScene(usersManagmentScene);
    }
    public void changeSceneToAccountsManagment(){
        this.setScene(accountsManagementScene);
    }
}
