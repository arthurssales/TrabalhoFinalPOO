package org.example.cursojavafx.controller;
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
import org.example.cursojavafx.model.Paciente;

import java.io.IOException;
import java.util.ArrayList;

public class CadastroPaciente {

    private ArrayList<Paciente> pacientesCadastrados = new ArrayList<>();

    public CadastroPaciente(){
        Paciente paciente1 = new Paciente("Eduardo","dudu@gmail.com","1234","Lucas","Masculino",12345);
        Paciente paciente2 = new Paciente("Luiz","luiz@hotmail.com","1234","Miguel","Masculino",1234);
        Paciente paciente3 = new Paciente("Arthur","arthursales@gmail.com","1234","Sales","Masculino",123);

        pacientesCadastrados.add(paciente1);
        pacientesCadastrados.add(paciente2);
        pacientesCadastrados.add(paciente3);
    }

    @FXML Button botaoVoltar;
    @FXML Button botaoConfirmar;

    @FXML TextField nomeCadastro;
    @FXML TextField sobrenomeCadastro;
    @FXML TextField senhaCadastro;
    @FXML TextField emailCadastro;
    @FXML TextField cpfCadastro;

    /**TRATAR A EXCESSÃO DO CPF **/
    //private int cpf = Integer.parseInt(cpfCadastro.getText());

    private String sexo;

    private int botaoMApertado = 0;
    private int botaoFApertado = 0;
    @FXML
    public void botaoMasculino(){
        botaoMApertado++;
        if(botaoMApertado%2 == 0 && botaoFApertado%2 != 0)
            sexo = "Feminino";
        else
            sexo = "Masculino";
    }

    @FXML
    public void botaoFeminino(){
        botaoFApertado++;
        if(botaoFApertado%2 == 0 && botaoMApertado%2 != 0)
            sexo = "Masculino";
        else
            sexo = "Feminino";
    }

        /*nomeCadastro.textProperty().addListener((obs, oldVal, newVal) -> verificarCampos());
        sobrenomeCadastro.textProperty().addListener((obs,oldVal,newVal) -> verificarCampos());
        emailCadastro.textProperty().addListener((obs, oldVal, newVal) -> verificarCampos());
        senhaCadastro.textProperty().addListener((obs, oldVal, newVal) -> verificarCampos());*/

    public void confirmarCadastro(){
        /*ENQUANTO O USUÁRIO NÃO CONCLUIR OS DADOS, A OPÇAO DE CONFIRMAR DEVE SUMIR*/
        boolean emailExiste = false;
        int i = 1;

        for(Paciente paciente : pacientesCadastrados){
            System.out.println("Passou " + i + " vezes");
            if(emailCadastro.getText().equals(paciente.getEmail())){
                emailExiste = true;
                break;
            }
        }

        if(emailExiste){
            System.out.println("Email existe!");
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Email existente!");
            alerta.setContentText("ERRO! Esse email já foi cadastrado!");
            alerta.showAndWait();
        }
        else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Cadastro realizado com sucesso!");
            alerta.setHeaderText("Usuário cadastrado!");
            alerta.showAndWait();

            System.out.println("Paciente cadastrado com sucesso!");
            Paciente pacienteCadastrado = new Paciente(nomeCadastro.getText(),emailCadastro.getText(),senhaCadastro.getText(),sobrenomeCadastro.getText(),sexo,12);
            pacientesCadastrados.add(pacienteCadastrado);

            System.out.println("Nome: " + pacienteCadastrado.getNome());
            System.out.println("email: " + pacienteCadastrado.getEmail());
            System.out.println("senha: " + pacienteCadastrado.getSenha());
            System.out.println("Sexo: " + pacienteCadastrado.getSexo());


            nomeCadastro.setText(null);
            emailCadastro.setText(null);
            senhaCadastro.setText(null);
            sobrenomeCadastro.setText(null);
            cpfCadastro.setText(null);

            /*----------DEBUG------------------*/
            for(Paciente paciente : pacientesCadastrados){
                System.out.printf("Nome completo: %s %s - Email: %s - Senha: %s - cpf: %d - sexo: %s\n",
                        paciente.getNome(),
                        paciente.getSobrenome(),
                        paciente.getEmail(),
                        paciente.getSenha(),
                        paciente.getCpf(),
                        paciente.getSexo());
            }
        }
    }

    public void verificarCampos(){
        boolean camposVazios =
                nomeCadastro.getText().isEmpty()
                || emailCadastro.getText().isEmpty()
                || senhaCadastro.getText().isEmpty()
                || sobrenomeCadastro.getText().isEmpty()
                || cpfCadastro.getText().isEmpty();

        botaoConfirmar.setDisable(camposVazios);
    }

    @FXML
    public void voltarTelaAnterior(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("TelaInicial.fxml"));

        Scene scene = new Scene(loader.load(),800,600);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
    }


    public ArrayList<Paciente> getPacientesCadastrados() {
        return pacientesCadastrados;
    }

    public void setPacientesCadastrados(ArrayList<Paciente> pacientesCadastrados) {
        this.pacientesCadastrados = pacientesCadastrados;
    }
}
