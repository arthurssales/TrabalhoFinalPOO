package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.service.UsuarioLogado;

import java.io.IOException;

public class DadosPacienteController {

    @FXML private Label nomePaciente;
    @FXML private Label sobrenomePaciente;
    @FXML private Label emailPaciente;
    @FXML private Label senhaPaciente;
    @FXML private Label cpfPaciente;
    @FXML private Label idadePaciente;

    @FXML private Button botaoVolta;

    public void setNome(String nome){
        nomePaciente.setText(nome);
    }

    public void setSobrenome(String sobrenome){
        sobrenomePaciente.setText(sobrenome);
    }

    public void setSenha(String senha){
        senhaPaciente.setText(senha);
    }

    public void setCpf(String cpf){
        cpfPaciente.setText(cpf);
    }

    public void setEmail(String email){
        emailPaciente.setText(email);
    }

    /*public void setIdade(int idade){
           this.idade.setText(idade);
       }*/

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        CarregarTelasController.carregarMenuPaciente(event);
    }


}
