module org.devctr.todo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.devctr.todo to javafx.fxml;
    exports org.devctr.todo;
}