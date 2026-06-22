package org.example.cursojavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.example.cursojavafx.model.Consulta;
import org.example.cursojavafx.model.Conta;
import org.example.cursojavafx.service.ConsultaService;

public class RealizarConsultaController {
    @FXML
    private Label lblPaciente;

    @FXML
    private Label lblData;

    private Consulta consulta;

    @FXML
    private TextArea txtSintomas;

    @FXML
    private TextArea txtDiagnostico;

    @FXML
    private TextArea txtTratamento;

    @FXML
    private TextArea txtMedicamentos;

    @FXML
    private TextArea txtExames;

    @FXML
    private TextArea txtObservacoes;

    public void setConsulta(Consulta consulta){
        this.consulta = consulta;
    }

    @FXML
    public void finalizarConsulta(){

        Conta conta =
                ConsultaService.realizarConsulta(
                        consulta,
                        txtSintomas.getText(),
                        txtDiagnostico.getText(),
                        txtTratamento.getText(),
                        txtMedicamentos.getText(),
                        txtExames.getText(),
                        txtObservacoes.getText()
                );

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if(conta == null){
            alert.setContentText("Consulta realizada com sucesso.");
        }
        else{
            alert.setContentText("Consulta realizada. Conta gerada: R$ " + conta.getValor());
        }

        alert.showAndWait();
    }
}