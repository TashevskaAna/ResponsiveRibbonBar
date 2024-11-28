module com.example.ribbonbar {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.ribbonbar to javafx.fxml;
    exports com.example.ribbonbar;
}