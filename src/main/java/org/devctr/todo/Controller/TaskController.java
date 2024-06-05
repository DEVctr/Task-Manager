package org.devctr.todo.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.devctr.todo.Model.DBModel.Task;

import java.io.IOException;
import java.util.List;

public class TaskController extends MainController {
    @FXML
    private VBox todoVbox;
    @FXML
    private Button add_button;

    public void initialize() {
        this.setTodoVbox();
        add_button.setOnAction(event -> {
            try {
                AddTask.newTaskPopUp();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setTodoVbox() {
        List<Task> tasks = taskDAO.selectAll();

        for (Task task : tasks) {
            Label label_name = new Label(task.getTask_name());
            label_name.getStyleClass().add("task-name");

            TextArea textArea_description = new TextArea(task.getDescription());
            textArea_description.setWrapText(true);
            textArea_description.setEditable(false);
            textArea_description.getStyleClass().add("task-description");

            Label label_priotity = new Label(task.getPriority());
            label_priotity.getStyleClass().add("task-priority");

            Label label_status = new Label(task.getStatus());
            label_status.getStyleClass().add("task-status");

            Label label_deadline = new Label();

            if (task.getDeadline() != null) {
                label_deadline.setText(task.getDeadline().toLocalDateTime().getMonth() +
                        " " + task.getDeadline().toLocalDateTime().getDayOfMonth());
                label_deadline.getStyleClass().add("task-deadline");
            }

            VBox vbox = new VBox();
            vbox.getStyleClass().add("todo-vbox");
            vbox.getChildren().addAll(label_status, label_name, textArea_description, label_deadline);

           todoVbox.getChildren().add(vbox);
        }
    }
}
