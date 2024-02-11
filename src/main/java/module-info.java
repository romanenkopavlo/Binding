module org.example.rectangle {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.rectangle to javafx.fxml;
    exports org.example.rectangle;
}