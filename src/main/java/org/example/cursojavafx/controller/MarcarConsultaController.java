package org.example.cursojavafx.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.service.CadastroUsuarioService;
import org.example.cursojavafx.service.LoginUsuarioService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MarcarConsultaController implements Initializable {

    @FXML private TableView<Medico> tabelaMedicos;

    @FXML private TableColumn<Medico, String> colunaNome;
    @FXML private TableColumn<Medico, String> colunaEspecialidade;
    @FXML private TableColumn<Medico, Double> colunaMedia;
    @FXML private TableColumn<Medico, Integer> colunaAvaliacoesRecentes;

    private final ObservableList<Medico> medicos = FXCollections.observableArrayList();
    private Medico medicoSelecionado;

    @FXML
    private void confirmar(ActionEvent event) throws IOException{
        if(medicoSelecionado != null){
            ConfirmarConsultaController.setMedicoSelecionado(medicoSelecionado);

            System.out.println(medicoSelecionado.getNome());
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ConfirmarConsulta.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        CarregarTelasController.carregarMenuPaciente(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tabelaMedicos.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {medicoSelecionado = novo;});

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEspecialidade.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        //colunaMedia.setCellValueFactory(new PropertyValueFactory<>("media"));
        /*colunaAvaliacoesRecentes.setCellValueFactory(new PropertyValueFactory<>("avaliacoesRecentes"));
        */
        tabelaMedicos.setItems(medicos);

        if(LoginUsuarioService.getPacienteLogado().getPlanoSaude().equals("Plano A"))
            medicos.addAll(CadastroUsuarioService.getMedicosPlano());

        else
            medicos.addAll(CadastroUsuarioService.getMedicosCadastrados());

    }


}
