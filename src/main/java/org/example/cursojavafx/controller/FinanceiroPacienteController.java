package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Paciente;
import org.example.cursojavafx.service.UsuarioLogado;

import java.io.IOException;

public class FinanceiroPacienteController {

    @FXML private Label labelPlano;
    @FXML private Label labelValorPlano;
    @FXML private Label labelCustoTotal;
    @FXML private Label labelValorConsulta;
    @FXML private Label labelAvisoPlano;
    @FXML private ComboBox<String> comboTipoMedico;
    @FXML private Button botaoVoltar;

    private Paciente paciente;

    /*@FXML
    public void initialize() {
        paciente = UsuarioLogado.getPacienteLogado();
        if (paciente == null) return;

        // Custo total
        labelCustoTotal.setText(String.format("R$ %.2f", paciente.getCustoTotal()));

        // Plano ativo
        if (paciente.getPlanoA() != null) {
            labelPlano.setText("Plano A");
            labelValorPlano.setText(String.format("R$ %.2f por consulta", paciente.getPlanoA().getValorPorConsulta()));
        } else if (paciente.getPlanoB() != null) {
            labelPlano.setText("Plano B");
            labelValorPlano.setText(String.format("R$ %.2f por consulta", paciente.getPlanoB().getValorPorConsulta()));
        } else {
            labelPlano.setText("Sem plano");
            labelValorPlano.setText("Valor varia por tipo de médico");
        }

        // Preenche o ComboBox com os tipos de médico
        comboTipoMedico.getItems().addAll(
                "Cardiologista",
                "Dermatologista",
                "Pediatra"
        );
    }*/

    @FXML
    private void calcularValor(ActionEvent event) {

        String selecionado = comboTipoMedico.getValue();
        if (selecionado == null) return;

        double valorSemPlano;
        switch (selecionado) {
            case "Cardiologista" -> valorSemPlano = 500.0;
            case "Dermatologista" -> valorSemPlano = 200.0;
            case "Pediatra" -> valorSemPlano = 250.0;
            default -> valorSemPlano = 0.0;
        }

        /*LEMBRANDO QUE O PLANO RETIRA O VALOR DA CONSULTA POR COMPLETO*/
        /*if (paciente.getPlanoSaude() != null) {
            labelValorConsulta.setText(String.format("Valor com Plano A: R$ %.2f", paciente.getPlanoSaude().getValorPorConsulta()));
            labelAvisoPlano.setText("");
        }
        } else {
            labelValorConsulta.setText(String.format("Valor sem plano: R$ %.2f", valorSemPlano));
            labelAvisoPlano.setText("Considere assinar um plano para pagar menos!");
        }*/
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMenuPaciente.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);

        MenuPacienteController controller = loader.getController();
        if (paciente != null) {
            controller.setNomePaciente(paciente.getNome());
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
