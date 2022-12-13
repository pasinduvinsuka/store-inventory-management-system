module com.example.javacw {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires controlsfx;


    opens com.example.javacw to javafx.fxml;
    exports com.example.javacw;
}