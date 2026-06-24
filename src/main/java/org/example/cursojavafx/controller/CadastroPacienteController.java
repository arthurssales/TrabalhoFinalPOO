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
import java.time.LocalDate;
import java.time.Period;

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
    @FXML private ToggleGroup sexoc;

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

    @FXML
    private String sexo;
    private int idade;

    @FXML
    public void confirmar(){
        verificarCampos();
        if(camposVazios){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Campo vazio!");
            alerta.setContentText("Preencha todos os campos para confirmar o cadastro!");
            alerta.showAndWait();
        }

        else if(!validarEmail(emailCadastro.getText())){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("E-mail inválido!");
            alerta.setContentText("Digite um e-mail em um formato válido (ex: nome@dominio.com).");
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

        else if(!validarCpf(cpfCadastro.getText())){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("CPF inválido!");
            alerta.setContentText("O CPF deve conter exatamente 11 números.");
            alerta.showAndWait();
        }
        else if(CadastroUsuarioService.verificarCpf(cpfCadastro.getText())){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("CPF inválido!");
            alerta.setContentText("Esse CPF já foi registrado.");
            alerta.showAndWait();
        }

        else{
            LocalDate dataNascimento = dataDeNascimento.getValue();
            if(dataNascimento == null){
                System.out.println("Selecione uma data de nascimento.");
                return;
            }
            LocalDate dataAtual = LocalDate.now();
            idade = Period.between(dataNascimento,dataAtual).getYears();
            if(idade > 120 || idade < 0){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Idade inválida! A idade deve ser entre 0 a 120 anos.");
                alerta.showAndWait();
                return;
            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Usuário cadastrado!");
            alerta.showAndWait();

            Paciente pacienteCadastrado = new Paciente(nomeCadastro.getText(),sobrenomeCadastro.getText(),emailCadastro.getText(),senhaCadastro.getText(),sexo,idade);
            String cpfLimpo = cpfCadastro.getText().replaceAll("[^0-9]", "");
            CadastroUsuarioService.cadastrar(pacienteCadastrado);
            pacienteCadastrado.setCpf(cpfLimpo);

            System.out.println("Nome: " + pacienteCadastrado.getNome());
            System.out.println("Sobrenome: " + pacienteCadastrado.getSobrenome());
            System.out.println("email: " + pacienteCadastrado.getEmail());
            System.out.println("senha: " + pacienteCadastrado.getSenha());
            System.out.println("idade: " + pacienteCadastrado.getIdade());
            System.out.println("sexo: " + pacienteCadastrado.getSexo());
            System.out.println("CPF: " + cpfLimpo);

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

    private boolean validarCpf(String cpf){
        if(cpf == null){
            return false;
        }
        String somenteNumeros = cpf.replaceAll("[^0-9]", "");
        return somenteNumeros.length() == 11;
    }

    // verifica se o email digitado tem um formato válido (jose@email.com)
    private boolean validarEmail(String email){
        if(email == null){
            return false;
        }
        return email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");
    }
}