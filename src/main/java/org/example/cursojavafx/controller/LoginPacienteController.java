package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Paciente;
import org.example.cursojavafx.service.CadastroUsuarioService;
import org.example.cursojavafx.service.UsuarioLogado;

import java.io.IOException;

public class LoginPacienteController {

    @FXML private TextField email;
    @FXML private TextField senha;
    @FXML private Button botaoVolta;
    @FXML private Hyperlink linkCadastro;
    @FXML private Button botaoConfirma;

    @FXML
    private void confirmar(ActionEvent event) throws IOException{
        boolean acessoPermitido = false;

        for(Paciente paciente : CadastroUsuarioService.getPacientesCadastrados()){
            System.out.printf("Nome completo: %s %s - email: %s - senha: %s - sexo: %s\n",
                    paciente.getNome(),
                    paciente.getSobrenome(),
                    paciente.getEmail(),
                    paciente.getSenha(),
                    paciente.getSexo());

            if(paciente.getEmail().equals(email.getText()) && paciente.getSenha().equals(senha.getText())){
                acessoPermitido = true;
                UsuarioLogado.setPacienteLogado(paciente);
                break;
            }
        }

        if(acessoPermitido){
            System.out.println("Acesso permitido");

            if(UsuarioLogado.getPacienteLogado().isPrimeiroAcesso()){

                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaPlano.fxml"));
                Scene scene = new Scene(loader.load(),800,600);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            }
            else {
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaMenuPaciente.fxml"));
                Parent root = loader.load();
                MenuPacienteController controller = loader.getController();

                if (UsuarioLogado.getPacienteLogado().getSexo().equals("Masculino"))
                    controller.setBoasVindas("Seja bem vindo, " + UsuarioLogado.getPacienteLogado().getNome());

                else
                    controller.setBoasVindas("Seja bem vinda, " + UsuarioLogado.getPacienteLogado().getNome());

                //controller.setNomePaciente(UsuarioLogado.getPacienteLogado().getNome());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
            }
        }

        else{
            System.out.println("Acesso negado");
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Senha ou email inválido\nTente novamente");
            alerta.setHeaderText("Acesso negado");
            alerta.showAndWait();
        }
    }

    @FXML
    public void cadastrar(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaCadastroPaciente.fxml"));
        Scene scene = new Scene(loader.load(),800,600);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));
        Scene scene = new Scene(loader.load(),800,600);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}