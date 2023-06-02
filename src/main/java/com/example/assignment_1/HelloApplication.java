package com.example.assignment_1;

import com.example.assignment_1.View.AdminDashboard;
import com.example.assignment_1.View.RegisterPage;
import com.example.assignment_1.View.ViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewManager.openRegisterPage();
    }

    public static void main(String[] args) {
        launch();
    }
}