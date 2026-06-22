package org.example.cursojavafx.controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.service.CadastroUsuarioService;
import org.example.cursojavafx.service.UsuarioLogado;

public class LoginMedicoController {

    @FXML private TextField email;
    @FXML private TextField senha;

    @FXML Button botaoVoltar;
    @FXML Button botaoConfirmar;

    @FXML
    public void confirmar(ActionEvent event) throws IOException{
        boolean acessoPermitido = false;

        for (Medico medico : CadastroUsuarioService.getMedicosCadastrados()) {
            if (email.getText().equals(medico.getEmail()) && senha.getText().equals(medico.getSenha())) {
                UsuarioLogado.setMedicoLogado(medico);
                acessoPermitido = true;
                break;
            }
        }

        if(acessoPermitido){
            System.out.println("Acesso permitido");

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("MenuMedico.fxml")); //criar tela de menu do medico
            Scene scene = new Scene(loader.load(),800,600);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }
        else {
            System.out.println("Acesso negado");
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Senha ou email inválido!\nTente novamente");
            alerta.setHeaderText("Acesso negado!");
            alerta.showAndWait();
        }
    }

    @FXML
    public void cadastrar(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaCadastroMedico.fxml"));
        Scene scene = new Scene(loader.load(),800,600);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));
        Scene scene = new Scene(loader.load(), 800, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        System.out.println("Voltando para a tela inicial");
    }
}

