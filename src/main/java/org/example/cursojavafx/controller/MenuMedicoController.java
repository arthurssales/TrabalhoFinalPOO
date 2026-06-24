package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.exception.ConsultaInvalidaException;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.service.LoginUsuarioService;

import java.io.IOException;

public class MenuMedicoController {

        @FXML private Label nomeMedico;
        @FXML private Label labelQtdConsultas;
        @FXML private Label labelAvaliacaoMedia;

        @FXML private Button botaoRealizaConsulta;
        @FXML private Button botaoConsultas;
        @FXML private Button botaoConsultasRealizadas;
        @FXML private Button botaoAvaliacao;
        @FXML private Button botaoSair;

        @FXML
        public void initialize() {
            Medico medico = LoginUsuarioService.getMedicoLogado();
            if (medico != null) {
                nomeMedico.setText(medico.getSobrenome());

                // Quantidade de consultas realizadas
                int qtdConsultas = medico.getConsultasRealizadas().size(); // ex: medico.getConsultasRealizadas().size()
                labelQtdConsultas.setText(qtdConsultas + " consulta(s) realizada(s)");

                // Avaliação média
                //calcular média real quando tiver os dados
                double media = 0.0; // ex: medico.getAvaliacaoMedia()
                if (media > 0) {
                    labelAvaliacaoMedia.setText(String.format("Média: %.1f ⭐", media));
                } else {
                    labelAvaliacaoMedia.setText("Média: sem avaliações");
                }
            }
        }

        @FXML
        private void verHistorico(ActionEvent event)throws IOException, ConsultaInvalidaException{
            Medico medico = LoginUsuarioService.getMedicoLogado();

            if(medico.getConsultasRealizadas().isEmpty())
                throw new ConsultaInvalidaException("Nnehum paciente foi atendido.");

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HistoricoConsultasMedico.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }

        @FXML
        private void realizarConsulta(ActionEvent event)throws IOException, ConsultaInvalidaException{
            Medico medico = LoginUsuarioService.getMedicoLogado();

            if(medico.getConsultasAgendadas().isEmpty())
                throw new ConsultaInvalidaException("Nenhuma consulta agendada");

            CarregarTelasController.carregarRealizarConsulta(event);
        }

        @FXML
        private void verConsultasAgendadas(ActionEvent event) throws IOException, ConsultaInvalidaException {
            Medico medico = LoginUsuarioService.getMedicoLogado();

            if(medico.getConsultasAgendadas().isEmpty()){
                throw new ConsultaInvalidaException("Nenhuma consulta foi agendada");
            }

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("ConsultasAgendadasMedico.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            System.out.println("Ver consultas agendadas");
        }

        @FXML
        private void sair(ActionEvent event) throws IOException {
            LoginUsuarioService.setMedicoLogado(null);
            System.out.println("Médico deslogado!");

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }
    }