package org.devctr.todo.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.devctr.todo.Model.DBModel.Task;
import org.devctr.todo.Model.Utils.Enums.TaskStatus;

import java.io.IOException;
import java.util.List;

public class TaskController extends MainController {
    private final List<Task> tasks = taskDAO.selectAll();
    @FXML
    private VBox todoVBox;
    @FXML
    private VBox doingVBox;
    @FXML
    private VBox doneVBox;
    @FXML
    private Button add_button;
    @FXML
    private Button add_button_progress;
    @FXML
    private Button add_button_completed;


    public void initialize() {
        setTodoVBox();
        setDoingVBox();
        setDoneVBox();

        setAddButtons();
    }

    public void setTodoVBox() {
        todoVBox.getChildren().clear();
        for (Task task : filterTasksByStatus("todo")) {
            todoVBox.getChildren().add(createTaskVBox(task));
        }
    }

    public void setDoingVBox() {
        doingVBox.getChildren().clear();
        for (Task task : filterTasksByStatus("doing")) {
            doingVBox.getChildren().add(createTaskVBox(task));
        }
    }

    private void setDoneVBox() {
        doneVBox.getChildren().clear();
        for (Task task : filterTasksByStatus("done")) {
            doneVBox.getChildren().add(createTaskVBox(task));
        }
    }

    private VBox createTaskVBox(Task task) {
        Label label_name = new Label(task.getTask_name());
        label_name.getStyleClass().add("task-name");

        TextArea textArea_description = new TextArea(task.getDescription());
        textArea_description.setWrapText(true);
        textArea_description.setEditable(false);
        textArea_description.getStyleClass().add("task-description");

        Label label_status = new Label(task.getStatus());
        label_status.getStyleClass().add("task-status");

        Label label_deadline = new Label();

        if (task.getDeadline() != null) {
            label_deadline.setText(task.getDeadline().toLocalDateTime().getMonth() +
                    " " + task.getDeadline().toLocalDateTime().getDayOfMonth());
            label_deadline.getStyleClass().add("task-deadline");
        }

        Button delete = new Button("X");
        delete.getStyleClass().add("delete-button");
        delete.setOnAction(event -> taskDAO.delete(task.getId()));

        HBox topHBox = new HBox();
        topHBox.getStyleClass().add("task-top-hbox");
        topHBox.getChildren().add(delete);

        VBox vbox = new VBox();
        vbox.getStyleClass().add("todo-vbox");

        if (task.getDescription() == null || task.getDescription().isBlank()) {
            vbox.getChildren().addAll(topHBox, label_name, label_deadline);
        } else {
            vbox.getChildren().addAll(topHBox, label_name, textArea_description, label_deadline);
        }

        return vbox;
    }

    public void addTaskToVBox(Task task, String s) {
        switch (s) {
            case "Todo" -> todoVBox.getChildren().add(createTaskVBox(task));
            case "Doing" -> doingVBox.getChildren().add(createTaskVBox(task));
            case "Done" -> doneVBox.getChildren().add(createTaskVBox(task));
        }
    }

    private List<Task> filterTasksByStatus(String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    private void setAddButtons() {
        add_button.setOnAction(event -> {
            try {
                AddTask.newTaskPopUp(this, TaskStatus.todo.getStatus());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        add_button_progress.setOnAction(event -> {
            try {
                AddTask.newTaskPopUp(this, TaskStatus.doing.getStatus());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        add_button_completed.setOnAction(event -> {
           try {
               AddTask.newTaskPopUp(this, TaskStatus.done.getStatus());
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
        });
    }
}
