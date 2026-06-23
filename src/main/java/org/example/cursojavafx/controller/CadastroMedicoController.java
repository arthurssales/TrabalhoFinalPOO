package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Cardiologista;
import org.example.cursojavafx.model.Medico;
import org.example.cursojavafx.model.Pediatra;
import org.example.cursojavafx.model.Dermatologista;
import org.example.cursojavafx.service.CadastroUsuarioService;

import java.io.IOException;

public class CadastroMedicoController implements Comandos {

    @FXML private RadioButton botaoMasculino;
    @FXML private RadioButton botaoFeminino;

    @FXML private MenuItem botaoCardiologista;
    @FXML private MenuItem botaoPediatra;
    @FXML private MenuItem botaoDermatologista;

    @FXML private MenuItem botaoPlanoA;
    @FXML private MenuItem botaoSemPlano;

    @FXML private Button botaoConfirma;
    @FXML private Button botaoVolta;

    @FXML TextField nomeCadastro;
    @FXML TextField sobrenomeCadastro;
    @FXML TextField emailCadastro;
    @FXML TextField senhaCadastro;
    @FXML TextField confirmarSenhaCadastro;
    @FXML TextField crmCadastro;

    @FXML ToggleGroup sexo;

    private boolean camposVazios = true;
    private String especialidade;
    private String sexoCadastro;

    @FXML
    public void escolherSexo(){
        if(botaoMasculino.isSelected())
            sexoCadastro = "Masculino";
        else if(botaoFeminino.isSelected())
            sexoCadastro = "Feminino";
        else
            sexoCadastro = null;
    }
    private String planoMedico;

    @FXML
    private void selecionarPlanoA(){
        planoMedico = "Plano A";
    }

    @FXML
    private void selecionarSemPlano(){
        planoMedico = "Sem plano";
    }

    @FXML
    private void selecionarCardiologista(){
        especialidade = "Cardiologista";
    }

    @FXML
    private void selecionarDermatologista(){
        especialidade = "Dermatologista";
    }

    @FXML
    private void selecionarPediatra(){
        especialidade = "Pediatra";
    }

    @FXML
    public void confirmar(){
        verificarCampos();
        if(camposVazios){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Campo vazio!");
            alerta.setContentText("Preencha todos os campos para confirmar o cadastro!");
            alerta.showAndWait();
        }

        else if(!CadastroUsuarioService.autenticarEmail(emailCadastro.getText())){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Esse email já foi registrado!");
            alerta.showAndWait();
        }

        else if(!CadastroUsuarioService.compararSenhas(senhaCadastro.getText(),confirmarSenhaCadastro.getText())) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Senhas incompatíveis!");
            alerta.setContentText("As senhas devem ser iguais!");
            alerta.showAndWait();
        }

        else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Usuário cadastrado!");
            alerta.showAndWait();

            switch (especialidade) {
                case "Pneumologista": {
                    Medico medicoCadastrado = new Dermatologista(nomeCadastro.getText(), sobrenomeCadastro.getText(), emailCadastro.getText(), senhaCadastro.getText(), sexoCadastro, 20,planoMedico);
                    CadastroUsuarioService.cadastrar(medicoCadastrado);
                    break;
                }
                case "Pediatra": {
                    Medico medicoCadastrado = new Pediatra(nomeCadastro.getText(), sobrenomeCadastro.getText(), emailCadastro.getText(), senhaCadastro.getText(), sexoCadastro, 20,planoMedico);
                    CadastroUsuarioService.cadastrar(medicoCadastrado);
                    break;
                }
                case "Cardiologista": {
                    Medico medicoCadastrado = new Cardiologista(nomeCadastro.getText(), sobrenomeCadastro.getText(), emailCadastro.getText(), senhaCadastro.getText(), sexoCadastro, 20,planoMedico);
                    CadastroUsuarioService.cadastrar(medicoCadastrado);
                    break;
                }
            }
            nomeCadastro.setText(null);
            sobrenomeCadastro.setText(null);
            emailCadastro.setText(null);
            planoMedico = null;
            senhaCadastro.setText(null);
            confirmarSenhaCadastro.setText(null);
            especialidade = null;
            /*botaoMasculino.fire();
            botaoFeminino.fire();*/
            sexo = null;
        }
    }

    @FXML
    public void voltar(ActionEvent event)throws IOException {
        FXMLLoader loader;
        loader = new FXMLLoader(HelloApplication.class.getResource("TelaLoginMedico.fxml"));

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
    }

    private void verificarCampos(){
        String nome = nomeCadastro.getText();
        String sobrenome = sobrenomeCadastro.getText();
        String email = emailCadastro.getText();
        String senha = senhaCadastro.getText();
        String confirmarSenha = confirmarSenhaCadastro.getText();

        camposVazios = nome == null ||
                sobrenome == null ||
                email == null ||
                senha == null ||
                confirmarSenha == null ||
                sexo == null ||
                especialidade == null ||
                planoMedico == null;
    }
}
