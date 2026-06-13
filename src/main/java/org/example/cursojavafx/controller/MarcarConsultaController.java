package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Cardiologista;
import org.example.cursojavafx.model.Dermatologista;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.model.Pediatra;
import org.example.cursojavafx.service.CadastroUsuarioService;
import org.example.cursojavafx.service.UsuarioLogado;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MarcarConsultaController implements Initializable {

    @FXML private ListView<String> cardiologistaListView;
    @FXML private ListView<String> dermatologistaListView;
    @FXML private ListView<String> pediatraListView;

    @FXML private ArrayList<String> listaCardiologista = new ArrayList<>();
    @FXML private ArrayList<String> listaDermatologista = new ArrayList<>();
    @FXML private ArrayList<String> listaPediatra = new ArrayList<>();


    /*VAI CLICAR EM UMA LISTVIEW*/
    @FXML
    private void confirmar(ActionEvent event){
        String medicoSelecionado = cardiologistaListView.getSelectionModel().getSelectedItem();
        System.out.println("Médico selecionado:  " + medicoSelecionado);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMenuPaciente.fxml"));

        Parent root = loader.load();
        MenuPacienteController controller = loader.getController();
        controller.setNomePaciente(UsuarioLogado.getPacienteLogado().getNome());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void pegarNomesMedicos(){
        for(Medico medico : CadastroUsuarioService.getMedicosCadastrados()){
            if(medico instanceof Cardiologista)
                listaCardiologista.add(medico.getNome());

            else if (medico instanceof Dermatologista)
                listaDermatologista.add(medico.getNome());

            else if (medico instanceof Pediatra)
                listaPediatra.add(medico.getNome());

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pegarNomesMedicos();
        cardiologistaListView.getItems().addAll(listaCardiologista);
        dermatologistaListView.getItems().addAll(listaDermatologista);
        pediatraListView.getItems().addAll(listaPediatra);
    }
}
