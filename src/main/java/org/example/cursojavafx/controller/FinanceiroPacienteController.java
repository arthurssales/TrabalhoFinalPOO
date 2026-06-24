package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.example.cursojavafx.model.Paciente;
import org.example.cursojavafx.service.LoginUsuarioService;

import java.io.IOException;

public class FinanceiroPacienteController {

    @FXML private Label custoPlano;
    @FXML private Label custoConsultas;
    @FXML private Label custoTotal;
    @FXML private Label labelValorConsulta;
    @FXML private Label labelAvisoPlano;
    @FXML private ComboBox<String> comboTipoMedico;
    @FXML private Button botaoVoltar;

    @FXML
    public void initialize() {

        Paciente paciente = LoginUsuarioService.getPacienteLogado();

        if (paciente == null) {
            return;
        }

        custoTotal.setText(String.format("R$ %.2f", paciente.getCustoTotal()));

        if ("Plano A".equals(paciente.getPlanoSaude())) {

            custoPlano.setText("Plano A");
            custoConsultas.setText("Valor do plano: 800 R$/mês\nValor por consulta: 0 R$");

        } else {

            custoPlano.setText("Sem plano");
            custoConsultas.setText("Valor integral por consulta");
        }

        comboTipoMedico.getItems().addAll(
                "Cardiologista",
                "Dermatologista",
                "Pediatra"
        );
    }

    @FXML
    private void calcularValor(ActionEvent event) {

        String selecionado = comboTipoMedico.getValue();

        if (selecionado == null) {
            return;
        }

        double valorSemPlano;

        switch (selecionado) {
            case "Cardiologista":
                valorSemPlano = 500.0;
                break;

            case "Dermatologista":
                valorSemPlano = 200.0;
                break;

            case "Pediatra":
                valorSemPlano = 250.0;
                break;

            default:
                valorSemPlano = 0.0;
        }

        Paciente paciente = LoginUsuarioService.getPacienteLogado();

        if ("Plano A".equals(paciente.getPlanoSaude())) {

            labelValorConsulta.setText("Valor da consulta: R$ 0,00");
            labelAvisoPlano.setText("Coberto pelo plano.");

        } else {

            labelValorConsulta.setText(
                    String.format("Valor da consulta: R$ %.2f", valorSemPlano)
            );

            labelAvisoPlano.setText(
                    "Você pagará o valor integral da consulta."
            );
        }
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        CarregarTelasController.carregarMenuPaciente(event);
    }
}