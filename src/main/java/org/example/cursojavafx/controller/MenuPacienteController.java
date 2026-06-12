package org.example.cursojavafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.service.AutenticacaoCadastroUsuario;

import java.io.IOException;

public class PacienteController {

    private AutenticacaoCadastroUsuario pacienteAutentica = new AutenticacaoCadastroUsuario();

    @FXML private Label boasVindas;
    @FXML private Label nomePaciente;

    @FXML private Button botaoExames;
    @FXML private Button botaoConsulta;
    @FXML private Button botaoHistorico;
    @FXML private Button botaoFinanceiro;
    @FXML private Button botaoCancelaConsulta;

    @FXML private Button botaoSair;
    @FXML private Button botaoDados;
    @FXML private Button botaoAlteraSenha;

    public void setNomePaciente(String nome){
        nomePaciente.setText(nome);
    }

    public void setBoasVindas(String mensagem){
        boasVindas.setText(mensagem);
    }


    @FXML
    private void verDados(){

    }

    @FXML
    private void alterarSenha(){

    }

    @FXML
    private void mostrarCustos(){

    }

    @FXML
    private void verExames(){

    }

    @FXML
    private void verHistorico() {

    }

    @FXML
    private void cancelarConsulta(){

    }

    @FXML
    private void marcarConsulta() {

    }

    public void sair(javafx.event.ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));

        Scene scene = new Scene(loader.load(),800,600);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);

        pacienteAutentica.setPacienteLogado(null);
        System.out.println("Paciente deslogado!");
    }
}
