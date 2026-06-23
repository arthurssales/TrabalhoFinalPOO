package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Consulta;

import java.awt.font.TextLayout;
import java.io.IOException;

public class ProntuarioController {

    private Consulta consulta;
    @FXML private Button botaoVolta;
    @FXML private Label dataConsulta;
    @FXML private Label nomeMedico;
    @FXML private Label nomePaciente;
    @FXML private Label especialidade;

    @FXML public Label sintomas;
    @FXML public Label diagnostico;
    @FXML public Label tratamento;

    @FXML public Label medicamentos;
    @FXML public Label observacoes;
    @FXML public Label exames;


    @FXML
    public void voltar(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HistoricoConsultasPaciente.fxml"));
        Scene scene  = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


    public void setInformacoes(String sintomas,String diagnostico,String tratamento, String observacoes,
                               String medicamentos, String exames,String nomeMedico,
                               String especialidade,String nomePaciente, String data){

        this.sintomas.setText(sintomas);
        this.diagnostico.setText(diagnostico);
        this.tratamento.setText(tratamento);
        this.observacoes.setText(observacoes);
        this.medicamentos.setText(medicamentos);
        this.exames.setText(exames);

        this.nomeMedico.setText(nomeMedico);
        this.especialidade.setText(especialidade);
        this.nomePaciente.setText(nomePaciente);
        this.dataConsulta.setText(data);
    }



}
