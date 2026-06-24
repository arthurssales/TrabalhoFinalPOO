package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;

import java.io.IOException;

public class DadosPacienteController {

    @FXML private Label nome;
    @FXML private Label email;
    @FXML private Label senha;
    @FXML private Label idade;
    @FXML private Label plano;
    @FXML private Label cpf;

    @FXML private Button botaoVolta;
    @FXML private Button botaoAlteraDados;

    public void setNome(String nome){
        this.nome.setText(nome);
    }

    public void setSenha(String senha){
        this.senha.setText(senha);
    }

    public void setEmail(String email){
        this.email.setText(email);
    }

    public void setIdade(int idade){
           this.idade.setText(String.valueOf(idade));
    }

    public void setPlano(String nomePlano){
        this.plano.setText(nomePlano);
    }

    public void setCpf(String cpf){
        this.cpf.setText(cpf);
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
