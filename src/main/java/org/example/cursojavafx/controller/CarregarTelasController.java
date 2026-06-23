package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.service.UsuarioLogado;

import java.io.IOException;

public class CarregarTelasController {



    public static void carregarTelas(){

    }


    public static void carregarMenuMedico(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("MenuMedico.fxml"));
        Scene scene = new Scene(loader.load(),800,600);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


    public static void carregarRealizarConsulta(ActionEvent event)throws IOException{
        Medico medico = UsuarioLogado.getMedicoLogado();

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("RealizarConsulta.fxml"));
        Parent root = loader.load();
        RealizarConsultaController controller = loader.getController();
        controller.setNomePaciente(medico.getConsultasAgendadas().getFirst().getPaciente().getNome());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void carregarMenuPaciente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMenuPaciente.fxml"));
        Parent root = loader.load();
        MenuPacienteController controller = loader.getController();
        controller.setNomePaciente(UsuarioLogado.getPacienteLogado().getNome());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
