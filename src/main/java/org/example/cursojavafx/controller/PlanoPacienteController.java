package org.example.cursojavafx.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.PlanoA;
import org.example.cursojavafx.model.PlanoB;
import org.example.cursojavafx.service.UsuarioLogado;
import java.io.IOException;

public class PlanoPacienteController {
    private String planoSelecionado = null;

    @FXML Button botaoConfirma;
    @FXML Button botaoVolta;
    @FXML Button botaoPlanoA;
    @FXML Button botaoPlanoB;
    @FXML Button botaoSemPlano;

    public void initialize() {
        botaoPlanoA.setOnAction(e -> aplicarEfeitos(botaoPlanoA));
        botaoPlanoB.setOnAction(e -> aplicarEfeitos(botaoPlanoB));
        botaoSemPlano.setOnAction(e -> aplicarEfeitos(botaoSemPlano));

        botaoPlanoA.setOnAction(e -> selecionarPlano(botaoPlanoA));
        botaoPlanoB.setOnAction(e -> selecionarPlano(botaoPlanoB));
        botaoSemPlano.setOnAction(e -> selecionarPlano(botaoSemPlano));
    }

    private Button botaoPlanoSelecionado;
    private void aplicarEfeitos(Button botao){
        botao.setOnMouseEntered(e -> {TranslateTransition tt = new TranslateTransition(Duration.millis(150), botao);
            tt.setByY(-5);
            tt.play();
        });
        botao.setOnMouseExited(e -> {TranslateTransition tt = new TranslateTransition(Duration.millis(150), botao);
            tt.setToY(0); // volta à posição original
            tt.play();
        });
        botao.setOnAction(e -> {botao.setStyle("-fx-background-color: #4CAF50;" + "-fx-text-fill: white;");
        });
    }

    private void selecionarPlano(Button botao) {
        if (botaoPlanoSelecionado != null)
            botaoPlanoSelecionado.setStyle("-fx-background-color: #6EC1E4;");

        botaoPlanoSelecionado = botao;

        botao.setStyle("-fx-background-color: #4CAF50;" + "-fx-text-fill: white;");

        escolherPlano(botao);
    }

    public void escolherPlano(Button botao){
        if(botao == botaoPlanoA){
            planoSelecionado = "Plano A";
        }
        else if(botao == botaoPlanoB){
            planoSelecionado = "Plano B";
        }
        else if(botao == botaoSemPlano){
            planoSelecionado = "Sem plano";
        }
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException{

        if(planoSelecionado != null){
            switch (planoSelecionado){
                case "Plano A":
                    PlanoA planoA = new PlanoA();
                    UsuarioLogado.getPacienteLogado().setPlanoA(planoA);
                    UsuarioLogado.getPacienteLogado().setPrimeiroAcesso(false);
                    break;

                case "Plano B":
                    PlanoB planoB = new PlanoB();
                    UsuarioLogado.getPacienteLogado().setPlanoB(planoB);
                    UsuarioLogado.getPacienteLogado().setPrimeiroAcesso(false);
                    break;

                case "Sem plano":
                    UsuarioLogado.getPacienteLogado().setPrimeiroAcesso(false);
            }

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMenuPaciente.fxml"));
            Parent root = loader.load();
            MenuPacienteController controller = loader.getController();
            controller.setNomePaciente(UsuarioLogado.getPacienteLogado().getNome());

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
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
