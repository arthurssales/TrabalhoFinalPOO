package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Consulta;
import org.example.cursojavafx.model.Conta;
import org.example.cursojavafx.service.ConsultaService;
import org.example.cursojavafx.service.LoginUsuarioService;
import java.io.IOException;

public class RealizarConsultaController {
    @FXML private Label lblPaciente;

    @FXML private Button botaoFinalizaConsulta;

    @FXML private Label lblData;

    private Consulta consulta;

    @FXML private TextArea txtSintomas;
    @FXML private TextArea txtDiagnostico;
    @FXML private TextArea txtTratamento;
    @FXML private TextArea txtMedicamentos;
    @FXML private TextArea txtExames;
    @FXML private TextArea txtObservacoes;
    @FXML private Button botaoHistorico;
    @FXML private Label nomePaciente;
    @FXML private Button botaoProntuario;

    @FXML
    private void verHistoricoConsultasPaciente(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HistoricoConsultasPaciente.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void setNomePaciente(String nome){
        this.nomePaciente.setText(nome);
    }


    @FXML
    private void finalizarConsulta(ActionEvent event) throws IOException {
        consulta = LoginUsuarioService.getMedicoLogado().getConsultasAgendadas().getFirst();
        if(consulta == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nenhuma consulta carregada.");
            alert.showAndWait();

            return;
        }

        String sintomas = txtSintomas.getText();
        String diagnostico = txtDiagnostico.getText();
        String tratamento = txtTratamento.getText();
        String medicamentos = txtMedicamentos.getText();
        String exames = txtExames.getText();
        String observacoes = txtObservacoes.getText();

        Conta conta = ConsultaService.realizarConsulta(consulta, sintomas, diagnostico, tratamento, medicamentos, exames, observacoes);

        if(conta.getValor() != 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Consulta realizada. Conta gerada: R$ " + conta.getValor());
            alert.showAndWait();
        }

        CarregarTelasController.carregarMenuMedico(event);
    }
}