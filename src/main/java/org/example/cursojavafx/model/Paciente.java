package org.example.cursojavafx.model;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends UsuarioCadastrado {
    private int cpf;
    private boolean primeiroAcesso = true;
    private double custoTotal;
    private List<Consulta> historicoConsultas = new ArrayList<>();
    private String planoSaude;
    private List<Consulta> consultasAgendadas = new ArrayList<>();



    public void adicionarConsulta(Consulta consulta) {
        consultasAgendadas.add(consulta);
    }
    public Paciente(String nome, String sobrenome, String email, String senha, String sexo,int idade) {
        super(nome,sobrenome, email, senha, sexo,idade);
        //this.cpf = cpf;
        custoTotal = 0;
    }

    public boolean marcarConsulta(Medico medicoSelecionado){
        return medicoSelecionado.getQntConsultas() != medicoSelecionado.getQntMaxConsulta();
    }
    public List<Consulta> getConsultasRealizadas() {
        List<Consulta> realizadas = new ArrayList<>();
        for (Consulta c : consultasAgendadas) {
            if (c.isRealizada()) {
                realizadas.add(c);
            }
        }
        return realizadas;
    }

    public List<Consulta> getConsultasAgendadas() {
        return consultasAgendadas;
    }

    public List<Consulta> getHistoricoConsultas() {
        return historicoConsultas;
    }

    public void setHistoricoConsultas(List<Consulta> historicoConsultas) {
        this.historicoConsultas = historicoConsultas;
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