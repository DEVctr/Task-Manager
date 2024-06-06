package org.devctr.todo.Controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.devctr.todo.Model.DBModel.Task;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.*;

import static org.devctr.todo.View.MainApplication.addPopUpScene;

public class AddTask extends MainController{
    public static Stage stage = new Stage();
    private static TaskController taskController;
    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private static String status;
    private String priority = "";
    @FXML
    private Button closeButton;
    @FXML
    private TextField taskName;
    @FXML
    private TextArea taskDescription;
    @FXML
    private DatePicker datePickerDeadline;
    @FXML
    private Button buttonUrgentPriority;
    @FXML
    private Button buttonNonUrgentPriority;

    public void initialize() throws IOException {
        stage.setTitle("Add Task");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);

        closeButton.setOnAction(actionEvent -> stage.close());

        buttonUrgentPriority.setOnAction(actionEvent -> priority = buttonUrgentPriority.getText());
        buttonNonUrgentPriority.setOnAction(actionEvent -> priority = buttonNonUrgentPriority.getText());
    }

    public static void newTaskPopUp(TaskController controller, String s) throws IOException {
        taskController = controller;;
        status = s;

        stage.getIcons().add(new Image(String.valueOf(AddTask.class.getResource("/org/devctr/todo/images/button-add.png"))));

        addPopUpScene.getStylesheets().add(AddTask.class.getResource("/org/devctr/todo/css/add-task.css").toExternalForm());
        addPopUpScene.setFill(Color.TRANSPARENT);

        moveStage(stage, addPopUpScene);
        stage.setScene(addPopUpScene);
        stage.show();
    }

    public void addTodoButton() {
        Task task = new Task(0, taskName.getText(), taskDescription.getText(),
                convertLocalDate(), timestamp, priority, status);

        if (!taskName.getText().isBlank()){
            taskDAO.create(task);
            taskController.addTaskToVBox(task, status);
            stage.close();
        } else {
            taskName.setBorder(Border.stroke(Paint.valueOf("red")));
        }
    }

    private Timestamp convertLocalDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (datePickerDeadline.getValue() != null) {
            LocalDateTime deadline = datePickerDeadline.getValue().atStartOfDay();
            ZonedDateTime zonedDateTime = deadline.atZone(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            timestamp = Timestamp.from(instant);
        }

        return timestamp;
    }

    private static void moveStage(Stage stage, Scene scene) {
        var ref = new Object() {
            double xOffset = 0;
            double yOffset = 0;
        };

        scene.setOnMousePressed(event -> {
            ref.xOffset = event.getSceneX();
            ref.yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - ref.xOffset);
            stage.setY(event.getScreenY() - ref.yOffset);
        });
    }
}
