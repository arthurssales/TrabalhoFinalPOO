package org.example.cursojavafx.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.service.UsuarioLogado;
import java.io.IOException;
import java.util.Optional;

public class PlanoPacienteController {
    private String planoSelecionado = null;

    @FXML Button botaoConfirma;
    @FXML Button botaoVolta;
    @FXML Button botaoPlanoA;
    @FXML Button botaoSemPlano;

    public void initialize() {
        botaoPlanoA.setOnAction(e -> aplicarEfeitos(botaoPlanoA));
        botaoSemPlano.setOnAction(e -> aplicarEfeitos(botaoSemPlano));

        botaoPlanoA.setOnAction(e -> selecionarPlano(botaoPlanoA));
        botaoSemPlano.setOnAction(e -> selecionarPlano(botaoSemPlano));
    }

    private Button botaoPlanoSelecionado;
    private void aplicarEfeitos(Button botao){
        botao.setOnMouseEntered(e -> {TranslateTransition tt = new TranslateTransition(Duration.millis(150), botao);
            tt.setByY(-5);
            tt.play();
        });
        botao.setOnMouseExited(e -> {TranslateTransition tt = new TranslateTransition(Duration.millis(150), botao);
            tt.setToY(0);
            tt.play();
        });
        botao.setOnAction(e -> {botao.setStyle("-fx-background-color: #4CAF50;" + "-fx-text-fill: white;");
        });
    }

    private void selecionarPlano(Button botao) {

        if(botao == botaoSemPlano){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmação");
            alerta.setHeaderText("Tem certeza?");
            alerta.setContentText("Você pagará o valor integral nas suas consultas e \nnão terá acesso ao dermatologista");

            Optional<ButtonType> resultado = alerta.showAndWait();

            if(resultado.isEmpty() || resultado.get() != ButtonType.OK){
                return;
            }
        }

        if (botaoPlanoSelecionado != null) {
            botaoPlanoSelecionado.setStyle("-fx-background-color: #6EC1E4;");
        }

        botaoPlanoSelecionado = botao;

        botao.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        escolherPlano(botao);
    }

    public void escolherPlano(Button botao){
        if(botao == botaoPlanoA){
            planoSelecionado = "Plano A";
        }

        else if(botao == botaoSemPlano){
            planoSelecionado = "não tenho";
        }
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException{

        if(planoSelecionado != null){
            switch (planoSelecionado){
                case "Plano A":
                    UsuarioLogado.getPacienteLogado().setPlanoSaude("Plano A");
                    UsuarioLogado.getPacienteLogado().setPrimeiroAcesso(false);
                    break;

                case "não tenho":
                    UsuarioLogado.getPacienteLogado().setPlanoSaude("não tenho");
                    UsuarioLogado.getPacienteLogado().setPrimeiroAcesso(false);
            }

            CarregarTelasController.carregarMenuPaciente(event);
        }
        else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Selecione um plano!");
            alerta.setHeaderText("Nenhum plano selecionado");
            alerta.showAndWait();
        }
    }

    @FXML
    private void voltar(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaNovaLoginPaciente.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
