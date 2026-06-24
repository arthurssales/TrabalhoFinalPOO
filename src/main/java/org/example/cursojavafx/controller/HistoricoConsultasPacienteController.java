package org.example.cursojavafx.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.cursojavafx.model.Consulta;
import org.example.cursojavafx.model.Paciente;
import org.example.cursojavafx.service.LoginUsuarioService;

import java.io.IOException;
import java.time.LocalDate;

public class HistoricoConsultasPacienteController {

    @FXML private TableView<Consulta> tabelaConsultas;

    @FXML private Button botaoVolta;
    @FXML private Button botaoConfirma;

    @FXML private TableColumn<Consulta, String> colunaNome;
    @FXML private TableColumn<Consulta, String> colunaEspecialidade;
    @FXML private TableColumn<Consulta, LocalDate> colunaData;

    private final ObservableList<Consulta> consultas = FXCollections.observableArrayList();
    private Consulta consultaSelecionada;

    // Paciente cujo histórico está sendo exibido nesta tela.
    // Pode ser o próprio paciente logado (quando o paciente acessa seu histórico)
    // ou um paciente específico (quando o médico está olhando o histórico de alguém).
    private Paciente pacienteExibido;

    public void initialize() {
        tabelaConsultas.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
            consultaSelecionada = novo;
        });

        colunaNome.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMedico().getNome()));

        colunaEspecialidade.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getMedico().getClass().getSimpleName()));

        colunaData.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getData()));

        tabelaConsultas.setItems(consultas);

        // Se ninguém chamou setPaciente(...) antes de abrir essa tela
        // (ex: o próprio paciente abrindo seu próprio histórico),
        // usa o paciente logado como padrão.
        if (pacienteExibido == null) {
            pacienteExibido = LoginUsuarioService.getPacienteLogado();
        }

        if (pacienteExibido != null) {
            consultas.addAll(pacienteExibido.getHistoricoConsultas());
        }
    }

    // Chamado pelo médico (via CarregarTelasController) ANTES de abrir essa tela,
    // pra informar de qual paciente mostrar o histórico.
    public void setPaciente(Paciente paciente) {
        this.pacienteExibido = paciente;
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        CarregarTelasController.carregarMenuPaciente(event);
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        if (consultaSelecionada != null) {
            CarregarTelasController.carregarProntuario(event, consultaSelecionada);
        }
    }
}
