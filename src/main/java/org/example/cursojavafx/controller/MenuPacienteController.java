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
import org.example.cursojavafx.exception.ConsultaInvalidaException;
import org.example.cursojavafx.service.LoginUsuarioService;

import java.io.IOException;

public class MenuPacienteController {

    @FXML private Label boasVindas;
    @FXML private Label nomePaciente;

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
    private void verDados(ActionEvent event)throws IOException{
        CarregarTelasController.carregarDadosPaciente(event);
    }

    @FXML
    private void alterarSenha(){

    }

    @FXML
    private void mostrarCustos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaFinanceiroPaciente.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void verHistorico(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HistoricoConsultasPaciente.fxml"));
        Scene scene = new Scene(loader.load(),800,600);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void cancelarConsulta(ActionEvent event)throws IOException, ConsultaInvalidaException {
        if (LoginUsuarioService.getPacienteLogado().getConsultasAgendadas().isEmpty()){
            System.out.println("Consultas agendadas: " + LoginUsuarioService.getPacienteLogado().getConsultasAgendadas().size());
            throw new ConsultaInvalidaException("Nenhuma consulta agendada");
        }

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("CancelarConsulta.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void marcarConsulta(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMarcarConsulta.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void sair(javafx.event.ActionEvent event) throws IOException{
        LoginUsuarioService.setPacienteLogado(null);
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        System.out.println("Paciente deslogado!");
    }
}
