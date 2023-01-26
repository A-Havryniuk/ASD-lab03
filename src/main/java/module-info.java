module com.example.lab03_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab03_fx to javafx.fxml;
    exports com.example.lab03_fx;
}