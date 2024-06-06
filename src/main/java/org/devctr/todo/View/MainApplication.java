package org.devctr.todo.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

public class MainApplication extends Application {
    public static Stage stage;
    public static Scene boardScene;
    public static Scene addPopUpScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("Task Manager");

        Parent fxmlBoard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/devctr/todo/fxml/board.fxml")));
        boardScene = new Scene(fxmlBoard);

        Parent fxmlAddTaskPopUp = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/devctr/todo/fxml/add-task.fxml")));
        addPopUpScene = new Scene(fxmlAddTaskPopUp);

        primaryStage.setScene(boardScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        launch();
    }
}