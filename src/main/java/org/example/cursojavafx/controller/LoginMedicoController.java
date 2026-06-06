package org.example.cursojavafx.controller;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Medico;

public class LoginMedico {

    @FXML
    private TextField email;
    @FXML
    private TextField senha;

    private ArrayList<Medico> medicosCadastrados = new ArrayList<>();

    @FXML
    Button botaoVoltar;

    @FXML
    Button botaoConfirmar;

    @FXML
    public void confirmarLogin(/*ArrayList<Medico> medicosCadastrados*/) {
        Medico medicoLogado = null;

        for (Medico medico : medicosCadastrados) {
            if (email.getText().equals(medico.getEmail())) {
                System.out.println("Esse email não existe");
                medicoLogado = medico;
            }
        }

        if (medicoLogado != null) {
            for (Medico medico : medicosCadastrados) {
                if (senha.getText().equals(medico.getEmail())) {
                    System.out.println("Entrou");
                } else
                    System.out.println("Senha incorreta");
            }
        }
    }

    public void voltarTelaAnterior(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));

        Scene scene = new Scene(loader.load(), 800, 600);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);

        System.out.println("Voltando para a tela inicial");
    }
}

