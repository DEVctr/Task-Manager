package org.devctr.todo.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    public static Stage stage;
    public static Scene boardScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("To Do");

        Parent fxmlBoard = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/devctr/todo/fxml/board.fxml")));
        boardScene = new Scene(fxmlBoard);

        primaryStage.setScene(boardScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}