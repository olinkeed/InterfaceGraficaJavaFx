package com.padaria.estoque.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/menu-view.fxml"));

        primaryStage.setTitle("Gerenciador de Estoque - Padaria");
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        try {
            Image icon = new Image(getClass().getResourceAsStream("/images/logo_padaria.png"));
            primaryStage.getIcons().add(icon);
        } catch (Exception ignored) {}

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
