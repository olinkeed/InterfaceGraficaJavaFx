module com.padaria.estoque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.padaria.estoque.application to javafx.fxml;
    opens com.padaria.estoque.controller to javafx.fxml;
    exports com.padaria.estoque.application;
}
