package org.example.cursojavafx.model;

public class Paciente extends UsuarioCadastrado {
    private int cpf;
    private boolean primeiroAcesso = true;
    private double custoTotal;

    public Paciente(String nome, String sobrenome, String email, String senha, String sexo,int idade) {
        super(nome,sobrenome, email, senha, sexo,idade);
        //this.cpf = cpf;
        custoTotal = 0;
    }
    PlanoA planoA;
    PlanoB planoB;

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

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public void setCustoTotal(double custoConsulta){
        custoTotal += custoConsulta;
    }

    public double getCustoTotal(){
        return custoTotal;
    }

    public void setPlanoB(PlanoB plano){
        planoB = plano;
    }

    public void setPlanoA(PlanoA plano){
        planoA = plano;
    }
}