package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;

import java.io.IOException;

public class Inicio {

    @FXML
    Button botaoPaciente;

    @FXML
    Button botaoMedico;

    @FXML
    Button botaoCadastro;

    @FXML
    public void abrirTelaLoginPaciente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaLoginPaciente.fxml"));

        Scene scene = new Scene(loader.load(),800,600);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        System.out.println("Botão apertado paciente");
    }

    @FXML
    public void abrirTelaCadastro(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaCadastro.fxml"));

        Scene scene = new Scene(loader.load(),800,600);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);

        System.out.println("Botão apertado cadastro");
    }


    @FXML
    public void abrirTelaLoginMedico(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaLoginMedico.fxml"));

        Scene scene = new Scene(loader.load(),800,600);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        System.out.println("Botão apertado médico");
    }
}