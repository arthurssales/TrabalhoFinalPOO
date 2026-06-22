package org.example.cursojavafx.model;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends UsuarioCadastrado {
    private int cpf;
    private boolean primeiroAcesso = true;
    private double custoTotal;
    private List<Consulta> historicoConsultas = new ArrayList<>();
    private String planoSaude;

    public void adicionarConsulta(Consulta consulta){
        historicoConsultas.add(consulta);
    }

    public Paciente(String nome, String sobrenome, String email, String senha, String sexo,int idade) {
        super(nome,sobrenome, email, senha, sexo,idade);
        //this.cpf = cpf;
        custoTotal = 0;
    }

    public boolean marcarConsulta(Medico medicoSelecionado){
        return medicoSelecionado.getQntConsultas() != medicoSelecionado.getQntMaxConsulta();
    }

    public boolean isPrimeiroAcesso(){
        return primeiroAcesso;
    }

    public void setPrimeiroAcesso(boolean primeiroAcesso){
        this.primeiroAcesso = primeiroAcesso;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCustoTotal(double custoConsulta){
        custoTotal += custoConsulta;
    }

    public double getCustoTotal(){
        return custoTotal;
    }

    public String getPlanoSaude(){
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude){
        this.planoSaude = planoSaude;
    }
}