package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Consulta;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.model.Paciente;
import org.example.cursojavafx.service.ConsultaService;
import org.example.cursojavafx.service.LoginUsuarioService;

import java.io.IOException;

public class CarregarTelasController {

    public static void carregarDadosPaciente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaDadosPaciente.fxml"));
        Parent root = loader.load();

        DadosPacienteController controller = loader.getController();
        Paciente paciente = LoginUsuarioService.getPacienteLogado();

        controller.setNome(paciente.getNome() + " " + paciente.getSobrenome());
        controller.setEmail(paciente.getEmail());
        controller.setSenha(paciente.getSenha());
        controller.setCpf(String.valueOf(paciente.getCpf()));
        controller.setIdade(paciente.getIdade());
        controller.setPlano(paciente.getPlanoSaude());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void carregarProntuario(ActionEvent event, Consulta consultaSelecionada)throws IOException {
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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void carregarMenuMedico(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("MenuMedico.fxml"));
        Scene scene = new Scene(loader.load(),800,600);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public static void carregarRealizarConsulta(ActionEvent event)throws IOException{
        Medico medico = LoginUsuarioService.getMedicoLogado();

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("RealizarConsulta.fxml"));
        Parent root = loader.load();
        RealizarConsultaController controller = loader.getController();
        controller.setNomePaciente(medico.getConsultasAgendadas().getFirst().getPaciente().getNome() + medico.getConsultasAgendadas().getFirst().getPaciente().getNome());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public static void carregarMenuPaciente(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMenuPaciente.fxml"));
        Parent root = loader.load();
        MenuPacienteController controller = loader.getController();
        controller.setNomePaciente(LoginUsuarioService.getPacienteLogado().getNome());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
