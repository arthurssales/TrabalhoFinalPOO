package org.example.cursojavafx.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Consulta;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.service.LoginUsuarioService;

import java.io.IOException;
import java.time.LocalDate;

public class HistoricoConsultasMedicoController {

    @FXML private TableView<Consulta> tabelaConsultas;

    @FXML private Button botaoVolta;
    @FXML private Button botaoConfirma;

    @FXML private TableColumn<Consulta,String> colunaNome;
    @FXML private TableColumn<Consulta, String> colunaPlano;
    @FXML private TableColumn<Consulta,LocalDate> colunaData;

    private final ObservableList<Consulta> consultas = FXCollections.observableArrayList();
    private Consulta consultaSelecionada;

    public void initialize(){
        tabelaConsultas.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {consultaSelecionada = novo;});

        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getNome() + " " + cellData.getValue().getPaciente().getSobrenome()));
        colunaPlano.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getPlanoSaude()));
        colunaData.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getData()));
        tabelaConsultas.setItems(consultas);

        Medico medico = LoginUsuarioService.getMedicoLogado();
        consultas.addAll(medico.getConsultasRealizadas());
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        Medico medico = LoginUsuarioService.getMedicoLogado();
        if(LoginUsuarioService.getMedicoLogado() != null){
            CarregarTelasController.carregarMenuMedico(event);
        }

        else
            CarregarTelasController.carregarRealizarConsulta(event);
    }

    @FXML
    private void confirmar(ActionEvent event)throws IOException{

        if(consultaSelecionada != null){
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Prontuario.fxml"));
            Parent root = loader.load();
            ProntuarioController controller = loader.getController();
            controller.setInformacoes(consultaSelecionada.getSintomas(),
                    consultaSelecionada.getDiagnostico(),
                    consultaSelecionada.getTratamento(),
                    consultaSelecionada.getObservacoes(),
                    consultaSelecionada.getReceita(),
                    consultaSelecionada.getExamesSolicitados(),

                    consultaSelecionada.getMedico().getNome(),
                    consultaSelecionada.getMedico().getClass().getSimpleName(),
                    consultaSelecionada.getPaciente().getNome(),
                    (String.valueOf(consultaSelecionada.getData())));

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        }
    }
}
