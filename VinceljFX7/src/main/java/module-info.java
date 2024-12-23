module hr.javafx.vinceljfx7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.javafx.vinceljfx7 to javafx.fxml;
    exports hr.javafx.vinceljfx7.controller;
    opens hr.javafx.vinceljfx7.controller to javafx.fxml;
    exports hr.javafx.vinceljfx7.main;
    opens hr.javafx.vinceljfx7.main to javafx.fxml;
}