module com.example.assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.assignment_1.Model to javafx.base;

    opens com.example.assignment_1 to javafx.fxml;
    exports com.example.assignment_1;
    exports com.example.assignment_1.Controller;
    opens com.example.assignment_1.Controller to javafx.fxml;
}