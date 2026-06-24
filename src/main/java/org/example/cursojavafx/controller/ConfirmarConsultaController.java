package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.service.ConsultaService;
import org.example.cursojavafx.service.LoginUsuarioService;
import java.io.IOException;
import java.time.LocalDate;

public class ConfirmarConsultaController {

    @FXML Button botaoVolta;
    @FXML Button botaoConfirma;
    private static Medico medicoSelecionado;

    @FXML DatePicker dataConsulta;

    @FXML
    public void selecionarData(){
        LoginUsuarioService.getPacienteLogado().marcarConsulta(medicoSelecionado);
    }

    @FXML
    public void confirmar(){
        LocalDate data = dataConsulta.getValue();

        //Impedir o usuário de marcar uma consulta sem data
        if(data == null){
            alertarUsuario("Data inválida", "Selecione uma data para marcar a consulta!", false);
            return;
        }

        //Impedir o usuário de marcar uma consulta em data inválida
        if(data.isBefore(LocalDate.now())){
            alertarUsuario("Data inválida", "A data da consulta não pode ser no passado!", false);
            return;
        }

        //Impedir o usuário de marcar mais de uma consulta com o mesmo médico
        if(LoginUsuarioService.getPacienteLogado().haConsultaPendente(medicoSelecionado)){
            alertarUsuario("Consulta indisponível", "Você já tem uma consulta marcada com este médico.\nAguarde ela ser realizada antes de marcar outra.", false);
            return;
        }

        ConsultaService.marcarConsulta(LoginUsuarioService.getPacienteLogado(),medicoSelecionado, data);

        if(!ConsultaService.isIdadeValida()){
            alertarUsuario("Consulta indisponível","Idade inválida!",false);
        }

        else if(!ConsultaService.isPlanoValido()){
            alertarUsuario("Consulta indisponível!","Plano inválido!",false);
        }

        else if(ConsultaService.isAgendaLotada()){
            alertarUsuario("Consulta indisponível","Agenda lotada!\nVocê foi adicionado à lista de espera!",false);
        }

        else{
            alertarUsuario("Consulta agendada","Sua consulta foi agendada com sucesso!",true);

        }
    }

    public void initialize(){
        configurarDatePicker();
    }

    @FXML
    public void voltar(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMenuPaciente.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private void alertarUsuario(String titulo,String mensagem,boolean confirmacao){
        if(confirmacao){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(titulo);
            alerta.setContentText(mensagem);
            alerta.showAndWait();
        }

        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(titulo);
            alerta.setContentText(mensagem);
            alerta.showAndWait();
        }
    }

    private void configurarDatePicker() {
        dataConsulta.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date != null && date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0c0;");
                }
            }
        });
    }

    public static void setMedicoSelecionado(Medico medicoSelecionado1){
        medicoSelecionado = medicoSelecionado1;
    }
}