package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.service.CadastroUsuarioService;

import java.io.IOException;

public class InicioController {

    @FXML
    private Button botaoPaciente;

    @FXML
    private Button botaoMedico;

    @FXML
    private void fazerLoginPaciente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaNovaLoginPaciente.fxml"));

        Scene scene = new Scene(loader.load(),800,600);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        System.out.println("Botão apertado paciente");
    }

    @FXML
    private void fazerLoginMedico(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaLoginMedico.fxml"));

        Scene scene = new Scene(loader.load(),800,600);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        System.out.println("Botão apertado médico");
    }

    public InicioController(){
        CadastroUsuarioService.cadastroInicial();
    }
}