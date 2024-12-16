module com.example.pasztormarkstopper {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pasztormarkstopper to javafx.fxml;
    exports com.example.pasztormarkstopper;
}