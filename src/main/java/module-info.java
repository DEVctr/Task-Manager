module org.devctr.todo {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.devctr.todo.View;
    opens org.devctr.todo.View to javafx.fxml;
    exports org.devctr.todo.Controller;
    opens org.devctr.todo.Controller to javafx.fxml;
}