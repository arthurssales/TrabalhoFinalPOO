package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Paciente;

import java.io.IOException;

public class LoginPaciente{
    CadastroPaciente cadastroPaciente = new CadastroPaciente();

    @FXML private TextField emailLogin;
    @FXML private TextField senhaLogin;

    @FXML private Button botaoVoltar;
    @FXML private Button botaoConfirmarLogin;

    @FXML
    private void confirmarLogin(ActionEvent event) throws IOException{
        boolean acessoPermitido = false;

        for(Paciente paciente : cadastroPaciente.getPacientesCadastrados()){
            if(paciente.getEmail().equals(emailLogin.getText()) && paciente.getSenha().equals(senhaLogin.getText())){
                acessoPermitido = true;
                break;
            }
        }

        if(acessoPermitido){
            //OS PACIENTES CADASTRADOS NÃO ESTÃO SENDO ATUALIZADOS
            System.out.println("Acesso permitido");

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaPaciente.fxml"));
            Scene scene = new Scene(loader.load(),800,600);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }

        else{
            System.out.println("Acesso negado");
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Senha ou email inválido\nTente novamente");
            alerta.setHeaderText("Acesso negado");

            alerta.showAndWait();
        }
    }

    @FXML
    private void voltarTelaAnterior(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));
        Scene scene = new Scene(loader.load(),800,600);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
    }
}