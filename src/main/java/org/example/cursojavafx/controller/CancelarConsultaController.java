package org.example.cursojavafx.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.cursojavafx.model.Consulta;
import org.example.cursojavafx.service.ConsultaService;
import org.example.cursojavafx.service.LoginUsuarioService;

import java.io.IOException;
import java.util.Optional;

public class CancelarConsultaController {


    @FXML private Button botaoVolta;
    @FXML private Button botaoConfirma;

    @FXML private TableView<Consulta> tabelaConsultas;
    @FXML private TableColumn<Consulta, String> colunaNome;
    @FXML private TableColumn<Consulta, String> colunaEspecialidade;
    @FXML private TableColumn<Consulta, Double> colunaMedia;

    private Consulta consultaSelecionada;
    private ObservableList<Consulta> consultas = FXCollections.observableArrayList();


    public void initialize() {
        tabelaConsultas.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {consultaSelecionada = novo;});

        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedico().getNome()));
        colunaEspecialidade.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMedico().getClass().getSimpleName()));
        //colunaMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
        /*colunaAvaliacoesRecentes.setCellValueFactory(new PropertyValueFactory<>("avaliacoesRecentes"));
         */
        tabelaConsultas.setItems(consultas);

        consultas.clear();
        consultas.addAll(LoginUsuarioService.getPacienteLogado().getConsultasAgendadas());
    }

    @FXML
    public void confirmar(ActionEvent event)throws IOException{
        if(consultaSelecionada != null){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Cancelar consulta");
            alerta.setHeaderText("Deseja realmente cancelar a consulta?");
            alerta.setContentText("Clique em confirmar para cancelar.");
            alerta.setContentText("Médico: " + consultaSelecionada.getMedico().getNome());

            Optional<ButtonType> resultado = alerta.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                ConsultaService.cancelarConsulta(consultaSelecionada);
                consultas.remove(consultaSelecionada);
            }
        }
    }

    @FXML
    public void voltar(ActionEvent event)throws IOException {
        CarregarTelasController.carregarMenuPaciente(event);
    }
}
