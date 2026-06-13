package org.example.cursojavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.cursojavafx.HelloApplication;
import org.example.cursojavafx.model.Paciente;
import org.example.cursojavafx.service.CadastroUsuarioService;

import java.io.IOException;

public class CadastroPacienteController implements Comandos{

    @FXML private RadioButton botaoMasculino;
    @FXML private RadioButton botaoFeminino;

    @FXML private Button botaoVoltar;
    @FXML private Button botaoConfirmar;

    @FXML private TextField nomeCadastro;
    @FXML private TextField sobrenomeCadastro;
    @FXML private TextField emailCadastro;
    @FXML private TextField senhaCadastro;
    @FXML private TextField confirmarSenhaCadastro;
    @FXML private TextField cpfCadastro;
    @FXML private DatePicker dataDeNascimento;

    private boolean camposVazios = true;

    @FXML
    public void selecionarSexo(ActionEvent event){
        if(botaoMasculino.isSelected())
            sexo = "Masculino";

        else if(botaoFeminino.isSelected())
            sexo = "Feminino";
        else
            sexo = null;
    }

    /**TRATAR A EXCESSÃO DO CPF E IDADE
     * - Não podem ser menores que 0
     * - CPF deve ter o mínimo de 11 caracteres
     * - Idade deve ser menor que 120**/
    private String sexo;
    private int idade;

    @FXML
    public void confirmar(){
        /*try{
            int cpf = Integer.parseInt(cpfCadastro.getText());
        }catch (NumberFormatException e){
            System.out.println();
        }

        try{
            idade = Integer.parseInt(idadeCadastro.getText());
        }catch (NumberFormatException ex){
        }*/

        verificarCampos();
        if(camposVazios){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Campo vazio!");
            alerta.setContentText("Preencha todos os campos para confirmar o cadastro!");
            alerta.showAndWait();
        }

        else if(!CadastroUsuarioService.autenticarEmail(emailCadastro.getText())) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Esse email já foi registrado!");
            alerta.showAndWait();
        }

        else if(!CadastroUsuarioService.compararSenhas(senhaCadastro.getText(),confirmarSenhaCadastro.getText())){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Senhas incompatíveis!");
            alerta.setContentText("As senhas devem ser iguais!");
            alerta.showAndWait();
        }

        else{
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Usuário cadastrado!");
            alerta.showAndWait();

            Paciente pacienteCadastrado = new Paciente(nomeCadastro.getText(),sobrenomeCadastro.getText(),emailCadastro.getText(),senhaCadastro.getText(),sexo,idade);
            CadastroUsuarioService.cadastrar(pacienteCadastrado);

            System.out.println("Nome: " + pacienteCadastrado.getNome());
            System.out.println("email: " + pacienteCadastrado.getEmail());
            System.out.println("senha: " + pacienteCadastrado.getSenha());
            System.out.println("idade: " + pacienteCadastrado.getIdade());
            System.out.println("sexo: " + pacienteCadastrado.getSexo());

            nomeCadastro.setText(null);
            sobrenomeCadastro.setText(null);
            emailCadastro.setText(null);
            senhaCadastro.setText(null);
            confirmarSenhaCadastro.setText(null);
            //idadeCadastro.setText(null);
            cpfCadastro.setText(null);
            sexo = null;
        }
    }

    @FXML
    public void voltar(ActionEvent event)throws IOException{
        FXMLLoader loader;
        loader = new FXMLLoader(HelloApplication.class.getResource("TelaNovaLoginPaciente.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    private void verificarCampos(){
        String nome = nomeCadastro.getText();
        String sobrenome = sobrenomeCadastro.getText();
        String email = emailCadastro.getText();
        String senha = senhaCadastro.getText();
        String confirmarSenha = senhaCadastro.getText();

        camposVazios = nome == null ||
                sobrenome == null ||
                email == null ||
                senha == null ||
                confirmarSenha == null ||
                sexo == null;
        }
}
