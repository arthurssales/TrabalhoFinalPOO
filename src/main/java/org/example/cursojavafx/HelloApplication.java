package org.example.cursojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 600);
        //stage.setTitle("Primeira Janela!");
        stage.setScene(scene);
        stage.show();

    }
}
