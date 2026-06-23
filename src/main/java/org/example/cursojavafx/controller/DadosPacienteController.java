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

    @FXML private Label nomeCompletoPaciente;
    @FXML private Label sobrenomePaciente;
    @FXML private Label emailPaciente;
    @FXML private Label senhaPaciente;
    @FXML private Label idadePaciente;
    @FXML private Label nomePlano;

    @FXML private Button botaoVolta;
    @FXML private Button botaoAlteraDados;

    public void setNome(String nome){
        nomeCompletoPaciente.setText(nome);
    }

    public void setSobrenome(String sobrenome){
        sobrenomePaciente.setText(sobrenome);
    }

    public void setSenha(String senha){
        senhaPaciente.setText(senha);
    }

    public void setEmail(String email){
        emailPaciente.setText(email);
    }

    public void setIdade(int idade){
           idadePaciente.setText(String.valueOf(idade));
    }

    public void setPlano(String nomePlano){
        this.nomePlano.setText(nomePlano);
    }

    @FXML
    private void alterarDados(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("AlterarDadosPaciente.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        CarregarTelasController.carregarMenuPaciente(event);
    }
}
