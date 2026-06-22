package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.service.UsuarioLogado;

import java.io.IOException;

public class CarregarTelasController {



    public static void carregarTelas(){

    }



    public static void carregarMenuPaciente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMenuPaciente.fxml"));
        Parent root = loader.load();
        MenuPacienteController controller = loader.getController();
        controller.setNomePaciente(UsuarioLogado.getPacienteLogado().getNome());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
