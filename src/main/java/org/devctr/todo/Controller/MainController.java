package org.devctr.todo.Controller;

import javafx.fxml.FXML;
import org.devctr.todo.Model.DBConnection.TaskDAO;

public class MainController {
    protected static TaskDAO taskDAO = new TaskDAO();

    @FXML
    protected void onHelloButtonClick() {
    }
}