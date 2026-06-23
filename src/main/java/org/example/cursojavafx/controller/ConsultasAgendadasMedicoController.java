package org.example.cursojavafx.controller;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Consulta;
import org.example.cursojavafx.service.UsuarioLogado;

import java.io.IOException;
import java.time.LocalDate;

public class ConsultasAgendadasMedicoController {

    @FXML private TableView<Consulta> tabelaConsultas;
    @FXML private TableColumn<Consulta, String> colunaNome;
    @FXML private TableColumn<Consulta, String> colunaPlano;
    @FXML private TableColumn<Consulta, Integer> colunaIdade;
    @FXML private TableColumn<Consulta, LocalDate> colunaData;

    private Consulta consultaSelecionada;
    private final ObservableList<Consulta> consultas = FXCollections.observableArrayList();


    public void initialize() {

        tabelaConsultas.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {consultaSelecionada = novo;});

        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getNome() + " " + cellData.getValue().getPaciente().getSobrenome()));
        colunaPlano.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getPlanoSaude()));
        colunaIdade.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPaciente().getIdade()));
        colunaData.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getData()));
        tabelaConsultas.setItems(consultas);

        consultas.addAll(UsuarioLogado.getMedicoLogado().getConsultasAgendadas());
    }


    @FXML private Button botaoHistorico;

    @FXML
    private void verHistorico(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HistoricoConsultasPaciente.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private Button botaoVolta;

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        CarregarTelasController.carregarMenuMedico(event);
    }
}